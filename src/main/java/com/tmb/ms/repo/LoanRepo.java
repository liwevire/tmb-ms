package com.tmb.ms.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Customer;
import com.tmb.ms.entity.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {
	Set<Loan> findByCustomer(Customer customer);
}