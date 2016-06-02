package GAME;

import java.util.ArrayList;

/**
 * Created by kostya on 28.04.2016.
 */
public class Team {
    private static ArrayList<Team> allTeams = new ArrayList<Team>();
    String teamName;
    String[] playerNames = new String[6];

    public static ArrayList<Team> getAllTeams() {
        return allTeams;
    }

    public static void setAllTeams(ArrayList<Team> allTeams) {
        Team.allTeams = allTeams;
    }

    /**
     *
     * @param team
     * @return returns old team if updated
     */
    public static Team addorUpdateTeam(Team team){
        for (int i = 0; i < allTeams.size(); i++) {
            if (allTeams.get(i).getTeamName().equals(team.getTeamName())) {
                Team oldTeam = allTeams.get(i);
                allTeams.set(i,team);
                return oldTeam;
            }
        }
        allTeams.add(team);
        return null;
    }
    public static boolean isInList(Team team){
        for (int i = 0; i < allTeams.size(); i++) {
            if (allTeams.get(i).getTeamName().equals(team.getTeamName())) {
                return true;
            }
        }
        return false;
    }

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

    @Override
    public String toString() {
        String result = teamName + ": ";
        for (int i = 0; i < playerNames.length; i++)
        {
            result += playerNames[i];
        }
        return  result;

    }
}
