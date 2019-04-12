/*
  Use this file to create some data for your quiz.
  Example of simple serialize using Gson.
  This class creates a file called "someQuestions.json"
 */
package functions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import questions.Answers;
import questions.Difficulty;
import questions.Question;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CreateSomeQuestions {
    private List<Question> output;
    private static final int NUMBER_OF_QUESTIONS = 30;
    private static final int NUMBER_OF_ANSERS = 3;

    private CreateSomeQuestions() {
        output = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            Answers answers = new Answers();
            Random random = new Random();
            int correctAnswer = random.nextInt(3);

            for (int j = 0; j < NUMBER_OF_ANSERS; j++) {
                if (j == correctAnswer) {
                    answers.addAnswer("Answer " + j, true);
                } else {
                    answers.addAnswer("Answer " + j, false);
                }
            }
            Question question = new Question("Question " + i, answers, Difficulty.getDeifficultt(random.nextInt(3) + 1));
            output.add(i, question);
        }
    }

    private List<Question> getOutput() {
        return output;
    }

    public static void main(String[] args) {
        CreateSomeQuestions sampleQuestions = new CreateSomeQuestions();
        List<Question> questions = sampleQuestions.getOutput();

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter fileWriter = new FileWriter("someQuestions.json");

            fileWriter.write(gson.toJson(questions));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write a file.");
            e.printStackTrace();
        }
    }
}
