package fr.project.bluechat.layout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.project.bluechat.R;

public class NicknameFragment extends Fragment {

	private String name;

	public NicknameFragment(String name) {
		super();
		this.name = name;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View containerView = inflater.inflate(R.layout.fragment_name, container, false);
		TextView pseudoLabel = (TextView)  containerView.findViewById(R.id.label_name);

		if( name == null ) pseudoLabel.setText(R.string.empty_name);
		else {
			pseudoLabel.setText(R.string.edit_name);
		}

		return containerView;
	}
}
