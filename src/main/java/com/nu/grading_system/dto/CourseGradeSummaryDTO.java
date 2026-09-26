package com.nu.grading_system.dto;

import java.util.List;
import java.util.UUID;

public record CourseGradeSummaryDTO(
		UUID courseId,
		String courseCode,
		String engine,
		String gradingSystem,
		List<StudentGradeDTO> students
) {
}