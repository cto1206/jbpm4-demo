<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title>Workflow</title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">Process Definition</td>
    </tr>
</table>
<br/>
<table cellspacing="0" width="100%">
    <tr>
        <td width="40%"><s:url id="url" action="deploy" /><a href="<s:property value="#url"/>">Deploy</a></td>
        <td width="60%" />
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ID</th>
        <th>Name</th>
        <th>Key</th>
        <th>Version</th>
        <th>DeploymentId</th>
        <th>Description</th>
        <th>Suspended</th>
        <th class="minNoWrap">Action</th>
    </tr>
    <s:iterator value="listProcessDefinition" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="id"/></td>
            <td class="noWrap"><s:property value="name"/></td>
            <td class="noWrap"><s:property value="key"/></td>
            <td class="noWrap"><s:property value="version"/></td>
            <td class="noWrap"><s:property value="deploymentId"/></td>
            <td class="noWrap"><s:property value="description"/></td>
            <td class="noWrap"><s:property value="suspended"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="start"><s:param name="processDefinitionId" value="id"></s:param></s:url><a href="<s:property value="#url"/>">Start</a>
        	|<s:url id="url" action="destroy"><s:param name="deploymentId" value="%{deploymentId}"></s:param></s:url><a href="<s:property value="#url"/>">Destroy</a>
        	</td>
        </tr>
    </s:iterator>
</table>
<br/>
<br/>


<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">Process Instance</td>
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ID</th>
        <th>Name</th>
        <th>Key</th>
        <th>State</th>
        <th>IsProcessInstance</th>
        <th>Ended</th>
        <th>Suspended</th>
        <th class="minNoWrap">Action</th>
    </tr>
    <s:iterator value="listProcessInstance" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="id"/></td>
            <td class="noWrap"><s:property value="name"/></td>
            <td class="noWrap"><s:property value="key"/></td>
            <td class="noWrap"><s:property value="state"/></td>
            <td class="noWrap"><s:property value="isProcessInstance"/></td>
            <td class="noWrap"><s:property value="ended"/></td>
            <td class="noWrap"><s:property value="suspended"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="view"><s:param name="executionId" value="id"></s:param></s:url><a href="<s:property value="#url"/>">View</a>
        	</td>
        </tr>
    </s:iterator>
</table>
<br/>
<br/>


<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">Person Task</td>
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Assignee</th>
        <th>CreateTime</th>
        <th>Duedate</th>
        <th>Priority</th>
        <th>Progress</th>
        <th>ExecutionId</th>
        <th>ActivityName</th>
        <th class="minNoWrap">Action</th>
    </tr>
    <s:iterator value="listPersonTask" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="id"/></td>
            <td class="noWrap"><s:property value="name"/></td>
            <td class="noWrap"><s:property value="description"/></td>
            <td class="noWrap"><s:property value="assignee"/></td>
            <td class="noWrap"><s:property value="createTime"/></td>
            <td class="noWrap"><s:property value="duedate"/></td>
            <td class="noWrap"><s:property value="priority"/></td>
            <td class="noWrap"><s:property value="progress"/></td>
            <td class="noWrap"><s:property value="executionId"/></td>
            <td class="noWrap"><s:property value="activityName"/></td>
            <td class="minNoWrap">
        	<s:url id="url" value="%{formResourceName}"><s:param name="taskId" value="id"></s:param></s:url><a href="<s:property value="#url"/>">Active</a>
        	</td>
        </tr>
    </s:iterator>
</table>
<br/>
<br/>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">Group Task</td>
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Assignee</th>
        <th>CreateTime</th>
        <th>Duedate</th>
        <th>Priority</th>
        <th>Progress</th>
        <th>ExecutionId</th>
        <th>ActivityName</th>
        <th class="minNoWrap">Action</th>
    </tr>
    <s:iterator value="listGroupTask" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="id"/></td>
            <td class="noWrap"><s:property value="name"/></td>
            <td class="noWrap"><s:property value="description"/></td>
            <td class="noWrap"><s:property value="assignee"/></td>
            <td class="noWrap"><s:property value="createTime"/></td>
            <td class="noWrap"><s:property value="duedate"/></td>
            <td class="noWrap"><s:property value="priority"/></td>
            <td class="noWrap"><s:property value="progress"/></td>
            <td class="noWrap"><s:property value="executionId"/></td>
            <td class="noWrap"><s:property value="activityName"/></td>
            <td class="minNoWrap">
        	<s:url id="url" value="%{formResourceName}"><s:param name="taskId" value="id"></s:param></s:url><a href="<s:property value="#url"/>">Active</a>
        	</td>
        </tr>
    </s:iterator>
</table>
<br/>
<br/>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">History Process Instance</td>
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ProcessInstanceId</th>
        <th>ProcessDefinitionId</th>
        <th>Key</th>
        <th>State</th>
        <th>StartTime</th>
        <th>EndTime</th>
        <th>Duration</th>
        <th>EndActivityName</th>
        <th class="minNoWrap">Action</th>
    </tr>
    <s:iterator value="listHistoryProcessInstance" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="processInstanceId"/></td>
            <td class="noWrap"><s:property value="processDefinitionId"/></td>
            <td class="noWrap"><s:property value="key"/></td>
            <td class="noWrap"><s:property value="state"/></td>
            <td class="noWrap"><s:property value="startTime"/></td>
            <td class="noWrap"><s:property value="endTime"/></td>
            <td class="noWrap"><s:property value="duration"/></td>
            <td class="noWrap"><s:property value="endActivityName"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="view"><s:param name="executionId" value="%{processInstanceId}"></s:param></s:url><a href="<s:property value="#url"/>">View</a>
        	</td>
        </tr>
    </s:iterator>
</table>

</body>
</html>
