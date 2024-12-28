package com.codeIt.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeIt.entity.Student;
import com.codeIt.entity.Trainer;
import com.codeIt.repo.StudentRepository;
import com.codeIt.repo.TrainerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	StudentRepository repository;
	
	@Autowired
	TrainerRepository trainerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Student student = repository.findByEmail(username);
        if (student != null) {
            return new User(student.getEmail(), student.getPassword(), 
                            Collections.singletonList(new SimpleGrantedAuthority(student.getRole())));
        }

        Trainer trainer = trainerRepository.findByEmail(username);
        if(trainer!=null)
        {
        	 return new User(trainer.getEmail(), trainer.getPassword(), 
                     Collections.singletonList(new SimpleGrantedAuthority(trainer.getRole())));
        }
     

        throw new UsernameNotFoundException("User not found");
		
		
		
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(student.getRole());
//
//		return new User(student.getEmail(), student.getPassword(),
//
//				Collections.singletonList(authority));
		
	}

}
