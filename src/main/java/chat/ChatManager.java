package chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatManager {

	private Set<ChatWebSocket> webSockets;
	
	public ChatManager(){
		this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
	}
	
	public void sendMessage(String message){
		for(ChatWebSocket user : webSockets){
			try{
				user.sendString(message);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void add(ChatWebSocket cws){
		webSockets.add(cws);
	}
	
	public void remove(ChatWebSocket cws){
		webSockets.remove(cws);
	}
}
