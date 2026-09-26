package com.nu.grading_system.repository;


import com.nu.grading_system.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository
		extends JpaRepository<Student, UUID> {
}