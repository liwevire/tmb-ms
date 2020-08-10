package com.tmb.ms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.tmb.ms.util.TmbMsConstant;

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
	private String category;
	private Date date = new Date();

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Activity))
			return false;
		Activity a = (Activity) o;
		return date.compareTo(a.getDate()) == 0 && StringUtils.equals(category, a.getCategory()) && amount == a.getAmount();
	}

	@Override
	public int hashCode() {
		int hash = 6;
		hash = 31 * hash + (date == null ? 0 : date.hashCode());
		hash = 31 * hash + (category == null ? 0 : category.hashCode());
		long am = Double.doubleToLongBits(amount);
		hash = 31 * hash + (int) (am ^ (am >>> 32));
		return hash;
	}

	public int compareTo(Activity a) {
		if (this.getDate().compareTo(a.getDate()) != 0)
			return this.getDate().compareTo(a.getDate());
		if (this.getCategory().equalsIgnoreCase(a.getCategory()))
			return 0;
		if (this.getCategory().equalsIgnoreCase(TmbMsConstant.ACT_CAT_PRINCIPAL))
			return -1;
		if (this.getCategory().equalsIgnoreCase(TmbMsConstant.ACT_CAT_IPAYMENT))
			return 1;
		if (this.getCategory().equalsIgnoreCase(TmbMsConstant.ACT_CAT_APPRSRFEE))
			return 1;
		return 0;
	}
}