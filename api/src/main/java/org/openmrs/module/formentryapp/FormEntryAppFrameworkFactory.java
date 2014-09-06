package org.openmrs.module.formentryapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openmrs.Form;
import org.openmrs.FormResource;
import org.openmrs.api.FormService;
import org.openmrs.module.appframework.domain.AppDescriptor;
import org.openmrs.module.appframework.domain.AppTemplate;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.module.appframework.factory.AppFrameworkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormEntryAppFrameworkFactory implements AppFrameworkFactory {

	@Autowired
	FormService formService;
	
	@Override
    public List<AppDescriptor> getAppDescriptors() throws IOException {
	    return Collections.emptyList();
    }

	@Override
    public List<Extension> getExtensions() throws IOException {
		List<Extension> formExtensions = new ArrayList<Extension>();
		
		List<Form> forms = formService.getAllForms();
		for (Form form : forms) {
	        Collection<FormResource> formResources = formService.getFormResourcesForForm(form);
	        for (FormResource formResource : formResources) {
	            if (formResource.getDatatypeClassname().equals(ExtensionFormResource.class.getName())) {
	            	Extension extension = (Extension) formResource.getValue();
	            	formExtensions.add(extension);
	            }
            }
        }
		
	    return formExtensions;
    }

	@Override
    public List<AppTemplate> getAppTemplates() throws IOException {
	    return Collections.emptyList();
    }
	
}
