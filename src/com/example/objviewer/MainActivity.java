package com.example.objviewer;

import rajawali.RajawaliActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends RajawaliActivity {
	public static final String[] OBJS = { "cube", "monkey", "teapot", "sandbox" };

	private ObjRenderer mRenderer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((ViewGroup) findViewById(R.id.placeholder)).addView(mLayout);

		ListView listView = (ListView) this.findViewById(R.id.list_view);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, OBJS));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onItemSelected(OBJS[position]);
			}
		});

		mRenderer = new ObjRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		setRenderer(mRenderer);

		final GestureDetector detector = new GestureDetector(this, new GestureListener(mRenderer));
		mSurfaceView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void setContentView(View view) {
		if (view != mLayout) {
			super.setContentView(view);
		}
	}

	public void onItemSelected(String name) {
		if (name.equals("cube")) {
			mRenderer.setObject(R.raw.cube);
		} else if (name.equals("monkey")) {
			mRenderer.setObject(R.raw.monkey);
		} else if (name.equals("teapot")) {
			mRenderer.setObject(R.raw.teapot);
		} else {
			mRenderer.setObject(0);
		}
	}
}
