package GAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 27.04.2016.
 */
public class ShowQuiz extends Screen {
    int shownQuestionId = 0;

    public ShowQuiz(JFrame f) {
        super(f,"Data/Fon1111.jpg", "Внимание вопрос");
    }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {
        super.setGui(frame, jpanel);
        if(getFirstQuiz()!=null);
        final JTextField questionText = new JTextField("" + getFirstQuiz().getQuestion());
        final JTextField answerText = new JTextField("" + getFirstQuiz().getAnswer());

        jpanel.setLayout(null);
        questionText.setBounds(140, 100, 1000, 300);
        answerText.setBounds(240, 430, 800, 40);
        jpanel.add(questionText);
        jpanel.add(answerText);

        JButton buttonSave = new JButton("Save question");
        buttonSave.setVisible(true);
        buttonSave.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent e) {
                                               Quiz quiz = new Quiz(questionText.getText(), answerText.getText());
                                               saveQuiz(quiz);
                                           }
                                       }
        );
        buttonSave.setBounds(120, 500, 200, 80);
        jpanel.add(buttonSave);


        JButton buttonPrev = new JButton("Previous question");
        buttonPrev.setVisible(true);
        buttonPrev.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             Quiz quizToShow = getPrevio1usQuiz();
                                             if (quizToShow != null) {
                                                 questionText.setText(quizToShow.getQuestion());
                                                 answerText.setText(quizToShow.getAnswer());
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
                                             Quiz quizToShow = getNextQuiz();
                                             if (quizToShow != null) {
                                                 questionText.setText(quizToShow.getQuestion());
                                                 answerText.setText(quizToShow.getAnswer());
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
                                               deleteQuiz();
                                               Quiz quizToShow = getPrevio1usQuiz();
                                               if (quizToShow != null) {
                                                   questionText.setText(quizToShow.getQuestion());
                                                   answerText.setText(quizToShow.getAnswer());
                                               }
                                               else {
                                                   quizToShow = getNextQuiz();
                                                   if (quizToShow != null) {
                                                       questionText.setText(quizToShow.getQuestion());
                                                       answerText.setText(quizToShow.getAnswer());
                                                   }else {
                                                       questionText.setText("");
                                                       answerText.setText("");
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
    private void saveQuiz(Quiz quiz){

        Quiz oldQuiz = Quiz.getQuizesList(shownQuestionId);
        DBHandler.getInstance().setQuiz(oldQuiz, quiz);
        Quiz.setQuiz(shownQuestionId, quiz);
    }

    private Quiz getNextQuiz() {
        shownQuestionId++;
        Quiz quizToShow = Quiz.getQuizesList(shownQuestionId);
        if (quizToShow == null) {
            shownQuestionId--;
        }
        return quizToShow;
    }

    private Quiz getPrevio1usQuiz() {
        if (shownQuestionId != 0) {
            shownQuestionId--;
            Quiz quizToShow = Quiz.getQuizesList(shownQuestionId);
            if (quizToShow == null) {
                shownQuestionId++;
            }
            return quizToShow;
        }
        return null;
    }

    private void deleteQuiz() {

        DBHandler.getInstance().delete(Quiz.getQuizesList(shownQuestionId).getQuestion());
        Quiz.deleteQuizefromList(shownQuestionId);
    }

    private Quiz getFirstQuiz() {
        Quiz quizToShow = Quiz.getQuizesList(0);
        return quizToShow;
    }
}
