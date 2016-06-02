package GAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 27.04.2016.
 */
public class ShowWords extends Screen {

    int wordShown = 0;
    public ShowWords(JFrame f) {
        super(f, "Data/Fon1111.jpg", "Внимание вопрос");
    }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {
        super.setGui(frame, jpanel);
        final String[] words = getWords();
        if (words[0] != null) ;
        final JTextField wordText = new JTextField(words[0]);

        jpanel.setLayout(null);
        wordText.setBounds(140, 100, 1000, 300);
        jpanel.add(wordText);
        JButton buttonSave = new JButton("Save word");
        buttonSave.setVisible(true);
        buttonSave.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             saveWord(words[wordShown], wordText.getText());
                                         }
                                     }
        );
        buttonSave.setBounds(120, 500, 200, 80);
        jpanel.add(buttonSave);


        JButton buttonPrev = new JButton("Previous question");
        buttonPrev.setVisible(true);
        buttonPrev.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {

                                             if (wordShown>0) {
                                                 wordShown--;
                                                 wordText.setText(words[wordShown]);
                                             }
                                         }
                                     }
        );
        buttonPrev.setBounds(330, 500, 200, 80);
        jpanel.add(buttonPrev);

        JButton buttonNext = new JButton("Next question");
        buttonNext.setVisible(true);
        buttonNext.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             if (wordShown < words.length - 1) {
                                                 wordShown++;
                                                 wordText.setText(words[wordShown]);
                                             }
                                         }
                                     }
        );

        buttonNext.setBounds(540, 500, 200, 80);
        jpanel.add(buttonNext);
        JButton buttonDelete = new JButton("Delete question");
        buttonDelete.setVisible(true);
        buttonDelete.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent e) {
                                               deleteWord(words[wordShown]);

                                               if (wordShown>0) {
                                                   wordShown--;
                                                   wordText.setText(words[wordShown]);
                                               } else {
                                                   if (wordShown < words.length - 1) {
                                                       wordShown++;
                                                       wordText.setText(words[wordShown]);
                                                   } else {
                                                       wordText.setText("");
                                                   }
                                               }
                                           }
                                       }
        );

        buttonDelete.setBounds(750, 500, 200, 80);
        jpanel.add(buttonDelete);

        JButton buttonBack = new JButton("Back");
        buttonBack.setVisible(true);
        buttonBack.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             closeWindow();
                                         }
                                     }
        );

        buttonBack.setBounds(960, 500, 200, 80);
        jpanel.add(buttonBack);


    }

    private void saveWord(String wordShown, String newWord){

        DBHandler.getInstance().setWord(wordShown, newWord);

    }


    private void deleteWord(String word) {

        DBHandler.getInstance().deleteWord(word);

    }

    private String[] getWords()
    {
        return DBHandler.getInstance().loadWarmup();
    }


}
