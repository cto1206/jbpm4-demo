package com.bulain.mybatis.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

@DataSet(file="test-data/init_orders.xml")
public class OrderServiceImplTest extends ServiceTestCase {
    @Autowired
	private OrderService orderService;
	
    @Test
	public void testGet() {
		Order order = orderService.get(Long.valueOf(102));
		assertNotNull(order);
		
		assertEquals("name_102", order.getName());
		assertEquals("note_102", order.getNote());
	}

    @Test
	public void testInsert() {
		Order order = new Order();
		order.setName("firstName");
		order.setNote("lastName");
		orderService.insert(order);
	}

    @Test
	public void testUpdate() {
		Order order = new Order();
		order.setId(Long.valueOf(104));
		order.setName("firstName-updated");
		order.setNote("lastName-updated");
		orderService.update(order, true);
	}

    @Test
	public void testDelete() {
		orderService.delete(Long.valueOf(101));
	}

    @Test
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
