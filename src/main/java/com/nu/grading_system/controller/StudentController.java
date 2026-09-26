package com.nu.grading_system.controller;

import com.nu.grading_system.domain.model.Student;
import com.nu.grading_system.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(
			@RequestBody Map<String, String> body) {

		return studentService.createStudent(
				body.get("studentNumber"),
				body.get("firstName"),
				body.get("lastName"),
				body.get("email")
		);
	}
}