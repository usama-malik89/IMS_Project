package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.core.appender.mom.jeromq.JeroMqAppender;

import com.qa.ims.utils.CommandLineTable;

public class Item {

	private Long id;
	private String name;
	private double value;
	private Integer quantity;

	public Item(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public Item(String name, double value, Integer quantity) {
		this.name = name;
		this.value = value;
		this.quantity = quantity;
	}

	public Item(Long id, String name, double value, Integer quantity) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "item_id:" + id + " name:" + name + " value:" + value;
	}
	
	public CommandLineTable toRow(CommandLineTable clt) {
		clt.addRow(Long.toString(id), name, Double.toString(value));
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
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value != other.value)
			return false;
		return true;
	}



}
