package com.qa.ims.persistence.domain;

import java.util.ArrayList;

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
		this.items = items;
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

//	public String getSurname() {
//		return surname;
//	}
//
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}

	@Override
	public String toString() {
		return "id:" + id + " customer id:" + customer_id;
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
