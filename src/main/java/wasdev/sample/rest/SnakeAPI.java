package wasdev.sample.rest;

import com.google.gson.Gson;
import persistenceAPI.DatabaseConnector;


import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.sql.SQLException;

/**
 * Created by mcada on 6/17/17.
 */
@ApplicationPath("api")
@Path("/highscore")
public class SnakeAPI extends Application {

   DatabaseConnector dbc = new DatabaseConnector();


   @POST
   @Produces("application/text")
   @Consumes("application/text")
   public String newToDo(String name) throws SQLException {
      if (dbc.contains(name)) {
         return String.format("Hello again %s!", name);
      }

      dbc.addNewRecord(name, 0);

      return String.format("Welcome new player %s! I've added you to the database.", name);

   }


   @GET
   @Path("/")
   @Produces({"application/json"})
   public String getHighscore() throws SQLException {

      return new Gson().toJson(dbc.getHighscore());
   }

   @POST
   @Path("/getBest")
   @Consumes("application/text")
   @Produces({"application/text"})
   public String getBest(String name) throws SQLException {

      return dbc.bestScore(name);
   }


   @POST
   @Path("/newBest")
   @Consumes("application/text")
   public void writeScore(String response) throws SQLException {
      String delims = "[ ]+";
      String[] tokens = response.split(delims);

      dbc.updateRecord(tokens[0], Integer.parseInt(tokens[1]));


   }
}
