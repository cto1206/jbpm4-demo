<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="order.model"/><s:text name="title.info"/></td>
        <s:hidden key="order.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="order.name"/></td>
        <td class="page-form-value"><s:textfield key="order.name"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="order.note"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="order.note"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 