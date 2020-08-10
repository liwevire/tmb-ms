package com.tmb.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Activity;

@Repository
public interface ReportRepo extends JpaRepository<Activity, Long> {
	String Q_SUM_OF_AMOUNT_AGG= "select coalesce(sum(a.amount),0) from Loan l join l.activities a where l.status=:status and a.category=:category";
	@Query(Q_SUM_OF_AMOUNT_AGG)
	long getAmountAgg(@Param("status")String loanStatus, @Param("category")String activityCategory);
}