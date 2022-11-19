package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.question.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnlimitedTriesQuizTest extends QuizTest{
    UnlimitedTriesQuiz unlimitedTriesQuiz;

    @BeforeEach
    void runBefore() {
        QuestionList questionList = generateQuestionList();
        unlimitedTriesQuiz = new UnlimitedTriesQuiz(questionList);
        quiz = unlimitedTriesQuiz;
    }

    // VVerify total max mark in Quiz is 6
    @Test
    void testConstructor() {
        super.testConstructor();
        assertEquals(6, quiz.getMaxMark());
    }

    // verify submit answer once and correct;
    // numAttempts = 1;
    // length = 1;
    // return "Correct!"
    @Test
    void testSubmitAnswerCorrect() {
        unlimitedTriesQuiz.getNextQuestion();
        try {
            unlimitedTriesQuiz.submitAnswer("Earth");
            assertEquals("It took you 1 attempts to answer 2 questions correctly.",
                    unlimitedTriesQuiz.endQuiz());
            assertEquals(1, unlimitedTriesQuiz.getNumAttempts());
            assertEquals("Correct!",unlimitedTriesQuiz.submitAnswer("Earth"));
        } catch (AnswerIncorrectException e) {
            fail();
        }

    }

    // verify submit answer once but incorrect;
    // numAttempts = 1;
    // length = 1;
    // return "Please try it again"
    @Test
    void testSubmitAnswerIncorrect() {
        unlimitedTriesQuiz.getNextQuestion();
        try {
            unlimitedTriesQuiz.submitAnswer("arth");
            fail();
        } catch (AnswerIncorrectException e) {
            try {
                assertEquals("Please try it again", unlimitedTriesQuiz.submitAnswer("arth"));
                unlimitedTriesQuiz.endQuiz();
                assertEquals(1, unlimitedTriesQuiz.getNumAttempts());
            } catch (AnswerIncorrectException ex) {
                // my answer is incorrect
            }
        }
    }



    // submit answer correct, submit next answer and correct;

}
