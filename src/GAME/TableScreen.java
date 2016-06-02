package GAME;

import gui.GUI;
import gui.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.BevelBorder;


public class TableScreen extends JDialog {



    boolean isFirstPress = true;
    long timeOfstart;
    TableSector sector;
    GameScore gameScore;
    int questionNumber = 0;
    int[] chosenSectors = new int[13];
    static Team[] teams = null;

    public static void setTeams(Team[] teams) {
        TableScreen.teams = teams;
    }

    public TableScreen(final JFrame f) {
        super(f, "???? ???? ??????");
        System.out.println(teams);
        if(teams == null) {
            f.dispose();
            JOptionPane.showMessageDialog(null, "Перед игрой необходимо задать команды в настройках");
            GUI gui = new GUI(1280, 720);
        }
        else {
            this.setVisible(true);
        }
        gameScore = new GameScore(teams);
        System.out.println("2");
        final TableSectorFinished tableSectorFinished = new TableSectorFinished();
        final TeamTable teamTable = new TeamTable(gameScore);
        this.setSize(1280, 760);
        this.setModal(true);
        setResizable(false);
        sector = new TableSector("Data/sector__1.png");
        sector.setVisible(false);

        final ForcePlaceHolder force = new ForcePlaceHolder();
        force.setBounds(MyCrc.PICTURE_CENTER_X - 150, MyCrc.PICTURE_CENTER_Y * 2 + 30, 300, 20);

        final ForcePlaceHolder forceValue = new ForcePlaceHolder();
        forceValue.setBounds(MyCrc.PICTURE_CENTER_X- 150, MyCrc.PICTURE_CENTER_Y * 2 + 30, 300, 20);
        forceValue.changeBackground("Data/force_bkgr.png");


        ForcePlaceHolder forcePlaceHolder = new ForcePlaceHolder();

        TablePanel leftPanel = new TablePanel();
        leftPanel.setBounds(0, 0, 640, 740);

        final MyCrc P = new MyCrc();
        P.setBounds(0, 0, 640, 740);
        final JButton B1 = new JButton(Strings.START);
        B1.setBorder(null);
        B1.setVisible(true);

        P.setAfterArrowChose(new AfterArrowChoseListener() {
            @Override
            public void onArrowFinish(final int sectorChosen) {

                System.out.println("sectorChosen " + sectorChosen);
                final int nextSector = getForwardedSector(sectorChosen);
                System.out.println("forwarded to " + nextSector);
                sector.setVisible(false);
                sector.changeBackground("Data/sector__"+nextSector+".png");
                sector.setVisible(true);
                new Timer(300, new Runnable() {
                    boolean isVisible = true;
                    int timer = 1500;
                    @Override
                    public void run() {
                        if (timer > 0) {
                            sector.setVisible(isVisible);
                            isVisible = !isVisible;
                            timer -= 300;
                            new Timer(300, this);
                        }
                    }
                });
                new Timer(1500, new Runnable() {
                    @Override
                    public void run() {

                        //P.add(new QuestionPanel());
                        QuestionScreen.setOnAnswerGiven(new QuestionScreen.OnAnswerGiven() {
                            @Override
                            public void onAnswer(String[] teamsAnswers ) {
                                if(questionNumber == 12) {
                                    GameOverScreen gameOverScreen = new GameOverScreen(f, new Object[] {gameScore});
                                    setVisible(false);
                                    dispose();
                                }
                                B1.setVisible(true);


                                gameScore.setAnswer(0,questionNumber,teamsAnswers[0]);
                                gameScore.setAnswer(1,questionNumber,teamsAnswers[1]);
                                teamTable.refreshTables(gameScore);
                                sector.setVisible(false);

                                chosenSectors[questionNumber] = nextSector;

                                if(questionNumber == 0)
                                {
                                    tableSectorFinished.changeBackground("Data/sector_finished_"+ nextSector +".png");

                                } else if(questionNumber == 1){
                                    tableSectorFinished.changeBackground(ImageMerger.merge("Data/sector_finished_"+ chosenSectors[0] +".png","Data/sector_finished_"+ nextSector +".png"));

                                } else {
                                    tableSectorFinished.changeBackground(ImageMerger.merge("Data/combined.png","Data/sector_finished_"+ nextSector +".png"));
                                }
                                //tableSectorFinished[0].changeBackground(ImageMerger.merge("Data/sector_finished_1.png","Data/sector_finished_2.png"));
                                tableSectorFinished.setVisible(true);
                                questionNumber++;
                            }
                        });
                        final QuestionScreen qs = new QuestionScreen(f, teams);
                    }
                });
            }
        });

        B1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     if (isFirstPress) {
                                         //????????? ????? ???????? ??? ????????????? ? ???????
                                         timeOfstart = System.currentTimeMillis();
                                         //TODO: ?????????? ?? ??????
                                         startDisplaingTime(P, B1, forceValue  , timeOfstart);
                                     } else {
                                         //????????? ??????? ??????? ?????? ? ??????? ??????? ???????
                                         P.start(System.currentTimeMillis() - timeOfstart);
                                         B1.setVisible(false);

                                     }
                                     isFirstPress = !isFirstPress;//change value
                                 }
                             }
        );

        B1.setBounds(MyCrc.PICTURE_CENTER_X - 40, MyCrc.PICTURE_CENTER_Y - 25, 80, 60);

        JButton emergencyStop = new JButton(Strings.EMERGENCY_STOP);
        emergencyStop.setBounds(getWidth()-150, getHeight()-70, 150, 50);
        emergencyStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOverScreen gameOverScreen = new GameOverScreen(f, new Object[] {gameScore});
                setVisible(false);
                dispose();
            }
        });
        this.add(emergencyStop);
        P.setLayout(null);
        P.add(B1);

       // force.changeBackground(ImageMerger.scaledMerge("Data/force_bkgr.png", "Data/force.png",10,10));
        this.add(forceValue);

       this.add(force);

        this.add(P);
        this.add(tableSectorFinished);
        this.add(sector);
        this.add(leftPanel);
        this.add(teamTable);

        this.add(new MyPanel("Data/Fontab.jpg"));


    }


    private void startDisplaingTime(final MyCrc P, final JButton B1, final ForcePlaceHolder forceValue, final long timeOfstart) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long timer = System.currentTimeMillis();

                while (!isFirstPress) {
                    if (System.currentTimeMillis() - timer > 33) {
                        int forceNow = (int) (System.currentTimeMillis() - timeOfstart);

                        forceValue.setBounds((MyCrc.PICTURE_CENTER_X - 150) + (forceNow / 20), MyCrc.PICTURE_CENTER_Y * 2 + 30, 300 - (forceNow / 20), 20);

                        if (System.currentTimeMillis() - timeOfstart > 6000) {
                            P.start(System.currentTimeMillis() - timeOfstart);
                            B1.setVisible(false);
                            isFirstPress = !isFirstPress;
                        }
                    }
                }
            }
        }).start();

    }
private int getForwardedSector(int startSector) {
    for (int i = 0; i < chosenSectors.length; i++) {
        if(chosenSectors[i] == startSector) {
            startSector++;
            if(startSector == 14) {
                startSector = 1;
            }
            i=-1;
        }

    }
    return startSector;
}
    private class TablePanel extends MyPanel {
        public TablePanel() {
            super("Data/table.png");
            setSize(640, 740);
        }
    }
    private class TableSectorFinished extends MyPanel {
        public TableSectorFinished() {
            super("Data/sector_finished_1.png");
            setSize(640, 740);
            setVisible(false);
        }
    }
    private class TableSector extends MyPanel {
        public TableSector(String picturePath) {
            super(picturePath);
            setSize(640, 740);
            setVisible(false);
        }
    }
    private class ForcePlaceHolder extends MyPanel {
        public ForcePlaceHolder() {
            super("Data/force.png");
            setSize(100, 60);

        }
    }
}