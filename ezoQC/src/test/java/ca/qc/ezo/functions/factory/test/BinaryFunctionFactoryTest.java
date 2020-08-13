package ca.qc.ezo.functions.factory.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ca.qc.ezo.enums.MathOperator;
import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.binary.Division;
import ca.qc.ezo.functions.binary.Minus;
import ca.qc.ezo.functions.binary.Modulus;
import ca.qc.ezo.functions.binary.Multiplication;
import ca.qc.ezo.functions.binary.Plus;
import ca.qc.ezo.functions.binary.Power;
import ca.qc.ezo.functions.factory.BinaryFunctionFactory;

public class BinaryFunctionFactoryTest {

	@Test
	void test() {
		assertEquals(Plus.class, BinaryFunctionFactory.getFunction(MathOperator.PLUS).getClass());
		assertEquals(Minus.class, BinaryFunctionFactory.getFunction(MathOperator.MINUS).getClass());
		assertEquals(Division.class, BinaryFunctionFactory.getFunction(MathOperator.DIVISION).getClass());
		assertEquals(Multiplication.class, BinaryFunctionFactory.getFunction(MathOperator.MULTIPLICATION).getClass());
		assertEquals(Power.class, BinaryFunctionFactory.getFunction(MathOperator.POWER).getClass());
		assertEquals(Modulus.class, BinaryFunctionFactory.getFunction(MathOperator.MODULUS).getClass());

		assertThrows(EvaluationException.class,
				() -> BinaryFunctionFactory.getFunction(MathOperator.UNARY_MINUS).getClass());

	}
}
