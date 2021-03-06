package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.AccountServer;

public class AdminPageServlet extends HttpServlet{

	public static final String PAGE_URL = "/admin";
	private final AccountServer accountServer;
	
	public AdminPageServlet(AccountServer accountServer){
		this.accountServer = accountServer;
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(accountServer.getUsersLimit());
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	
}
