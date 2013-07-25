package com.example.objviewer;

import rajawali.RajawaliFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLayout = (FrameLayout) inflater.inflate(R.layout.fragment_list, container, false);
		mLayout.addView(mSurfaceView);
		return mLayout;
	}
}
