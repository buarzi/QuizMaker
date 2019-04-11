package questions;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Question {
    private String text;
    private Answers answers;
    private Dificullty dificullty;

    public Question(String text, Answers answers, Dificullty dificullty) {
        this.text = text;
        this.answers = answers;
        this.dificullty = dificullty;
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

    public Dificullty getDificullty() {
        return dificullty;
    }

    public void setDificullty(Dificullty dificullty) {
        this.dificullty = dificullty;
    }
}
