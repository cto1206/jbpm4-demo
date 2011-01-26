package com.bulain.mybatis.test;

import java.util.List;

import org.jbpm.api.Deployment;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;

import com.bulain.common.test.Struts2TestCase;

public abstract class JbpmTestCase extends Struts2TestCase {
	
	protected RepositoryService repositoryService;
	protected ExecutionService executionService;
	protected TaskService taskService;
	protected HistoryService historyService;
	protected IdentityService identityService;
	protected ManagementService managementService;
	
	protected void setUpJbpm() throws Exception {
		repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
	    executionService = (ExecutionService) applicationContext.getBean("executionService");
	    taskService = (TaskService) applicationContext.getBean("taskService");
	    historyService = (HistoryService) applicationContext.getBean("historyService");
	    identityService = (IdentityService) applicationContext.getBean("identityService");
	    managementService = (ManagementService) applicationContext.getBean("managementService");
	}
	
	protected void setUpCleanJbpm() throws Exception {
		if(repositoryService==null) setUpJbpm();
		List<Deployment> listDeployment = repositoryService.createDeploymentQuery().list();
	    for(Deployment deployment : listDeployment){
	    	repositoryService.deleteDeploymentCascade(deployment.getId());
	    }
	}
	
	protected void tearDownJbpm() throws Exception {}
}
