package com.tests.swing;

import java.awt.*;

import com.tests.swing.shapes.Oval;
import com.tests.swing.shapes.Rectangle;
import com.tests.swing.shapes.Rectangle3D;
import com.tests.swing.shapes.Triangle;


public class Paint {

	public static void main(String[] args) {
		ImageEditor editor = new ImageEditor();
		editor.loadShapes(new Oval(10, 10, 33,33, Color.BLUE),
				new CompoundShape(new Oval(110, 110, 50,40, Color.RED),
						new Oval(120, 120, 100,88, Color.DARK_GRAY),
						new Rectangle3D(120, 120, 250,200, Color.DARK_GRAY),
						new Rectangle3D(120, 120, 210,160, Color.BLUE),
						new Rectangle3D(120, 120, 270,250, Color.RED),
						new Triangle(40, 30, 300,300, Color.BLUE),
						new Rectangle(400, 150, 110, 300, Color.GREEN)
				));
	}
}
