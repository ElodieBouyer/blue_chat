package fr.project.bluechat.layout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.project.bluechat.R;

public class ErrorFragment extends Fragment {

	public ErrorFragment() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("BlueChat.ErrorFragment.onCreateView()", "Création du fragment Error.");
		
		return inflater.inflate(R.layout.fragment_error, container, false);
	}
}
