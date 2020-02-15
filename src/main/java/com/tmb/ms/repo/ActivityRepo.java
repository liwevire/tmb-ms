package com.tmb.ms.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmb.ms.entity.Activity;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
	Set<Activity> findByLoanId(long id);
}