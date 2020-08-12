package ca.qc.ezo.functions.unary;

import java.util.function.Function;

public class Sqrt implements Function<Double, Double> {

	@Override
	public Double apply(Double operand1) {
		return Math.sqrt(operand1);
	}

}
