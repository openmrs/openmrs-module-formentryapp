<%
    ui.decorateWith("appui", "standardEmrPage")
%>
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
		{ label: "${ ui.message('adminui.app.configureMetadata.label')}" , link: '${ui.pageLink("adminui", "metadata/configureMetadata")}'},
        { label: "${ui.message("formentryapp.manageforms")}", link: "${ ui.pageLink("formentryapp", "forms?app=formentryapp.forms") }"},
        { label: "${ ui.message("formentryapp.addform")}"}
    ];
</script>

<h2>${ui.encodeHtmlContent(form.name)} - ${ ui.message("formentryapp.addform") }</h2>

<form method="post" action="${ ui.pageLink("formentryapp", "forms/extension") }">
<input type="hidden" name="extensionForm.form" value="${form.id}" />
<input type="hidden" name="extensionForm.id" value="${extensionForm.id}" />

<p>
<label name="extensionForm.formTechnology">${ui.message("formentryapp.formtechnology")}: ${formTechnology}<label>
</p>

<p>
<label name="extensionForm.uiLocation">${ui.message("formentryapp.uilocation")}:
<% if (extensionForm.id) { %>
${ui.message("formentryapp." + extensionForm.uiLocation)}
<input type="hidden" id="extensionForm.uiLocation" name="extensionForm.uiLocation" value="${extensionForm.uiLocation}" />
</label>
<% } else { %>
</label>
<select id="extensionForm.uiLocation" name="extensionForm.uiLocation">
<%
	uiLocations.each { uiLocation ->
%>
<option value="${uiLocation}">${ui.message("formentryapp." + uiLocation)}</option>
<%
	}
}
%>
</select>
</p>

<% if (!displayStyles.isEmpty()) { %>
<p>
<label name="extensionForm.displayStyle">${ui.message("formentryapp.displaystyle")}</label>
<select id="extensionForm.displayStyle" name="extensionForm.displayStyle">
<% displayStyles.each { displayStyle -> %>
<option <% if (displayStyle.equals(extensionForm.displayStyle)) { %> selected <% } %> >${displayStyle}</option>
<% } %>
</select>
</p>
<% } %>

<p><label name="extensionForm.label">${ui.message("formentryapp.labeltext")}</label>
<input type="text" id="extensionForm.label" name="extensionForm.label" value="${ui.encodeHtmlContent(extensionForm.label)}"/></p>

<p><label name="extensionForm.icon">${ui.message("formentryapp.icon")}</label>
<input type="text" id="extensionForm.icon" name="extensionForm.icon" value="${ui.encodeHtmlContent(extensionForm.icon)}"/></p>

<p><label name="extensionForm.requiredPrivilege">${ui.message("formentryapp.privilege")}</label>
<select id="extensionForm.requiredPrivilege" name="extensionForm.requiredPrivilege">
<option></option>
<% privileges.each { privilege -> %>
<option <% if (privilege.equals(extensionForm.requiredPrivilege)) { %> selected <% } %> >${privilege}</option>
<% } %>
</select>
</p>

<p><label name="extensionForm.order">${ui.message("formentryapp.order")}</label>
<input type="number" id="extensionForm.order" name="extensionForm.order" value="${extensionForm.order}" required/></p>

<p><label name="extensionForm.showIf">${ui.message("formentryapp.showif")}</label>
<input type="text" id="extensionForm.showIf" name="extensionForm.showIf" value="${ui.escapeAttribute(extensionForm.showIf)}"/></p>

<p>
<input type="button" onclick="window.location='${ ui.pageLink("formentryapp", "forms?app=formentryapp.forms") }'" value="${ui.message("general.cancel")}"/>
<input type="submit" value="${ui.message("general.save")}"/>
</p>
</form>
