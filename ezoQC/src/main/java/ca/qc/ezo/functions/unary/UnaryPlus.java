package ca.qc.ezo.functions.unary;

import java.util.function.Function;

public class UnaryPlus implements Function<Double, Double> {

	@Override
	public Double apply(Double operand1) {
		return operand1;
	}

}
