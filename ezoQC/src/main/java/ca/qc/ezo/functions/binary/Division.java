package ca.qc.ezo.functions.binary;

import java.util.function.BiFunction;

import ca.qc.ezo.exceptions.EvaluationException;

public class Division implements BiFunction<Double, Double, Double> {

	@Override
	public Double apply(Double operand1, Double operand2) {
		if (operand2 == 0.0) {
			throw new EvaluationException("Error division by O");
		}
		return operand1 / operand2;
	}

}
