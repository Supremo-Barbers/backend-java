package com.nu.grading_system.controller;

import com.nu.grading_system.domain.model.Assessment;
import com.nu.grading_system.service.AssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class AssessmentController {

	private final AssessmentService assessmentService;

	public AssessmentController(
			AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}

	@PostMapping("/{categoryId}/assessments")
	@ResponseStatus(HttpStatus.CREATED)
	public Assessment createAssessment(
			@PathVariable UUID categoryId,
			@RequestBody Map<String, Object> body) {

		return assessmentService.createAssessment(
				categoryId,
				(String) body.get("title"),
				((Number) body.get("maxScore")).doubleValue()
		);
	}
}