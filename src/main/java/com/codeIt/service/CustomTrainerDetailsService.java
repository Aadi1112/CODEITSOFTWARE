package com.codeIt.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeIt.entity.Trainer;
import com.codeIt.repo.TrainerRepository;

@Service
public class CustomTrainerDetailsService implements UserDetailsService {

	@Autowired
	  TrainerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		Trainer trainer= repository.findByEmail(username);
		
		if(trainer==null)
		{
			throw new UsernameNotFoundException(username);
		}
		
		return User.builder()
                .username(trainer.getEmail())  // Using email as username
                .password(trainer.getPassword()) // Password stored in the Trainer entity
                .roles(trainer.getRole()) // Role of the trainer (like "TRAINER" or "ADMIN")
                .build();
	}

}
