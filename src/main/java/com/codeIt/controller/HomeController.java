package com.codeIt.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeIt.entity.Student;
import com.codeIt.repo.StudentRepository;
import com.codeIt.service.StudentService;

@Controller
public class HomeController {
	
	@Autowired
	private StudentService service;
	
//	private AuthenticationProvider authenticationProvider;
//	
//	
//	private  AuthenticationManager authenticationManager;

	
//    public HomeController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
	
//	public HomeController(AuthenticationProvider AuthenticationProvider, AuthenticationProvider authenticationProvider) {
//		// TODO Auto-generated constructor stub
//		this.authenticationProvider=authenticationProvider;
//	}
    
   

	@Autowired
	StudentRepository repository;
	
	
	

	@GetMapping({"/","/home"})
	public String homePage() {
		return "home";
	}

	@GetMapping("/login1")
	public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
		  if (error != null) {
		        model.addAttribute("error", "Invalid Email or password..!");
		        return "loginform"; 
		  }
		    

		model.addAttribute("student", new Student());
		return "loginform";

	}
	
	// @PostMapping for handling custom login logic is unnecessary, as Spring Security manages this
	// But if you need to perform specific actions (such as saving login events), it can still be used.
	
//	public String login(@RequestParam("email") String email,
//	                    @RequestParam("password") String password,
//	                    Model model) {
//
//	    // Spring Security handles the login process. You don't need to manually authenticate.
//	    try {
//	        // Locate the student by email
//	        Student student = repository.findByEmail(email);
//	        if (student != null) {
//	            // Return the "dashboard" page upon successful login
//	            return "redirect:/dashboard?id=" + student.getId();
//	        } else {
//	            model.addAttribute("error", "User not found");
//	            model.addAttribute("student", new Student());
//	            return "loginform"; // Return to the login page
//	        }
//	    } catch (org.springframework.security.core.AuthenticationException e) {
//	        // Handle any errors that happen during authentication
//	        model.addAttribute("error", "Invalid email or password");
//	        model.addAttribute("student", new Student());
//	        return "loginform"; // Return to the login page if authentication fails
//	    }
//	}

//	
//	@PostMapping("/loginstudent")
//    public String login(@RequestParam("email") String email,
//                        @RequestParam("password") String password,
//                        Model model)
//	
//	{  System.out.println("Method called..");
//
//        try {
//
//            UsernamePasswordAuthenticationToken token =
//                    new UsernamePasswordAuthenticationToken(email, password);
//           Authentication auth=    authenticationProvider.authenticate(token);
//
//            // Set Authentication in SecurityContextHolder
//           SecurityContextHolder.getContext().setAuthentication(auth);
//
//            System.out.println("Authentication successful: " + auth.getAuthorities());
//            Student student = repository.findByEmail(email);
//            if (student != null) {
//                // Redirect to the dashboard with the student's ID as a parameter
//                return "redirect:/dashboard?id=" + student.getId();
//            } else {
//                model.addAttribute("error", "User not found");
//                return "loginform";
//            }
//            // Authentication successful
//            
//        } catch (org.springframework.security.core.AuthenticationException e) {
//            // Authentication failed
//            model.addAttribute("error", "Invalid email or password");
//            model.addAttribute("student", new Student());
//            return "loginform"; // Return to the login pag
//        }
//	}
//    

	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute("student", new Student());
		return "register";
	}

	@PostMapping("/registerstudent")
	public String save(Student student, Model model) {
		if(student.getCourse().equals("Full Stack Java Development"))
		{
		student.setCourseFees("50000.00");
		}
		
		else if(student.getCourse().equals("Data Science"))
		{
			student.setCourseFees("90000.00");
		}
		else if(student.getCourse().equals("Android Development"))
		{
			student.setCourseFees("70000.00");
		}
		else if(student.getCourse().equals("Python Development"))
		{
			student.setCourseFees("50000.00");
		}
		else if(student.getCourse().equals("Software Testing"))
		{
			student.setCourseFees("40000.00");
		}
		student.setStatus("Active");
		
		
		if (student.getName().equalsIgnoreCase("Payal") ||
		        student.getName().equalsIgnoreCase("Aadinath") ||
		        student.getName().equalsIgnoreCase("Priti") ||
		        student.getName().equalsIgnoreCase("Shruti")) {
		        student.setRole("ADMIN");
		    } else {
		        // If not, set the role as "USER"
		        student.setRole("ROLE_USER");
		    }
		repository.save(student);

		return "redirect:/";

	}
	
	
//	@GetMapping("/dashboard")
//	public String getStudentById(@RequestParam("id") Integer id, Model model) {
//	    // Retrieve the student by ID
//	    Student student = service.getStudentById(id);
//
//	    // Check if the student exists
//	    if (student == null) {
//	        // Log the issue and redirect or show an error page
//	        System.err.println("Student not found with ID: " + id);
//	        model.addAttribute("error", "Student not found with ID: " + id);
//	        return "redirect:error"; // Replace with the name of your error page template
//	    }
//
//	    // Add the student details to the model
//	    model.addAttribute("students", student); // Wrap the single student in a list for iteration
//	    model.addAttribute("Name", student.getName()); // Add the name for dynamic display in the welcome message
//	    
//	    return "dashboard";
//	}
//	
//	
//	@GetMapping("/dashboard")
//	public String getDashboardForUser(Model model, Principal principal) {
//	    // Use the username from the Principal to fetch student details
//	    String username = principal.getName();
//	   
//	    Student student = service.getStudentByUsername(username); // Ensure this method exists in your service
//
//	    if (student == null) {
//	        System.err.println("No student found for username: " + username);
//	        model.addAttribute("error", "Student not found for username: " + username);
//	        return "error"; // Redirect to an error page
//	    }
//
//	    model.addAttribute("students", student);
//	    model.addAttribute("Name", student.getName());
//
//	    return "dashboard";
//	}
	
//	@GetMapping("/dashboard")
//	public String getStudentById(@RequestParam(value = "id", required = false) Integer id, Model model) {
//	    if (id == null) {
//	        // Load default data for the dashboard, or show a generic view
//	        model.addAttribute("students", new ArrayList<>()); // Empty list or some default data
//	        model.addAttribute("Name", "Guest"); // Default message or data
//	        return "dashboard";
//	    }
//
//	    // Retrieve the student by ID
//	    Student student = service.getStudentById(id);
//
//	    // Check if the student exists
//	    if (student == null) {
//	        System.err.println("Student not found with ID: " + id);
//	        model.addAttribute("error", "Student not found with ID: " + id);
//	        return "error"; // Direct to an error view
//	    }
//
//	    // Add student details to the model
//	    model.addAttribute("students", student);
//	    model.addAttribute("Name", student.getName());
//
//	    return "dashboard";
//	}
	
	
	@GetMapping("/dashboard")
	public String getLoggedInStudentData(Model model, Principal principal) {
		System.out.println("Method Called");
	    // Fetch the username of the logged-in user from the Principal object
	    String username = principal.getName();

	    // Retrieve the full student details for the logged-in user
	    Student student = service.getStudentByUsername(username); // Ensure this method is implemented in your service

	    // Check if the student exists
	    if (student == null) {
	        System.err.println("No student found for username: " + username);
	        model.addAttribute("error", "Student not found for the logged-in user.");
	        return "error"; // Redirect to an error page
	    }

	    // Add all student details to the model
	    model.addAttribute("students", student);

	    // Render the dashboard view
	    return "dashboard";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
