package ca.qc.ezo.functions.factory.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.factory.UnaryFunctionFactory;
import ca.qc.ezo.functions.unary.Sqrt;
import ca.qc.ezo.functions.unary.UnaryMinus;
import ca.qc.ezo.functions.unary.UnaryPlus;

public class UnaryFunctionFactoryTest {

	@Test
	void test() {
		assertEquals(UnaryPlus.class, UnaryFunctionFactory.getFunction(MathOperator.UNARY_PLUS).getClass());
		assertEquals(UnaryMinus.class, UnaryFunctionFactory.getFunction(MathOperator.UNARY_MINUS).getClass());
		assertEquals(Sqrt.class, UnaryFunctionFactory.getFunction(MathOperator.SQRT).getClass());

		assertThrows(EvaluationException.class,
				() -> UnaryFunctionFactory.getFunction(MathOperator.MODULUS).getClass());

	}
}
