package com.tmb.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}