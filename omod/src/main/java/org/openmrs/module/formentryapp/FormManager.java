package org.openmrs.module.formentryapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openmrs.Form;
import org.openmrs.Privilege;
import org.openmrs.api.UserService;
import org.openmrs.module.Extension;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.web.FormEntryContext;
import org.openmrs.module.web.extension.FormEntryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormManager {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FormEntryAppService appService;

	final Set<String> SUPPORTED_MODULES = new HashSet<String>(Arrays.asList("htmlformentry", "xforms"));
	
	public List<FormEntryHandler> getSupportedFormEntryHandlers() {
		List<FormEntryHandler> handlers = new ArrayList<FormEntryHandler>();
		
		List<Extension> extensions = ModuleFactory.getExtensions(FormEntryHandler.class.getName());
		for (Extension extension : extensions) {
			FormEntryHandler handler = (FormEntryHandler) extension;
			if (SUPPORTED_MODULES.contains(handler.getModuleId())) {
				handlers.add(handler);
			}
		}
		
		return handlers;
	}
	
	public List<String> getUILocations(Form form) {
		List<String> list = new ArrayList<String>();
		list.add("patientDashboard.overallActions");
		list.add("patientDashboard.visitActions");
		list.add("registrationSummary.overallActions");
		for (org.openmrs.module.appframework.domain.Extension extension : appService.getFormExtensions(form)) {
			list.remove(extension.getExtensionPointId());
        }
		
		return list;
	}
	
	public String getFormUrl(Form form, Map<String, String> options) {
		String formTechnology = getFormTechnology(form);
		if ("htmlformentry".equals(formTechnology)) {
			String displayStyle = options.get("displayStyle");
			String url = "htmlformentryui/htmlform/enterHtmlFormWith" + displayStyle
			        + "Ui.page?patientId={{patient.uuid}}&visitId={{visit.uuid}}&formUuid=";
			return url + form.getUuid();
		} else if ("xforms".equals(formTechnology)) {
			String url = "xforms/formentry/xformEntry.page?target=xformentry&formId=" + form.getFormId()
			        + "&patientId={{patient.patientId}}&visitId={{visit.id}}&refappui=true";
			return url;
		}
		
		return "http://about:blank";
	}
	
	public List<String> getRequiredPrivileges() {
		List<String> privileges = new ArrayList<String>();
		for (Privilege privilege : userService.getAllPrivileges()) {
			if (privilege.getName().startsWith("Task:") || privilege.getName().startsWith("App:")) {
				privileges.add(privilege.getName());
			}
		}
		return privileges;
	}
	
	public List<Form> getSupportedForms() {
		List<Form> forms = new ArrayList<Form>();
		
		List<FormEntryHandler> handlers = getSupportedFormEntryHandlers();
		FormEntryContext formEntryContext = new FormEntryContext(null);
		for (FormEntryHandler handler : handlers) {
			List<Form> handledForms = handler.getFormsModuleCanEnter(formEntryContext);
			forms.addAll(handledForms);
		}
		
		Collections.sort(forms, new Comparator<Form>() {
			
			@Override
			public int compare(Form o1, Form o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		return forms;
	}
	
	public String getFormTechnology(Form form) {
		List<FormEntryHandler> handlers = getSupportedFormEntryHandlers();
		FormEntryContext formEntryContext = new FormEntryContext(null);
		for (FormEntryHandler handler : handlers) {
			List<Form> forms = handler.getFormsModuleCanEnter(formEntryContext);
			if (forms.contains(form)) {
				return handler.getModuleId();
			}
		}
		
		return null;
	}
	
	public List<String> getDisplayStyles(Form form) {
		List<String> styles = new ArrayList<String>();
		String formTechnology = getFormTechnology(form);
		if ("htmlformentry".equals(formTechnology)) {
			styles.add("Standard");
			styles.add("Simple");
		}
		
		return styles;
	}
}
