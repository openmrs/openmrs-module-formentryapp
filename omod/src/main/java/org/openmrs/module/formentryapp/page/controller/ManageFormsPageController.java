package org.openmrs.module.formentryapp.page.controller;

import java.util.List;

import org.openmrs.Form;
import org.openmrs.module.appframework.domain.AppDescriptor;
import org.openmrs.module.formentryapp.FormManager;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ManageFormsPageController {
	
	public String get(@RequestParam("app") AppDescriptor app, PageModel model, @SpringBean("formManager") FormManager manager) {
		List<Form> forms = manager.getSupportedForms();
		
		model.put("forms", forms);
		
		return null;
	}
}
