package com.tmb.ms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	@ManyToOne(cascade = {  CascadeType.PERSIST })
	private Customer customer;

	private String status;
	private String weight;
	private String comment;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "loan_id", nullable = false, insertable = false, updatable = false)
	private Set<Item> items;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "loan_id", nullable = false, insertable = false, updatable = false)
	private Set<Activity> activities;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Customer))
			return false;
		Loan l = (Loan) o;
		return customer.equals(l.getCustomer()) && status.equalsIgnoreCase(l.getStatus())
				&& weight.equalsIgnoreCase(l.getWeight()) && comment.equalsIgnoreCase(l.getComment())
				&& items.equals(l.getItems()) && activities.equals(l.getActivities());
	}

	@Override
	public int hashCode() {
		int hash = 4;
		hash = 31 * hash + (customer == null ? 0 : customer.hashCode());
		hash = 31 * hash + (items == null ? 0 : items.hashCode());
		hash = 31 * hash + (activities == null ? 0 : activities.hashCode());
		hash = 31 * hash + (status == null ? 0 : status.hashCode());
		hash = 31 * hash + (weight == null ? 0 : weight.hashCode());
		hash = 31 * hash + (comment == null ? 0 : comment.hashCode());
		return hash;
	}
}