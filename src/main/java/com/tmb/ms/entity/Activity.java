package com.tmb.ms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "activity")
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	@Column(name = "loan_id", nullable = false)
	private long loanId;
	private double amount;
	private int category;
	private Date date = new Date();

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Activity))
			return false;
		Activity a = (Activity) o;
		return date.compareTo(a.getDate()) == 0 && category == a.getCategory() && amount == a.getAmount();
	}

	@Override
	public int hashCode() {
		int res = 6;
		res = 31 * res + (date == null ? 0 : date.hashCode());
		res = 31 * res + category;
		long am = Double.doubleToLongBits(amount);
		res = 31 * res + (int) (am ^ (am >>> 32));
		return res;
	}
}