<%
    ui.decorateWith("appui", "standardEmrPage")
%>
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("formentryapp.manageforms.addform.label")}"}
    ];
</script>

<h3>${ ui.message("formentryapp.manageforms.addform.label") }</h3>

<h4>${ui.message("Form.fieldType")}: ${form.name}</h4>
<p>${ui.message("formentryapp.manageforms.formtechnology")}: ${formTechnology}</p>
<p>${ui.message("formentryapp.manageforms.uilocation")}</p>