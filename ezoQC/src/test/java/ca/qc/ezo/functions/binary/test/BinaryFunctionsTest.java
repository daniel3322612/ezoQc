package ca.qc.ezo.functions.binary.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.qc.ezo.functions.unary.Sqrt;
import ca.qc.ezo.functions.unary.UnaryMinus;
import ca.qc.ezo.functions.unary.UnaryPlus;

public class BinaryFunctionsTest {

	@DisplayName("+1")
	@Test
	void testUnaryPlus() {
		assertEquals(1.0d, new UnaryPlus().apply(1.0d).doubleValue());
	}

	@DisplayName("-1")
	@Test
	void testUnaryMinus() {
		assertEquals(-1.0d, new UnaryMinus().apply(1.0d).doubleValue());
	}

	@DisplayName("sqrt(2)")
	@Test
	void testSqrt() {
		assertEquals(2.0d, new Sqrt().apply(4.0d).doubleValue());
	}
}
