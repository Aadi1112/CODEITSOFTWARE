package com.codeIt.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codeIt.entity.Student;
import com.codeIt.entity.Trainer;
import com.codeIt.repo.StudentRepository;
import com.codeIt.repo.TrainerRepository;

@Controller
public class TrainerController {

	@Autowired
	TrainerRepository trainerRepository;
	
	
	@Autowired
	StudentRepository studentRepository;
	@PostMapping("/updatestudent")
	public String updateStudent(@ModelAttribute("student") Student student, Model model) {
	    Optional<Student> existingStudentOpt = studentRepository.findById(student.getId());
	    if (existingStudentOpt.isEmpty()) {
	        model.addAttribute("error", "Student does not exist for update!");
	        return "error_page";
	    }

	    Student existingStudent = existingStudentOpt.get();
	    
	    // Preserve values for fields not present in the form
	    student.setPassword(existingStudent.getPassword());
	    student.setRole(existingStudent.getRole());
	    student.setStatus(existingStudent.getStatus());
	    
	    studentRepository.save(student);
	    return "redirect:/trainerdashboard";
	}
	
	
	@GetMapping("/update")
	public String loadUpdateForm(@RequestParam("id") Integer id,Model model)
	{
		Optional<Student> op= studentRepository.findById(id);
		if(op.isPresent())
		{
			Student ss=op.get();
			model.addAttribute("student", ss);
		}
		return "update_student";
	}
	
	
	@GetMapping("/delete")
	public String deleteById(@RequestParam("id") Integer id,Model model)
	{
		studentRepository.deleteById(id);
		model.addAttribute("students", studentRepository.findAll());
		
		return "trainerdashboard";
		
	}
	
	
	@GetMapping("/trainerdashboard")
	public String goToTrainerDashBoard(Model model,Principal principal) {
		
		String name= principal.getName();
		model.addAttribute("students", studentRepository.findAll());
		model.addAttribute("Name", name);
		
		return "trainerdashboard";
	}
	
	@GetMapping("/trainerlogin")
	public String loginTrainer(@RequestParam(value = "error",required = false) String error, Model model) {
	
		 if (error != null) {
		        model.addAttribute("error", "Invalid Email or password..!");
		        return "trainer_login"; 
		  }
		
		model.addAttribute("trainer", new Trainer());
		
		return "trainer_login";
	}


}
