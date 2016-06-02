package GAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 26.04.2016.
 */
public class SettingsScreen extends Screen {
    public SettingsScreen(final JFrame f) {
        super(f, "Data/FonForSettings.jpg", "?????????");
    }

    @Override
    protected void setGui(final JFrame f, final JPanel jp) {


        jp.setLayout(null);
        JButton setTeams = new JButton(Strings.TEAMS);

        setTeams .setVisible(true);
        setTeams .addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     TeamScreen ff = new TeamScreen(f);
                                     f.setVisible(false);
                                 }
                             }
        );
        setTeams .setBounds(490, 230, 300, 60);
        jp.add(setTeams );
        JButton B1 = new JButton(Strings.ADD_QUESTION);
        B1.setVisible(true);
        B1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     AddQuestion ff = new AddQuestion(f);

                                 }
                             }
        );
        B1.setBounds(490, 300, 300, 60);
        jp.add(B1);

        JButton B2 = new JButton(Strings.CHANGE_QUESTION);
        B2.setVisible(true);
        B2.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     ShowQuiz ff = new ShowQuiz(f);

                                 }
                             }
        );
        B2.setBounds(490, 370, 300, 60);
        jp.add(B2);

        JButton B3 = new JButton(Strings.ADD_WARMUP_WORDS);
        B3.setVisible(true);
        B3.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     AddWord ff = new AddWord(f);

                                 }
                             }
        );
        B3.setBounds(490, 440, 300, 60);
        jp.add(B3);
        JButton B4 = new JButton(Strings.CHANGE_WARMUP_WORDS);
        B4.setVisible(true);
        B4.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     //TODO: chtoto
                                     f.setVisible(false);
                                     ShowWords ff = new ShowWords(f);
                                     f.setVisible(false);

                                 }
                             }
        );
        B4.setBounds(490, 510, 300, 60);
        jp.add(B4);
    }

}

