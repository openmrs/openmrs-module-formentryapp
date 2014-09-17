package org.openmrs.module.formentryapp.page.controller.forms;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.Form;
import org.openmrs.module.appframework.domain.Extension;

public class ExtensionForm {
	
	private String id;
	
	private Form form;
	
	private String uiLocation;
	
	private String displayStyle;
	
	private String label;
	
	private String icon;
	
	private String requiredPrivilege;
	
	private Integer order;
	
	private String showIf;
		
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Form getForm() {
		return form;
	}
	
	public void setForm(Form form) {
		this.form = form;
	}
	
	public String getUiLocation() {
		return uiLocation;
	}
	
	public void setUiLocation(String uiLocation) {
		this.uiLocation = uiLocation;
	}
	
	public String getDisplayStyle() {
		return displayStyle;
	}
	
	public void setDisplayStyle(String displayStyle) {
		this.displayStyle = displayStyle;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getRequiredPrivilege() {
		return requiredPrivilege;
	}
	
	public void setRequiredPrivilege(String requiredPrivilege) {
		this.requiredPrivilege = requiredPrivilege;
	}
	
	public Integer getOrder() {
		return order;
	}
	
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public String getShowIf() {
		return showIf;
	}
	
	public void setShowIf(String showIf) {
		this.showIf = showIf;
	}
	
	public void copyFrom(Extension extension) {
		id = extension.getId();
		uiLocation = extension.getExtensionPointId();
		label = extension.getLabel();
		icon = extension.getIcon();
		requiredPrivilege = extension.getRequiredPrivilege();
		order = extension.getOrder();
		showIf = extension.getRequire();
		displayStyle = (String) extension.getExtensionParams().get("displayStyle");
	}
	
	public void copyTo(Extension extension) {
		extension.setId(id);
		extension.setExtensionPointId(uiLocation);
		extension.setLabel(label);
		extension.setIcon(icon);
		extension.setRequiredPrivilege(requiredPrivilege);
		extension.setOrder(order);
		extension.setRequire(showIf);
		Map<String,Object> extensionParams = new HashMap<String, Object>();
		extensionParams.put("displayStyle", displayStyle);
		extension.setExtensionParams(extensionParams);
	}
}
