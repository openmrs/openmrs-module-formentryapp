package org.openmrs.module.formentryapp;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.api.FormService;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.formentryapp.FormEntryAppService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;


public class FormEntryAppFunctionalTest extends BaseModuleContextSensitiveTest {
	
	@Autowired
	FormEntryAppService formEntryUIService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	AppFrameworkService appFrameworkService;
	
	@Test
	public void shouldAddNewExtension() {
		Form form = formService.getForm(1);
		
		Extension extension = newMockExtension();
	    
	    formEntryUIService.saveFormExtension(form, extension);
	    
	    List<Extension> extensions = appFrameworkService.getExtensionsById("patientDashboard.visitActions", extension.getId());
	    
	    assertThat(extensions, hasItem(extension));
	}
	
	@Test
	public void shouldUpdateExistingExtension() {
		Form form = formService.getForm(1);
		
		Extension extension = newMockExtension();
		
		formEntryUIService.saveFormExtension(form, extension);
		
		extension.setLabel("referenceapplication.form.one.label");
	
		formEntryUIService.saveFormExtension(form, extension);
		
		List<Extension> extensions = appFrameworkService.getExtensionsById("patientDashboard.visitActions", extension.getId());
		
	    assertThat(extensions, hasItem(extension));
	    Extension formExtension = extensions.get(0);
		assertThat(formExtension.getLabel(), is(extension.getLabel()));
	}
	
	@Test
	public void shouldPurgeExistingExtension() {
		Form form = formService.getForm(1);
		
		Extension extension = newMockExtension();
		
		formEntryUIService.saveFormExtension(form, extension);
		
		formEntryUIService.purgeFormExtension(form, extension);
		
		List<Extension> extensions = appFrameworkService.getExtensionsById("patientDashboard.visitActions", extension.getId());
		
	    assertThat(extensions, is(empty()));
	}

	private Extension newMockExtension() {
	    Extension extension = new Extension();
		extension.setExtensionPointId("patientDashboard.visitActions");
		extension.setType("link");
		extension.setLabel("referenceapplication.form.one.title");
		extension.setUrl("htmlformentryui/htmlform/enterHtmlFormWithStandardUi.page?patientId={{patient.uuid}}&visitId={{visit.uuid}}&definitionUiResource=referenceapplication:htmlforms/formOne.xml");
		extension.setIcon("icon-stethoscope");
		extension.setOrder(30);
		extension.setRequiredPrivilege("Task: referenceapplication.formOne");
	    extension.setRequire("visit.active");
	    return extension;
    }
}
