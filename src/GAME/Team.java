package GAME;

/**
 * Created by kostya on 28.04.2016.
 */
public class Team {
    String teamName;
    String[] playerNames = new String[6];

    public Team(String teamName, String[] playerNames) {
        this.teamName = teamName;
        this.playerNames = playerNames;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }
}
