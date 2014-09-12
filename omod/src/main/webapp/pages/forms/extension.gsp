<%
    ui.decorateWith("appui", "standardEmrPage")
%>
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ui.message("formentryapp.manageforms")}", link: "${ ui.pageLink("formentryapp", "forms?app=formentryapp.forms") }"},
        { label: "${ ui.message("formentryapp.addform")}"}
    ];
</script>

<h2>${form.name} - ${ ui.message("formentryapp.addform") }</h2>

<form method="post" action="${ ui.pageLink("formentryapp", "forms/extension") }">
<input type="hidden" name="extensionForm.form" value="${form.id}" />
<input type="hidden" name="extensionForm.id" value="${extensionForm.id}" />

<p>
<label name="extensionForm.formTechnology">${ui.message("formentryapp.formtechnology")}: ${formTechnology}<label>
</p>

<p>
<label name="extensionForm.uiLocation">${ui.message("formentryapp.uilocation")}</label>
<select id="extensionForm.uiLocation" name="extensionForm.uiLocation">
<% 
uiLocations.each { uiLocation ->
%>
<option <% if (uiLocation.equals(extensionForm.uiLocation)) { %> selected <% } %> value="${uiLocation}">${ui.message("formentryapp." + uiLocation)}</option>
<%
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
<input type="text" id="extensionForm.label" name="extensionForm.label" value="${extensionForm.label}"/></p>

<p><label name="extensionForm.icon">${ui.message("formentryapp.icon")}</label>
<input type="text" id="extensionForm.icon" name="extensionForm.icon" value="${extensionForm.icon}"/></p>

<p><label name="extensionForm.requiredPrivilege">${ui.message("formentryapp.privilege")}</label>
<select id="extensionForm.requiredPrivilege" name="extensionForm.requiredPrivilege">
<option></option>
<% privileges.each { privilege -> %>
<option <% if (privilege.equals(extensionForm.requiredPrivilege)) { %> selected <% } %> >${privilege}</option>
<% } %>
</select>
</p>

<p><label name="extensionForm.order">${ui.message("formentryapp.order")}</label>
<input type="text" id="extensionForm.order" name="extensionForm.order" value="${extensionForm.order}"/></p>

<p><label name="extensionForm.showIf">${ui.message("formentryapp.showif")}</label>
<input type="text" id="extensionForm.showIf" name="extensionForm.showIf" value="${extensionForm.showIf}"/></p>

<p>
<input type="button" onclick="window.location='${ ui.pageLink("formentryapp", "forms?app=formentryapp.forms") }'" value="${ui.message("general.cancel")}"/>
<input type="submit" value="${ui.message("general.save")}"/>
</p>
</form>