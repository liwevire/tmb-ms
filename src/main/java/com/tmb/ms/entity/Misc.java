package com.tmb.ms.entity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
@Table(name = "misc")
public class Misc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id = 1;
	@Column(name = "gold_rate")
	private long goldRate;
	private Date date = new Date();

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Misc))
			return false;
		Misc a = (Misc) o;
		return date.compareTo(a.getDate()) == 0 && goldRate == a.getGoldRate();
	}

	@Override
	public int hashCode() {
		int hash = 6;
		hash = 31 * hash + (date == null ? 0 : date.hashCode());
		long am = Double.doubleToLongBits(goldRate);
		hash = 31 * hash + (int) (am ^ (am >>> 32));
		return hash;
	}

	public long compareTo(Date date) {
		return ChronoUnit.HALF_DAYS.between(Instant.ofEpochMilli(this.getDate().getTime()),
				Instant.ofEpochMilli(date.getTime()));
	}
}