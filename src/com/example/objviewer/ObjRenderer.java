package com.example.objviewer;

import javax.microedition.khronos.opengles.GL10;

import rajawali.BaseObject3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.math.Quaternion;
import rajawali.parser.AParser.ParsingException;
import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;

public class ObjRenderer extends RajawaliRenderer {
	private DirectionalLight mLight;
	private BaseObject3D mObject;
	private Sandbox mSandbox;
	private Quaternion mOrientation;
	private Quaternion mFling;

	public ObjRenderer(Context context) {
		super(context);
		setFrameRate(30);
		initOrientation();
	}

	@Override
	public void initScene() {
		mLight = new DirectionalLight(1f, 0.2f, -1.0f);
		mLight.setColor(1.0f, 1.0f, 1.0f);
		mLight.setPower(2);

		//mCamera.setZ(6.4f);
		mCamera.setZ(3.4f);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		if (mFling != null) {
			multiplyOrientation(mFling);
		}

		if (mObject != null) {
			mObject.setOrientation(mOrientation);
		}
		if (mSandbox != null) {
			mSandbox.randomWalk();
			mSandbox.getObject().setOrientation(mOrientation);
		}

		super.onDrawFrame(glUnused);
	}

	public void setObject(int id) {
		if (mObject != null) {
			removeChild(mObject);
			mObject = null;
		}
		if (mSandbox != null) {
			removeChild(mSandbox.getObject());
			mSandbox = null;
		}

		if (id != 0) {
			loadObject(id);
		} else {
			mSandbox = new Sandbox();
			mSandbox.getObject().addLight(mLight);
			addChild(mSandbox.getObject());
		}

		initOrientation();
		setFling(null);
	}

	private void loadObject(int id) {
		try {
			ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, id);
			objParser.parse();
			mObject = objParser.getParsedObject();
			mObject.addLight(mLight);
			DiffuseMaterial material = new DiffuseMaterial();
			material.setUseColor(true);
			mObject.setMaterial(material);
			mObject.setColor(0xff00ff00);
			addChild(mObject);
		} catch(ParsingException e) {
			e.printStackTrace();
		}
	}

	public void initOrientation() {
		mOrientation = new Quaternion();
	}

	public void multiplyOrientation(Quaternion quaternion) {
		mOrientation.multiply(quaternion);
	}

	public void setFling(Quaternion fling) {
		mFling = fling;
	}
}
