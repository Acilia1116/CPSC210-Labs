package model.question.checker;

public class ArithmeticAnswerChecker extends AnswerChecker {

    // Return false if  the user enters a non-integer value (e.g. 6.0, 5.2, -3.6).
    // Return false if the user enters an integer that cannot be represented as an int. (Beyond the range)
    // Return false if the user enters a sequence of characters that cannot be interpreted as an int.

    private int answer;

    // EFFECTS: constructs checker for given answer
    public ArithmeticAnswerChecker(int answer) {
        this.answer = answer;
    }


    @Override
    public boolean checkAnswer(String userResponse) {
        try {
            return answer == Integer.parseInt(userResponse);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
