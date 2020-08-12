package ca.qc.ezo.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.qc.ezo.exceptions.EvaluationException;
import ca.qc.ezo.service.ExpressionEngine;

public class ExpressionEngineTest {

	@DisplayName("compound tests")
	@Test
	void test15() {
		assertEquals(-1.0d, ExpressionEngine.evaluate("1-sqrt(4)").doubleValue());
		assertEquals(0.0d, ExpressionEngine.evaluate("1+1-sqrt(4)").doubleValue());
		assertEquals(0.0d, ExpressionEngine.evaluate("(1+1)-sqrt(4)").doubleValue());
		assertEquals(0.0d, ExpressionEngine.evaluate("(1+1)-sqrt(2+2)").doubleValue());

		assertEquals(17.0d, ExpressionEngine.evaluate("2+5*3").doubleValue());
	}

	@DisplayName("1+1")
	@Test
	void test1() {
		assertEquals(2.0d, ExpressionEngine.evaluate("1+1").doubleValue());
	}

	@DisplayName("1 + 2")
	@Test
	void test2() {
		assertEquals(3.0d, ExpressionEngine.evaluate("1 + 2").doubleValue());
	}

	@DisplayName("1 + -1")
	@Test
	void test3() {
		assertEquals(0.0d, ExpressionEngine.evaluate("1 + -1").doubleValue());
	}

	@DisplayName("-1 - -1")
	@Test
	void test4() {
		assertEquals(0.0d, ExpressionEngine.evaluate("-1 - -1").doubleValue());
	}

	@DisplayName("5-4")
	@Test
	void test5() {
		assertEquals(1.0d, ExpressionEngine.evaluate("5-4").doubleValue());
	}

	@DisplayName("5*2")
	@Test
	void test6() {
		assertEquals(10.0d, ExpressionEngine.evaluate("5*2").doubleValue());
	}

	@DisplayName("(2+5)*3")
	@Test
	void test7() {
		assertEquals(21.0d, ExpressionEngine.evaluate("(2+5)*3").doubleValue());
	}

	@DisplayName("10/2")
	@Test
	void test8() {
		assertEquals(5.0d, ExpressionEngine.evaluate("10/2").doubleValue());
	}

	@DisplayName("2+2*5+5")
	@Test
	void test9() {
		assertEquals(17.0d, ExpressionEngine.evaluate("2+2*5+5").doubleValue());
	}

	@DisplayName("2.8*3-1")
	@Test
	void test10() {
		assertEquals(7.4d, ExpressionEngine.evaluate("2.8*3-1").doubleValue());
	}

	@DisplayName("2^8")
	@Test
	void test11() {
		assertEquals(256.0d, ExpressionEngine.evaluate("2^8").doubleValue());
	}

	@DisplayName("2^8*5-1")
	@Test
	void test12() {
		assertEquals(1279.0d, ExpressionEngine.evaluate("2^8*5-1").doubleValue());
	}

	@DisplayName("sqrt(4)")
	@Test
	void test13() {
		assertEquals(2.0d, ExpressionEngine.evaluate("sqrt(4)").doubleValue());
	}

	@DisplayName("1/0")
	@Test
	void test14() {
		RuntimeException exception = assertThrows(EvaluationException.class, () -> ExpressionEngine.evaluate("1/0"));
		assertEquals("Error division by O", exception.getMessage());
	}

}