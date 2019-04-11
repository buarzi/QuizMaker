package functions;

import questions.Answers;
import questions.Dificullty;
import questions.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizMaker {
    private static final String fileName = "questions.json";
    private List<Question> questionList;
    private static final int MAX_ANSWERS = 3;

    public QuizMaker() {
        // TODO: Read file to String -> convert JSON to Objects
        try {
            FileReader fileReader = new FileReader(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file " + fileName);
            e.printStackTrace();
        }
        //

        questionList = new LinkedList<>();
        Answers answers = new Answers();
        answers.addAnswer("A1", false);
        answers.addAnswer("A2", true);
        answers.addAnswer("A3", false);
        questionList.add(0, new Question("Q1", answers, Dificullty.EASY));
        questionList.add(1, new Question("Q2", answers, Dificullty.EASY));
        questionList.add(2, new Question("Q3", answers, Dificullty.EASY));
    }

    public List<Question> getQuestionList(Dificullty dificullty) {
        switch (dificullty) {
            case EASY:
                return questionList.stream()
                        .filter(q -> q.getDificullty().equals(Dificullty.EASY))
                        .collect(Collectors.toList());
            case MEDIUM:
                return questionList.stream()
                        .filter(q -> q.getDificullty().equals(Dificullty.MEDIUM))
                        .collect(Collectors.toList());
            case HARD:
                return questionList.stream()
                        .filter(q -> q.getDificullty().equals(Dificullty.HARD))
                        .collect(Collectors.toList());
            case ALL:
                return questionList;
            default:
                return questionList;
        }
    }

    public int getQuizSize() {
        return questionList.size();
    }
}
