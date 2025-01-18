package com.codeIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeIt.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{

}
