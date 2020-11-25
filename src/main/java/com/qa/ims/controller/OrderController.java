package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.CommandLineTable;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
			CommandLineTable clt = new CommandLineTable();
			clt.setHeaders("ID", "NAME", "VALUE");
			order.toRow(clt);
			clt.print();
			LOGGER.info(" ");
		}
		
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		boolean validID = false;
		Order order;
		do {
			LOGGER.info("Please enter a customer id");
			Long customer_id = utils.getLong();
			order = orderDAO.create(new Order(customer_id));
			if(order != null){
				validID = true;
			}
		}while(!validID);
		
		boolean fin = false;
		do {
			LOGGER.info("Please enter the Item id to add into the order");
			LOGGER.info("Enter q to stop adding items");
			Long item_id = utils.getOrderItemAction();
			if(item_id == null) {
				fin = true;
			}else {
				orderDAO.createOrderItems(order, item_id);
			}
			
		} while(!fin);
		
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		boolean validOrderID = false;
		Long id;
		Order order;
		do {
			LOGGER.info("Please enter the id of the order you would like to update");
			id = utils.getLong();
			
			Order checkOrder = orderDAO.readOrder(id);
			if(checkOrder != null){
				validOrderID = true;
			}else {
				LOGGER.info("This order id does not exist!");
			}
		}while(!validOrderID);
		
		boolean validCustomerID = false;
		do {
			LOGGER.info("Please enter the new customer id");
			Long customer_id = utils.getLong();
			order = orderDAO.update(new Order(id, customer_id));
			if(order != null){
				validCustomerID = true;
			}
		}while(!validCustomerID);
		
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
