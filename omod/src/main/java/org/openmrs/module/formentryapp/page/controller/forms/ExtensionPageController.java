package org.openmrs.module.formentryapp.page.controller.forms;

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
			extensionForm.setLabel(form.getName());
			extensionForm.setIcon("icon-pencil");
			extensionForm.setOrder(15);
			extensionForm.setShowIf("");
		}
		model.put("form", form);
		model.put("formTechnology", manager.getFormTechnology(form));
		model.put("uiLocations", manager.getUILocations());
		model.put("privileges", manager.getRequiredPrivileges());
		
		return null;
	}
	
	public String delete(@RequestParam("formId") Form form, @RequestParam("deleteExtensionId") String extensionId,
			  @SpringBean("formEntryAppService") FormEntryAppService service) {
		Extension extension = service.getFormExtension(form, extensionId);
		service.purgeFormExtension(form, extension);
		
		return "redirect:" + FormsPageController.PAGE_URL;
	}
	
	public String post(@BindParams("extensionForm") ExtensionForm extensionForm,
	        @SpringBean("formEntryAppService") FormEntryAppService service) {
		Extension extension = null;
		if (extensionForm.getId() != null) {
			extension = service.getFormExtension(extensionForm.getForm(), extensionForm.getId());
		}
		if (extension == null) {
			extension = new Extension();
		}
		
		extensionForm.copyTo(extension);
		
		extension.setType("link");
		//TODO: set to correct URL
		extension.setUrl("about:blank");
		
		service.saveFormExtension(extensionForm.getForm(), extension);
		
		return "redirect:" + FormsPageController.PAGE_URL;
	}
	
}
