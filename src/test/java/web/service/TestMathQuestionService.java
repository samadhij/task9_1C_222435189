package web.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import web.service.MathQuestionService;

public class TestMathQuestionService {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testTrueAddPositive() {
		Assert.assertEquals(MathQuestionService.q1Addition("1", "2"), 3, 0);
	}
	
	@Test
    public void testFalseAddInvalidNumber() {
		thrown.expect(NumberFormatException.class);
        MathQuestionService.q1Addition("a", "2");
    }
	
	@Test
    public void testTrueAddNegative() {
		Assert.assertEquals(MathQuestionService.q1Addition("-1", "-2"), -3, 0);
    }
	
	@Test
    public void testTrueAddPositiveNegative() {
		Assert.assertEquals(MathQuestionService.q1Addition("-3", "1"), -2, 0);
    }
	
	@Test
    public void testTrueAddZero() {
		Assert.assertEquals(MathQuestionService.q1Addition("0", "2"), 2, 0);
    }
	

	@Test
	public void testAddNumber1Empty() {
		thrown.expect(IllegalArgumentException.class);
		MathQuestionService.q1Addition("", "2");
	}
	
	@Test
	public void testAddNumber1Null() {
		thrown.expect(IllegalArgumentException.class);
		MathQuestionService.q1Addition(null, "2");
	}
	
	
	@Test
	public void testTrueSubtractPositive() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("4", "2"), 2, 0);
	}
	
	@Test
    public void testFalseSubtractInvalidNumber() {
		thrown.expect(NumberFormatException.class);
        MathQuestionService.q2Subtraction("2", "b");
    }
	
	@Test
    public void testTrueSubtractNegative() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("-1", "-2"), 1, 0);
    }
	
	@Test
    public void testTrueSubtractPositiveNegative() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("-3", "1"), -4, 0);
    }
	
	@Test
    public void testTrueSubtractZero() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("0", "2"), -2, 0);
    }
	
	@Test
	public void testSubtractNumber1Empty() {
		thrown.expect(IllegalArgumentException.class);
		MathQuestionService.q2Subtraction("", "2");
	}
	
	@Test
	public void testSubtractNumber1Null() {
		thrown.expect(IllegalArgumentException.class);
		MathQuestionService.q2Subtraction(null, "2");
	}
	
	@Test
	public void testTrueMultiplyPositive() {
		Assert.assertEquals(MathQuestionService.q3Multiplication("4", "2"), 8, 0);
	}
	
	@Test
    public void testFalseMultiplyInvalidNumber() {
		thrown.expect(NumberFormatException.class);
        MathQuestionService.q3Multiplication("4", "c");
    }
	
	@Test
    public void testTrueMultiplyNegative() {
		Assert.assertEquals(MathQuestionService.q3Multiplication("-1", "-2"), 2, 0);
    }
	
	@Test
    public void testTrueMultiplyPositiveNegative() {
		Assert.assertEquals(MathQuestionService.q3Multiplication("-3", "2"), -6, 0);
    }
	
	@Test
    public void testTrueMultiplyZero() {
		Assert.assertEquals(MathQuestionService.q3Multiplication("0", "2"), 0, 0);
    }
	
	@Test
	public void testMultiplyNumber1Empty() {
		thrown.expect(IllegalArgumentException.class);
		MathQuestionService.q3Multiplication("", "2");
	}
	
	@Test
	public void testMultiplyNumber1Null() {
		thrown.expect(IllegalArgumentException.class);
		MathQuestionService.q3Multiplication(null, "2");
	}
}
