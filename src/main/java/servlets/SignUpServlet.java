package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databases.DBManager;

public class SignUpServlet extends HttpServlet{

	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
		
		//here should be checks
		DBManager dbm = new DBManager();
		dbm.addUser(request.getParameter("login"),request.getParameter("password"));
		
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
}
