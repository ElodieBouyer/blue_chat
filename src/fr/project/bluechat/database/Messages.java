package fr.project.bluechat.database;

public class Messages {

	String nickname;
	String messages;
	
	public Messages(String n, String msg) {
		nickname = n;
		messages = msg;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getMessages() {
		return messages;
	}
}
