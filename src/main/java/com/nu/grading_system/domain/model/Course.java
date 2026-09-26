package com.nu.grading_system.domain.model;

import com.nu.grading_system.domain.strategy.GradingScaleStrategy;
import com.nu.grading_system.dto.CategoryBreakdownDTO;
import com.nu.grading_system.dto.StudentGradeDTO;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String term;

	@OneToMany(
			mappedBy = "course",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<AssessmentCategory> categories = new ArrayList<>();

	public Course() {
	}

	public Course(String code, String title, String term) {
		this.code = code;
		this.title = title;
		this.term = term;
	}

	public void addCategory(AssessmentCategory category) {
		category.assignCourse(this);
		categories.add(category);
	}

	public StudentGradeDTO evaluateStudent(
			Student student,
			List<StudentScore> scores,
			GradingScaleStrategy gradingScaleStrategy) {

		List<CategoryBreakdownDTO> breakdown = new ArrayList<>();

		double finalRawPercentage = 0.0;

		for (AssessmentCategory category : categories) {
			CategoryBreakdownDTO categoryBreakdown =
					category.calculateBreakdown(scores);

			breakdown.add(categoryBreakdown);

			finalRawPercentage += categoryBreakdown.weightedScore();
		}

		finalRawPercentage = BigDecimal
				.valueOf(finalRawPercentage)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();

		var gradingResult =
				gradingScaleStrategy.evaluate(finalRawPercentage);

		return new StudentGradeDTO(
				student.getId(),
				student.getStudentNumber(),
				student.getFullName(),
				breakdown,
				finalRawPercentage,
				gradingResult.gradePoint(),
				gradingResult.remark()
		);
	}

	public UUID getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getTerm() {
		return term;
	}

	public List<AssessmentCategory> getCategories() {
		return categories;
	}
}