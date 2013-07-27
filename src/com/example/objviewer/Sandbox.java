package com.example.objviewer;

import rajawali.BaseObject3D;
import rajawali.materials.DiffuseMaterial;
import rajawali.primitives.Cube;

public class Sandbox {
	public static final int SIZE = 48;
	public static final int LEVEL = 16;

	private int[][] data;

	private BaseObject3D object;
	private BaseObject3D[][] objects;

	public Sandbox() {
		data = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				//data[i][j] = (int) (Math.random() * LEVEL) + 1;
				data[i][j] = 1;
			}
		}

		DiffuseMaterial material = new DiffuseMaterial();
		material.setUseColor(true);

		object = new BaseObject3D();
		objects = new BaseObject3D[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				objects[i][j] = new Cube(2.0f / SIZE);
				objects[i][j].setX(((float) i - SIZE / 2) * 2 / SIZE);
				objects[i][j].setY(((float) j - SIZE / 2) * 2 / SIZE);
				objects[i][j].setZ((float) data[i][j] / SIZE);
				objects[i][j].setScaleZ(data[i][j]);
				objects[i][j].setMaterial(material);
				objects[i][j].setColor(0xff00ff00);
				object.addChild(objects[i][j]);
			}
		}
	}

	public BaseObject3D getObject() {
		return object;
	}

	private int x = -1;
	private int y = -1;

	public void randomWalk() {
		if (Math.random() < 0.02) {
			x = -1;
			y = -1;
		}

		if (x < 0 || y < 0) {
			x = (int) (Math.random() * SIZE);
			y = (int) (Math.random() * SIZE);
		} else {
			x += (int) (Math.random() * 3) - 1;
			if (x < 0) { x = 0; } else if (x >= SIZE) { x = SIZE - 1; }
			y += (int) (Math.random() * 3) - 1;
			if (y < 0) { y = 0; } else if (y >= SIZE) { y = SIZE - 1; }
		}

		if (data[x][y] < LEVEL) {
			data[x][y]++;
		}
		objects[x][y].setZ((float) data[x][y] / SIZE);
		objects[x][y].setScaleZ(data[x][y]);
	}
}
