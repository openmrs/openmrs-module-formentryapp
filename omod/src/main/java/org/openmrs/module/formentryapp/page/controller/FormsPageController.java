package org.openmrs.module.formentryapp.page.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
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
		
		Set<String> builtInForms = getBuiltInForms(app);
		
		Map<Form, List<Extension>> forms = new LinkedHashMap<Form, List<Extension>>();
		Map<Integer, Boolean> addUiLocations = new HashMap<Integer, Boolean>();
		Map<Integer, Boolean> editFormLocations = new HashMap<Integer, Boolean>();
		for (Form supportedForm : supportedForms) {
			editFormLocations.put(supportedForm.getId(), !builtInForms.contains(supportedForm.getUuid()));
			
			List<Extension> extensions = service.getFormExtensions(supportedForm);
			forms.put(supportedForm, extensions);
			
			List<String> uiLocations = manager.getUILocations(supportedForm);
			addUiLocations.put(supportedForm.getId(), !uiLocations.isEmpty());
        }
		model.put("forms", forms);
		model.put("addUiLocations", addUiLocations);
		model.put("editFormLocations", editFormLocations);
		
		return null;
	}

	private Set<String> getBuiltInForms(AppDescriptor app) {
	    Set<String> builtInFormUuids = new HashSet<String>();
		JsonNode builtInForms = app.getConfig().get("builtInForms");
		if (builtInForms != null) {
			for (JsonNode builtInForm : builtInForms) {
	            builtInFormUuids.add(builtInForm.getTextValue());
            }
		}
		return builtInFormUuids;
    }
}
