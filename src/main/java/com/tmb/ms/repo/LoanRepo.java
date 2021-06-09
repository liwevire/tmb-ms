package com.tmb.ms.repo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Customer;
import com.tmb.ms.entity.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {
	String Q_LOAN_DATED = "select l "
			+ "from Loan l join l.activities a where a.date >= :startDate and a.date <= :endDate";

	@Query(Q_LOAN_DATED)
	Set<Loan> getLoanDated(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	List<Loan> findByCustomer(Customer customer);
	List<Loan> findByStatus(String status);
}