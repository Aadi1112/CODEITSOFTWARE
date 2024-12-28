package com.codeIt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    
    private String password;

    @Column(length = 15, nullable = false)
    private String phone;

    private String address;

    private String gender;

    private String course;
    
    private String role;
    
    public String getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}

	private String courseFees;
	
	private String pendingFees;
	
	private String completedFees;
	
	
	
	
	

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

	@Column(insertable = false)
	private LocalDateTime updationDate=LocalDateTime.now();

    @Column(updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    private String status ; // active registration

    // Getters and setters
   

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

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
