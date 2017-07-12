package org.zdp.domain;

/**
 * 水果类
 */
public class Fruit { 
	private String name; // 水果名称
	
	private float price; // 水果价格

	public Fruit() {

	}

	public Fruit(String name, float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
