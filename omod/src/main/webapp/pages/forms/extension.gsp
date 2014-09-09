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

<h3>${ ui.message("formentryapp.addform") }</h3>

<h4>${ui.message("Form.fieldType")}: ${form.name}</h4>

<form method="post" action="${ ui.pageLink("formentryapp", "forms/extension") }">
<input type="hidden" name="extensionForm.form" value="${form.id}" />
<input type="hidden" name="extensionForm.id" value="${extensionForm.id}" />

<p>${ui.message("formentryapp.formtechnology")}: ${formTechnology}</p>

<p>${ui.message("formentryapp.uilocation")} </p>
<p>
<select name="extensionForm.uiLocation">
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
<p>${ui.message("formentryapp.displaystyle")}</p>
<p><select name="extensionForm.displayStyle">
<% displayStyles.each { displayStyle -> %>
<option <% if (displayStyle.equals(extensionForm.displayStyle)) { %> selected <% } %> >${displayStyle}</option>
<% } %>
</select>
</p>
<% } %>

<p>${ui.message("formentryapp.labeltext")}</p>
<p><input type="text" name="extensionForm.label" value="${extensionForm.label}"/></p>

<p>${ui.message("formentryapp.icon")}</p>
<p><input type="text" name="extensionForm.icon" value="${extensionForm.icon}"/></p>

<p>${ui.message("formentryapp.privilege")}</p>
<p>
<select name="extensionForm.requiredPrivilege">
<% privileges.each { privilege -> %>
<option <% if (privilege.equals(extensionForm.requiredPrivilege)) { %> selected <% } %> >${privilege}</option>
<% } %>
</select>
</p>

<p>${ui.message("formentryapp.order")}</p>
<p><input type="text" name="extensionForm.order" value="15" value="${extensionForm.order}"/></p>

<p>${ui.message("formentryapp.showif")}</p>
<p><input type="text" name="extensionForm.showIf" value="${extensionForm.showIf}"/></p>

<p>
<input type="button" onclick="window.location='${ ui.pageLink("formentryapp", "forms?app=formentryapp.forms") }'" value="${ui.message("general.cancel")}"/>
<input type="submit" value="${ui.message("general.save")}"/>
</p>
</form>