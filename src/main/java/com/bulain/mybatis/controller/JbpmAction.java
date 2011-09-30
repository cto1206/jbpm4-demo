package com.bulain.mybatis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.springframework.core.io.ClassPathResource;

public class JbpmAction extends DefaultActionSupport {
    private static final long serialVersionUID = 2150996113887310905L;
    private static final Logger LOG = Logger.getLogger(JbpmAction.class);

    private List<ProcessDefinition> listProcessDefinition;
    private List<ProcessInstance> listProcessInstance;
    private List<Task> listPersonTask;
    private List<Task> listGroupTask;
    private List<HistoryTask> listHistoryTask;
    private List<HistoryActivityInstance> listHistoryActivityInstance;
    private List<HistoryProcessInstance> listHistoryProcessInstance;

    private String processDefinitionId;
    private String executionId;
    private String deploymentId;

    private transient RepositoryService repositoryService;
    private transient ExecutionService executionService;
    private transient TaskService taskService;
    private transient HistoryService historyService;

    public String list() {
        listProcessDefinition = repositoryService.createProcessDefinitionQuery().list();
        listProcessInstance = executionService.createProcessInstanceQuery().list();
        listPersonTask = taskService.findPersonalTasks("bulain");
        listGroupTask = taskService.findGroupTasks("bulain");
        listHistoryProcessInstance = historyService.createHistoryProcessInstanceQuery().list();

        return SUCCESS;
    }

    public String deploy() {
        ClassPathResource classPathResource = new ClassPathResource("jpdl/order.zip");
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(classPathResource.getInputStream());
        } catch (IOException e) {
            LOG.error("deploy()", e);
        }
        repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();

        return SUCCESS;
    }

    public String destroy() {
        repositoryService.deleteDeploymentCascade(deploymentId);
        return SUCCESS;
    }

    public String start() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "bulain");
        executionService.startProcessInstanceById(processDefinitionId, variables);

        return SUCCESS;
    }

    public String view() {
        listHistoryTask = historyService.createHistoryTaskQuery().executionId(executionId).list();
        listHistoryActivityInstance = historyService.createHistoryActivityInstanceQuery().executionId(executionId)
                .list();

        return SUCCESS;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }
    public String getExecutionId() {
        return executionId;
    }
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }
    public String getDeploymentId() {
        return deploymentId;
    }
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
    public List<ProcessDefinition> getListProcessDefinition() {
        return listProcessDefinition;
    }
    public void setListProcessDefinition(List<ProcessDefinition> listProcessDefinition) {
        this.listProcessDefinition = listProcessDefinition;
    }
    public List<ProcessInstance> getListProcessInstance() {
        return listProcessInstance;
    }
    public void setListProcessInstance(List<ProcessInstance> listProcessInstance) {
        this.listProcessInstance = listProcessInstance;
    }
    public List<Task> getListPersonTask() {
        return listPersonTask;
    }
    public void setListPersonTask(List<Task> listPersonTask) {
        this.listPersonTask = listPersonTask;
    }
    public List<Task> getListGroupTask() {
        return listGroupTask;
    }
    public void setListGroupTask(List<Task> listGroupTask) {
        this.listGroupTask = listGroupTask;
    }
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    public void setExecutionService(ExecutionService executionService) {
        this.executionService = executionService;
    }
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }
    public List<HistoryTask> getListHistoryTask() {
        return listHistoryTask;
    }
    public void setListHistoryTask(List<HistoryTask> listHistoryTask) {
        this.listHistoryTask = listHistoryTask;
    }
    public List<HistoryActivityInstance> getListHistoryActivityInstance() {
        return listHistoryActivityInstance;
    }
    public void setListHistoryActivityInstance(List<HistoryActivityInstance> listHistoryActivityInstance) {
        this.listHistoryActivityInstance = listHistoryActivityInstance;
    }
    public List<HistoryProcessInstance> getListHistoryProcessInstance() {
        return listHistoryProcessInstance;
    }
    public void setListHistoryProcessInstance(List<HistoryProcessInstance> listHistoryProcessInstance) {
        this.listHistoryProcessInstance = listHistoryProcessInstance;
    }

}
