package com.nu.grading_system.service;

import com.nu.grading_system.domain.model.Student;
import com.nu.grading_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student createStudent(
			String studentNumber,
			String firstName,
			String lastName,
			String email) {

		Student student = new Student(
				studentNumber,
				firstName,
				lastName,
				email
		);

		return studentRepository.save(student);
	}

	public Student getStudent(UUID studentId) {
		return studentRepository.findById(studentId)
				.orElseThrow(() ->
						new RuntimeException("Student not found"));
	}
}