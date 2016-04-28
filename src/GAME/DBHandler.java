package GAME;

import java.sql.*;

/**
 * Created by MisterY on 26.04.2016.
 */
public class DBHandler {
    private static DBHandler instance = null;
    Connection conn = null;

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/whatwherewhen?" + "user=root&password=12345");

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

        String query = "DELETE from questions where question = '"+ fieldName +"';";
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
            String query = "insert into teams (team, people)"
                    + " values (?, ?)";

            for (int i = 0; i < team.getPlayerNames().length; i++) {
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, team.getTeamName());
                preparedStmt.setString(2, team.getPlayerNames()[i]);
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
}