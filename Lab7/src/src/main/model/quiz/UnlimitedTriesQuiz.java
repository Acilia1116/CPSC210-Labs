package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.exceptions.OutOfTriesException;
import model.question.QuestionList;

public class UnlimitedTriesQuiz extends Quiz {
    private int numAttempts;

    public UnlimitedTriesQuiz(QuestionList questions) {
        super(questions);
        this.numAttempts = 0;
    }

    // EFFECTS: returns number of attempts taken to answer questions so far
    public int getNumAttempts() {
        return numAttempts;
    }

    // EFFECTS: returns number of attempts taken to answer questions so far
    // public int getNumAttempts()
    @Override
    public String endQuiz() {
        return "It took you " + getNumAttempts() + " attempts to answer " + questions.length()
                + " questions correctly.";
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // does not modify max mark of current question;
    // throws AnswerIncorrectException if the user should re-try the question;
    @Override
    public String submitAnswer(String answer) throws AnswerIncorrectException {
        boolean isCorrect = super.checkAnswer(answer);
        numAttempts++;
        if (!isCorrect) {
            throw new AnswerIncorrectException("Please try it again");
        }
        return "Correct!";
    }
}
