package com.codeIt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeIt.entity.Student;
import com.codeIt.repo.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;

	public Student getStudentById(Integer id) {
		Student student = null;
		Optional<Student> ss = repository.findById(id);

		if (ss.isPresent()) {

			student = ss.get();
		}
		return student;

	}

	public Student getStudentByUsername(String username) {
		return repository.findByEmail(username);
		
	}
	
}
