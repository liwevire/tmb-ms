package com.tmb.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Misc;

@Repository
public interface MiscRepo extends JpaRepository<Misc, Long> {}