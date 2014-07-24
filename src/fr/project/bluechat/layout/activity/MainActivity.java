package fr.project.bluechat.layout.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fr.project.bluechat.R;
import fr.project.bluechat.chat.Bluetooth;
import fr.project.bluechat.layout.fragment.ChatFragment;
import fr.project.bluechat.layout.fragment.EditFragment;
import fr.project.bluechat.layout.fragment.ErrorFragment;

public class MainActivity extends FragmentActivity {

	public static final String NICKNAME = "myNickname";

	private final int CHAT = 0;
	private final int EDIT = 1;
	private final int ERROR = 2;

	private static final int BLUETOOTH_ENABLE = 0;

	private int position = CHAT;
	private Menu menu = null;
	private ChatFragment chatFragment = null;
	private String userName;
	private Bluetooth mBluetooch;
	private ProgressDialog wait;
	private int nbPeople = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// To read preferences to get the user's name.
		SharedPreferences settings = getSharedPreferences(NICKNAME, Context.MODE_PRIVATE);
		String nickname = settings.getString(NICKNAME, new String());

		Log.i("BlueChat.MainActivity.onCreate()", "Activity crée.");

		mBluetooch = new Bluetooth(this);
		mBluetooch.start();

		if (savedInstanceState == null && position != ERROR ) {
			if( nickname.isEmpty()  ) { // We open the PseudoFragment.
				Log.i("BlueChat", "Nickname =" + nickname.length() + ".");
				openFragmentNickname();
			}
			else { // We open the chat fragment.
				userName = nickname;
				openFragmentChat();
			}
		} 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu m) {

		if( m == null ) return false;

		MenuInflater inflater = getMenuInflater();
		m.clear();

		switch (position) {
		case EDIT:	
			inflater.inflate(R.menu.menu_edit, m);
			break;

		case CHAT:
			inflater.inflate(R.menu.menu_chat, m);
			break;

		default:
			break;
		}
		menu = m;
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_edit:
			openFragmentNickname();
			return true;

		case R.id.action_back:
			openFragmentChat();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onStop() {
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mBluetooch.destroy();
	}

	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		if( requestCode == BLUETOOTH_ENABLE ) {

			if( resultCode == Activity.RESULT_CANCELED) {
				Log.i("BlueChat.MainActivity.onActivityResult()", "Bluetooth n'a pas été activé.");	
			}
			else {
				Log.i("BlueChat.MainActivity.onActivityResult()", "Le Bluetooth a été activé.");
				mBluetooch.findingDevices();
			}
		}
	}


	/**
	 * Called when the user adds his nickname. 
	 * @param v Button to add.
	 */
	public void newName(View v) {
		EditText text = (EditText) findViewById(R.id.edit_name);

		if( text.getText().toString().isEmpty() ) {
			Toast toast = Toast.makeText(getApplicationContext(), R.string.error_name, Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		userName = text.getText().toString();

		// Edit share preferences to store the new name.
		SharedPreferences settings = getSharedPreferences(NICKNAME, Context.MODE_PRIVATE);
		settings.edit().putString(NICKNAME,userName).apply();


		// To display a text message for user.
		Toast toast = Toast.makeText(getApplicationContext(), R.string.ok_name, Toast.LENGTH_SHORT);
		toast.show();

		openFragmentChat();
	}

	public void notSupportBluetooth() {
		openFragmentError();
	}

	public void peopleFound(String name) {
		nbPeople++;
		Toast toast = Toast.makeText(getApplicationContext(), 
				name + " " + getString(R.string.wait_discovery_msg0), Toast.LENGTH_SHORT);
		toast.show();
	}

	public void peopleDeconect(String name) {
		nbPeople--;
		Toast toast = Toast.makeText(getApplicationContext(), 
				name + " " + getString(R.string.wait_discovery_msg1), Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Called when the user want to send a message.
	 * @param v Button to send.
	 */
	public void sendMessage(View v) {
		EditText newMessage = (EditText) findViewById(R.id.edit_message);
		String message = newMessage.getText().toString();
		if( message.isEmpty()) {

			Toast toast = Toast.makeText(getApplicationContext(), R.string.error_message, Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		chatFragment.writeUserMessage(userName, message);
		mBluetooch.sendMessage(userName, message);
	}

	/**
	 * Open the chat fragment.
	 */
	public void openFragmentChat() {
		position = CHAT;
		if(chatFragment == null) {
			chatFragment = new ChatFragment();
		}
		else getSupportFragmentManager().beginTransaction().remove(chatFragment);

		try {
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, chatFragment).commit();
		}
		catch(Exception e) {
			Log.i("Error", e.getMessage());
		}
		onCreateOptionsMenu(menu);

	}

	/**
	 * Open the nickname fragment.
	 */
	private void openFragmentNickname() {
		Log.i("BlueChat.MainActivity.openFragmentNickname()", "Ouverture du fragment edit.");

		position = EDIT;	
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new EditFragment(userName)).commit();
		onCreateOptionsMenu(menu);
	}


	/**
	 * Open the error fragment.
	 */
	public void openFragmentError() {
		Log.i("BlueChat.MainActivity.openFragmentNickname()", "Ouverture du fragment error.");

		position = ERROR;
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new ErrorFragment()).commit();
		onCreateOptionsMenu(menu);
	}

}
