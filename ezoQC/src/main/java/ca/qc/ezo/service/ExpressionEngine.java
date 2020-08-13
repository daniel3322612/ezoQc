package ca.qc.ezo.service;

import java.util.ArrayDeque;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.utils.ProcessingUtils;

public class ExpressionEngine {

	/**
	 * Permet d'evaluer une expression arithmethique infixée. Elle s'appuie sur
	 * l'alogorithme <b>Shunting Yard Algorithm</b> de Edgar Dijkstra qui consiste a
	 * transformer une expression infixée a une expressions postfixée, toute en
	 * evaluation l'expression.
	 * 
	 * @param expression Expression infixée
	 * @return le resultat de l'evaluation de l'expression
	 * @throws EvaluationException
	 */
	public static Double evaluate(String expression) throws EvaluationException {
		ProcessingUtils.validateExpression(expression);

		char[] tokens = ProcessingUtils.TOKENIZER.apply(expression);

		ArrayDeque<Double> operands = new ArrayDeque<Double>();
		ArrayDeque<MathOperator> operators = new ArrayDeque<MathOperator>();

		int index = 0;
		String operand;
		while (index < tokens.length) {
			if (ProcessingUtils.IS_OPERAND.test(tokens[index])) {
				operand = ProcessingUtils.getOperand(tokens, index);
				index += operand.length();
				operands.push(Double.parseDouble(operand));
				continue;
			}

			MathOperator operator = ProcessingUtils.getOperator(tokens, index);
			ProcessingUtils.computeOperator(operator, operators, operands);
			index++;

		}
		while (!operators.isEmpty()) {
			ProcessingUtils.EVALUATE_EXPRESSION.accept(operators, operands);
		}

		return ProcessingUtils.round(operands.pop(), 1);
	}
}
