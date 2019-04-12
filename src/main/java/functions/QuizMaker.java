package functions;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import questions.Difficulty;
import questions.Question;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizMaker {
    private static final String fileName = "someQuestions.json";
    private List<Question> questionList;
    private static StringBuilder output;

    public QuizMaker() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            output = new StringBuilder();
            while (bufferedReader.ready()) {
                output.append(bufferedReader.readLine());
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Cannot find the file " + fileName);
            e.printStackTrace();
        }

        //Deserialize using Gson
        Type listType = new TypeToken<LinkedList<Question>>() {
        }.getType();
        Gson gson = new Gson();

        questionList = new LinkedList<>();
        questionList = gson.fromJson(output.toString(), listType);
    }

    public List<Question> getQuestionList(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return questionList.stream()
                        .filter(q -> q.getDifficulty().equals(Difficulty.EASY))
                        .collect(Collectors.toList());
            case MEDIUM:
                return questionList.stream()
                        .filter(q -> q.getDifficulty().equals(Difficulty.MEDIUM))
                        .collect(Collectors.toList());
            case HARD:
                return questionList.stream()
                        .filter(q -> q.getDifficulty().equals(Difficulty.HARD))
                        .collect(Collectors.toList());
            case ALL:
                return questionList;
        }
        return questionList;
    }

    public int getQuizSize() {
        return questionList.size();
    }
}
