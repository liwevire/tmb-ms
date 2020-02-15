package com.tmb.ms.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
	Set<Item> findByLoanId(long id);
}