package com.nu.grading_system.controller;

import com.nu.grading_system.domain.model.Enrollment;
import com.nu.grading_system.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	public EnrollmentController(
			EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@PostMapping("/{courseId}/enrollments")
	@ResponseStatus(HttpStatus.CREATED)
	public Enrollment enrollStudent(
			@PathVariable UUID courseId,
			@RequestBody Map<String, String> body) {

		return enrollmentService.enrollStudent(
				courseId,
				UUID.fromString(body.get("studentId"))
		);
	}
}