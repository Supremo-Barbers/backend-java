package com.nu.grading_system.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "assessments")
public class Assessment {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private AssessmentCategory category;

	@Column(nullable = false)
	private String title;

	@Column(name = "max_score", nullable = false)
	private double maxScore;

	@OneToMany(
			mappedBy = "assessment",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<StudentScore> scores = new ArrayList<>();

	public Assessment() {
	}

	public Assessment(String title, double maxScore) {
		if (maxScore <= 0) {
			throw new IllegalArgumentException(
					"maxScore must be greater than 0"
			);
		}

		this.title = title;
		this.maxScore = maxScore;
	}

	public void assignCategory(AssessmentCategory category) {
		this.category = category;
	}

	public UUID getId() {
		return id;
	}

	public AssessmentCategory getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public double getMaxScore() {
		return maxScore;
	}

	public List<StudentScore> getScores() {
		return scores;
	}
}