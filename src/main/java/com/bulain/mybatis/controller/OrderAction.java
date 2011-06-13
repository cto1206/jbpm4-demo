package com.bulain.mybatis.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;
import com.bulain.mybatis.service.OrderService;

public class OrderAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -1592145738956326742L;
    private static final Logger LOG = Logger.getLogger(OrderAction.class);

    private Integer id;
    private String taskId;
    private String submit;

    private OrderSearch search;
    private Order order;
    private List<Order> listOrder;

    private transient OrderService orderService;
    private transient TaskService taskService;

    public String list() {
        search = (OrderSearch) getSearchFromSession(OrderSearch.class, search);
        page = getPageFromSession();

        listOrder = orderService.page(search, page);

        putSearchToSession(OrderSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        order = new Order();
        return SUCCESS;
    }
    public String create() {
        try {
            orderService.insert(order);
            String msg = getText("common.createInfo", new String[]{"Order"});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("common.createError", new String[]{"Order"});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String show() {
        order = orderService.get(id);
        return SUCCESS;
    }
    public String edit() {
        order = orderService.get(id);
        return SUCCESS;
    }
    public String update() {
        try {
            orderService.update(order, false);
            String msg = getText("common.updateInfo", new String[]{"Order"});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("common.updateError", new String[]{"Order"});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        try {
            orderService.delete(id);
            String msg = getText("common.deleteInfo", new String[]{"Order"});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("common.deleteError", new String[]{"Order"});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }

    // workflow
    public String request() {
        Task task = taskService.getTask(taskId);
        String executionId = task.getExecutionId();
        order = orderService.getByWfId(executionId);

        if (order == null) {
            order = new Order();
            order.setWfId(executionId);
        }

        return SUCCESS;
    }

    public String submitRequest() {
        order.setWfStatus("Requested");
        if (order.getId() == null) {
            orderService.insert(order);
        } else {
            orderService.update(order, false);
        }
        if ("Request".equals(submit)) {
            taskService.completeTask(taskId);
        }

        return SUCCESS;
    }

    public String approve() {
        Task task = taskService.getTask(taskId);
        String executionId = task.getExecutionId();
        order = orderService.getByWfId(executionId);

        return SUCCESS;
    }

    public String submitApprove() {
        order.setWfStatus("Approve".equals(submit) ? "Approved" : "Rejected");
        orderService.update(order, false);

        taskService.completeTask(taskId, "Approve".equals(submit) ? "approve" : "reject");
        return SUCCESS;
    }

    public void prepareUpdate() {
        if (id != null) {
            order = orderService.get(id);
        }
    }

    public void prepareSubmitRequest() {
        if (order != null && order.getId() != null) {
            order = orderService.get(order.getId());
        }
    }

    public void prepareSubmitApprove() {
        if (order != null && order.getId() != null) {
            order = orderService.get(order.getId());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderSearch getSearch() {
        return search;
    }

    public void setSearch(OrderSearch search) {
        this.search = search;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    public String getSubmit() {
        return submit;
    }
    public void setSubmit(String submit) {
        this.submit = submit;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
