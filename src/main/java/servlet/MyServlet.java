package servlet;

import persistenceAPI.DatabaseConnector;

import java.io.IOException;
import java.sql.SQLException;

      import javax.servlet.ServletException;
      import javax.servlet.annotation.WebServlet;
      import javax.servlet.http.HttpServlet;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;



@WebServlet("/novy")
public class MyServlet extends HttpServlet {

   private DatabaseConnector dc = new DatabaseConnector();

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Preprocess request: load list of products for display in JSP.
      try {
         request.setAttribute("pokus", dc.getHighscore());
      } catch (SQLException e) {
         e.printStackTrace();
      }

      System.out.print("uvnitr servletu.");
      request.setAttribute("cosi", "randomstring");
      request.getRequestDispatcher("novy.html").forward(request, response);
   }

}