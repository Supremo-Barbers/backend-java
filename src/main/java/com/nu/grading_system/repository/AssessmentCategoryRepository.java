package com.nu.grading_system.repository;


import com.nu.grading_system.domain.model.AssessmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssessmentCategoryRepository
		extends JpaRepository<AssessmentCategory, UUID> {
}