package fr.project.bluechat.layout.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fr.project.bluechat.R;
import fr.project.bluechat.database.User;
import fr.project.bluechat.layout.fragment.NicknameFragment;

public class MainActivity extends FragmentActivity {

	private User userDatabse;

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
				
			}
		} 
	}

	/**
	 * Called when the user adds his nickname. 
	 * @param v Button add.
	 */
	public void newName(View v) {
		EditText text = (EditText) findViewById(R.id.edit_name);

		if( text.getText().toString().isEmpty() ) {
			Toast toast = Toast.makeText(getApplicationContext(), R.string.error_name, Toast.LENGTH_SHORT);
			toast.show();
		}
		userDatabse.setName(text.getText().toString());
		Toast toast = Toast.makeText(getApplicationContext(), R.string.ok_name, Toast.LENGTH_SHORT);
		toast.show();

		openFragmentChat();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_edit:
			openFragmentNickname();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Open the chat fragment.
	 */
	private void openFragmentChat() {
		//getSupportFragmentManager().beginTransaction()
		//.add(R.id.container, new ChatFragment.commit();
	}

	/**
	 * Open the nickname fragment.
	 */
	private void openFragmentNickname() {
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new NicknameFragment(userDatabse.getName())).commit();
	}
}
