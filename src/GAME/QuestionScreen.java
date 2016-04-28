package GAME;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kostya on 28.04.2016.
 */
public class QuestionScreen extends Screen{
    Quiz quiz;
    public QuestionScreen(JFrame f) {
        super(f, "Data/Fon1111.jpg", "Внимание вопрос");

    }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {

        jpanel.setLayout(null);

        JLabel questionLabel = new JLabel((this.quiz = Quiz.getRandomQuiz()).getQuestion());
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 40));

        questionLabel.setBounds(440,200,400,200);


        final JLabel answerLabel = new JLabel();
        answerLabel.setHorizontalAlignment(JLabel.CENTER);
        answerLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 40));

        answerLabel.setBounds(440,300,400,200);

        JButton buttonSave = new JButton("Answer");
        buttonSave.setVisible(true);
        buttonSave.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             answerLabel.setText(quiz.getAnswer());
                                         }
                                     }
        );
        buttonSave.setBounds(540, 500, 200, 80);
        
        buttonSave.setBounds(540, 500, 200, 80);
        jpanel.add(buttonSave);
        jpanel.add(questionLabel);
        jpanel.add(answerLabel);

    }
}
