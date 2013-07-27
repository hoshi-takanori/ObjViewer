package com.example.objviewer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	public static final String[] OBJS = { "cube", "monkey", "teapot", "sandbox" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onItemSelected(String name) {
		ObjFragment fragment = (ObjFragment) getFragmentManager().findFragmentById(R.id.fragment_obj);
		if (name.equals("cube")) {
			fragment.getRenderer().setObject(R.raw.cube);
		} else if (name.equals("monkey")) {
			fragment.getRenderer().setObject(R.raw.monkey);
		} else if (name.equals("teapot")) {
			fragment.getRenderer().setObject(R.raw.teapot);
		} else {
			fragment.getRenderer().setObject(0);
		}
	}
}
