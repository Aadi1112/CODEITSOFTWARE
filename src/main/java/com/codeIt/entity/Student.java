package com.codeIt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required and cannot be empty.")
    @Size(min = 2, max = 50, message = "Name must be between 3 and 15 characters.")
    private String name;

    @Email(message = "Email must be a valid email address.")
    @NotBlank(message = "Email is required.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits.")
    @Column(length = 15, nullable = false)
    private String phone;

    @NotBlank(message = "Address cannot be empty.")
    private String address;

    private String gender;

    private String course;

    private String role;


    private String courseFees;

    private String pendingFees;

    private String completedFees;

    @Column(insertable = false)
    private LocalDateTime updationDate = LocalDateTime.now();

    @Column(updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

   
    private String status;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(String courseFees) {
        this.courseFees = courseFees;
    }

    public String getPendingFees() {
        return pendingFees;
    }

    public void setPendingFees(String pendingFees) {
        this.pendingFees = pendingFees;
    }

    public String getCompletedFees() {
        return completedFees;
    }

    public void setCompletedFees(String completedFees) {
        this.completedFees = completedFees;
    }

    public LocalDateTime getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(LocalDateTime updationDate) {
        this.updationDate = updationDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
                + phone + ", address=" + address + ", gender=" + gender + ", course=" + course + ", role=" + role
                + ", courseFees=" + courseFees + ", pendingFees=" + pendingFees + ", completedFees=" + completedFees
                + ", updationDate=" + updationDate + ", registrationDate=" + registrationDate + ", status=" + status
                + "]";
    }
}
