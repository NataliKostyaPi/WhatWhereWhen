package GAME;

import gui.GUI;
import gui.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

/**
 * Created by MisterY on 27.04.2016.
 */
public class TeamScreen extends Screen {
    int height = 500;

    public TeamScreen(final JFrame f) {
        super(f, "Data/Fon1111.jpg", "Что? Где? Зубы лишние тварь?");


    }

    GeatherDataFromPanel[] geatherData;

    @Override
    protected void setGui(final JFrame frame, final JPanel jp) {


        this.setModal(true);
        geatherData = new GeatherDataFromPanel[2];
        Team.setAllTeams(DBHandler.getInstance().loadTeams());

        JPanel leftPanel = preparePanel(1);
        JPanel rightPanel = preparePanel(2);

        JLabel topLabel = new JLabel("Введите название команд и состав игроков");
        topLabel.setBounds(0,10,1280,20);
        topLabel.setHorizontalAlignment(JTextField.CENTER);
        topLabel.setFont(new Font(topLabel.getFont().getName(), Font.PLAIN, 20));

        MyPanel arrow = new MyPanel("Data/arrow.png");
        arrow.setBounds(426, 38, 10, 7);
        JLabel topLabel2 = new JLabel("(   выбор из ранее определенных, + внесение в таблицу новых.)");
        topLabel2.setBounds(0,30,1280,20);
        topLabel2.setHorizontalAlignment(JTextField.CENTER);
        topLabel2.setFont(new Font(topLabel.getFont().getName(), Font.PLAIN, 15));


        JButton B1 = new JButton("Сохранить");
        B1.setBounds(getSizeX() / 3, getSizeY() - 90, getSizeX() / 3, 50);
        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Team team1 = geatherData[0].geatherTeam();
                Team team2 = geatherData[1].geatherTeam();

                if (Team.isInList(team1)){
                    Team oldTeam = Team.addorUpdateTeam(team1);
                    DBHandler.getInstance().update(oldTeam,team1);
                }else{
                    Team.addorUpdateTeam(team1);
                    DBHandler.getInstance().insert(team1);
                }

                if (Team.isInList(team2)){
                    Team oldTeam = Team.addorUpdateTeam(team2);
                    DBHandler.getInstance().update(oldTeam,team2);
                }else{
                    Team.addorUpdateTeam(team2);
                    DBHandler.getInstance().insert(team2);
                }



                TableScreen.setTeams(new Team[]{team1, team2});
                setVisible(false);
                dispose();
                GUI gui = new GUI(1280, 720);


            }
        });



        JPanel gridPanel = new MyPanel(null);
        gridPanel.setLayout(null);

        leftPanel.setBounds(0, 0, getSizeX() / 2 - 75, getSizeY() - 100);
        rightPanel.setBounds(getSizeX() / 2 - 75, 0, getSizeX() / 2 - 75, getSizeY() - 100);
        gridPanel.setBounds(0, 0, getSizeX(), getSizeY() - 100);
        gridPanel.add(leftPanel);
        gridPanel.add(rightPanel);

        jp.setLayout(null);
        jp.add(gridPanel);
        jp.add(B1);
        jp.add(arrow);
        jp.add(topLabel);
        jp.add(topLabel2);
        jp.add(arrow);
    }

    private void printTeam(Team team){
        System.out.println(team.getTeamName());
        for (int i = 0; i < team.getPlayerNames().length; i++) {
            System.out.println(team.getPlayerNames()[i]);
        }
    }
    private JPanel preparePanel(int teamNo)
    {

        JPanel jp = new MyPanel(null);
        JLabel[] labels = new JLabel[7];

        JButton[] pluses = new JButton[7];

        final JComboBox[] fields = new JComboBox[7];

        labels[0] = new JLabel("Название команды №" + teamNo);
        fields[0] = new JComboBox();
        pluses[0] = new JButton("+");

        labels[1] = new JLabel("Игрок 1:");
        fields[1] = new JComboBox();
        pluses[1] = new JButton("+");

        labels[2] = new JLabel("Игрок 2:");
        fields[2] = new JComboBox();
        pluses[2] = new JButton("+");

        labels[3] = new JLabel("Игрок 3:");
        fields[3] = new JComboBox();
        pluses[3] = new JButton("+");

        labels[4] = new JLabel("Игрок 4:");
        fields[4] = new JComboBox();
        pluses[4] = new JButton("+");

        labels[5] = new JLabel("Игрок 5:");
        fields[5] = new JComboBox();
        pluses[5] = new JButton("+");

        labels[6] = new JLabel("Игрок 6:");
        fields[6] = new JComboBox();
        pluses[6] = new JButton("+");

        jp.setLayout(null);
        jp.setVisible(true);
        jp.setSize(250, height);
        int elementHeight = 35;
        for (int i = 0; i < labels.length; i++)
        {
            labels[i].setBounds(150, 60 + (10 * i) +  (i * elementHeight * 2), 415, elementHeight);
            labels[i].setHorizontalAlignment(JTextField.CENTER);
            jp.add(labels[i]);

        }
        for (int i = 0; i < labels.length; i++)
        {

            pluses[i].setBounds(150, 60+elementHeight + (10 * i) +  (i * elementHeight * 2), 35, elementHeight);
            pluses[i].setFont(new Font(pluses[i].getFont().getName(), Font.PLAIN, 20));
            pluses[i].setMargin(new Insets(0, 0, 0, 0));
            pluses[i].addActionListener(new Listener(teamNo, i, fields[i]));

            fields[i].setBounds(185, 60+elementHeight + (10 * i) +  (i * elementHeight * 2), 380, elementHeight);
            fields[i].setEditable(true);
          //  fields[i].addItemListener(new ItemChangeListener());

            jp.add(pluses[i]);
            jp.add(fields[i]);

            final int finalI = i;
            pluses[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String result = ((JTextField)fields[finalI].getEditor().getEditorComponent()).getText();
                    fields[finalI].addItem(result);

                }
            });
        }

        for (int j = 0; j < Team.getAllTeams().size(); j++) {
            fields[0].addItem(Team.getAllTeams().get(j).getTeamName());

        }
        fields[0].addItemListener(new TeamItemChangeListener(fields));
        TeamChangeListener.getInstance().setFields(fields[0], teamNo);

        prepareTeam(fields,0);


        geatherData[teamNo-1] = new GeatherDataFromPanel() {
                @Override
                public Team geatherTeam() {
                    String[] teamPlayerNames = new String[fields.length-1];
              for (int i = 1; i < fields.length; i++) {
                  teamPlayerNames[i-1] = ((JTextField)fields[i].getEditor().getEditorComponent()).getText();
              }
                return new Team(((JTextField)fields[0].getEditor().getEditorComponent()).getText(), teamPlayerNames);
                }};
        return jp;
    }

    private void prepareTeam(JComboBox[] fields, int selectdId){
        if(Team.getAllTeams().size() > 0){
        for (int j = 0; j < Team.getAllTeams().get(selectdId).getPlayerNames().length; j++) {
            fields[j+1].removeAllItems();
            fields[j+1].addItem( Team.getAllTeams().get(selectdId).getPlayerNames()[j] );
            System.out.println(Team.getAllTeams().get(selectdId).getPlayerNames()[j]);
        }
        }
    }
    private void prepareTeam(JComboBox[] fields, String selectdName){
        int selectedId = 0;
        for (int i = 0; i < Team.getAllTeams().size(); i++) {
            if(Team.getAllTeams().get(i).getTeamName().equals(selectdName))
            {
                selectedId = i;
            }

        }
        for (int j = 0; j < Team.getAllTeams().get(selectedId).getPlayerNames().length; j++) {
            fields[j+1].removeAllItems();
            fields[j+1].addItem( Team.getAllTeams().get(selectedId).getPlayerNames()[j] );
            System.out.println(Team.getAllTeams().get(selectedId).getPlayerNames()[j]);
        }
    }
    private interface GeatherDataFromPanel{
        public Team geatherTeam();
    }
    private class Listener implements ActionListener{
        int id;
        JComboBox field;
        int teamNo;
        public Listener(int teamNo, int id, JComboBox field){
            this.id = id;
            this.field = field;
            this.teamNo = teamNo;

            }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(id == 0) {

                String teamName = ((JTextField)field.getEditor().getEditorComponent()).getText();
                for (int i = 0; i < Team.getAllTeams().size(); i++) {

                    if( Team.getAllTeams().get(i).getTeamName().equals(teamName)) {
                        Team.getAllTeams().set(i, geatherData[teamNo-1].geatherTeam());
                    }
                }

            }
        }
    }
    class ItemChangeListener implements ItemListener {
        JComboBox[] fields;
        int selectedId;
        public ItemChangeListener(JComboBox[] fields, int selectedId) {
            this.fields = fields;
            this.selectedId = selectedId;
        }
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object item = event.getItem();

                prepareTeam(fields,0);

            }
        }
    }
    class TeamItemChangeListener implements ItemListener {
        JComboBox[] fields;
        public TeamItemChangeListener(JComboBox[] fields){
            this.fields = fields;
        }
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {

                prepareTeam(fields, fields[0].getSelectedItem().toString());

            }
        }
    }
    static class TeamChangeListener implements ItemListener {
        private static TeamChangeListener instance = null;
        private JComboBox[] fields = new JComboBox[2];

        public static TeamChangeListener getInstance() {
            if(instance == null) {
                instance = new TeamChangeListener();
                return instance;
            }
            return instance;
        }

        public void setFields(JComboBox field, int teamNo) {
            this.fields[teamNo-1] = field;
            field.addItemListener(this);
        }

        private TeamChangeListener(){}

        @Override
        public void itemStateChanged(ItemEvent event) {
            /*if (event.getStateChange() == ItemEvent.SELECTED) {
                boolean isInside = false;
                for (int i = 0; i < fields[0].getItemCount(); i++) {

                    for (int j = 0; j < fields[1].getItemCount(); j++) {
                        if (fields[0].getItemAt(i).equals(fields[1].getItemAt(j))) {
                            isInside = true;
                        }
                    }
                    if (!isInside) {
                        fields[1].addItem(fields[0].getItemAt(i));
                    }
                }

                for (int i = 0; i < fields[1].getItemCount(); i++) {

                    for (int j = 0; j < fields[0].getItemCount(); j++) {
                        if (fields[1].getItemAt(i).equals(fields[0].getItemAt(j))) {
                            isInside = true;
                        }
                    }
                    if (!isInside) {
                        fields[0].addItem(fields[1].getItemAt(i));
                    }
                }

                fields[0].removeItem(fields[1].getSelectedItem());
                fields[1].removeItem(fields[0].getSelectedItem());
            }*/
        }
    }
}

