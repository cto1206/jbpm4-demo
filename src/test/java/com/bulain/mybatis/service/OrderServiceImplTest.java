package com.bulain.mybatis.service;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public class OrderServiceImplTest extends ServiceTestCase {
	private OrderService orderService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_orders.xml");
		orderService = (OrderService) applicationContext.getBean("orderService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(OrderServiceImplTest.class);
	}

	public void testGet() {
		Order order = orderService.get(Integer.valueOf(102));
		assertNotNull(order);
		
		assertEquals("name_102", order.getName());
		assertEquals("note_102", order.getNote());
	}

	public void testInsert() {
		Order order = new Order();
		order.setName("firstName");
		order.setNote("lastName");
		orderService.insert(order);
	}

	public void testUpdate() {
		Order order = new Order();
		order.setId(Integer.valueOf(104));
		order.setName("firstName-updated");
		order.setNote("lastName-updated");
		orderService.update(order, true);
	}

	public void testDelete() {
		orderService.delete(Integer.valueOf(101));
	}

	public void testFind() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		List<Order> find = orderService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Long count = orderService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Page page = new Page();
		List<Order> listOrder = orderService.page(search, page );
		assertEquals(3, listOrder.size());
	}

	public void testGetByWfId(){
		Order byWfId = orderService.getByWfId("wf_id_wf");
		assertNotNull(byWfId);
		assertEquals("name_wf", byWfId.getName());
		assertEquals("note_wf", byWfId.getNote());
		assertEquals("wf_id_wf", byWfId.getWfId());
	}
}
