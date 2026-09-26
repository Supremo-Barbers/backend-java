package com.nu.grading_system.controller;

import com.nu.grading_system.domain.model.StudentScore;
import com.nu.grading_system.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessments")
public class ScoreController {

	private final ScoreService scoreService;

	public ScoreController(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@PostMapping("/{assessmentId}/scores")
	public Map<String, Object> recordScores(
			@PathVariable UUID assessmentId,
			@RequestBody Map<String, Object> body) {

		List<Map<String, Object>> scores =
				(List<Map<String, Object>>) body.get("scores");

		int recordsUpdated = 0;

		for (Map<String, Object> score : scores) {

			UUID studentId =
					UUID.fromString(
							(String) score.get("studentId"));

			double scoreObtained =
					((Number) score.get("scoreObtained"))
							.doubleValue();

			scoreService.recordScore(
					assessmentId,
					studentId,
					scoreObtained
			);

			recordsUpdated++;
		}

		return Map.of(
				"assessmentId", assessmentId,
				"recordsUpdated", recordsUpdated
		);
	}
}