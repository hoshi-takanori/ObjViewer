package com.example.objviewer;

import javax.microedition.khronos.opengles.GL10;

import rajawali.BaseObject3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.parser.AParser.ParsingException;
import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;

public class ObjRenderer extends RajawaliRenderer {
	private DirectionalLight mLight;
	private BaseObject3D mObject;

	public ObjRenderer(Context context) {
		super(context);
		setFrameRate(30);
	}

	@Override
	public void initScene() {
		mLight = new DirectionalLight(1f, 0.2f, -1.0f);
		mLight.setColor(1.0f, 1.0f, 1.0f);
		mLight.setPower(2);

		mCamera.setZ(6.4f);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);

		if (mObject != null) {
			mObject.setRotY(mObject.getRotY() + 1);
		}
	}

	public void setObject(int id) {
		if (mObject != null) {
			removeChild(mObject);
		}

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
}
