package com.bulain.mybatis.dao;

import java.util.List;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public class OrderMapperTest extends ServiceTestCase {
	private OrderMapper orderMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_orders.xml");
		orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(OrderMapperTest.class);
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = orderMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		Order record = new Order();
		record.setName("name");
		record.setNote("note");
		int insert = orderMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		Order record = new Order();
		record.setName("name");
		record.setNote("note");
		int insert = orderMapper.insertSelective(record);
		assertEquals(1, insert);
	}

	public void testSelectByPrimaryKey() {
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertNotNull(selectByPrimaryKey);
		
		assertEquals("name_102", selectByPrimaryKey.getName());
		assertEquals("note_102", selectByPrimaryKey.getNote());
	}

	public void testUpdateByPrimaryKeySelective() {
		Order record = new Order();
		record.setId(Integer.valueOf(103));
		record.setName("name-updated");
		record.setNote("note-updated");
		int updateByPrimaryKeySelective = orderMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		Order record = new Order();
		record.setId(Integer.valueOf(104));
		record.setName("name-updated");
		record.setNote("note-updated");
		int updateByPrimaryKey = orderMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	public void testFind(){
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		List<Order> find = orderMapper.find(search);
		assertEquals(3, find.size());
	}
	
	public void testShoudExecuteWhenNoParam(){
		OrderSearch search = new OrderSearch();
		List<Order> find = orderMapper.find(search);
		assertEquals(8, find.size());
	}
	
	
	public void testShoudExecuteWhenFirstNameIsNullStr(){
		OrderSearch search = new OrderSearch();
		search.setName("");
		List<Order> find = orderMapper.find(search);
		assertEquals(8, find.size());
	}
	
	public void testCount(){
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Long count = orderMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}
	
	public void testPage(){
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		search.setLow(0);
		search.setHigh(20);
		List<Order> listOrder = orderMapper.page(search);
		assertEquals(3, listOrder.size());
	}

	public void testGetByWfId(){
		Order byWfId = orderMapper.getByWfId("wf_id_wf");
		assertNotNull(byWfId);
		assertEquals("name_wf", byWfId.getName());
		assertEquals("note_wf", byWfId.getNote());
		assertEquals("wf_id_wf", byWfId.getWfId());
	}
}
