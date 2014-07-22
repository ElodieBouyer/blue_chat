package fr.project.bluechat.chat;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class Client extends Thread {

	private final BluetoothSocket mySocket;
	private final BluetoothDevice myDevice;
	private Bluetooth myBluetooth;

	public Client(Bluetooth bluetooth, BluetoothDevice device, UUID uuid) {
		BluetoothSocket tmp = null;
		myDevice = device;
		myBluetooth = bluetooth;

		try { // Get a BluetoothSocket to connect with the given BluetoothDevice
			tmp = myDevice.createRfcommSocketToServiceRecord(uuid);
		} 
		catch (IOException e) { }
		mySocket = tmp;
	}

	public void run() {
		// Cancel discovery because it will slow down the connection
		myBluetooth.getBluetoothAdapter().cancelDiscovery();

		try {
			// Connect the device through the socket. This will block
			// until it succeeds or throws an exception
			mySocket.connect();
		} 
		catch (IOException connectException) {
			// Unable to connect; close the socket and get out
			try {
				mySocket.close();
			} 
			catch (IOException closeException) { }
			return;
		}

		// Do work to manage the connection (in a separate thread)
		myBluetooth.connect(mySocket);
	}

	/** Will cancel an in-progress connection, and close the socket */
	public void cancel() {
		try {
			mySocket.close();
		} catch (IOException e) { }
	}
}

