package com.nu.grading_system.controller;

import com.nu.grading_system.dto.CourseGradeSummaryDTO;
import com.nu.grading_system.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class GradeController {

	private final GradeService gradeService;

	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@GetMapping("/{courseId}/grades")
	public CourseGradeSummaryDTO getGrades(
			@PathVariable UUID courseId) {

		return gradeService.getCourseGrades(courseId);
	}
}