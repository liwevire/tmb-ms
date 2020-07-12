package com.tmb.ms;

import java.util.HashSet;
import java.util.Set;

import com.tmb.ms.entity.Item;

public class Test {
	public static void main(String[] args) {
		
		Item i1 = new Item();
		i1.setId(5);
		i1.setLoanId(6);
		i1.setName("Chain");
		i1.setQuantity("2");
		
		Item i2 = new Item();
		i2.setLoanId(6);
		i2.setName("Chain");
		i2.setQuantity("2");
		Set<Item> items = new HashSet<Item>();
		
		items.add(i1);
		for (Item item : items) {
			System.out.println(item.toString());
		}
		items.add(i2);
		for (Item item : items) {
			System.out.println(item.toString());
		}
		items.stream().filter(i -> i.getId() == 5);
	}
}