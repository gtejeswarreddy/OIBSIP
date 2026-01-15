package com.oasis.exam.ui;

import javax.swing.*;
import java.awt.*;

public class ExamForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private String username;

    private JLabel questionLabel, timerLabel;
    private JRadioButton op1, op2, op3, op4;
    private ButtonGroup bg;
    private JButton nextBtn, submitBtn;

    private Timer examTimer;
    private int timeLeft = 60;
    private int score = 0;
    private int index = 0;

    private String[] questions = {
            "Java is a?",
            "Who owns Java?",
            "Keyword used for inheritance?"
    };

    private String[][] options = {
            {"Language", "Browser", "OS", "Database"},
            {"Google", "Microsoft", "Oracle", "Apple"},
            {"this", "super", "extends", "final"}
    };

    private int[] answers = {0, 2, 2};

    public ExamForm(String username) {

        this.username = username;

        setTitle("Online Examination");
        setSize(500, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        timerLabel = new JLabel("Time Left: 60 sec", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setForeground(Color.RED);
        add(timerLabel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(6, 1));
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();

        bg = new ButtonGroup();
        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        center.add(questionLabel);
        center.add(op1);
        center.add(op2);
        center.add(op3);
        center.add(op4);

        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        nextBtn = new JButton("Next");
        submitBtn = new JButton("Submit");

        bottom.add(nextBtn);
        bottom.add(submitBtn);
        add(bottom, BorderLayout.SOUTH);

        loadQuestion();
        startTimer();

        nextBtn.addActionListener(_ -> nextQuestion());
        submitBtn.addActionListener(_ -> submitExam());

        setVisible(true);
    }

    private void loadQuestion() {
        bg.clearSelection();
        questionLabel.setText("Q" + (index + 1) + ". " + questions[index]);
        op1.setText(options[index][0]);
        op2.setText(options[index][1]);
        op3.setText(options[index][2]);
        op4.setText(options[index][3]);
    }

    private void nextQuestion() {
        checkAnswer();
        index++;

        if (index < questions.length) {
            loadQuestion();
        } else {
            submitExam();
        }
    }

    private void checkAnswer() {

        if (index >= answers.length) {
            return;
        }

        int selected = -1;
        if (op1.isSelected()) selected = 0;
        if (op2.isSelected()) selected = 1;
        if (op3.isSelected()) selected = 2;
        if (op4.isSelected()) selected = 3;

        if (selected == answers[index]) {
            score++;
        }
    }

    private void startTimer() {
        examTimer = new Timer(1000, _ -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " sec");

            if (timeLeft <= 0) {
                examTimer.stop();
                JOptionPane.showMessageDialog(this, "Time Over!");
                submitExam();
            }
        });
        examTimer.start();
    }

    private void submitExam() {
        if (examTimer != null) {
            examTimer.stop();
        }

        JOptionPane.showMessageDialog(this,
                "Exam Finished!\nScore: " + score + " / " + questions.length);

        dispose();
        new ResultForm(username, score);
    }
}
