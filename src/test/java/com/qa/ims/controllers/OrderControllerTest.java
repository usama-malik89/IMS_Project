package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final long CUSTOMER_ID = 1L;
		final Order created = new Order(CUSTOMER_ID);

		Mockito.when(utils.getLong()).thenReturn(CUSTOMER_ID);
		Mockito.when(dao.create(created)).thenReturn(created);
		Mockito.when(utils.getOrderItemAction()).thenReturn(null);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getOrderItemAction();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<Item> items = new ArrayList<>();
		orders.add(new Order(1L, 1L, items));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, 1L);

		Mockito.when(this.utils.getLong()).thenReturn(updated.getCustomerId());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);
		Mockito.when(this.dao.readOrder(1L)).thenReturn(updated);
		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
