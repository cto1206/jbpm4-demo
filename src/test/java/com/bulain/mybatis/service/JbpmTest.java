package com.bulain.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.bulain.common.test.JbpmTestCase;

public class JbpmTest extends JbpmTestCase {
	private String deploymentId;
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(JbpmTest.class);
	}	  
	
	protected void setUp() throws Exception {
	    super.setUp();
	    super.setUpJbpm();
	    deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("jpdl/order.jpdl.xml")
	        .deploy();
	}
	
	protected void tearDown() throws Exception {
	    repositoryService.deleteDeploymentCascade(deploymentId);
	    super.tearDownJbpm();
	    super.tearDown();
	}
	
	public void testOrderApprove() {
	    Map<String, Object> variables = new HashMap<String, Object>(); 
	    variables.put("owner", "johndoe");
	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order", variables);
	    String pid = processInstance.getId();

	    List<Task> taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(1, taskList.size());
	    Task task = taskList.get(0);
	    assertEquals("request", task.getName());
	    assertEquals("johndoe", task.getAssignee());

	    taskService.completeTask(task.getId());
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(0, taskList.size());
	    
	    taskList = taskService.findPersonalTasks("bulain");
	    assertEquals(1, taskList.size());

	    task = taskList.get(0);
	    assertEquals("approve", task.getName());
	    assertEquals("bulain", task.getAssignee());

	    taskService.completeTask(task.getId(), "approve");
	    
	    processInstance = executionService.findProcessInstanceById(pid);
	    assertNull(processInstance);
	  }
	
	public void testOrderReject() {
	    Map<String, Object> variables = new HashMap<String, Object>(); 
	    variables.put("owner", "johndoe");
	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order", variables);
	    String pid = processInstance.getId();

	    List<Task> taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(1, taskList.size());
	    Task task = taskList.get(0);
	    assertEquals("request", task.getName());
	    assertEquals("johndoe", task.getAssignee());

	    taskService.completeTask(task.getId());
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(0, taskList.size());
	    
	    taskList = taskService.findPersonalTasks("bulain");
	    assertEquals(1, taskList.size());

	    task = taskList.get(0);
	    assertEquals("approve", task.getName());
	    assertEquals("bulain", task.getAssignee());

	    taskService.completeTask(task.getId(), "reject");
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(1, taskList.size());
	    task = taskList.get(0);
	    assertEquals("request", task.getName());
	    assertEquals("johndoe", task.getAssignee());
	    
	    taskService.completeTask(task.getId());
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(0, taskList.size());
	    
	    taskList = taskService.findPersonalTasks("bulain");
	    assertEquals(1, taskList.size());

	    task = taskList.get(0);
	    assertEquals("approve", task.getName());
	    assertEquals("bulain", task.getAssignee());

	    taskService.completeTask(task.getId(), "approve");
	    
	    processInstance = executionService.findProcessInstanceById(pid);
	    assertNull(processInstance);
	  }
}
