package GAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 26.04.2016.
 */
public class AddWord extends Screen {
        public AddWord(JFrame f) {

            super(f,"Data/Fon1111.jpg", "Внимание вопрос");
        }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {
        final JTextField  wordText = new JTextField(Strings.ENTER_WORD);
        JButton B1 = new JButton(Strings.ADD_WORD);
        B1.setVisible(true);
        B1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     DBHandler.getInstance().insertWord(wordText.getText());
                                     closeWindow();
                                 }
                             }
        );
        jpanel.setLayout(null);
        wordText.setBounds(frame.getWidth()/2-250, 100, 500, 300);
        B1.setBounds(frame.getWidth()/2-100, 500, 200, 80);
        jpanel.add(wordText);
        jpanel.add(B1);
    }
}


