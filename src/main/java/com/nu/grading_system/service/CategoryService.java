package com.nu.grading_system.service;

import com.nu.grading_system.domain.model.AssessmentCategory;
import com.nu.grading_system.domain.model.Course;
import com.nu.grading_system.repository.AssessmentCategoryRepository;
import com.nu.grading_system.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

	private final AssessmentCategoryRepository categoryRepository;
	private final CourseRepository courseRepository;

	public CategoryService(
			AssessmentCategoryRepository categoryRepository,
			CourseRepository courseRepository) {

		this.categoryRepository = categoryRepository;
		this.courseRepository = courseRepository;
	}

	public AssessmentCategory createCategory(
			UUID courseId,
			String name,
			double weight) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() ->
						new RuntimeException("Course not found"));

		AssessmentCategory category =
				new AssessmentCategory(name, weight);

		course.addCategory(category);

		return categoryRepository.save(category);
	}

	public List<AssessmentCategory> getCategories(
			UUID courseId) {

		return categoryRepository
				.findAll()
				.stream()
				.filter(category ->
						category.getCourse()
								.getId()
								.equals(courseId))
				.toList();
	}
}