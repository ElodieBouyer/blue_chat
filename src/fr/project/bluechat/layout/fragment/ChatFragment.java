package fr.project.bluechat.layout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.project.bluechat.R;

public class ChatFragment extends Fragment {

	public ChatFragment() {
		super();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View containerView = inflater.inflate(R.layout.fragment_chat, container, false);
		return containerView;
	}

}
