package fr.project.bluechat.chat;

import java.io.IOException;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;


public class User{

	private String name = null;
	private BluetoothSocket btSocket =null; 
	private BluetoothAdapter mAdapter= BluetoothAdapter.getDefaultAdapter();
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

	public void connect(){

		if (btSocket != null) {
			if(mAdapter.isDiscovering()) {
				try {
					btSocket.connect();
				} catch (IOException e) {}
			}
		}
	}
}