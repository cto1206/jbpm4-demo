<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="order.model"/><s:text name="title.request"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="order.model"/><s:text name="title.request"/></td>
    </tr>
</table>
<br/>

<s:form action="submitRequest" method="post" theme="simple">
    <s:include value="_wf_form.jsp"/>
    <s:hidden key="taskId"/>
    <s:hidden key="order.wfId"/>
    <table cellspacing="0" width="100%">
        <tr>
            <td class="page-form-centered"><input type="submit" name="submit" value="Save"/><input type="submit" name="submit" value="Request"/></td>
        </tr>
    </table>
</s:form>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><s:url id="url" value="/jbpm/list" /><a href="<s:property value="#url"/>"><s:text name="action.back"/></a></td>
    </tr>
</table>

<script type="text/javascript">
$(document).ready(function() {
	$("#submitRequest").validate();
});
</script>

</body>
</html>
