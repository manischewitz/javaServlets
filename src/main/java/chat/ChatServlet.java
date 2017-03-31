package chat;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import servlets.PageLoader;


@SuppressWarnings("serial")
@WebServlet(name = "ChatServlet", urlPatterns = {"/chat"})
public class ChatServlet extends WebSocketServlet {

	private final static int LOGOUT_TIME = 10*60*1000;
	private final ChatManager chatManager;
	
	public ChatServlet(){
		this.chatManager = new ChatManager();
	}
	
	@Override
	public void configure(WebSocketServletFactory arg0) {
		
		arg0.getPolicy().setIdleTimeout(LOGOUT_TIME);
		arg0.setCreator((req, resp) -> new ChatWebSocket(chatManager));
		
	}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().println(PageLoader.instance().getPage("chat.html", null));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
	}
}
