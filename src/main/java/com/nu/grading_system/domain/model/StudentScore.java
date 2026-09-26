package com.nu.grading_system.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
		name = "student_scores",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"assessment_id", "student_id"}
				)
		}
)
public class StudentScore {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assessment_id", nullable = false)
	private Assessment assessment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@Column(name = "score_obtained", nullable = false)
	private double scoreObtained;

	@Column(name = "recorded_at")
	private LocalDateTime recordedAt;

	public StudentScore() {
	}

	public StudentScore(
			Assessment assessment,
			Student student,
			double scoreObtained) {

		if (scoreObtained < 0 ||
				scoreObtained > assessment.getMaxScore()) {
			throw new IllegalArgumentException(
					"Score must be between 0 and max score"
			);
		}

		this.assessment = assessment;
		this.student = student;
		this.scoreObtained = scoreObtained;
		this.recordedAt = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public Student getStudent() {
		return student;
	}

	public double getScoreObtained() {
		return scoreObtained;
	}

	public LocalDateTime getRecordedAt() {
		return recordedAt;
	}
}
