package web.service;

public class MathQuestionService {

	/**
	 * Calculate Q1 result.
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double q1Addition(String number1, String number2) {
		if (number1 == null || number1.isEmpty() || number2 == null || number2.isEmpty()) {
            throw new IllegalArgumentException("Input numbers cannot be null or empty");
        }
        try {
            double result = Double.valueOf(number1) + Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input: non-numeric value");
        }
	}
	
	/**
	 * Calculate Q2 result.
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double q2Subtraction(String number1, String number2) {
		if (number1 == null || number1.isEmpty() || number2 == null || number2.isEmpty()) {
            throw new IllegalArgumentException("Input numbers cannot be null or empty");
        }
        try {
            double result = Double.valueOf(number1) - Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input: non-numeric value");
        }
	}
	
	/**
     * Calculate Q3 result.
     * @param number1
     * @param number2
     * @return
     */
    public static double q3Multiplication(String number1, String number2) {
        if (number1 == null || number1.isEmpty() || number2 == null || number2.isEmpty()) {
            throw new IllegalArgumentException("Input numbers cannot be null or empty");
        }
        try {
            double result = Double.valueOf(number1) * Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input: non-numeric value");
        }
    }
}

