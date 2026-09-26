package com.nu.grading_system.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "student_number", nullable = false, unique = true)
	private String studentNumber;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Student() {
	}

	public Student(String studentNumber, String firstName,
	               String lastName, String email) {
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createdAt = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}