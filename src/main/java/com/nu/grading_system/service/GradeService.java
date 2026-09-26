package com.nu.grading_system.service;

import com.nu.grading_system.domain.model.Course;
import com.nu.grading_system.domain.model.Student;
import com.nu.grading_system.domain.model.StudentScore;
import com.nu.grading_system.domain.strategy.GradingScaleStrategy;
import com.nu.grading_system.dto.CourseGradeSummaryDTO;
import com.nu.grading_system.dto.StudentGradeDTO;
import com.nu.grading_system.repository.CourseRepository;
import com.nu.grading_system.repository.EnrollmentRepository;
import com.nu.grading_system.repository.StudentScoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GradeService {

	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;
	private final StudentScoreRepository scoreRepository;
	private final GradingScaleStrategy gradingScaleStrategy;

	public GradeService(
			CourseRepository courseRepository,
			EnrollmentRepository enrollmentRepository,
			StudentScoreRepository scoreRepository,
			GradingScaleStrategy gradingScaleStrategy) {

		this.courseRepository = courseRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.scoreRepository = scoreRepository;
		this.gradingScaleStrategy = gradingScaleStrategy;
	}

	public CourseGradeSummaryDTO getCourseGrades(UUID courseId) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() ->
						new RuntimeException("Course not found"));

		List<StudentGradeDTO> studentGrades = new ArrayList<>();

		enrollmentRepository
				.findByCourseId(courseId)
				.forEach(enrollment -> {

					Student student = enrollment.getStudent();

					List<StudentScore> scores =
							scoreRepository.findByStudentId(
									student.getId()
							);

					StudentGradeDTO grade =
							course.evaluateStudent(
									student,
									scores,
									gradingScaleStrategy
							);

					studentGrades.add(grade);
				});

		return new CourseGradeSummaryDTO(
				course.getId(),
				course.getCode(),
				"java-oop",
				"NU",
				studentGrades
		);
	}
}