package fr.project.bluechat.layout.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import fr.project.bluechat.R;
import fr.project.bluechat.database.User;
import fr.project.bluechat.layout.fragment.ChatFragment;
import fr.project.bluechat.layout.fragment.EditFragment;

public class MainActivity extends FragmentActivity {

	private final int CHAT = 0;
	private final int EDIT = 1;

	private User userDatabse;
	private int position = CHAT;
	private Menu menu = null;
	private ChatFragment chatFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		userDatabse = new User(getApplicationContext());

		if (savedInstanceState == null) {
			if( userDatabse.getName() == null ) { // We open the PseudoFragment.
				openFragmentNickname();
			}
			else { // We open the chat fragment.
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
		openFragmentChat();
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
		userDatabse.setName(text.getText().toString());
		Toast toast = Toast.makeText(getApplicationContext(), R.string.ok_name, Toast.LENGTH_SHORT);
		toast.show();

		openFragmentChat();
	}

	/**
	 * Called when the user want to send a message.
	 * @param v Button to send.
	 */
	public void sendMessage(View v) {
		EditText newMessage = (EditText) findViewById(R.id.edit_message);
		if( newMessage.getText().toString().isEmpty()) {
			Toast toast = Toast.makeText(getApplicationContext(), R.string.error_message, Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		chatFragment.writeUserMessage(userDatabse.getName(), newMessage.getText().toString());
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
		position = EDIT;
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new EditFragment(userDatabse.getName())).commit();
		onCreateOptionsMenu(menu);
	}


}
