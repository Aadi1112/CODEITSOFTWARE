package com.codeIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeIt.entity.Student;
import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Integer>{

	
	Student findByEmail(String email);
	
    //Optional<Student> findByName(String username); // Ensure 'username' matches your database column name

}
