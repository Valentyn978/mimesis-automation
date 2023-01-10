package com.tests.swing.shapes;

import java.awt.*;

import com.tests.swing.BaseShape;

public class Triangle extends BaseShape {
	public int width;
	public int height;

	public Triangle(int x, int y, int width, int height, Color color) {
		super(x, y, color);
		this.width = width;
		this.height = height;
	}

	@Override
	public int getWidth() {
		return (width + height / 2) - 1;

	}

	@Override
	public int getHeight() {
		return (width + height / 2) - 1;
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.drawLine(x, y, getWidth(), getHeight());
		graphics.drawLine(getWidth(), getHeight(), y, getWidth());
		graphics.drawLine(x, getWidth(), x, y);
	}
}
