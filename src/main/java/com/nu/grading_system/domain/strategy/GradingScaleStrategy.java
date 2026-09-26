package com.nu.grading_system.domain.strategy;

public interface GradingScaleStrategy {

	GradingResult evaluate(double rawPercentage);
}