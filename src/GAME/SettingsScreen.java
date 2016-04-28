package GAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 26.04.2016.
 */
public class SettingsScreen extends Screen {
    public SettingsScreen(final JFrame f) {
        super(f, "Data/FonForSettings.jpg", "Настройки");
    }

    @Override
    protected void setGui(final JFrame f, final JPanel jp) {
        jp.setLayout(null);
        JButton B1 = new JButton("Set Question");
        B1.setVisible(true);
        B1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     QuestionManager ff = new QuestionManager(f);
                                     f.setVisible(true);
                                 }
                             }
        );
        B1.setBounds(540, 300, 200, 60);
        jp.add(B1);

        JButton B2 = new JButton("Show Questions");
        B2.setVisible(true);
        B2.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     ShowQuiz ff = new ShowQuiz(f);
                                     f.setVisible(true);
                                 }
                             }
        );
        B2.setBounds(540, 370, 200, 60);
        jp.add(B2);

    }
}
