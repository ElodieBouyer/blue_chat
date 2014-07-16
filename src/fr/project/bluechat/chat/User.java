package fr.project.bluechat.chat;

import java.io.IOException;
<<<<<<< HEAD
import java.lang.Object;

import android.R;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.BaseAdapter;
=======

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

>>>>>>> parent of 579cf20... add brodcast receiver

public class User {

	private final int REQUEST_ENABLE_BT = 0;
<<<<<<< HEAD
	
	
=======

>>>>>>> parent of 579cf20... add brodcast receiver
	private String name = null;
	FragmentActivity mActivity;
	private BluetoothSocket btSocket =null; 
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
<<<<<<< HEAD
	
=======

>>>>>>> parent of 579cf20... add brodcast receiver

	public User(FragmentActivity activity, String name) {
		mActivity = activity;
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void SetName(String name){
		this.name=name;
	}
<<<<<<< HEAD
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver(){
	public void onReceive(Context context, Intent intent){
	String action = intent.getAction();
		if (BluetoothDevice.ACTION_FOUND.equals(action)){
			BluetoothDevice device= intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		}
		}
	};
	
	
	
=======

>>>>>>> parent of 579cf20... add brodcast receiver
	public void sendMessage(String name, String message){

	}

<<<<<<< HEAD
	public void receiverMessage(String name, String message){
	
	}
	
	
=======
>>>>>>> parent of 579cf20... add brodcast receiver
	public void connect() {
		if (btSocket != null) {
			if(mBluetoothAdapter.isDiscovering()) {
				try {
					btSocket.connect();
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * Start the Bluetooth.
	 * @return True if Bluetooth is connect, false otherwise.
	 */
	public boolean start() {
		if( mBluetoothAdapter == null ) return false; // Device does not support Bluetooth.

		if( !mBluetoothAdapter.isEnabled() ) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			mActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		return true;
	}
}