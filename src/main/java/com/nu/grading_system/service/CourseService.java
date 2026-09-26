package com.nu.grading_system.service;


import com.nu.grading_system.domain.model.Course;
import com.nu.grading_system.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourse(UUID courseId) {
		return courseRepository.findById(courseId)
				.orElseThrow(() ->
						new RuntimeException("Course not found"));
	}

	public Course createCourse(
			String code,
			String title,
			String term) {

		Course course = new Course(code, title, term);

		return courseRepository.save(course);
	}
}