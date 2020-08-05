package com.tmb.ms.entity;

import java.util.Date;

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
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	private String name;
	@Column(name = "sec_name")
	private String secondaryName;
	private Date date = new Date();
	private String address;
	private String post;
	private String pin;
	private String phone;
	private String comment;

	public void assign(Customer c) {
		if (this.equals(c))
			return;
		if (StringUtils.isNotBlank(c.getName())) {
			name = c.getName();
		}
		if (StringUtils.isNotBlank(c.getSecondaryName())) {
			secondaryName = c.getSecondaryName();
		}
		if (StringUtils.isNotBlank(c.getAddress())) {
			address = c.getAddress();
		}
		if (StringUtils.isNotBlank(c.getPost())) {
			post = c.getPost();
		}
		if (c.getDate() != null) {
			date = c.getDate();
		}
		if (StringUtils.isNotBlank(c.getPin())) {
			pin = c.getPin();
		}
		if (StringUtils.isNotBlank(c.getPhone())) {
			phone = c.getPhone();
		}
		if (StringUtils.isNotBlank(c.getComment())) {
			phone = c.getComment();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Customer))
			return false;
		Customer c = (Customer) o;
		return name.equalsIgnoreCase(c.getName()) && secondaryName.equalsIgnoreCase(c.getSecondaryName())
				&& date.compareTo(c.getDate()) == 0 && address.equalsIgnoreCase(c.getAddress())
				&& post.equalsIgnoreCase(c.getPost()) && pin.equalsIgnoreCase(c.getPin())
				&& phone.equalsIgnoreCase(c.getPhone()) && comment.equalsIgnoreCase(c.getComment());
	}

	@Override
	public int hashCode() {
		int hash = 4;
		hash = 31 * hash + (name == null ? 0 : name.hashCode());
		hash = 31 * hash + (secondaryName == null ? 0 : secondaryName.hashCode());
		hash = 31 * hash + (date == null ? 0 : date.hashCode());
		hash = 31 * hash + (address == null ? 0 : address.hashCode());
		hash = 31 * hash + (post == null ? 0 : post.hashCode());
		hash = 31 * hash + (pin == null ? 0 : pin.hashCode());
		hash = 31 * hash + (phone == null ? 0 : phone.hashCode());
		hash = 31 * hash + (comment == null ? 0 : comment.hashCode());
		return hash;
	}

}
