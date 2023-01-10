package com.tests.swing.shapes;

import java.awt.*;

import com.tests.swing.BaseShape;

public class Oval extends BaseShape {
	public int width;
	public int height;

	public Oval(int x, int y, int width, int height, Color color) {
		super(x, y, color);
		this.width = width;
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.drawOval(x, y, getWidth() - 1, getHeight() - 1);
	}
}
