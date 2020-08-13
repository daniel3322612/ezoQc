package ca.qc.ezo.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.factory.BinaryFunctionFactory;
import ca.qc.ezo.functions.factory.UnaryFunctionFactory;

public class ProcessingUtils {

	/**
	 * Evalue l'operateur en tete de la file des operateurs, et modifie la pile des
	 * operands pour mettre le resultat de l'evaluation en tete de la pile
	 */
	public static BiConsumer<Deque<MathOperator>, Deque<Double>> EVALUATE_EXPRESSION = (operatorsV, operandsV) -> {
		if (operatorsV.peek().isUnary()) {
			operandsV.push(UnaryFunctionFactory.getFunction(operatorsV.pop()).apply(operandsV.pop()));
		} else {
			Double operand2 = operandsV.pop();
			Double operand1 = operandsV.pop();
			operandsV.push(BinaryFunctionFactory.getFunction(operatorsV.pop()).apply(operand1, operand2));
		}
	};

	/**
	 * Supprime tous les espaces de l'expression, remplace toutes les occurrences de
	 * sqrt par s et les virgule (,) par le (.). Enfin transforme la chaine
	 * resultante en un tableau de caracteres.
	 */
	public static Function<String, char[]> TOKENIZER = (expression) -> {
		return expression.replaceAll("\\s+", "").replaceAll("sqrt+", "s").replaceAll(",", ".").toCharArray();
	};

	/**
	 * Verifie si un caratere est un chiffre ou un .
	 */
	public static Predicate<Character> IS_OPERAND = (token) -> Character.isDigit(token) || token == '.' || token == ',';

	/**
	 * Evalue la precedence entre deux operateurs. Retourne <true> si operator1 a
	 * moin de priorite que operators2
	 */
	public static BiPredicate<MathOperator, MathOperator> HAS_PRECEDENCE = (operator1,
			operateur2) -> operateur2.getPriorite() == 0 || operator1.getPriorite() < operateur2.getPriorite();

	/**
	 * Verifie si l'expression est valide
	 * 
	 * @param expression
	 * @throws EvaluationException
	 */
	public static void validateExpression(String expression) throws EvaluationException {
		if (expression == null || expression.replaceAll("\\s+", "").isEmpty()) {
			throw new EvaluationException("Empty Expression");
		}
	}

	/**
	 * Permet de faire correspondre un caractere a un operateur
	 * 
	 * @param tokens
	 * @param offset
	 * @return
	 */
	public static MathOperator getOperator(char[] tokens, int offset) {
		MathOperator operator = MathOperator.parse(String.valueOf(tokens[offset]),
				(offset - 1 == -1 || (!IS_OPERAND.test(tokens[offset - 1]) && tokens[offset - 1] != ')')));

		if (operator == null) {
			throw new EvaluationException("Invalid token " + tokens[offset] + " in expression");
		}
		return operator;
	}

	/**
	 * Permet de recuperer une liste consecutive de chiffre dans un tableau de
	 * caracteres
	 * 
	 * @param tokens
	 * @param offset
	 * @return un nombre
	 */
	public static String getOperand(char[] tokens, int offset) {
		StringBuilder buffer = new StringBuilder();
		while (offset < tokens.length && IS_OPERAND.test(tokens[offset])) {
			buffer.append(tokens[offset++]);
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @param operator
	 * @param operators
	 * @param operands
	 */
	public static void computeOperator(MathOperator operator, ArrayDeque<MathOperator> operators,
			ArrayDeque<Double> operands) {
		if (operator.equals(MathOperator.OPENED_PARANTHESE)) {
			operators.push(operator);
		} else if (operator.equals(MathOperator.CLOSED_PARANTHESE)) {
			while (!MathOperator.OPENED_PARANTHESE.equals(operators.peek())) {
				EVALUATE_EXPRESSION.accept(operators, operands);
			}
			operators.pop();
		} else {
			while (!operands.isEmpty() && !operators.isEmpty() && !HAS_PRECEDENCE.test(operator, operators.peek())) {
				EVALUATE_EXPRESSION.accept(operators, operands);
			}
			operators.push(operator);
		}
	}

	/**
	 * Arrondi une valeur selon une precision
	 * 
	 * @param value
	 * @param precision
	 * @return
	 */
	public static double round(double value, int precision) {
		double p = Math.pow(10.0, precision);
		return Math.floor((value * p) + 0.5) / p;
	}

}
