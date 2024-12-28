package com.codeIt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Table(name = "trainers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "role")
    private String role;  // For example, "ADMIN", "TRAINER"



}
