package com.example.objviewer;

import rajawali.RajawaliFragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ObjFragment extends RajawaliFragment {
	private ObjRenderer mRenderer;

	public ObjRenderer getRenderer() {
		return mRenderer;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mRenderer = new ObjRenderer(getActivity());
		mRenderer.setSurfaceView(mSurfaceView);
		setRenderer(mRenderer);

		final GestureDetector detector = new GestureDetector(getActivity(), new GestureListener(this));
		mSurfaceView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLayout = (FrameLayout) inflater.inflate(R.layout.fragment_list, container, false);
		mLayout.addView(mSurfaceView);
		return mLayout;
	}
}
