package com.nu.grading_system.repository;


import com.nu.grading_system.domain.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository
		extends JpaRepository<Enrollment, UUID> {

	List<Enrollment> findByCourseId(UUID courseId);

	List<Enrollment> findByStudentId(UUID studentId);
}