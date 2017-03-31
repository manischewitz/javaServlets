package chat;

import java.util.logging.Logger;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocket {

	private ChatManager chatManager;
	private Session session;
	
	public ChatWebSocket(ChatManager cm){
		this.chatManager = cm;
	}
	
	@OnWebSocketConnect
	public void onOpen(Session session){
		chatManager.add(this);
		this.session = session;
	}
	
	@OnWebSocketMessage
	public void onMessgae(String message){
		chatManager.sendMessage(message);
	}
	
	@OnWebSocketClose
	public void onClose(int statusCode, String reason){
		chatManager.remove(this);
	}
	
	public void sendString(String message){
		try{
			session.getRemote().sendString(message);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void sayServerStarted(){Logger.getGlobal().info("Server started");}
}
