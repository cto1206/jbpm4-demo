package com.bulain.mybatis.controller;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.ProcessInstance;

import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.service.OrderService;
import com.bulain.mybatis.test.JbpmTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class OrderActionWorkflowTest extends JbpmTestCase {
	private String processDefinitionId;
	private String deploymentId;
	
	private OrderService orderService;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(OrderActionWorkflowTest.class);
	}
	
	protected void setUp() throws Exception {
	    super.setUp();
	    super.setUpJbpm();
	    super.setUpCleanJbpm();
	    
	    deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("jpdl/order.jpdl.xml")
	        .deploy();
	    processDefinitionId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).uniqueResult().getId();
	    orderService = (OrderService) applicationContext.getBean("orderService");
	}
	
	protected void tearDown() throws Exception {
	    repositoryService.deleteDeploymentCascade(deploymentId);
		super.tearDown();
	}

	protected String start() throws Exception{
		Map<String, Object> variables = new HashMap<String, Object>(); 
		variables.put("owner", "bulain");
		ProcessInstance processInstance = executionService.startProcessInstanceById(processDefinitionId, variables);
		return processInstance.getId();
		
	}
	
	protected String task(String executionId) throws Exception{
		return taskService.createTaskQuery().executionId(executionId).uniqueResult().getId();
	}
	
	public void testWorkflowApprove() throws Exception{
		String executionId = start();
		String taskId = task(executionId);
		
		initServletMockObjects();
		request.addParameter("taskId", taskId);
		ActionProxy proxy = getActionProxy("/order/request");
		OrderAction orderAction = (OrderAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		String wfId = orderAction.getOrder().getWfId();
		
		initServletMockObjects();
		request.setParameter("order.name", "name");
		request.setParameter("order.note", "note");
		request.setParameter("order.wfId", wfId);
		request.setParameter("taskId", taskId);
		request.setParameter("submit", "Request");
		proxy = getActionProxy("/order/submitRequest");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		taskId = task(executionId);
		
		initServletMockObjects();
		request.addParameter("taskId", taskId);
		proxy = getActionProxy("/order/approve");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Integer orderId = orderAction.getOrder().getId();
		
		initServletMockObjects();
		request.setParameter("order.id", Integer.toString(orderId));
		request.setParameter("taskId", taskId);
		request.setParameter("submit", "Approve");
		proxy = getActionProxy("/order/submitApprove");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Order order = orderService.getByWfId(executionId);
		orderService.delete(order.getId());
	}
	
	public void testWorkflowReject() throws Exception{
		String executionId = start();
		String taskId = task(executionId);
		
		initServletMockObjects();
		request.addParameter("taskId", taskId);
		ActionProxy proxy = getActionProxy("/order/request");
		OrderAction orderAction = (OrderAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		String wfId = orderAction.getOrder().getWfId();
		
		initServletMockObjects();
		request.setParameter("order.name", "name");
		request.setParameter("order.note", "note");
		request.setParameter("order.wfId", wfId);
		request.setParameter("taskId", taskId);
		request.setParameter("submit", "Request");
		proxy = getActionProxy("/order/submitRequest");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		taskId = task(executionId);
		
		initServletMockObjects();
		request.addParameter("taskId", taskId);
		proxy = getActionProxy("/order/approve");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Integer orderId = orderAction.getOrder().getId();
		
		initServletMockObjects();
		request.setParameter("order.id", Integer.toString(orderId));
		request.setParameter("taskId", taskId);
		request.setParameter("submit", "Reject");
		proxy = getActionProxy("/order/submitApprove");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Order order = orderService.getByWfId(executionId);
		orderService.delete(order.getId());
	}
}
