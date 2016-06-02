package GAME;

import gui.MyPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MisterY on 27.04.2016.
 */
public class TeamTable extends MyPanel {
    EditableField[] team1Fields = new EditableField[15];
    EditableField[] team2Fields = new EditableField[15];

    TeamTable(GameScore gameScore)
    {
        super(null);
        this.setSize(600, 720);
        this.setLocation(650, 0);
        this.setVisible(true);

        team1Fields[0] = new EditableField("Команда № 1", gameScore.getTeamName(0), new int[] {0,0}).setMarked();
        team1Fields[1] = new EditableField("Счет:", "", new int[] {1,0}).setMarked();

        team1Fields[2] = new EditableField("Вопрос 1:", "", new int[] {0,1});
        team1Fields[3] = new EditableField("Вопрос 2:", "", new int[] {0,2});
        team1Fields[4] = new EditableField("Вопрос 3:", "", new int[] {0,3});
        team1Fields[5] = new EditableField("Вопрос 4:", "", new int[] {0,4});
        team1Fields[6] = new EditableField("Вопрос 5:", "", new int[] {0,5});
        team1Fields[7] = new EditableField("Вопрос 6:", "", new int[] {0,6});
        team1Fields[8] = new EditableField("Вопрос 7:", "", new int[] {0,7});
        team1Fields[9] = new EditableField("Вопрос 8:", "", new int[] {1,1});//8
        team1Fields[10] = new EditableField("Вопрос 9:", "", new int[] {1,2});
        team1Fields[11] = new EditableField("Вопрос 10:", "", new int[] {1,3});
        team1Fields[12] = new EditableField("Вопрос 11:", "", new int[] {1,4});
        team1Fields[13] = new EditableField("Вопрос 12:", "", new int[] {1,5});
        team1Fields[14] = new EditableField("Вопрос 13:", "", new int[] {1,6});

        team2Fields[0] = new EditableField("Команда № 2", gameScore.getTeamName(1), new int[] {2,0}).setMarked();
        team2Fields[1] = new EditableField("Счет:","", new int[] {3,0}).setMarked();

        team2Fields[2] = new EditableField("Вопрос 1:", "", new int[] {2,1});
        team2Fields[3] = new EditableField("Вопрос 2:", "", new int[] {2,2});
        team2Fields[4] = new EditableField("Вопрос 3:", "", new int[] {2,3});
        team2Fields[5] = new EditableField("Вопрос 4:", "", new int[] {2,4});
        team2Fields[6] = new EditableField("Вопрос 5:", "", new int[] {2,5});
        team2Fields[7] = new EditableField("Вопрос 6:", "", new int[] {2,6});
        team2Fields[8] = new EditableField("Вопрос 7:", "", new int[] {2,7});
        team2Fields[9] = new EditableField("Вопрос 8:", "", new int[] {3,1});//8
        team2Fields[10] = new EditableField("Вопрос 9:", "", new int[] {3,2});
        team2Fields[11] = new EditableField("Вопрос 10:", "", new int[] {3,3});
        team2Fields[12] = new EditableField("Вопрос 11:", "", new int[] {3,4});
        team2Fields[13] = new EditableField("Вопрос 12:", "", new int[] {3,5});
        team2Fields[14] = new EditableField("Вопрос 13:", "", new int[] {3,6});


    }
public void refreshTables(GameScore gameScore) {
    team1Fields[1].setEditable("" + gameScore.getTeamScore(0));
    team1Fields[2].setEditable(gameScore.getAnsweredPlayer(0,0));
    team1Fields[3].setEditable(gameScore.getAnsweredPlayer(0,1));
    team1Fields[4].setEditable(gameScore.getAnsweredPlayer(0,2));
    team1Fields[5].setEditable(gameScore.getAnsweredPlayer(0,3));
    team1Fields[6].setEditable(gameScore.getAnsweredPlayer(0,4));
    team1Fields[7].setEditable(gameScore.getAnsweredPlayer(0,5));
    team1Fields[8].setEditable(gameScore.getAnsweredPlayer(0,6));
    team1Fields[9].setEditable(gameScore.getAnsweredPlayer(0,7));
    team1Fields[10].setEditable(gameScore.getAnsweredPlayer(0,8));
    team1Fields[11].setEditable(gameScore.getAnsweredPlayer(0,9));
    team1Fields[12].setEditable(gameScore.getAnsweredPlayer(0,10));
    team1Fields[13].setEditable(gameScore.getAnsweredPlayer(0,11));
    team1Fields[14].setEditable(gameScore.getAnsweredPlayer(0,12));


    team2Fields[1].setEditable("" + gameScore.getTeamScore(1));
    team2Fields[2].setEditable(gameScore.getAnsweredPlayer(1,0));
    team2Fields[3].setEditable(gameScore.getAnsweredPlayer(1,1));
    team2Fields[4].setEditable(gameScore.getAnsweredPlayer(1,2));
    team2Fields[5].setEditable(gameScore.getAnsweredPlayer(1,3));
    team2Fields[6].setEditable(gameScore.getAnsweredPlayer(1,4));
    team2Fields[7].setEditable(gameScore.getAnsweredPlayer(1,5));
    team2Fields[8].setEditable(gameScore.getAnsweredPlayer(1,6));
    team2Fields[9].setEditable(gameScore.getAnsweredPlayer(1,7));
    team2Fields[10].setEditable(gameScore.getAnsweredPlayer(1,8));
    team2Fields[11].setEditable(gameScore.getAnsweredPlayer(1,9));
    team2Fields[12].setEditable(gameScore.getAnsweredPlayer(1,10));
    team2Fields[13].setEditable(gameScore.getAnsweredPlayer(1,11));
    team2Fields[14].setEditable(gameScore.getAnsweredPlayer(1,12));
}
    private class EditableField {
        final int height = 20;
        final int width = 110;
        final int margin = 10;
        //int startX =
        JLabel label;
        JLabel editable;

        public EditableField(String labelText, String editableText, int[] cells) {
            label = new JLabel("<html><B>" + labelText +"</B></html>");
            editable = new JLabel(editableText);
            label.setBounds(cells[0]*(width+margin), margin + cells[1]*(height+(int)(margin*2.5))*2 ,width,height);
            editable.setBounds(cells[0]*(width+margin), margin +cells[1]*(height+(int)(margin*2.5))*2 + (height+margin),width,height);
            label.setHorizontalAlignment(JTextField.CENTER);
            editable.setHorizontalAlignment(JTextField.CENTER);
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 16));


            add(label);
            add(editable);
        }
        public EditableField setMarked() {
            label.setForeground(Color.BLUE);
            editable.setFont(new Font(editable.getFont().getName(), Font.PLAIN, 20));
            return this;
        }
        public void setEditable(String editableText) {
            this.editable.setText(editableText);
        }





    }
}
