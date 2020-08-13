package ca.qc.ezo.functions.unary.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.functions.binary.Division;
import ca.qc.ezo.functions.binary.Minus;
import ca.qc.ezo.functions.binary.Modulus;
import ca.qc.ezo.functions.binary.Multiplication;
import ca.qc.ezo.functions.binary.Plus;
import ca.qc.ezo.functions.binary.Power;

public class UnaryFunctionsTest {

	@DisplayName("1+1")
	@Test
	void testPlus() {
		assertEquals(2.0d, new Plus().apply(1.0d, 1.0d).doubleValue());
	}

	@DisplayName("1-1")
	@Test
	void testMinus() {
		assertEquals(0.0d, new Minus().apply(1.0d, 1.0d).doubleValue());
	}

	@DisplayName("1*1")
	@Test
	void testMultiplication() {
		assertEquals(1.0d, new Multiplication().apply(1.0d, 1.0d).doubleValue());
	}

	@DisplayName("2%4")
	@Test
	void testModulus() {
		assertEquals(2.0d, new Modulus().apply(2.0d, 4.0d).doubleValue());
	}

	@DisplayName("2^4")
	@Test
	void testPower() {
		assertEquals(16.0d, new Power().apply(2.0d, 4.0d).doubleValue());
	}

	@DisplayName("4/2")
	@Test
	void testDivision() {
		assertEquals(2.0d, new Division().apply(4.0d, 2.0d).doubleValue());
	}

	@DisplayName("4/0")
	@Test
	void testDivision0() {
		assertThrows(EvaluationException.class, () -> new Division().apply(4.0d, 0.0d));
	}
}
