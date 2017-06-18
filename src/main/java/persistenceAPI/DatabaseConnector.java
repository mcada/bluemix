package persistenceAPI;


import java.sql.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mcada on 6/17/17.
 */
public class DatabaseConnector {

   private static final String url = "jdbc:mysql://us-cdbr-sl-dfw-01.cleardb.net/ibmx_5e56cc9c0cea8a3";
   private static final String username = "b087d08e341077";
   private static final String password = "74cd795b";
   private Connection dbconnection;

   List<Record> highscoreList = new LinkedList<>();

   public void createConnection() throws SQLException {
      this.dbconnection = DriverManager.getConnection(url, username, password);
   }

   public List<Record> getHighscore() throws SQLException {
      System.out.println("Connecting database...");

      createConnection();


      System.out.println("Database connected!");

      Statement stmt = dbconnection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM highscore");


      while (rs.next()) {
         highscoreList.add(new Record(rs.getString("nick"), rs.getInt("score")));
      }

      highscoreList.sort(Comparator.comparingInt(Record::getScore).reversed());

      stmt.close();
      rs.close();
      dbconnection.close();
      return highscoreList;
   }

   public Boolean contains(String name) throws SQLException {
      createConnection();


      Statement stmt = dbconnection.createStatement();
      ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM highscore WHERE nick=\"%s\"", name));


      Boolean answer = rs.next();

      rs.close();
      stmt.close();
      dbconnection.close();
      return answer;

   }

   public void updateRecord(String name, int score) throws SQLException {
      createConnection();

      Statement stmt = dbconnection.createStatement();

      stmt.executeUpdate(String.format("update highscore set score=%d where nick='%s'", score, name));
      stmt.close();
      dbconnection.close();
   }

   public void addNewRecord(String name, int score) throws SQLException {
      createConnection();

      Statement stmt = dbconnection.createStatement();

      stmt.executeUpdate(String.format("insert into highscore values ('%s', %d)", name, score));
      stmt.close();
      dbconnection.close();
   }

   public String bestScore(String name) throws SQLException {
      createConnection();


      Statement stmt = dbconnection.createStatement();
      ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM highscore WHERE nick=\"%s\"", name));
      int best;

      rs.next();
      best = rs.getInt("score");


      rs.close();
      stmt.close();
      dbconnection.close();
      return Integer.toString(best);

   }


}
