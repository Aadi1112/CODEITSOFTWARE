package com.codeIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeIt.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

	
	Trainer findByEmail(String email);
}
