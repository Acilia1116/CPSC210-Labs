package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.exceptions.OutOfTriesException;
import model.question.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LimitedTriesQuizTest extends QuizTest {
    LimitedTriesQuiz limitedTriesQuiz;

    @BeforeEach
    void runBefore() {
        QuestionList questionList = generateQuestionList();
        limitedTriesQuiz = new LimitedTriesQuiz(questionList);
        quiz = limitedTriesQuiz;
    }

    // verify total max mark in Quiz is 6
    @Test
    void testConstructor() {
        super.testConstructor();
        assertEquals(6, quiz.getMaxMark());
    }

    // MaxMark = 6;
    // submit once and correct;
    // MaxMark = 6;
    // return "Correct!"

    @Test
    void testSubmitAnswerCorrect() {
        assertEquals(6, limitedTriesQuiz.getMaxMark());
        limitedTriesQuiz.getNextQuestion();
        try {
            assertEquals("Correct!", limitedTriesQuiz.submitAnswer("Earth"));
            assertEquals(6, limitedTriesQuiz.getMaxMark());
        } catch (AnswerIncorrectException e) {
            fail();
        } catch (OutOfTriesException e) {
            fail();
        }
    }


    // MaxMark = 6;
    // submit once but incorrect;
    // MaxMark = 5;
    // still have chance to re-try
    // return "Please try it again";

    @Test
    void testSubmitAnswerIncorrect() {
        assertEquals(6, limitedTriesQuiz.getMaxMark());
        limitedTriesQuiz.getNextQuestion();
        try {
            limitedTriesQuiz.submitAnswer("earth");
        } catch (AnswerIncorrectException e) {
            try {
                assertEquals("Please try it again", limitedTriesQuiz.submitAnswer("earth"));
                assertEquals(5, limitedTriesQuiz.getMaxMark());
            } catch (AnswerIncorrectException ex) {
                throw new RuntimeException(ex);
            } catch (OutOfTriesException ex) {
                fail();
            }
        } catch (OutOfTriesException e) {
            fail();
        }
    }

    // MaxMark = 6;
    // submit answer and ran out of attempts;
    // MaxMark = 1;
    // return "No more attempt";
    @Test
    void testSubmitAnswerFail() {
        assertEquals(6, limitedTriesQuiz.getMaxMark());
        int next = limitedTriesQuiz.getNextQuestion().getMaxMark();

        for (int i =0; i < next - 1 ; i++ ) {
            try {
                limitedTriesQuiz.submitAnswer("arth");
            } catch (AnswerIncorrectException e) {
            } catch (OutOfTriesException e) {
               fail();
            }
        }
        try {
            limitedTriesQuiz.submitAnswer("arth");
            fail();
        } catch (AnswerIncorrectException e) {
            fail();
        } catch (OutOfTriesException e) {
            assertEquals("No more attempts",e.getMessage());
        }
    }
}
