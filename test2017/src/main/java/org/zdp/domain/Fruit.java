package org.zdp.domain;

/**
 * ˮ����
 */
public class Fruit { 
	private String name; // ˮ������
	
	private float price; // ˮ���۸�

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
