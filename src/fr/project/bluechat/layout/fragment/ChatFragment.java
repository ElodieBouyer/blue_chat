package fr.project.bluechat.layout.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import fr.project.bluechat.R;
import fr.project.bluechat.database.Messages;

public class ChatFragment extends Fragment {

	private TableLayout tableChat = null;
	private EditText editMessage = null;
	private List<Messages> messages;

	public ChatFragment() {
		super();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if( tableChat == null ) Log.i("BOUH", "sniiif");
		View containerView = inflater.inflate(R.layout.fragment_chat, container, false);
		tableChat = (TableLayout) containerView.findViewById(R.id.table_chat);
		editMessage = (EditText) containerView.findViewById(R.id.edit_message);
		
		if( messages == null ) messages = new ArrayList<Messages>();
		
		for(Messages msg : messages) {
			writeUserMessage(msg.getNickname(), msg.getMessages());
		}
		
		return containerView;
	}

	public TableLayout getTableChat() {
		return tableChat;
	}

	public void setTableChat(TableLayout table) {
		tableChat = table;
	}

	public void writeUserMessage(String nickname, String message) {
		messages.add(new Messages(nickname, message));
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
