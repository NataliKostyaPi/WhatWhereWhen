package GAME;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by MisterY on 26.04.2016.
 */
public class DBHandler {
    private static DBHandler instance = null;
    private Connection conn = null;

    private DBHandler()
    {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DBHandler getInstance() {
        if(instance == null)
        {return instance = new DBHandler();}
        return instance;
    }

    public DBHandler connect()
    {
        try {
            Properties p=new Properties();
            p.setProperty("user","root");
            p.setProperty("password","12345");
            p.setProperty("useUnicode","true");
            p.setProperty("characterEncoding","cp1251");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/whatwherewhen?", p);
            //conn = DriverManager.getConnection( + "user=root&password=12345");
            Statement stmt = conn.createStatement();
            stmt.executeQuery("SET NAMES 'cp1251'");
            stmt.executeQuery("SET CHARACTER SET 'cp1251'");

            // Do something with the Connection
           // showTables(conn);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return this;
    }
    private void showTables(Connection conn) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
            System.out.println(rs.getString(3));
        }

        conn.close();
    }
    public void setWord(String wordShown, String newWord) {
        String query = "update words set word = ?,  where word = ?";
        connect();
        try {

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newWord);
            preparedStmt.setString(2
                    , wordShown);
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            conn.close();
        }  catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public void setQuiz(Quiz oldQuiz, Quiz newQuiz) {

        /*String query = "UPDATE questions " +
                "SET qusetion = "+newQuiz.getQuestion()+", answer = "+newQuiz.getAnswer()+" WHERE question = " + oldQuiz.getQuestion();*/
        String query = "update questions set question = ?, answer = ? where question = ?";
        connect();
        try {

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newQuiz.getQuestion());
            preparedStmt.setString(2, newQuiz.getAnswer());
            preparedStmt.setString(3, oldQuiz.getQuestion());
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            conn.close();
        }  catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    public void delete(String fieldName) {

        String query = "DELETE from questions where question = \""+ fieldName +"\";";
        Statement stmt = null;
        connect();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }  catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    public void deleteWord(String fieldName) {

        String query = "DELETE from words where word = \""+ fieldName +"\";";
        Statement stmt = null;
        connect();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }  catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    public void loadQuizes()
    {
        Statement stmt = null;

        connect();
        String query = "SELECT * FROM questions";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Now do something with the ResultSet ....
            //Quiz quiz = new Quiz()
            while (rs.next())
            {
                Quiz quiz = new Quiz(rs.getString("question"), rs.getString("answer"));
                System.out.println(rs.getString("question") + " : "+ rs.getString("answer"));
                Quiz.addQuiz(quiz);
            }
            stmt.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

        }
    }
    public void insertTeamStatistic(GameScore gameScore, int teamNumber)
    {
        try
        {
            connect();
            // the mysql insert statement
            String query = " insert into statistics (gamedate, team, teamScore, bestPlayer, playerScore)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate   (1, gameScore.getGameDate());
            preparedStmt.setString (2, gameScore.getTeamName(teamNumber));
            preparedStmt.setInt    (3, gameScore.getTeamScore(teamNumber));
            preparedStmt.setString (4, gameScore.getTeams()[teamNumber].getPlayerNames()[gameScore.getBestPlayerInTeam(teamNumber)[0]] );
            preparedStmt.setInt    (5, gameScore.getBestPlayerInTeam(teamNumber)[1]);
            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! INSERT");
            e.printStackTrace();
        }
    }

    public void insertWarmupWinner(String winner)
    {
        try
        {
            connect();
            // the mysql insert statement
            String query = " insert into warmupwinners (winner)"
                    + " values (?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, winner);
            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! INSERT");
            e.printStackTrace();
        }
    }
    public void insertWord(String word)
    {
        try
        {
            connect();
            // the mysql insert statement
            String query = " insert into words (word)"
                    + " values (?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, word);
            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! INSERT");
            e.printStackTrace();
        }
    }

    public String[] loadWarmup()
    {
        String[] words = new String[500];
        Statement stmt = null;

        connect();
        String query = "SELECT * FROM words";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Now do something with the ResultSet ....
            //Quiz quiz = new Quiz()
            int wordsCount = 0;

            while (rs.next())
            {
                words[wordsCount]= rs.getString("word");
                wordsCount++;
            }
            String[] result = new String[wordsCount];
            for (int i = 0; i < wordsCount; i++) {
                result[i] = words[i];
            }
            words = result;
            stmt.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return words;
    }
    public ArrayList<TeamStatitic> loadStatistics()
    {
        ArrayList<TeamStatitic> teamStatitics = new ArrayList<TeamStatitic>();
        Statement stmt = null;

        connect();
        String query = "SELECT * FROM statistics";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Now do something with the ResultSet ....
            //Quiz quiz = new Quiz()

            while (rs.next())
            {
                TeamStatitic ts = new TeamStatitic(rs.getDate("gamedate"), rs.getString("team"),  rs.getInt("teamScore"), rs.getString("bestPlayer"), rs.getInt("playerScore"));
                teamStatitics.add(ts);
            }

            stmt.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return teamStatitics;
    }

    public void insert(Quiz quiz)
    {
        try
        {
            connect();
            // the mysql insert statement
            String query = " insert into questions (question, answer)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, quiz.getQuestion());
            preparedStmt.setString (2, quiz.getAnswer());


            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! INSERT");
            e.printStackTrace();
        }
    }

    public void insert(Team team)
    {
        try
        {
            connect();
            // the mysql insert statement
            String query = "insert into teams (teamNumber, team, people)"
                    + " values (?, ?, ?)";

            for (int i = 0; i < team.getPlayerNames().length; i++) {
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, Team.getAllTeams().size()-1);
                preparedStmt.setString(2, team.getTeamName());
                preparedStmt.setString(3, team.getPlayerNames()[i]);
                // execute the preparedstatement
                preparedStmt.execute();
            }
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! INSERT");
            e.printStackTrace();
        }
    }
    public void update(Team oldTeam, Team newTeam){
        try
        {
            connect();
            // the mysql insert statement
            String query = "update teams set people = ? where team = ? and people = ?" ;

            for (int i = 0; i < newTeam.getPlayerNames().length; i++) {

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, newTeam.getPlayerNames()[i]);
                preparedStmt.setString(2, oldTeam.getTeamName());
                preparedStmt.setString(3, oldTeam.getPlayerNames()[i]);

                preparedStmt.executeUpdate();
            }
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! UPDATE");
            e.printStackTrace();
        }
    }

    public ArrayList<Team> loadTeams()
    {
        ArrayList<Team> teams = new ArrayList<Team>();
        Statement stmt = null;

        connect();
        String query = "SELECT * FROM teams";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int oldTeamValue = 0;
            int playersCount = 0;
            String teamName = "";
            String[] playersNames = new String[6];

            while (rs.next())
            {
                int newTeamValue = rs.getInt("teamNumber");

                if(oldTeamValue != newTeamValue)
                {
                    System.out.println(playersNames[0]);
                    teams.add(new Team(teamName, playersNames));
                    System.out.println("size " + teams.size());
                    playersNames = new String[6];
                    oldTeamValue = newTeamValue;
                    playersCount = 0;
                    //// TODO: 22.05.2016 cut array playerNames
                }
                teamName = rs.getString("team");
                playersNames[playersCount] = rs.getString("people");
                playersCount++;
                System.out.println("" +  rs.getInt("teamNumber") +", " + rs.getString("team") + ", "+ rs.getString("people"));

            }
            teams.add(new Team(teamName, playersNames));
            stmt.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return teams;

    }
}