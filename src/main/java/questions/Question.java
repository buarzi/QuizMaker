package questions;

import java.util.Map;

public class Question {
    private String text;
    private Answers answers;
    private Difficulty difficulty;

    public Question(String text, Answers answers, Difficulty difficulty) {
        this.text = text;
        this.answers = answers;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return text;
    }

    public void setQuestionText(String text) {
        this.text = text;
    }

    public Map<String, Boolean> getAnswersFromQuestion() {
        return answers.getAnswers();
    }

    public void setAnswersForQuestion(Answers answers) {
        this.answers = answers;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
