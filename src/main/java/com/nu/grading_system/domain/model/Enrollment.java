package com.nu.grading_system.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
		name = "enrollments",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"student_id", "course_id"}
				)
		}
)
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Column(name = "enrolled_at")
	private LocalDateTime enrolledAt;

	public Enrollment() {
	}

	public Enrollment(Student student, Course course) {
		this.student = student;
		this.course = course;
		this.enrolledAt = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	public LocalDateTime getEnrolledAt() {
		return enrolledAt;
	}
}