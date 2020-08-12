package ca.qc.ezo.service;

import java.util.ArrayDeque;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.factory.BinaryFunctionFactory;
import ca.qc.ezo.functions.factory.UnaryfunctionFactory;

public class ExpressionEngine {

	public static void main(String[] args) throws EvaluationException {
		System.out.println(ExpressionEngine.evaluate("1 + -1"));
	}

	public static Double evaluate(String expression) throws EvaluationException {
		validateExpression(expression);

		char[] tokens = ExpressionEngine.TOKENIZER.apply(expression);

		ArrayDeque<Double> operands = new ArrayDeque<Double>();
		ArrayDeque<MathOperator> operators = new ArrayDeque<MathOperator>();

		int index = 0;
		String operand;
		while (index < tokens.length) {
			if (ExpressionEngine.IS_OPERAND.test(tokens[index])) {
				operand = getOperand(tokens, index);
				index += operand.length();
				operands.push(Double.parseDouble(operand));
				continue;
			}

			MathOperator operator = getOperator(tokens, index);
			computeOperator(operator, operators, operands);
			index++;

		}
		while (!operators.isEmpty()) {
			ExpressionEngine.EVALUATE_EXPRESSION.accept(operators, operands);
		}

		return round(operands.pop(), 1);
	}

	private static Predicate<Character> IS_OPERAND = (token) -> Character.isDigit(token) || token == '.'
			|| token == ',';

	private static BiPredicate<MathOperator, MathOperator> HAS_PRECEDENCE = (operand1,
			operand2) -> operand2.getPriorite() == 0 || operand1.getPriorite() < operand2.getPriorite();

	private static BiConsumer<ArrayDeque<MathOperator>, ArrayDeque<Double>> EVALUATE_EXPRESSION = (operatorsV,
			operandsV) -> {
		if (operatorsV.peek().isUnary()) {
			operandsV.push(UnaryfunctionFactory.getFunction(operatorsV.pop()).apply(operandsV.pop()));
		} else {
			Double operand2 = operandsV.pop();
			Double operand1 = operandsV.pop();
			operandsV.push(BinaryFunctionFactory.getFunction(operatorsV.pop()).apply(operand1, operand2));
		}
	};

	private static void validateExpression(String expression) throws EvaluationException {
		if (expression == null || expression.replaceAll("\\s+", "").isEmpty()) {
			throw new EvaluationException("Empty Expression");
		}
	}

	private static Function<String, char[]> TOKENIZER = (expression) -> {
		return expression.replaceAll("\\s+", "").replaceAll("sqrt+", "s").replaceAll(",", ".").toCharArray();
	};

	private static MathOperator getOperator(char[] tokens, int offset) {
		MathOperator operator = MathOperator.parse(String.valueOf(tokens[offset]), (offset - 1 == -1
				|| (!ExpressionEngine.IS_OPERAND.test(tokens[offset - 1]) && tokens[offset - 1] != ')')));

		if (operator == null) {
			throw new EvaluationException("Invalid token " + tokens[offset] + " in expression");
		}
		return operator;
	}

	private static String getOperand(char[] tokens, int offset) {
		StringBuilder buffer = new StringBuilder();
		while (offset < tokens.length && ExpressionEngine.IS_OPERAND.test(tokens[offset])) {
			buffer.append(tokens[offset++]);
		}
		return buffer.toString();
	}

	private static void computeOperator(MathOperator operator, ArrayDeque<MathOperator> operators,
			ArrayDeque<Double> operands) {
		if (operator.equals(MathOperator.OPENED_PARANTHESE)) {
			operators.push(operator);
		} else if (operator.equals(MathOperator.CLOSED_PARANTHESE)) {
			while (!MathOperator.OPENED_PARANTHESE.equals(operators.peek())) {
				ExpressionEngine.EVALUATE_EXPRESSION.accept(operators, operands);
			}
			operators.pop();
		} else {
			while (!operands.isEmpty() && !operators.isEmpty() && !HAS_PRECEDENCE.test(operator, operators.peek())) {
				ExpressionEngine.EVALUATE_EXPRESSION.accept(operators, operands);
			}
			operators.push(operator);
		}
	}

	public static double round(double montant, int precision) {
		double p = Math.pow(10.0, precision);
		return Math.floor((montant * p) + 0.5) / p;
	}

}
