package fr.project.bluechat.chat;

import android.bluetooth.BluetoothSocket;

public class User{
	
	private String name = null;
	private int BluetoothSocket; 
	
	public User(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void SetName(String name){
		this.name=name;
	}
	
	public void sendMessage(String name, String message){
		
	}	
	
}