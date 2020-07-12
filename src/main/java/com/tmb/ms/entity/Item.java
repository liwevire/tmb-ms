package com.tmb.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	@Column(name = "loan_id", nullable = false)
	private long loanId;
	private String name;
	private String quantity;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Item))
			return false;
		Item i = (Item) o;
		return StringUtils.equals(name, i.getName()) && StringUtils.equals(quantity, i.getQuantity());
	}

	@Override
	public int hashCode() {
		int hash = 6;
		hash = 31 * hash + (name == null ? 0 : name.hashCode());
		hash = 31 * hash + (quantity == null ? 0 : quantity.hashCode());
		return hash;
	}

	public Item(long id, long loanId, String name, String quantity) {
		super();
		this.id = id;
		this.loanId = loanId;
		this.name = name;
		this.quantity = quantity;
	}

	public Item() {
		super();
	}
}
