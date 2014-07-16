package fr.project.bluechat.layout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import fr.project.bluechat.R;

public class ChatFragment extends Fragment {

	private TableLayout tableChat = null;
	private LinearLayout layoutChat = null;
	private EditText editMessage = null;
	private View containerView = null;

	public ChatFragment() {
		super();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("BlueChat.ChatFragment", "onCreateView");

		// The container view.
		containerView = inflater.inflate(R.layout.fragment_chat, container, false);

		// The edit text.
		editMessage = (EditText) containerView.findViewById(R.id.edit_message);

		TableLayout aux = new TableLayout(getActivity().getApplicationContext());
		// The table layout.
		if( tableChat != null && tableChat.getChildCount() != 0) {
			Log.i("chatFragment", "nb ligne = " + tableChat.getChildCount());
			for( int i = tableChat.getChildCount()-1 ; i >= 0  ; i--) {
				Log.i("chatFragment", "Ligne = " + i);
				TableRow row = (TableRow) tableChat.getChildAt(i);
				tableChat.removeViewAt(i);
				aux.addView(row);
			}

		}
		tableChat = aux;
		aux = null;
		// The chat layout.
		layoutChat = (LinearLayout) containerView.findViewById(R.id.table_chat);
		layoutChat.addView(tableChat);

		return containerView;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("BOUH", "re.");
	}

	public TableLayout getTableChat() {
		return tableChat;
	}

	public void setTableChat(TableLayout table) {
		tableChat = table;
	}

	public void writeUserMessage(String nickname, String message) {
		Context context = getActivity().getApplicationContext();
		TableRow row          = new TableRow(context);
		TextView nicknameView = new TextView(context);
		TextView messageView  = new TextView(context);

		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		nicknameView.setText(nickname+" : ");
		nicknameView.setTextAppearance(context, R.style.chat_nickname);

		messageView.setText(message);
		messageView.setTextAppearance(context, R.style.chat_message);

		row.addView(nicknameView);
		row.addView(messageView);

		tableChat.addView(row);
		editMessage.getText().clear();
	}

}
