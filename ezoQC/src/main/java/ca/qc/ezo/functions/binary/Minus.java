package ca.qc.ezo.functions.binary;

import java.util.function.BiFunction;

public class Minus implements BiFunction<Double, Double, Double> {

	@Override
	public Double apply(Double operand1, Double operand2) {
		return operand1 - operand2;
	}

}
