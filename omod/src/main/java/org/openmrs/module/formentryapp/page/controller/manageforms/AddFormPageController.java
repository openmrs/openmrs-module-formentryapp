package org.openmrs.module.formentryapp.page.controller.manageforms;

import org.openmrs.Form;
import org.openmrs.module.formentryapp.FormManager;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;


public class AddFormPageController {
	
	public String get(@RequestParam("formId") Form form, @SpringBean("formManager") FormManager manager, PageModel model) {
		model.put("form", form);
		model.put("formTechnology", manager.getFormTechnology(form));
		return null;
	}
	
}
