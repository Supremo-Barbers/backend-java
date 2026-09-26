package com.nu.grading_system.controller;

import com.nu.grading_system.domain.model.AssessmentCategory;
import com.nu.grading_system.domain.model.Course;
import com.nu.grading_system.service.CategoryService;
import com.nu.grading_system.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;
	private final CategoryService categoryService;

	public CourseController(
			CourseService courseService,
			CategoryService categoryService) {
		this.courseService = courseService;
		this.categoryService = categoryService;
	}

	@GetMapping
	public List<Course> getCourses() {
		return courseService.getAllCourses();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Course createCourse(
			@RequestBody Map<String, String> body) {

		return courseService.createCourse(
				body.get("code"),
				body.get("title"),
				body.get("term")
		);
	}

	@PostMapping("/{courseId}/categories")
	@ResponseStatus(HttpStatus.CREATED)
	public AssessmentCategory createCategory(
			@PathVariable UUID courseId,
			@RequestBody Map<String, Object> body) {

		return categoryService.createCategory(
				courseId,
				(String) body.get("name"),
				((Number) body.get("weight")).doubleValue()
		);
	}

	@GetMapping("/{courseId}/categories")
	public List<AssessmentCategory> getCategories(
			@PathVariable UUID courseId) {

		return categoryService.getCategories(courseId);
	}
}