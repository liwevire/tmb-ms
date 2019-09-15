package com.tmb.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
}