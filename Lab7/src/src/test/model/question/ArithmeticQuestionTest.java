package model.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.question.ArithmeticQuestion.Operation.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticQuestionTest extends QuestionTest{
    private ArithmeticQuestion addQuestion;
    private ArithmeticQuestion subtractQuestion;
    private ArithmeticQuestion multiplyQuestion;

    @BeforeEach
    void runBefore() {
        addQuestion = new ArithmeticQuestion(10, ADDITION,3,7);
        subtractQuestion = new ArithmeticQuestion(20,SUBTRACTION,10,5);
        multiplyQuestion = new ArithmeticQuestion(30,MULTIPLICATION,10,10);
        question = new ArithmeticQuestion(40,ADDITION,10,10);
    }

    @Test
    void testConstructor() {
        assertEquals(10, addQuestion.getMaxMark());
        assertEquals(20,subtractQuestion.getMaxMark());
        assertEquals(30,multiplyQuestion.getMaxMark());
        // test operation and two operators;
    }

    @Test
    void testCheckAnswerCorrect() {
        assertTrue(addQuestion.isCorrect("10"));
        assertTrue(subtractQuestion.isCorrect("5"));
        assertTrue(multiplyQuestion.isCorrect("100"));
    }

    @Test
    void testCheckAnswerIncorrect() {
        assertFalse(addQuestion.isCorrect("21"));
        assertFalse(subtractQuestion.isCorrect("15"));
        assertFalse(multiplyQuestion.isCorrect("10"));
    }

    @Test
    void testQuestionString() {
        assertEquals("What is 3 + 7 ? [10 points]",addQuestion.getQuestionString());
        assertEquals("What is 10 - 5 ? [20 points]",subtractQuestion.getQuestionString());
        assertEquals("What is 10 * 10 ? [30 points]", multiplyQuestion.getQuestionString());
    }







}
