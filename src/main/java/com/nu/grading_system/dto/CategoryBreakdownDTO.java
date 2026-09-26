package com.nu.grading_system.dto;

import java.util.UUID;

public record CategoryBreakdownDTO(
		UUID categoryId,
		String categoryName,
		double weight,
		double pointsEarned,
		double totalPossible,
		double categoryPercentage,
		double weightedScore
) {
}