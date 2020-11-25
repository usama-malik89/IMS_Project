package com.qa.ims.persistence.domain;

import java.util.ArrayList;

import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import com.qa.ims.utils.CommandLineTable;

public class Order {

	private Long id;
	private Long customer_id;
	private ArrayList<Item> items;


	public Order(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Order(Long id, Long customer_id, ArrayList<Item> items) {
		this.id = id;
		this.customer_id = customer_id;
		this.items = items;
	}
	
	public Order(Long id, Long customer_id) {
		this.id = id;
		this.customer_id = customer_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customer_id;
	}

	public void setCustomerId(Long customer_id) {
		this.customer_id = customer_id;
	}

	public ArrayList<Item> getItems(){
		return items;
	}
	
	public void setItems(ArrayList<Item> items){
		this.items = items;
	}

	@Override
	public String toString() {
		String result = "order_id:" + id + " customer_id:" + customer_id;
		return result;
	}
	
	public CommandLineTable toRow(CommandLineTable clt) {
		for(Item item : items) {
			clt.addRow(Long.toString(item.getId()), item.getName(), Double.toString(item.getValue()));
		}
		return clt;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (customer_id != other.customer_id)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
