package model.question;

import model.question.checker.AnswerChecker;
import model.question.checker.ArithmeticAnswerChecker;

public class ArithmeticQuestion extends Question {
    public enum Operation { ADDITION, SUBTRACTION, MULTIPLICATION }

    public ArithmeticQuestion(int maxMark, Operation operation, int firstOp, int secondOp) {
        super(maxMark, getQuestionString(operation, firstOp, secondOp), answerChecker(operation, firstOp,secondOp));
    }

    public static String getQuestionString(Operation operation, int firstOp, int secondOp) {
        String question = "";
        switch (operation) {
            case ADDITION:
                question = "What is " +  firstOp + " + " + secondOp + " ?";
                break;
            case MULTIPLICATION:
                question = "What is " + firstOp + " * " + secondOp + " ?";
                break;
            default:
                question = "What is " + firstOp + " - " + secondOp + " ?";
        }
        return question;
    }

    public static AnswerChecker answerChecker(Operation operation, int firstOp, int secondOp) {
        int answer = 0;
        switch (operation) {
            case ADDITION:
                answer = firstOp + secondOp;
                break;
            case MULTIPLICATION:
                answer = firstOp * secondOp;
                break;
            default:
                answer = firstOp - secondOp;
        }
        return new ArithmeticAnswerChecker(answer);
    }
}
