package fr.project.bluechat.chat;

import java.io.IOException;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import fr.project.bluechat.layout.activity.MainActivity;

public class Bluetooth {

	private final int REQUEST_ENABLE_BT = 0;

	private MainActivity mActivity;
	private BluetoothSocket btSocket =null; 
	private BluetoothAdapter mBluetoothAdapter;

	private final BroadcastReceiver mReceiver;



	/**
	 * Bluetooth class constructor.
	 * @param activity Activity who call the bluetooth.
	 */
	public Bluetooth(MainActivity activity) {
		mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		mReceiver = new BroadcastReceiver() { // Create a BroadcastReceiver for ACTION_FOUND

			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				// When discovery finds a device
				if (BluetoothDevice.ACTION_FOUND.equals(action)) {
					// Get the BluetoothDevice object from the Intent
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					Log.i("Bluetooth", device.getName() + "\n" + device.getAddress());
				}
			}
		};
	}

	/**
	 * Start the Bluetooth.
	 * @return True if Bluetooth is connect, false otherwise.
	 */
	public boolean start() {
		if( mBluetoothAdapter == null ) return false; // Device does not support Bluetooth.

		mBluetoothAdapter.disable();

		if( !mBluetoothAdapter.isEnabled() ) {
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

			mActivity.startActivity(discoverableIntent);
		}
		return true;
	}

	/**
	 * Use the BluetoothAdapter to find remote Bluetooth devices.
	 */
	private void findingDevices() {
		mBluetoothAdapter.startDiscovery();

		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

		if( pairedDevices.size() > 0 ) {
			for (BluetoothDevice device : pairedDevices) {
				// Add the name and address to an array adapter to show in a ListView
				Log.i("Bluetooth", device.getName() + "\n" + device.getAddress());
			}
		}
		else Log.i("Bluetooth", "Aucun appareil trouvé.");
	}


	public void sendMessage(String name, String message){
		findingDevices();
	}

	public void receiverMessage(String name, String message){

	}

	public void connect() {
		if (btSocket != null) {
			if(mBluetoothAdapter.isDiscovering()) {
				try {
					btSocket.connect();
				} catch (IOException e) {}
			}
		}
	}
}