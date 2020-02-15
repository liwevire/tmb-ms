package com.tmb.ms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH })
	private Customer customer;

	private String status;
	private String weight;
	private String comment;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "loan_id")
	private Set<Item> items = new HashSet<Item>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "loan_id")
	private Set<Activity> activities = new HashSet<Activity>();
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Customer))
			return false;
		Loan l = (Loan) o;
		return customer.equals(l.getCustomer())
				&& status.equalsIgnoreCase(l.getStatus()) 
				&& weight.equalsIgnoreCase(l.getWeight())
				&& comment.equalsIgnoreCase(l.getComment())
				&& items.equals(l.getItems())
				&& activities.equals(l.getActivities());
	}

	@Override
	public int hashCode() {
		int res = 4;
		res = 31 * res + (customer == null ? 0 : customer.hashCode());
		res = 31 * res + (items == null ? 0 : items.hashCode());
		res = 31 * res + (activities == null ? 0 : activities.hashCode());
		res = 31 * res + (status == null ? 0 : status.hashCode());
		res = 31 * res + (weight == null ? 0 : weight.hashCode());
		res = 31 * res + (comment == null ? 0 : comment.hashCode());
		return res;
	}
}