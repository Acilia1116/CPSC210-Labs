package model.question.checker;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ArithmeticAnswerCheckerTest {
    private ArithmeticAnswerChecker arithmeticAnswerChecker;

    @BeforeEach
    void runBefore() {
         arithmeticAnswerChecker = new ArithmeticAnswerChecker(10);
    }

    @Test
    void testValidInteger() {
        assertTrue(arithmeticAnswerChecker.checkAnswer("10"));
    }

    @Test
    void testDouble() {
        assertFalse(arithmeticAnswerChecker.checkAnswer("10.0"));
    }

    @Test
    void testOutOfRangeInt() {
        assertFalse(arithmeticAnswerChecker.checkAnswer(" -2147483649"));
        assertFalse(arithmeticAnswerChecker.checkAnswer(" 2147483649"));
    }

    @Test
    void testCharacter() {
        assertFalse(arithmeticAnswerChecker.checkAnswer("ten"));
    }
}
