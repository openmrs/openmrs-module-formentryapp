<%
    ui.decorateWith("appui", "standardEmrPage")
%>
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ui.message("referenceapplication.configuremetadata")}", link: "${ ui.pageLink("referenceapplication", "configureMetadata?app=referenceapplication.configuremetadata") }"},
        { label: "${ ui.message("formentryapp.manageforms")}"}
    ];
</script>

<h3>${ ui.message("formentryapp.manageforms") }</h3>

<table id="forms" width="100%" border="1" cellspacing="0" cellpadding="2">
	<thead>
		<tr>
			<th>${ ui.message("general.name") }</th>
			<th>${ ui.message("general.description") }</th>
			<th>${ ui.message("general.version") }</th>
			<th>${ ui.message("Form.published") }</th>
			<th>UI</th>
		</tr>
	</thead>
	<tbody>
		<% forms.each { f -> %>
			<tr id="form-${ f.key.name }">
				<td>${ f.key.name }</td>
				<td>${ f.key.description ?: '-' }</td>
				<td>${ f.key.version }</td>
				<td>${ f.key.published }</td>
				<td>
				<% f.value.each { extension -> %>
				<p style="white-space: nowrap;">${ui.message("formentryapp." + extension.extensionPointId)} 
				<a href="forms/extension.page?formId=${f.key.id}&extensionId=${extension.id}">${ ui.message("general.edit") }</a> 
				<a href="forms/deleteExtension.page?formId=${f.key.id}&extensionId=${extension.id}">${ ui.message("general.delete") }</a>
				</p>
				<% } 
				if (addUiLocations[f.key.id]) {
				%>
				<p><a href="forms/extension.page?formId=${f.key.id}">${ ui.message("general.add") }</a></p>
				<% } %>
				</td>
			</tr>
		<% } %>
	</tbody>
</table>