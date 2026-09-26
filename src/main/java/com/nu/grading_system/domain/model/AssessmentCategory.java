package com.nu.grading_system.domain.model;

import com.nu.grading_system.dto.CategoryBreakdownDTO;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "assessment_categories")
public class AssessmentCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double weight;

	@OneToMany(
			mappedBy = "category",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Assessment> assessments = new ArrayList<>();

	public AssessmentCategory() {
	}

	public AssessmentCategory(String name, double weight) {
		if (weight <= 0 || weight > 1) {
			throw new IllegalArgumentException(
					"Weight must be between 0.01 and 1.00"
			);
		}

		this.name = name;
		this.weight = weight;
	}

	public void assignCourse(Course course) {
		this.course = course;
	}

	public void addAssessment(Assessment assessment) {
		assessment.assignCategory(this);
		assessments.add(assessment);
	}

	public CategoryBreakdownDTO calculateBreakdown(
			List<StudentScore> scores) {

		double totalPossible = 0.0;
		double pointsEarned = 0.0;

		for (Assessment assessment : assessments) {
			totalPossible += assessment.getMaxScore();

			for (StudentScore score : scores) {
				if (score.getAssessment().getId()
						.equals(assessment.getId())) {

					pointsEarned += score.getScoreObtained();
				}
			}
		}

		double categoryPercentage = totalPossible == 0
				? 0
				: (pointsEarned / totalPossible) * 100;

		double weightedScore =
				categoryPercentage * weight;

		categoryPercentage = BigDecimal
				.valueOf(categoryPercentage)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();

		weightedScore = BigDecimal
				.valueOf(weightedScore)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();

		return new CategoryBreakdownDTO(
				id,
				name,
				weight,
				pointsEarned,
				totalPossible,
				categoryPercentage,
				weightedScore
		);
	}

	public UUID getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public List<Assessment> getAssessments() {
		return assessments;
	}
}