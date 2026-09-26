package com.nu.grading_system.service;

import com.nu.grading_system.domain.model.Assessment;
import com.nu.grading_system.domain.model.AssessmentCategory;
import com.nu.grading_system.repository.AssessmentCategoryRepository;
import com.nu.grading_system.repository.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssessmentService {

	private final AssessmentRepository assessmentRepository;
	private final AssessmentCategoryRepository categoryRepository;

	public AssessmentService(
			AssessmentRepository assessmentRepository,
			AssessmentCategoryRepository categoryRepository) {

		this.assessmentRepository = assessmentRepository;
		this.categoryRepository = categoryRepository;
	}

	public Assessment createAssessment(
			UUID categoryId,
			String title,
			double maxScore) {

		AssessmentCategory category =
				categoryRepository.findById(categoryId)
						.orElseThrow(() ->
								new RuntimeException(
										"Category not found"));

		Assessment assessment =
				new Assessment(title, maxScore);

		category.addAssessment(assessment);

		return assessmentRepository.save(assessment);
	}
}