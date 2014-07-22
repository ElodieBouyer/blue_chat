package fr.project.bluechat.chat;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class Server extends Thread {
	
	private final BluetoothServerSocket mmServerSocket;
	private static final String SERVICE_NAME = "BLUECHAT_SERVICE";
	private static final UUID SERVICE_UUID = UUID.fromString("9dbe5b39-11ad-11e4-9191-0800200c9a66");
	
	private Bluetooth myBluetooth;

	public Server(Bluetooth bluetooth) {
		myBluetooth = bluetooth;
		
		BluetoothServerSocket tmp = null;
		try {
			tmp = myBluetooth.getBluetoothAdapter().listenUsingRfcommWithServiceRecord(SERVICE_NAME, SERVICE_UUID);
		} catch (IOException e) { }
		mmServerSocket = tmp;
	}

	public void run() {
		BluetoothSocket socket = null;
		
		while (true) { // Keep listening until exception occurs or a socket is returned
			try {
				socket = mmServerSocket.accept();
			} catch (IOException e) {
				break;
			}
			// If a connection was accepted
			if (socket != null) {
				// Do work to manage the connection (in a separate thread)
				myBluetooth.connect(socket);
				try {
					mmServerSocket.close();
				} catch (IOException e) {
					break;
				}
				break;
			}
		}
	}

	/** Will cancel the listening socket, and cause the thread to finish */
	public void cancel() {
		try {
			mmServerSocket.close();
		} catch (IOException e) { }
	}

}
