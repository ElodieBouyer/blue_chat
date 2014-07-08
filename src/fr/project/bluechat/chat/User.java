package fr.project.bluechat.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass.Device;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;


public class User{
	
	private String name = null;
	private BluetoothSocket btSocket =null; 
	
	public User(String name){
		this.name=name;
		//this.btSocket= Device.createRfcommSocketToServiceRecord(1);
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
		
		/*if (btSocket != null) {
            if(mAdapter.isDiscovering()) {
            	try {
            		btSocket.connect();
            	} catch (IOException e) {}
            }*/
	}
}