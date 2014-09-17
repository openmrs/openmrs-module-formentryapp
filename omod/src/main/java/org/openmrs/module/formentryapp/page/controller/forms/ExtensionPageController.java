package org.openmrs.module.formentryapp.page.controller.forms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Form;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.module.formentryapp.FormEntryAppService;
import org.openmrs.module.formentryapp.FormManager;
import org.openmrs.module.formentryapp.page.controller.FormsPageController;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ExtensionPageController {
	
	public String get(@RequestParam("formId") Form form,
	        @RequestParam(value = "extensionId", required = false) String extensionId,
	        @SpringBean("formManager") FormManager manager, @SpringBean("formEntryAppService") FormEntryAppService service,
	        PageModel model) {
		ExtensionForm extensionForm = new ExtensionForm();
		model.put("extensionForm", extensionForm);
		if (!StringUtils.isBlank(extensionId)) {
			Extension extension = service.getFormExtension(form, extensionId);
			extensionForm.copyFrom(extension);			
		} else {
			//Set defaults
			extensionForm.setId("");
			extensionForm.setLabel(form.getName());
			extensionForm.setIcon("icon-file");
			extensionForm.setOrder(15);
			extensionForm.setShowIf("");
		}
		
		model.put("uiLocations", manager.getUILocations(form));
		model.put("form", form);
		model.put("formTechnology", manager.getFormTechnology(form));	
		model.put("privileges", manager.getRequiredPrivileges());
		model.put("displayStyles", manager.getDisplayStyles(form));
		
		return null;
	}
	
	public String post(@BindParams("extensionForm") ExtensionForm extensionForm,
	        @SpringBean("formEntryAppService") FormEntryAppService service, @SpringBean("formManager") FormManager manager) {
		Extension extension = null;
		if (extensionForm.getId() != null) {
			extension = service.getFormExtension(extensionForm.getForm(), extensionForm.getId());
		}
		if (extension == null) {
			extension = new Extension();
		}
		
		extensionForm.copyTo(extension);
		
		extension.setType("link");
		Map<String, String> options = new HashMap<String, String>();
		options.put("displayStyle", extensionForm.getDisplayStyle());
		extension.setUrl(manager.getFormUrl(extensionForm.getForm(), options));
		
		service.saveFormExtension(extensionForm.getForm(), extension);
		
		return "redirect:" + FormsPageController.PAGE_URL;
	}
	
}
