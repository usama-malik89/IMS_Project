package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.persistence.domain.Item;
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
	private ItemDAO itemDAO;
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
			clt.setHeaders("ID", "NAME", "QUANTITY", "VALUE");
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
				LOGGER.info("Enter the quantity of items to add into the order");
				int quantity = utils.getInt();
				orderDAO.createOrderItems(order, item_id, quantity);
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
			
			order = orderDAO.readOrder(id);
			if(order != null){
				validOrderID = true;
			}else {
				LOGGER.info("This order id does not exist!");
			}
		}while(!validOrderID);
		
		boolean showOptions = true;
		do {
			LOGGER.info("What would you like to update?");
			LOGGER.info("ADD: To add an item to an order");
			LOGGER.info("REMOVE: To remove an item from an order");
			LOGGER.info("RETURN: To return to previous selection");
			
			boolean stop = false;
			do {
				String option = utils.getString().toLowerCase();
				switch (option) {
				case "add":
					boolean validItemID = false;
					do {
						LOGGER.info("Please enter the Item id to add into the order");
						LOGGER.info("Enter q to exit without adding items");
						Long item_id = utils.getOrderItemAction();
						if(item_id == null) {
							stop = true;
							break;
						}
						LOGGER.info("Enter the quantity of items to add into the order");
						int quantity = utils.getInt();
						order = orderDAO.createOrderItems(order, item_id, quantity);
						
						if(order != null) {
							LOGGER.info("Item added to order!");
							validItemID = true;
							stop = true;
						}
					}while(!validItemID);
					break;
				case "remove":
					boolean validItmID = false;
					do {
						LOGGER.info("Please enter the Item id to remove from the order");
						LOGGER.info("Enter q to exit without removing items");
						Long item_id = utils.getOrderItemAction();
						if(item_id == null) {
							stop = true;
							break;
						}
						order = orderDAO.removeOrderItems(order, item_id);
						
						if(order != null) {
							LOGGER.info("Item removed from order!");
							validItmID = true;
							stop = true;
						}
					}while(!validItmID);
					break;
				case "return":
					stop = true;
					showOptions = false;
					break;
				default:
					LOGGER.info("That's not an option! Please try again");
					break;
				}
			} while (!stop);
		} while (showOptions);
		
		
		
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
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
