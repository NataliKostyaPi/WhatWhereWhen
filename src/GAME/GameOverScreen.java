package GAME;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kostya on 17.05.2016.
 */
public class GameOverScreen extends Screen {

    public GameOverScreen(JFrame f, Object[] obj) {
        super(f, obj, "Data/Fonowl.jpg", "Что? где? когда?");
    }


    @Override
    protected void setGui(final JFrame frame, JPanel jpanel, Object[] obj) {
        super.setGui(frame, jpanel, obj);
        final GameScore gameScore = (GameScore) obj[0];

        JLabel team1NameLabel = setupJLabel(jpanel, "Название команды: ",40);
        team1NameLabel.setBounds(0, 70, frame.getWidth()/2, 50);

        JLabel team1Name = setupJLabel(jpanel, ""+gameScore.getTeamName(0),40);
        team1Name.setBounds(0, 130, frame.getWidth()/2, 50);

        JLabel team1Score = setupJLabel(jpanel, "Счет: "+gameScore.getTeamScore(0),40);
        team1Score.setBounds(0, 200, frame.getWidth()/2, 50);


        JLabel team2NameLabel = setupJLabel(jpanel, "Название команды: ",40);
        team2NameLabel.setBounds(frame.getWidth()/2, 70, frame.getWidth()/2, 50);

        JLabel team2Name = setupJLabel(jpanel, ""+gameScore.getTeamName(1),40);
        team2Name.setBounds(frame.getWidth()/2, 130, frame.getWidth()/2, 50);

        JLabel team2Score = setupJLabel(jpanel, "Счет: "+gameScore.getTeamScore(1),40);
        team2Score.setBounds(frame.getWidth()/2, 200, frame.getWidth()/2, 50);

        JLabel winner = setupJLabel(jpanel, "Победитель: "+gameScore.getTeamName(gameScore.getWinnerTeam()),45);
        winner.setBounds(0, 330, frame.getWidth(), 50);
        winner.setForeground(Color.RED);

        JLabel bestPlayer = setupJLabel(jpanel, "Лучший игрок: "+gameScore.getBestPlayer(),35);
        bestPlayer.setBounds(0, 400, frame.getWidth(), 50);
        gameScore.setGameDate(GameScore.getDateNow());
        final JTextField date = new JTextField("" + gameScore.getStringGameDate());
        date.setBounds((frame.getWidth()/2) - 100 , 525, 200, 50);
        date.setHorizontalAlignment(JTextField.CENTER);
        date.setFont(new Font(date.getFont().getName(), Font.PLAIN, 20));

        JButton toMainMenu = new JButton("В главное меню");
        toMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(dateTest(date.getText())) {
                gameScore.setGameDate(date.getText());
                saveData(gameScore);
                closeWindow();
                GUI gui = new GUI(1280, 720);
            }
            else {
                date.setBackground(Color.red);
            }

            }
        }
        );
        toMainMenu.setBounds(150, 500, 300, 100);
        toMainMenu.setFont(new Font(toMainMenu.getFont().getName(), Font.PLAIN, 30));
        jpanel.add(toMainMenu);

        JButton newGame = new JButton("Начать новую игру");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(dateTest(date.getText())) {
                    gameScore.setGameDate(date.getText());
                    saveData(gameScore);
                    closeWindow();
                    TableScreen.setTeams(gameScore.getTeams());
                    TableScreen cl = new TableScreen(frame);
                    cl.setVisible(true);
                }
                else {
                    date.setBackground(Color.red);
                }

            }
        }
        );
        newGame.setBounds(frame.getWidth()-450, 500, 300, 100);
        newGame.setFont(new Font(toMainMenu.getFont().getName(), Font.PLAIN, 30));





        gameScore.setGameDate(GameScore.getDateNow());

        jpanel.add(date);
        jpanel.add(newGame);
    }
    private void saveData(GameScore gameScore) {
        DBHandler.getInstance().insertTeamStatistic(gameScore,0);
        DBHandler.getInstance().insertTeamStatistic(gameScore,1);
    }
    private JLabel setupJLabel(JPanel jpanel, String name, int fontSize) {
        JLabel jLabel = new JLabel(name);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setFont(new Font(jLabel.getFont().getName(), Font.PLAIN, fontSize));
        jLabel.setVisible(true);
        jpanel.add(jLabel);
        return jLabel;
    }
    private boolean dateTest(String date){
        if(date.length() != 10) {
            return false;
        } else if( !(("" + date.charAt(2)).equals(".") ) || !(("" + date.charAt(5)).equals(".") )  ) {
            return false;
        }else if( !Character.isDigit(date.charAt(0)) ||
                !Character.isDigit(date.charAt(1)) ||
                !Character.isDigit(date.charAt(3)) ||
                !Character.isDigit(date.charAt(4)) ||
                !Character.isDigit(date.charAt(6)) ||
                !Character.isDigit(date.charAt(7)) ||
                !Character.isDigit(date.charAt(8)) ||
                !Character.isDigit(date.charAt(9)) ) {
            return false;
        }else if( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) > 12 || Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) < 1 ) {
            return false;
        } else if( Integer.parseInt("" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9) ) < 2016 ) {
            return false;
        }
        else if( ( ( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 1 )
                || (Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 3 )
                || (Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 5 )
                ||  (Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 7 )
                ||  (Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 8 )
                ||  (Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 10 )
                ||  (Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 12 )) && Integer.parseInt("" + date.charAt(0) + date.charAt(1) ) > 31
                || (( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 4 )
                || ( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 9 )
                || ( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 11 )) && Integer.parseInt("" + date.charAt(0) + date.charAt(1) ) > 30
                || ( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 2 && Integer.parseInt("" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9) ) != 2016 && Integer.parseInt("" + date.charAt(0) + date.charAt(1) ) > 28)
                || ( Integer.parseInt("" + date.charAt(3) + date.charAt(4) ) == 2   && Integer.parseInt("" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9) ) == 2016
                && Integer.parseInt("" + date.charAt(0) + date.charAt(1) ) > 29)
                )  {
            return false;
        }

        return true;
    }

}
