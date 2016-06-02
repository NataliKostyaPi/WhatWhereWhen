package GAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 26.04.2016.
 */
public class QuestionManager extends Screen {
        public QuestionManager(JFrame f) {
            super(f);
        }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {
        final JTextField  questionText = new JTextField("Enter new question");
        final JTextField  answerText = new JTextField("Enter answer");
        JButton B1 = new JButton("Add Question");

        B1.setVisible(true);

        B1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     DBHandler.getInstance().insert(new Quiz(questionText.getText(), answerText.getText()));
                                     Quiz.addQuiz(new Quiz(questionText.getText(), answerText.getText()));
                                     closeWindow();
                                 }
                             }
        );
        jpanel.setLayout(null);
        questionText.setBounds(140,100, 1000, 300);
        answerText.setBounds(240, 430, 800, 40);
        B1.setBounds(540, 500, 200, 80);
        jpanel.add(questionText);
        jpanel.add(answerText);
        jpanel.add(B1);
    }
}


