<%
    ui.decorateWith("appui", "standardEmrPage")
%>
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("formentryapp.manageforms.label")}"}
    ];
</script>

<h3>${ ui.message("formentryapp.manageforms.label") }</h3>

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
			<tr id="form-${ f.name }">
				<td>${ f.name }</td>
				<td>${ f.description }</td>
				<td>${ f.version }</td>
				<td>${ f.published }</td>
				<td><a href="manageforms/addForm.page?formId=${f.id}">${ ui.message("general.add") }</a></td>
			</tr>
		<% } %>
	</tbody>
</table>