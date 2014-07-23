package fr.project.bluechat.chat;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class Server extends Thread {
	
	private final BluetoothServerSocket mmServerSocket;
	
	
	private Bluetooth myBluetooth;

	public Server(Bluetooth bluetooth, String name, UUID uuid) {
		Log.i("BlueChat.Server.Server()", "Lancement du serveur.");
		
		myBluetooth = bluetooth;
		BluetoothServerSocket tmp = null;
		
		try {
			tmp = myBluetooth.getBluetoothAdapter().listenUsingRfcommWithServiceRecord(name, uuid);
		} catch (IOException e) { }
		mmServerSocket = tmp;
	}

	public void run() {
		Log.i("BlueChat.Server.run()", "Attente d'une connexion.");
		
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
		Log.i("BlueChat.Server.cancel()", "Socket du serveur detruite.");
		try {
			mmServerSocket.close();
		} catch (IOException e) { }
	}

}
