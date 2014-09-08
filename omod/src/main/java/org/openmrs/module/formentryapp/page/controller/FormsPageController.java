package org.openmrs.module.formentryapp.page.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.Form;
import org.openmrs.module.appframework.domain.AppDescriptor;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.module.formentryapp.FormEntryAppService;
import org.openmrs.module.formentryapp.FormManager;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class FormsPageController {
	
	public static final String PAGE_URL = "formentryapp/forms.page?app=formentryapp.forms";
	
	public String get(@RequestParam("app") AppDescriptor app, PageModel model, @SpringBean("formManager") FormManager manager,
			@SpringBean("formEntryAppService") FormEntryAppService service) {
		List<Form> supportedForms = manager.getSupportedForms();
		
		Map<Form, List<Extension>> forms = new LinkedHashMap<Form, List<Extension>>();
		for (Form supportedForm : supportedForms) {
	        List<Extension> extensions = service.getFormExtensions(supportedForm);
	        forms.put(supportedForm, extensions);
        }
		model.put("forms", forms);
		
		return null;
	}
}
