package com.nu.grading_system.service;

import com.nu.grading_system.domain.model.Course;
import com.nu.grading_system.domain.model.Enrollment;
import com.nu.grading_system.domain.model.Student;
import com.nu.grading_system.repository.CourseRepository;
import com.nu.grading_system.repository.EnrollmentRepository;
import com.nu.grading_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;

	public EnrollmentService(
			EnrollmentRepository enrollmentRepository,
			StudentRepository studentRepository,
			CourseRepository courseRepository) {

		this.enrollmentRepository = enrollmentRepository;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public Enrollment enrollStudent(
			UUID courseId,
			UUID studentId) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() ->
						new RuntimeException("Course not found"));

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() ->
						new RuntimeException("Student not found"));

		Enrollment enrollment =
				new Enrollment(student, course);

		return enrollmentRepository.save(enrollment);
	}
}