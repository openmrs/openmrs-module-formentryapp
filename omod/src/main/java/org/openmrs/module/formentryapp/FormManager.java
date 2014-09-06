package org.openmrs.module.formentryapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openmrs.Form;
import org.openmrs.module.Extension;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.web.FormEntryContext;
import org.openmrs.module.web.extension.FormEntryHandler;
import org.springframework.stereotype.Component;

@Component
public class FormManager {
	
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
	
	public List<String> getUILocations() {
		List<String> uiLocations = new ArrayList<String>();
		
		
		
		return uiLocations;
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
}
