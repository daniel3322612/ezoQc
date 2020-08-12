package ca.qc.ezo.enums;

public enum MathOperator {

	PLUS("+", 5, false), MINUS("-", 5, false), DIVISION("/", 2, false), MULTIPLICATION("*", 2, false),
	POWER("^", 2, false), MODULUS("%", 2, false), SQRT("sqrt", 1, true), UNARY_PLUS("+", 1, true),
	UNARY_MINUS("-", 1, true), OPENED_PARANTHESE("(", 0, false), CLOSED_PARANTHESE(")", 0, false);

	private String symbol;
	private int priorite;
	private boolean unary;

	private MathOperator(String symbol, int priorite, boolean unary) {
		this.symbol = symbol;
		this.priorite = priorite;
		this.unary = unary;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getPriorite() {
		return priorite;
	}

	public boolean isUnary() {
		return unary;
	}

	public static MathOperator parse(String token, boolean unary) {
		switch (token) {
		case "+":
			return unary ? MathOperator.UNARY_PLUS : MathOperator.PLUS;
		case "-":
			return unary ? MathOperator.UNARY_MINUS : MathOperator.MINUS;
		case "/":
			return !unary ? MathOperator.DIVISION : null;
		case "*":
			return !unary ? MathOperator.MULTIPLICATION : null;
		case "s":
			return unary ? MathOperator.SQRT : null;
		case "^":
			return !unary ? MathOperator.POWER : null;
		case "%":
			return !unary ? MathOperator.MODULUS : null;
		case "(":
			return MathOperator.OPENED_PARANTHESE;
		case ")":
			return MathOperator.CLOSED_PARANTHESE;
		default:
			return null;
		}
	}
}
