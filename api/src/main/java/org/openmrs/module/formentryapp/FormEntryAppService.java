package org.openmrs.module.formentryapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Form;
import org.openmrs.FormResource;
import org.openmrs.api.FormService;
import org.openmrs.module.appframework.AppFrameworkActivator;
import org.openmrs.module.appframework.domain.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormEntryAppService {
	
	private final String FORMENTRYAPP_RESOURCE_PREFIX = "formentryapp.";
	
	@Autowired
	FormService formService;

	public void saveFormExtension(Form form, Extension extension) {
		FormResource formResource = null;
		if (!StringUtils.isBlank(extension.getId())) {
			formResource = formService.getFormResource(form, FORMENTRYAPP_RESOURCE_PREFIX + extension.getId());
		}
		if (formResource == null) {
			if (StringUtils.isBlank(extension.getId())) {
				extension.setId(extension.getExtensionPointId() + ".form." + form.getId());
			}
			
			formResource = new FormResource();
			formResource.setForm(form);
			formResource.setName(FORMENTRYAPP_RESOURCE_PREFIX + extension.getId());
			formResource.setDatatypeClassname(ExtensionFormResource.class.getName());
		}
		formResource.setValue(extension);
		formService.saveFormResource(formResource);
		
		new AppFrameworkActivator().contextRefreshed();
	}
	
	public void purgeFormExtension(Form form, Extension extension) {
		FormResource formResource = formService.getFormResource(form, FORMENTRYAPP_RESOURCE_PREFIX + extension.getId());
		if (formResource != null) {
			formService.purgeFormResource(formResource);
		}
		
		new AppFrameworkActivator().contextRefreshed();
	}
	
	public List<Extension> getFormExtensions(Form form) {
		List<Extension> extensions = new ArrayList<Extension>();
		
		Collection<FormResource> formResources = formService.getFormResourcesForForm(form);
		for (FormResource formResource : formResources) {
	        if (formResource.getDatatypeClassname().equals(ExtensionFormResource.class.getName())) {
	        	extensions.add((Extension) formResource.getValue());
	        }
        }
		
		return extensions;
	}
	
	public Extension getFormExtension(Form form, String extensionId) {
		List<Extension> extensions = getFormExtensions(form);
		for (Extension existingExtension : extensions) {
	        if (existingExtension.getId().equals(extensionId)) {
	        	return existingExtension;
	        }
        }
		
		return null;
	}
}