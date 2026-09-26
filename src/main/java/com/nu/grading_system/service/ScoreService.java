package com.nu.grading_system.service;

import com.nu.grading_system.domain.model.Assessment;
import com.nu.grading_system.domain.model.Student;
import com.nu.grading_system.domain.model.StudentScore;
import com.nu.grading_system.repository.AssessmentRepository;
import com.nu.grading_system.repository.StudentRepository;
import com.nu.grading_system.repository.StudentScoreRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScoreService {

	private final StudentScoreRepository scoreRepository;
	private final AssessmentRepository assessmentRepository;
	private final StudentRepository studentRepository;

	public ScoreService(
			StudentScoreRepository scoreRepository,
			AssessmentRepository assessmentRepository,
			StudentRepository studentRepository) {

		this.scoreRepository = scoreRepository;
		this.assessmentRepository = assessmentRepository;
		this.studentRepository = studentRepository;
	}

	public StudentScore recordScore(
			UUID assessmentId,
			UUID studentId,
			double scoreObtained) {

		Assessment assessment =
				assessmentRepository.findById(assessmentId)
						.orElseThrow(() ->
								new RuntimeException(
										"Assessment not found"));

		Student student =
				studentRepository.findById(studentId)
						.orElseThrow(() ->
								new RuntimeException(
										"Student not found"));

		StudentScore score =
				new StudentScore(
						assessment,
						student,
						scoreObtained
				);

		return scoreRepository.save(score);
	}
}