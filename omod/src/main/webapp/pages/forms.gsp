<%
    ui.decorateWith("appui", "standardEmrPage")
%>
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
		{ label: "${ ui.message('adminui.app.configureMetadata.label')}" , link: '${ui.pageLink("adminui", "metadata/configureMetadata")}'},
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
        <% if (!forms) { %>
            <tr>
                <td colspan="5">
                    ${ ui.message("emr.none") }
                </td>
            </tr>
        <% } %>
		<% forms.each { f -> %>
			<tr id="form-${ ui.encodeHtmlContent(f.key.name) }">
				<td>${ ui.encodeHtmlContent(f.key.name) }</td>
				<td>${ ui.encodeHtmlContent(f.key.description ?: '-') }</td>
				<td>${ ui.encodeHtmlContent(f.key.version) }</td>
				<td>${ f.key.published }</td>
				<td>
				<% if (editFormLocations[f.key.id]) {
					f.value.each { extension -> %>
					
				<p style="white-space: nowrap;">
                    ${ui.message("formentryapp." + extension.extensionPointId)}
                    <i class="icon-pencil edit-action" onclick="location.href='forms/extension.page?formId=${f.key.id}&extensionId=${extension.id}'"></i>
                    <i class="icon-remove delete-action" onclick="location.href='forms/deleteExtension.page?formId=${f.key.id}&extensionId=${extension.id}'"></i>
				</p>
				
					<% } 
					
					if (addUiLocations[f.key.id]) { %>
					
				<p>
                    <a href="forms/extension.page?formId=${f.key.id}">${ ui.message("general.add") }</a>
                </p>
                
					<% }
					
				} else { %>
				
				<p style="white-space: nowrap;">
					${ui.message("formentryapp.formLocked")} <i class="icon-lock"></i>
				</p>
				
				<% } %>
				</td>
			</tr>
		<% } %>
	</tbody>
</table>