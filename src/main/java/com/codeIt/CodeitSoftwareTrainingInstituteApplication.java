package com.codeIt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codeIt.entity.Student;
import com.codeIt.repo.StudentRepository;

@SpringBootApplication
public class CodeitSoftwareTrainingInstituteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodeitSoftwareTrainingInstituteApplication.class, args);
	}
	
	@Autowired
	StudentRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		Student student=new Student();
//		student.setName("Ram");.
//		student.setCourse("JAVA FULL STACK");
//		student.setAddress("Pune");
//		student.setEmail("aadi@gmail.com");
//		student.setGender("Male");
//		student.setStatus("Active");
//	
//		student.setPassword("1234");
//		student.setPhone("9022154508");
//		
//		repository.save(student);
		
	}

}
