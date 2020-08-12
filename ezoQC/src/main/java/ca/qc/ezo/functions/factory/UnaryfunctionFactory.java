package ca.qc.ezo.functions.factory;

import java.util.function.Function;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.unary.Sqrt;
import ca.qc.ezo.functions.unary.UnaryMinus;
import ca.qc.ezo.functions.unary.UnaryPlus;

public class UnaryfunctionFactory {

	private static Function<Double, Double> UNARY_PLUS = new UnaryPlus();
	private static Function<Double, Double> UNARY_MINUS = new UnaryMinus();
	private static Function<Double, Double> SQRT = new Sqrt();

	public static Function<Double, Double> getFunction(MathOperator operator) {
		switch (operator) {
		case UNARY_PLUS:
			return UnaryfunctionFactory.UNARY_PLUS;
		case UNARY_MINUS:
			return UnaryfunctionFactory.UNARY_MINUS;
		case SQRT:
			return UnaryfunctionFactory.SQRT;
		default:
			throw new EvaluationException("Invalid unary operator " + operator);
		}
	}
}
