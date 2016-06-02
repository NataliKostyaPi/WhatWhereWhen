package GAME;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by kostya on 17.05.2016.
 */
public class TeamStatitic {
    private Date gameDate;
    private String team;
    private int teamScore;
    private String bestPlayer;
    private int playerScore;

    private static ArrayList<TeamStatitic> allStatistics = new ArrayList<TeamStatitic>();

    public static ArrayList<TeamStatitic> getAllStatistics() {
        return allStatistics;
    }

    public static void setAllStatistics(ArrayList<TeamStatitic> allStatistics) {
        TeamStatitic.allStatistics = allStatistics;
    }

    public TeamStatitic(Date gameDate, String team, int teamScore, String bestPlayer, int playerScore) {
        this.gameDate = gameDate;
        this.team = team;
        this.teamScore = teamScore;
        this.bestPlayer = bestPlayer;
        this.playerScore = playerScore;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDateString(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(gameDate);
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public String getBestPlayer() {
        return bestPlayer;
    }

    public void setBestPlayer(String bestPlayer) {
        this.bestPlayer = bestPlayer;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
