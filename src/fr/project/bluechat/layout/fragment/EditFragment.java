package fr.project.bluechat.layout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.project.bluechat.R;

public class EditFragment extends Fragment {

	private String name;

	public EditFragment(String name) {
		super();
		this.name = name;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i("BlueChat.EditFragment", "onCreateView");
		View containerView = inflater.inflate(R.layout.fragment_edit, container, false);
		TextView pseudoLabel = (TextView)  containerView.findViewById(R.id.label_name);

		if( name == null ) pseudoLabel.setText(R.string.empty_name);
		else {
			pseudoLabel.setText(R.string.edit_name);
		}

		return containerView;
	}
}
