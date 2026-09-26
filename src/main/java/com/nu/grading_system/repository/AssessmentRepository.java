package com.nu.grading_system.repository;


import com.nu.grading_system.domain.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssessmentRepository
		extends JpaRepository<Assessment, UUID> {
}