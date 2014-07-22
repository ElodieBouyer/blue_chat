package fr.project.bluechat.chat;

import java.io.IOException;
import java.util.ArrayList;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import fr.project.bluechat.layout.activity.MainActivity;

public class Bluetooth {

	private static final int BLUETOOTH_ENABLE = 0;

	private MainActivity mActivity;
	private BluetoothSocket btSocket =null; 
	private BluetoothAdapter mBluetoothAdapter;
	private ArrayList<BluetoothDevice> mDevides = new ArrayList<BluetoothDevice>();

	private final BroadcastReceiver mReceiver;

	/**
	 * Bluetooth class constructor.
	 * @param activity Activity who call the bluetooth.
	 */
	public Bluetooth(MainActivity activity) {
		mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		mReceiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();

				// When discovery finds a device
				if (BluetoothDevice.ACTION_FOUND.equals(action)) {
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					mDevides.add(device);
					Log.i("BlueChat.Bluetooth.onReceive()", device.getName() + "\n" + device.getAddress());
				}
			}
		};

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		//filter.addAction(BluetoothDevice.ACTION_UUID);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		mActivity.registerReceiver(mReceiver, filter);
	}

	/**
	 * Start the Bluetooth.
	 * @return True if Bluetooth is connect, false otherwise.
	 */
	public boolean start() {
		if( mBluetoothAdapter == null ) return false; // Device does not support Bluetooth.

		if( !mBluetoothAdapter.isEnabled() ) {
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

			mActivity.startActivityForResult(discoverableIntent, BLUETOOTH_ENABLE);
		}
		else findingDevices();

		return true;
	}

	/**
	 * Use the BluetoothAdapter to find remote Bluetooth devices.
	 */
	public void findingDevices() {
		Log.i("BlueChat.Bluetooth.findingDevices()", "Recherche lancée.");
		mBluetoothAdapter.startDiscovery();
	}


	public void sendMessage(String name, String message){

	}

	public void receiverMessage(String name, String message){

	}

	/**
	 * Clear bluetooth variable.
	 * Called by MainActivity.
	 */
	public void destroy() {
		if( mBluetoothAdapter != null) {
			mBluetoothAdapter.cancelDiscovery();
		}
		mActivity.unregisterReceiver(mReceiver);
		mBluetoothAdapter.disable();
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