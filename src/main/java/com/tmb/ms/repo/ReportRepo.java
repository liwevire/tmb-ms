package com.tmb.ms.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Activity;

@Repository
public interface ReportRepo extends JpaRepository<Activity, Long> {
	String Q_SUM_OF_AMOUNT_AGG = "select coalesce(sum(a.amount),0) from Loan l join l.activities a "
			+ "where l.status=:status and a.category=:category";
	String Q_SUM_OF_AMOUNT_AGG_DATED = "select coalesce(sum(a.amount),0) from Loan l join l.activities a "
			+ "where l.status=:status and a.category=:category and "
			+ "date >= :startDate and date <= :endDate";
	String Q_COUNT = "select count(*) from Loan l where l.status=:status";
	String Q_COUNT_DATED = "select count(distinct l.id) from Loan l join l.activities a "
			+ "where l.status=:status and date >= :startDate and date <= :endDate";
	
	@Query(Q_SUM_OF_AMOUNT_AGG)
	long getAmountAgg(@Param("status") String loanStatus, @Param("category") String activityCategory);
	@Query(Q_SUM_OF_AMOUNT_AGG_DATED)
	long getAmountAggDated(@Param("status") String loanStatus, @Param("category") String activityCategory,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	@Query(Q_COUNT)
	long getLoanCount(@Param("status") String loanStatus);
	@Query(Q_COUNT_DATED)
	long getLoanCountDated(@Param("status") String loanStatus, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}