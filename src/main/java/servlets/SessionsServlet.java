package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;





public class SessionsServlet extends HttpServlet{

	
	
	public SessionsServlet(){
		
	}
	
	/*public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
		
		Map<String, Object> pageVars = new HashMap<String, Object>();
		pageVars.put("login", "");
		pageVars.put("password", "");
		
		String sessionID = request.getSession().getId();
        response.setContentType("text/html;charset=utf-8");
        
		
        //if user account doesn't exist servlet return unauth.  
        if(ua == null){
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	pageVars.put("message", "UNAUTHORIZED");
    		response.getWriter().println(PageLoader.instance().getPage("login.html", pageVars));
        }else{
        	pageVars.put("message", "AUTHORIZED");
    		response.getWriter().println(PageLoader.instance().getPage("login.html", pageVars));
        	Gson gs = new Gson();
        	String gson = gs.toJson(ua);
        	response.getWriter().println(gson);
        	response.setStatus(HttpServletResponse.SC_OK);
        }
     }*/
	//sign in
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		
		if(login == null || password == null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		/*UserAccount account = accountManager.getUserByLogin(login);
		
		if(account == null || !password.equals(account.getPassword())){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		accountManager.addUserWithSessionID(request.getSession().getId(), account);
		Gson gs = new Gson();
		String gson = gs.toJson(account);
		response.getWriter().println(gson);*/
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	//sign out
	public void doDelete(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
		String sessionID = request.getSession().getId();
		/*UserAccount account = accountManager.getUserBySessionID(sessionID);
		response.setContentType("text/html;charset=utf-8");
		
		if(account == null){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}else{
			accountManager.deleteUserWithSessionID(sessionID);
			response.getWriter().println("log out");
			response.setStatus(HttpServletResponse.SC_OK);
		}*/
	}
	
	
    public static void logger(){
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
