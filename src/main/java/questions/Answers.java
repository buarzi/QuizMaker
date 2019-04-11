package questions;

import java.util.*;

public class Answers {
    private Map<String, Boolean> answers;
    private static final int MAX_ANSWERS = 3;

    public Answers() {
        answers = new HashMap<>();
    }

    public void addAnswer(String text, boolean isCorrect) {
        if (answers.size() > MAX_ANSWERS) {
            throw new InputMismatchException("List is full.");
        } else {
            answers.put(text, isCorrect);
        }
    }

    Map<String, Boolean> getAnswers() {
        return answers;
    }
}
