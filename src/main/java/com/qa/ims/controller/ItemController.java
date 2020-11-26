package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.CommandLineTable;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		CommandLineTable clt = new CommandLineTable();
		clt.setHeaders("ID", "NAME", "VALUE");
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			item.toRow(clt);
		}
		clt.print();
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter a name");
		String name = utils.getString();
		LOGGER.info("Please enter a value");
		double value = utils.getDouble();
		Item item = itemDAO.create(new Item(name, value));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		
		boolean valid = false;
		Long id;
		do {
			LOGGER.info("Please enter the id of the item you would like to update");
			id = utils.getLong();
			Item checkItem = itemDAO.readItem(id);
			if(checkItem != null){
				valid = true;
			}else {
				LOGGER.info("This order id does not exist!");
			}
		}while(!valid);
		
		LOGGER.info("Please enter a name");
		String name = utils.getString();
		LOGGER.info("Please enter a value");
		double value = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, value, null));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}
