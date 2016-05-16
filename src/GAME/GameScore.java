package GAME;

/**
 * Created by kostya on 15.05.2016.
 */
public class GameScore {
    public static final String NO_BODY = "Nobody";
    private String winnerTeam;
    private TeamScore[] teamScores = new TeamScore[2];

    public GameScore(Team[] teams) {
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
        return teamScores[teamNumber].teamName;
    }

    public String getAnsweredPlayer(int teamNumber, int questionNumber) {
        return teamScores[teamNumber].answeredPlayer[questionNumber];
    }
    private class TeamScore{
    String teamName = "2";
    int score;
    String[] answeredPlayer = new String[13];

        public TeamScore() {
            for (int i = 0; i < answeredPlayer.length; i++) {
                answeredPlayer[i] = "";
            }

        }
    }

}
