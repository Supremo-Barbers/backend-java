package com.nu.grading_system.dto;

import java.util.List;
import java.util.UUID;

public record StudentGradeDTO(
		UUID studentId,
		String studentNumber,
		String fullName,
		List<CategoryBreakdownDTO> categoryBreakdown,
		double finalRawPercentage,
		String gradePoint,
		String academicRemark
) {
}