package ca.qc.ezo.functions.factory;

import java.util.function.BiFunction;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.binary.Division;
import ca.qc.ezo.functions.binary.Minus;
import ca.qc.ezo.functions.binary.Modulus;
import ca.qc.ezo.functions.binary.Multiplication;
import ca.qc.ezo.functions.binary.Plus;
import ca.qc.ezo.functions.binary.Power;

public class BinaryFunctionFactory {

	private static BiFunction<Double, Double, Double> PLUS = new Plus();
	private static BiFunction<Double, Double, Double> MINUS = new Minus();
	private static BiFunction<Double, Double, Double> DIVISION = new Division();
	private static BiFunction<Double, Double, Double> MULTIPLICATION = new Multiplication();
	private static BiFunction<Double, Double, Double> POWER = new Power();
	private static BiFunction<Double, Double, Double> MODULUS = new Modulus();

	public static BiFunction<Double, Double, Double> getFunction(MathOperator operator) {
		switch (operator) {
		case PLUS:
			return BinaryFunctionFactory.PLUS;
		case MINUS:
			return BinaryFunctionFactory.MINUS;
		case DIVISION:
			return BinaryFunctionFactory.DIVISION;
		case MULTIPLICATION:
			return BinaryFunctionFactory.MULTIPLICATION;
		case POWER:
			return BinaryFunctionFactory.POWER;
		case MODULUS:
			return BinaryFunctionFactory.MODULUS;
		default:
			throw new EvaluationException("Invalid binary operator " + operator);
		}
	}
}
