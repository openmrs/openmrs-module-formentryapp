package org.openmrs.module.formentryapp.page.controller.forms;

import org.openmrs.Form;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.module.formentryapp.FormEntryAppService;
import org.openmrs.module.formentryapp.page.controller.FormsPageController;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.springframework.web.bind.annotation.RequestParam;

public class DeleteExtensionPageController {
	
	public String get(@RequestParam("formId") Form form, @RequestParam("extensionId") String extensionId,
			  @SpringBean("formEntryAppService") FormEntryAppService service) {
		Extension extension = service.getFormExtension(form, extensionId);
		service.purgeFormExtension(form, extension);
		
		return "redirect:" + FormsPageController.PAGE_URL;
	}
}
