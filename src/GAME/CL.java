package GAME;

import gui.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class CL extends JDialog {

    private final String forceText = "Force";

    boolean isFirstPress = true;
    long timeOfstart;
    TableSector sector;
    GameScore gameScore;
    static int questionNumber = 0;
    static int[] chosenSectors = new int[13];
    public CL(final JFrame f, final Team[] teams) {
        super(f, "���? ���? �����?");
        gameScore = new GameScore(teams);
        final TableSectorFinished tableSectorFinished = new TableSectorFinished();
        final TeamTable teamTable = new TeamTable(gameScore);
        this.setSize(1280, 760);
        this.setModal(true);
        setResizable(false);
        sector = new TableSector("Data/sector__1.png");
        sector.setVisible(false);
        final JLabel forceLabel = new JLabel(forceText);
        forceLabel.setFont(new Font(forceLabel.getFont().getName(), Font.PLAIN, 25));
        //idLabel.setHorizontalAlignment(JTextField.LEFT);
        TablePanel leftPanel = new TablePanel();
        leftPanel.setBounds(0, 0, 640, 740);
        final MyCrc P = new MyCrc();
        P.setBounds(0, 0, 640, 740);
        final JButton B1 = new JButton("START");

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
                new Timer(1500, new Runnable() {
                    @Override
                    public void run() {

                        //P.add(new QuestionPanel());
                        QuestionScreen.setOnAnswerGiven(new QuestionScreen.OnAnswerGiven() {
                            @Override
                            public void onAnswer(String[] teamsAnswers ) {
                                B1.setVisible(true);
                                forceLabel.setText(""+forceText);
                                gameScore.setAnswer(0,questionNumber,teamsAnswers[0]);
                                gameScore.setAnswer(1,questionNumber,teamsAnswers[1]);
                                teamTable.refreshTables(gameScore);
                                sector.setVisible(false);
                                //tableSectorFinished[sectorChosen].setVisible(true);

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
                                         //поллучить время записать для использования в тстарте
                                         timeOfstart = System.currentTimeMillis();
                                         //TODO: отобразить на экране
                                         startDisplaingTime(P, B1, forceLabel, timeOfstart);
                                     } else {
                                         //вычислить сколько времени прошло с момента первого нажатия
                                         P.start(System.currentTimeMillis() - timeOfstart);
                                         B1.setVisible(false);

                                     }
                                     isFirstPress = !isFirstPress;//change value
                                 }
                             }
        );

        B1.setBounds(MyCrc.PICTURE_CENTER_X - 40, MyCrc.PICTURE_CENTER_Y - 25, 80, 60);
        forceLabel.setBounds(MyCrc.PICTURE_CENTER_X, MyCrc.PICTURE_CENTER_Y * 2 + 10, 150, 20);
        P.setLayout(null);
        P.add(B1);
        P.add(forceLabel);
        this.add(P);
        this.add(tableSectorFinished);
        this.add(sector);
        this.add(leftPanel);
        this.add(teamTable);

        this.add(new MyPanel("Data/Fontab.jpg"));


    }


    private void startDisplaingTime(final MyCrc P, final JButton B1, final JLabel forceLabel, final long timeOfstart) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long timer = System.currentTimeMillis();

                while (!isFirstPress) {
                    if (System.currentTimeMillis() - timer > 33) {
                        int forceNow = (int) (System.currentTimeMillis() - timeOfstart);
                        forceLabel.setText(forceText + ": " + forceNow);
                        forceLabel.setFont(new Font(forceLabel.getFont().getName(), Font.PLAIN, 25));

                        if (System.currentTimeMillis() - timeOfstart > 5000) {
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
            i=-1;
        }
    }
    return startSector;
}
    private class TablePanel extends MyPanel {
        public TablePanel() {
            super("Data/table.png");
            setSize(640, 760);
        }
    }
    private class TableSectorFinished extends MyPanel {
        public TableSectorFinished() {
            super("Data/sector_finished_1.png");
            setSize(640, 760);
            setVisible(false);
        }
    }
    private class TableSector extends MyPanel {
        public TableSector(String picturePath) {
            super(picturePath);
            setSize(640, 760);
            setVisible(false);
        }
    }
}