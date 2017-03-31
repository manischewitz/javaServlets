package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;


import databases.DBManager;
import databases.UsersDAO;
import databases.UsersDataSet;

public class SignInServlet extends HttpServlet{

	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			DBManager dbm = new DBManager();
		    UsersDataSet user = dbm.getUser(request.getParameter("login"));
		
	    if(user.getName().equals(request.getParameter("login")) && user.getPassword().equals(request.getParameter("password"))){
			response.getWriter().println("Authorized: "+user.getName());
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}else{
			response.getWriter().println("Unauthorized");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		}catch(HibernateException e){e.printStackTrace();}
	}
}
