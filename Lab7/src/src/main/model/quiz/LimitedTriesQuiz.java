package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.exceptions.OutOfTriesException;
import model.question.QuestionList;

public class LimitedTriesQuiz extends Quiz {
    public LimitedTriesQuiz(QuestionList questions) {
        super(questions);
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // if the answer is incorrect, decrements the max mark of the current question by one;
    // throws AnswerIncorrectException if the user should re-try the question
    // throws an OutOfTriesException if the answer is incorrect and no more
    // attempts are allowed
    @Override
    public String submitAnswer(String answer) throws AnswerIncorrectException, OutOfTriesException {
        boolean isCorrect = super.checkAnswer(answer);
        int tryAttempts = curQuestion.getMaxMark();
        if (!isCorrect) {
            if (tryAttempts <= 1) {
                throw new OutOfTriesException("No more attempts");
            } else {
                tryAttempts--;
                curQuestion.setMaxMark(tryAttempts);
                throw new AnswerIncorrectException("Please try it again");
            }
        }
        return "Correct!";
    }
}
