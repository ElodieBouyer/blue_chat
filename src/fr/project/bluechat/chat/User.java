package fr.project.bluechat.chat;

import java.io.IOException;
import java.lang.Object;

import android.R;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
<<<<<<< HEAD
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.BaseAdapter;

public class User {

	private final int REQUEST_ENABLE_BT = 0;
	
	
=======


public class User{

>>>>>>> parent of 1de0ef6... Add User.Start() to start the bluetooth.
	private String name = null;
	private BluetoothSocket btSocket =null; 
<<<<<<< HEAD
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	

	public User(FragmentActivity activity, String name) {
		mActivity = activity;
=======
	private BluetoothAdapter mAdapter= BluetoothAdapter.getDefaultAdapter();
	public User(String name){
>>>>>>> parent of 1de0ef6... Add User.Start() to start the bluetooth.
		this.name=name;

	}

	public String getName(){
		return this.name;
	}

	public void SetName(String name){
		this.name=name;
	}
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver(){
	public void onReceive(Context context, Intent intent){
	String action = intent.getAction();
		if (BluetoothDevice.ACTION_FOUND.equals(action)){
			BluetoothDevice device= intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		}
		}
	};
	
	
	
	public void sendMessage(String name, String message){

	}	

	public void connect(){

<<<<<<< HEAD
	public void receiverMessage(String name, String message){
	
	}
	
	
	public void connect() {
=======
>>>>>>> parent of 1de0ef6... Add User.Start() to start the bluetooth.
		if (btSocket != null) {
			if(mAdapter.isDiscovering()) {
				try {
					btSocket.connect();
				} catch (IOException e) {}
			}
		}
	}
}