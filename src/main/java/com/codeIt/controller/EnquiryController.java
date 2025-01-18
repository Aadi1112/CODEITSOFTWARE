package com.codeIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codeIt.entity.Enquiry;
import com.codeIt.entity.Student;
import com.codeIt.repo.EnquiryRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	EnquiryRepo repo;

	@PostMapping("/registerenquiry")
	public String save(@Validated @ModelAttribute("Enquiry") Enquiry enquiry, BindingResult result, Model model, HttpSession session) {
	    if (result.hasErrors()) {
	        // Add an attribute to indicate the modal should remain open
	        model.addAttribute("modalOpen", true);
	        return "home"; // Return the same view with error details
	    }

	    // Save valid enquiry to the database
	    repo.save(enquiry);
	    session.setAttribute("formSubmitted", true);

	    // Clear the form after successful submission
	    model.addAttribute("Enquiry", new Enquiry());
	    return "redirect:/"; // Redirect to avoid duplicate submission
	}


}
