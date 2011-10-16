package com.bulain.mybatis.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.bulain.mybatis.model.Order;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@DataSet(file="test-data/init_orders.xml")
public class OrderActionTest extends ActionTestCase {
    @Before
	public void setUp() throws Exception {
	    super.setUp();
	}
	
    @After
	public void tearDown() throws Exception {
		super.tearDown();
	}

    @Test
	public void testList() throws Exception {
		initServletMockObjects();
		request.setParameter("search.name", "name_page");
		
		ActionProxy proxy = getActionProxy("/order/list");
		OrderAction orderAction = (OrderAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		List<Order> listOrder = orderAction.getListOrder();
		assertEquals(3, listOrder.size());
		Page page = orderAction.getPage();
		assertEquals(1, page.getPage());
	}

    @Test
	public void testNewn() throws Exception {
		initServletMockObjects();
		
		ActionProxy proxy = getActionProxy("/order/new");
		OrderAction orderAction = (OrderAction)proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Order order = orderAction.getOrder();
		assertNotNull(order);
	}

    @Test
	public void testCreate() throws Exception {
		initServletMockObjects();
		request.setParameter("order.name", "name");
		request.setParameter("order.note", "note");
		
		ActionProxy proxy = getActionProxy("/order/create");
		proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

    @Test
	public void testShow() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "102");
		
		ActionProxy proxy = getActionProxy("/order/show");
		OrderAction orderAction = (OrderAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Order order = orderAction.getOrder();
		assertNotNull(order);
		assertEquals("name_102", order.getName());
		assertEquals("note_102", order.getNote());
	}

    @Test
	public void testEdit() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "103");
		
		ActionProxy proxy = getActionProxy("/order/edit");
		OrderAction orderAction = (OrderAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		Order order = orderAction.getOrder();
		assertNotNull(order);
		assertEquals("name_103", order.getName());
		assertEquals("note_103", order.getNote());
	}

    @Test
	public void testUpdate() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "103");
		request.setParameter("order.name", "name_103-updated");
		request.setParameter("order.note", "note_103-updated");
		
		ActionProxy proxy = getActionProxy("/order/update");
		proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

    @Test
	public void testDestroy() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "101");
		
		ActionProxy proxy = getActionProxy("/order/destroy");
		proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

}
