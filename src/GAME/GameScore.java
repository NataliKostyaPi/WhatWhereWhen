package GAME;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

/**
 * Created by kostya on 15.05.2016.
 */
public class GameScore {
    public static final String NO_BODY = Strings.NOBODY;
    public static final String GAME_RESULT_TIE = "Ничья";
    private Date gameDate;

    private TeamScore[] teamScores = new TeamScore[2];
    private Team[] teams;

    public Team[] getTeams() {
        return teams;
    }

    public GameScore(Team[] teams) {
        this.teams = teams;
        teamScores[0] = new TeamScore();

        teamScores[1] = new TeamScore();

        this.teamScores[0].teamName = teams[0].getTeamName();
        this.teamScores[1].teamName = teams[1].getTeamName();
    }
        public void setAnswer(int teamNumber, int questionNumber, String answeredPlayer) {
            this.teamScores[teamNumber].answeredPlayer[questionNumber] = answeredPlayer;
            if(!answeredPlayer.equals(NO_BODY)) {
                this.teamScores[teamNumber].score += 1;
            }
        }
    public int getTeamScore(int teamNumber) {
        return this.teamScores[teamNumber].score;
    }

    public String getTeamName(int teamNumber) {
        if (teamNumber == 2)
        {return GAME_RESULT_TIE;}
        return teamScores[teamNumber].teamName;
    }

    public String getAnsweredPlayer(int teamNumber, int questionNumber) {
        return teamScores[teamNumber].answeredPlayer[questionNumber];
    }

    public Date getGameDate() {
        return gameDate;
    }
    public String getStringGameDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(gameDate);
    }
    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
    public void setGameDate(String gameDate) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date = null;
        try {
            date = sdf1.parse(gameDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        this.gameDate = sqlDate ;
    }
    public static Date getDateNow() {
        return new Date(System.currentTimeMillis());

    }

    public int getWinnerTeam() {
        if(getTeamScore(0) > getTeamScore(1)) {
            return 0;
        } else if (getTeamScore(0) < getTeamScore(1)) {
            return 1;
        } else //if(getTeamScore(0) == getTeamScore(1)){
            return 2;
    }
    public GameScore prepareTestObject() {
    for(int i = 0; i < 13; i++) {
            setAnswer(0,i,NO_BODY);
            setAnswer(1,i,NO_BODY);
    }
        return this;
    }


    public String getBestPlayer() {
        int[] best0 = getBestPlayerInTeam(0);
        int[] best1 = getBestPlayerInTeam(1);
        if(best0[1] > best1[1]) {
            return teams[0].getPlayerNames()[best0[0]];
        }
        if(best0[1] < best1[1]) {
            return teams[1].getPlayerNames()[best1[0]];
        }
        else {
            return teams[0].getPlayerNames()[best0[0]] + ", " + teams[1].getPlayerNames()[best1[0]];
        }
    }

    public int[] getBestPlayerInTeam(int teamNumber) {
        int[] playerScores = new int[6];
        for (int playerID = 0; playerID < playerScores.length; playerID++) {
            for (int questionNum = 0; questionNum < teamScores[teamNumber].answeredPlayer.length; questionNum++) {
                if(teamScores[teamNumber].answeredPlayer[questionNum].equals(teams[teamNumber].getPlayerNames()[playerID] ) ) {
                    playerScores[playerID]++;
                }
            }
        }
        int bestTeamId = 0;
        int bestTeamScore = 0;
        for (int playerID = 0; playerID < playerScores.length; playerID++) {
            if(playerScores[playerID] > bestTeamScore) {
                bestTeamScore = playerScores[playerID];
                bestTeamId = playerID;
            }
        }
        return new int[] {bestTeamId, bestTeamScore};
    }

    private class TeamScore{
    String teamName = "";
    int score;
    String[] answeredPlayer = new String[13];

        public TeamScore() {
            for (int i = 0; i < answeredPlayer.length; i++) {
                answeredPlayer[i] = "";
            }

        }
    }

}
