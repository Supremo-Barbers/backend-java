package com.nu.grading_system.repository;

import com.nu.grading_system.domain.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentScoreRepository
		extends JpaRepository<StudentScore, UUID> {

	List<StudentScore> findByStudentId(UUID studentId);

	List<StudentScore> findByAssessmentId(UUID assessmentId);
}