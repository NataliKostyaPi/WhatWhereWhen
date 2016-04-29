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
    public QuestionScreen(JFrame f, Team[] teams) {
        super(f, teams,"Data/Fon1111.jpg", "Внимание вопрос");

    }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel, Object[] obj) {
        Team team1 = (Team) obj[0];
        Team team2 = (Team) obj[1];
        jpanel.setLayout(null);

        JLabel questionLabel = new JLabel((this.quiz = Quiz.getRandomQuiz()).getQuestion());
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 40));

        questionLabel.setBounds(440,100,400,200);


        final JLabel answerLabel = new JLabel();
        answerLabel.setHorizontalAlignment(JLabel.CENTER);
        answerLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 40));

        answerLabel.setBounds(440,200,400,200);
        String[] items = new String[1+team1.getPlayerNames().length + team2.getPlayerNames().length];
        items[0] = "Nobody";

        for (int i = 0; i < team1.getPlayerNames().length; i++)
        {
            items[i+1] = team1.getPlayerNames()[i];
        }

        for (int i = 0; i < team2.getPlayerNames().length; i++)
        {
            items[i + 1 + team1.getPlayerNames().length] = team2.getPlayerNames()[i];
        }
        JComboBox comboBox = new JComboBox(items);
        comboBox.setBounds(540, 300, 200, 80);

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
        jpanel.add(comboBox);

    }
}
