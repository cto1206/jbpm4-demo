package com.bulain.mybatis.controller;

import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.bulain.mybatis.test.JbpmTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class JbpmActionTest extends JbpmTestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(JbpmActionTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpCleanJbpm();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testWorkflow() throws Exception {
		initServletMockObjects();
		ActionProxy proxy = getActionProxy("/jbpm/deploy");
		JbpmAction jbpmAction = (JbpmAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/jbpm/list");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		List<ProcessDefinition> listProcessDefinition = jbpmAction.getListProcessDefinition();
		List<ProcessInstance> listProcessInstance = jbpmAction.getListProcessInstance();
		List<Task> listTask = jbpmAction.getListPersonTask();
		
		assertEquals(1, listProcessDefinition.size());
		assertEquals(0, listProcessInstance.size());
		assertEquals(0, listTask.size());
		
		String processDefinitionId = listProcessDefinition.get(0).getId();
		
		initServletMockObjects();
		request.setParameter("processDefinitionId", processDefinitionId);
		proxy = getActionProxy("/jbpm/start");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/jbpm/list");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		assertEquals(1, jbpmAction.getListProcessInstance().size());
		String executionId = jbpmAction.getListProcessInstance().get(0).getId();
		
		initServletMockObjects();
		request.setParameter("executionId", executionId);
		proxy = getActionProxy("/jbpm/view");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/jbpm/list");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		listProcessDefinition = jbpmAction.getListProcessDefinition();
		listProcessInstance = jbpmAction.getListProcessInstance();
		listTask = jbpmAction.getListPersonTask();
		
		assertEquals(1, listProcessDefinition.size());
		assertEquals(1, listProcessInstance.size());
		assertEquals(1, listTask.size());
		
		String deploymentId = listProcessDefinition.get(0).getDeploymentId();
		
		initServletMockObjects();
		request.setParameter("deploymentId", deploymentId);
		proxy = getActionProxy("/jbpm/destroy");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/jbpm/list");
		jbpmAction = (JbpmAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		listProcessDefinition = jbpmAction.getListProcessDefinition();
		listProcessInstance = jbpmAction.getListProcessInstance();
		listTask = jbpmAction.getListPersonTask();
		
		assertEquals(0, listProcessDefinition.size());
		assertEquals(0, listProcessInstance.size());
		assertEquals(0, listTask.size());
	} 
}
