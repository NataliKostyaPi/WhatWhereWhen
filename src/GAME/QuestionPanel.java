package GAME;

import gui.MyPanel;

import javax.swing.*;
import java.awt.*;


/**
 * Created by kostya on 28.04.2016.
 */
public class QuestionPanel extends JPanel {
    public QuestionPanel()
    {
        this.setVisible(true);
        this.setSize(640, 720);
        this.setLocation(0, 0);
        Quiz quiz = Quiz.getRandomQuiz();
        final JLabel forceLabel = new JLabel("dsadfasdfasd"+ quiz.getQuestion());
        forceLabel.setFont(new Font(forceLabel.getFont().getName(), Font.PLAIN, 25));
        forceLabel.setBounds(100,100,600,300);
    }
}
