package com.nu.grading_system.domain.strategy;

import org.springframework.stereotype.Component;

@Component
public class NuGradingScaleStrategy implements GradingScaleStrategy {

	@Override
	public GradingResult evaluate(double rawPercentage) {

		if (rawPercentage >= 96.00) {
			return new GradingResult("4.0", "Passed");
		}

		if (rawPercentage >= 90.00) {
			return new GradingResult("3.5", "Passed");
		}

		if (rawPercentage >= 84.00) {
			return new GradingResult("3.0", "Passed");
		}

		if (rawPercentage >= 78.00) {
			return new GradingResult("2.5", "Passed");
		}

		if (rawPercentage >= 72.00) {
			return new GradingResult("2.0", "Passed");
		}

		if (rawPercentage >= 66.00) {
			return new GradingResult("1.5", "Passed");
		}

		if (rawPercentage >= 60.00) {
			return new GradingResult("1.0", "Passed");
		}

		return new GradingResult("R", "Repeat");
	}
}