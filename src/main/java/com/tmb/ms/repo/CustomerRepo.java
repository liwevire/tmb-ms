package com.tmb.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
}