package fr.project.bluechat.chat;


import java.util.ArrayList;
import java.util.UUID;

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
	private static final String SERVICE_NAME = "BLUECHAT_SERVICE";
	private static final UUID SERVICE_UUID = UUID.fromString("9dbe5b39-11ad-11e4-9191-0800200c9a66");

	private MainActivity mActivity;
	private BluetoothAdapter mBluetoothAdapter;
	private ArrayList<BluetoothDevice> mDevides = new ArrayList<BluetoothDevice>();
	private Server server;

	private final BroadcastReceiver mReceiver;

	/**
	 * Bluetooth class constructor :
	 *  - get the device's own Bluetooth adapter ;
	 *  - create a broadcast receiver ;
	 *  - register the broadcast receiver.
	 *  
	 * @param activity Activity who call the Bluetooth.
	 */
	public Bluetooth(MainActivity activity) {
		mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		mReceiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();

				// When discovery finds a device
				if( BluetoothDevice.ACTION_FOUND.equals(action) ) {
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					mDevides.add(device);
					Log.i("BlueChat.Bluetooth.onReceive()", device.getName() + "\n" + device.getAddress());
					//ParcelUuid[] uuids = device.getUuids();
					//Log.i("BlueChat.Bluetooth.Bluetooth()", "Nombre d'uuid = "+ uuids.length);
					mActivity.updateShowWait();
				}

				// When the discovery started.
				else if( BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action) ) {
					Log.i("BlueChat.Bluetooth.onReceive()", "Recherche lancée.");
					mActivity.showWait();
				}

				// When the discovery finished.
				else if( BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action) ) {
					Log.i("BlueChat.Bluetooth.onReceive()", "Recherche terminée.");
					//if( !mDevides.isEmpty() ) startServer();
					mActivity.removeWait();
				}
			}
		};

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		mActivity.registerReceiver(mReceiver, filter);
	}


	/**
	 * Start the Bluetooth.
	 * 
	 * @return True if Bluetooth is connect, false otherwise.
	 */
	public boolean start() {

		// If the device does not support Bluetooth.
		if( mBluetoothAdapter == null ) { 
			mActivity.notSupportBluetooth();
			return false; 
		}

		if( !mBluetoothAdapter.isEnabled() ) {
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

			mActivity.startActivityForResult(discoverableIntent, BLUETOOTH_ENABLE);
		}
		else findingDevices();

		return true;
	}


	/**
	 * Find remote Bluetooth devices.
	 */
	public void findingDevices() {
		mBluetoothAdapter.startDiscovery();
	}


	/**
	 * Get the Bluetooth adapter.
	 * 
	 * @return The Bluetooth adapter.
	 */
	public BluetoothAdapter getBluetoothAdapter() {
		return mBluetoothAdapter;
	}


	public void startServer() {
		server = new Server(this, SERVICE_NAME, SERVICE_UUID);
	}

	public void sendMessage(String name, String message){

	}

	public void receiverMessage(String name, String message){

	}

	/**
	 * Called by server thread.
	 * @param socket Bluetooth socket for the communication.
	 */
	public void connect(BluetoothSocket socket) {
		Log.i("BlueChat.Bluetooth.connect()", "Appareil connecté a " + socket.getRemoteDevice().getName());
	}

	/**
	 * Clear bluetooth variable.
	 * Called by MainActivity.
	 */
	public void destroy() {
		if( mBluetoothAdapter != null) {
			mBluetoothAdapter.cancelDiscovery();
			mBluetoothAdapter.disable();
		}
		mActivity.unregisterReceiver(mReceiver);
		
		if( server != null ) server.cancel();
	}
}