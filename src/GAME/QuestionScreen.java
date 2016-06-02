package GAME;

import gui.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kostya on 28.04.2016.
 */
public class QuestionScreen extends Screen {
    static int time = 60;
    private static OnAnswerGiven onAnswerGiven = new OnAnswerGiven() {
        @Override
        public void onAnswer(String[] teamsAnswers) {
            System.out.println("listener");
        }
    };
    private Quiz quiz;
    private Timer t1;
    boolean isTimerStoped = true;

    public QuestionScreen(JFrame f, Team[] teams) {
        super(f, teams, "Data/Fontime.jpg", "Внимание вопрос");

    }

    public static void setOnAnswerGiven(OnAnswerGiven onAnswerGiven) {
        QuestionScreen.onAnswerGiven = onAnswerGiven;
    }

    @Override
    protected void setGui(final JFrame frame, JPanel jpanel, Object[] obj) {
        final Team team1 = (Team) obj[0];
        Team team2 = (Team) obj[1];
        jpanel.setLayout(null);
        time = 60;
        isTimerStoped = true;
        quiz = Quiz.getRandomQuiz();
        JLabel questionLabel = new JLabel(""+stringSizeOptimizer(quiz.getQuestion()));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 40));
        questionLabel.setBounds(80, 200, frame.getWidth()-160, frame.getHeight() / 3);
        questionLabel.setVisible(true);

        final JLabel timerLabel = new JLabel(""+time);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 150));
        timerLabel.setForeground(Color.BLUE);
        timerLabel.setBounds(0, -30, 200, 200);
        timerLabel.setVisible(true);

        final JLabel answerLabel = new JLabel();
        answerLabel.setHorizontalAlignment(JLabel.CENTER);
        answerLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 40));
        answerLabel.setText(quiz.getAnswer());
        answerLabel.setVisible(false);
        answerLabel.setBounds(80, 550, frame.getWidth()-160, frame.getHeight() / 3);


        final JButton buttonStartTimer = new JButton(Strings.START_TIMER);
        buttonStartTimer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                isTimerStoped = !isTimerStoped;
                if (isTimerStoped) {
                    buttonStartTimer.setText(Strings.START_TIMER);
                } else {
                    buttonStartTimer.setText(Strings.STOP_TIMER);
                }

                timerLabel.setText(""+time);
                t1 = new Timer(1000, new Runnable() {
                    @Override
                    public void run() {
                        if (time >= 1 && isTimerStoped == false) {
                            if (time < 21) {
                                timerLabel.setForeground(Color.RED);
                                if (time < 11) {
                                    SoundHandler.beepSound("Sounds/beep.wav");
                                }
                            } else {
                                timerLabel.setForeground(Color.BLACK);
                            }
                            timerLabel.setText(""+time);
                            time--;
                            t1 = new Timer(1000, this);
                        } else {
                            timerLabel.setText(""+time);
                            timerLabel.setForeground(Color.BLUE);
                            if (isTimerStoped == false) {
                                SoundHandler.beepSound("Sounds/beep-final.wav");
                                isTimerStoped = true;
                            }
                        }
                    }
                });


            }
        });
        buttonStartTimer.setBounds(200, 20, 200, 40);


        final JLabel team1ComboLabel = new JLabel(Strings.TEAM+" 1");
        team1ComboLabel.setHorizontalAlignment(JLabel.CENTER);
        team1ComboLabel.setBounds(440, 20, 190, 40);
        team1ComboLabel.setVisible(false);

        final JLabel team2ComboLabel = new JLabel(Strings.TEAM + " 2");
        team2ComboLabel.setHorizontalAlignment(JLabel.CENTER);
        team2ComboLabel.setBounds(640, 20, 190, 40);
        team2ComboLabel.setVisible(false);

        final JComboBox comboBoxTeam1 = new JComboBox(getItems(team1));
        comboBoxTeam1.setBounds(440, 60, 190, 60);
        comboBoxTeam1.setVisible(false);

        final JComboBox comboBoxTeam2 = new JComboBox(getItems(team2));
        comboBoxTeam2.setBounds(640, 60, 190, 60);
        comboBoxTeam2.setVisible(false);

        JButton buttonResetTimer = new JButton(Strings.RESET_TIMER);
        buttonResetTimer.addActionListener(new ActionListener() {
                                               public void actionPerformed(ActionEvent e) {
                                                   time = 60;
                                                   isTimerStoped = true;
                                                   timerLabel.setForeground(Color.BLUE);
                                                   timerLabel.setText(""+time);
                                                   buttonStartTimer.setText(Strings.START_TIMER);
                                               }
                                           }
        );
        buttonResetTimer.setBounds(200, 80, 200, 40);
        final JButton buttonAnswer = new JButton(Strings.GET_ANSWER);
        buttonAnswer.addActionListener(new ActionListener() {
                                           boolean isFirstClick = true;

                                           public void actionPerformed(ActionEvent e) {
                                               System.out.println("isFirstClick "+isFirstClick);
                                               if (isFirstClick == true) {
                                                   buttonAnswer.setText(Strings.SEND_BACK);
                                                   answerLabel.setVisible(true);
                                                   comboBoxTeam1.setVisible(true);
                                                   comboBoxTeam2.setVisible(true);
                                                   team1ComboLabel.setVisible(true);
                                                   team2ComboLabel.setVisible(true);
                                                   isFirstClick = false;
                                               } else {
                                                   String[] teamsAnswers = {(String) comboBoxTeam1.getSelectedItem(), (String) comboBoxTeam2.getSelectedItem()};

                                                   onAnswerGiven.onAnswer(teamsAnswers);
                                                   closeWindow();

                                               }
                                           }
                                       }

        );
        buttonAnswer.setBounds(frame.getWidth()-400, 20, 300, 100);
        jpanel.add(timerLabel);
        jpanel.add(buttonStartTimer);
        jpanel.add(buttonResetTimer);

        jpanel.add(buttonAnswer);
        jpanel.add(questionLabel);
        jpanel.add(answerLabel);
        jpanel.add(team1ComboLabel);
        jpanel.add(team2ComboLabel);
        jpanel.add(comboBoxTeam1);
        jpanel.add(comboBoxTeam2);

    }

    private String[] getItems(Team team) {
        String[] itemsTeam1 = new String[team.getPlayerNames().length+1];
        itemsTeam1[0] = GameScore.NO_BODY;
        for (int i = 0; i < team.getPlayerNames().length; i++) {
            itemsTeam1[i+1] = team.getPlayerNames()[i];
        }
        return itemsTeam1;
    }

    private String stringSizeOptimizer(String input) {
        String[] arrays = input.split(" ");
        String result = "<html>";
        //<html>dddddddd<br> rrrrrrrrrrr</html>
        int charSum = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (charSum+arrays[i].length() < 45) {
                charSum += arrays[i].length();
                result += arrays[i]+" ";
            } else {
                charSum = arrays[i].length();
                result += "<br>"+arrays[i]+" ";
            }

        }
        return result+"</html>";
    }

    public interface OnAnswerGiven {
        public void onAnswer(String[] teamsAnswers);
    }
}
