package buarzi.quizmaker;

import functions.QuizMaker;
import questions.Dificullty;
import questions.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainWindow {
    private JPanel mainPanel;
    private JTextField questionTextField;
    private JPanel contentPanel;
    private JPanel anwersPanel;
    private JPanel summaryPanel;
    private JButton a_button;
    private JButton c_button;
    private JButton b_button;
    private JLabel summaryField;
    private JLabel correctAnswersField;
    private JLabel correctAnswers;
    private JLabel wrongAnswers;
    private JLabel wrongAnswersField;
    private JLabel statisticsField;
    private JLabel statisticsPercent;
    private JComboBox difficultyComboBox;
    private JButton restartButton;
    private JPanel dificultyPanel;
    private List<JButton> answerButtons = new LinkedList<>();
    private List<Question> questions;
    private int actualQuestion = 0;
    private int quizSize;
    private String correctAnswer = "";
    private int numberOfCorrectAnswers = 0;
    private int numberOfWrongAnswers = 0;
    private double stats = 0.0;

    private void setCorrectAnswersField() {
        correctAnswers.setText(String.valueOf(numberOfCorrectAnswers));
    }

    private void setWrongAnswersField() {
        wrongAnswers.setText(String.valueOf(numberOfWrongAnswers));
    }

    private void setStatisticsField() {
        if (numberOfCorrectAnswers != 0 && actualQuestion != 0) {
            stats = new BigDecimal(((double) numberOfCorrectAnswers / actualQuestion) * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            stats = 0.0;
        }
        statisticsPercent.setText(stats + "%");
    }

    private void summaryForm() {
        JOptionPane.showMessageDialog(mainPanel, "Thank you for playing.\nCorrect answers: " + numberOfCorrectAnswers + "\nStatistics: " + stats + "%", "Game over!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void nextQuestion() {
        if (actualQuestion < quizSize) {
            //Get question
            questionTextField.setText(questions.get(actualQuestion).getQuestionText());
            int helper = 0;
            //Get answers
            for (Map.Entry<String, Boolean> entry : questions.get(actualQuestion).getAnswersFromQuestion().entrySet()) {
                answerButtons.get(helper).setText(entry.getKey());
                if (entry.getValue()) {
                    correctAnswer = entry.getKey();
                }
                helper++;
            }
            actualQuestion++;
        } else {
            questionTextField.setText("No more questions.");
            for (JButton answerButton : answerButtons) {
                answerButton.setEnabled(false);
            }
            summaryForm();
        }
    }

    private void isCorrect(String text) {
        if (text.equals(correctAnswer)) {
            numberOfCorrectAnswers++;
            setCorrectAnswersField();
        } else {
            numberOfWrongAnswers++;
            setWrongAnswersField();
        }
        setStatisticsField();
    }

    private void initAfterReset() {
        actualQuestion = 0;
        numberOfCorrectAnswers = 0;
        numberOfWrongAnswers = 0;
        stats = 0.0;

        setCorrectAnswersField();
        setWrongAnswersField();
        setStatisticsField();

        for(JButton button : answerButtons) {
            button.setEnabled(true);
        }
    }

    private MainWindow() {
        //Add buttons to list
        answerButtons.add(a_button);
        answerButtons.add(b_button);
        answerButtons.add(c_button);

        //Create a quiz
        QuizMaker quizMaker = new QuizMaker();
        questions = quizMaker.getQuestionList(Dificullty.ALL);
        quizSize = quizMaker.getQuizSize();

        //Add an action listener for reset button (and init afer)
        restartButton.addActionListener(e -> {
            String difficullty = (String) difficultyComboBox.getSelectedItem();
            if (difficullty != null) {
                switch (difficullty) {
                    case "Easy":
                        questions = questions = quizMaker.getQuestionList(Dificullty.EASY);
                        initAfterReset();
                        nextQuestion();
                        break;
                    case "Medium":
                        questions = questions = quizMaker.getQuestionList(Dificullty.MEDIUM);
                        initAfterReset();
                        nextQuestion();
                        break;
                    case "Hard":
                        questions = questions = quizMaker.getQuestionList(Dificullty.HARD);
                        initAfterReset();
                        nextQuestion();
                        break;
                    case "":
                        questions = questions = quizMaker.getQuestionList(Dificullty.ALL);
                        initAfterReset();
                        nextQuestion();
                        break;
                }
            }
        });

        //Add action liseners for answer buttons
        for (JButton answerButton : answerButtons) {
            answerButton.addActionListener(e -> nextQuestion());
            answerButton.addActionListener(e -> isCorrect(answerButton.getText()));
        }

        //init - load the first question
        nextQuestion();

        JFrame frame = new JFrame("Let's make a quiz!");
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
