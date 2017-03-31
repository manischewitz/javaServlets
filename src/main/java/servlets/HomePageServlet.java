package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import accounts.AccountServer;

@SuppressWarnings("serial")
public class HomePageServlet extends HttpServlet{

	static final Logger logger = LogManager.getLogger(HomePageServlet.class.getName());
	public static final String PAGE_URL = "/home";
	private final AccountServer accountServer;
	
	public HomePageServlet(AccountServer accountServer){
		this.accountServer = accountServer;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		
		String remove = request.getParameter("remove");
		
		response.getWriter().println(PageLoader.instance().getPage("/homePage/home.html", null));
		
		if(remove != null){
			accountServer.removeUser();
			response.getWriter().println("GoodBye!");
			response.setStatus(HttpServletResponse.SC_OK);
            return;
		}
		
		int limit = accountServer.getUsersLimit();
		int count = accountServer.getUsersCount();
		
		logger.info("Limit: {}. Count {}",limit, count);
		
		if(limit>count){
			logger.info("User pass");
			accountServer.addNewUser();
			response.getWriter().println("Welcome!");
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			logger.info("Server is full");
			response.getWriter().println("Server is full!");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}
}
