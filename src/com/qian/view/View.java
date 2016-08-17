package com.qian.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.qian.model.Cell;
import com.qian.model.Field;

public class View extends JPanel {
	private static final long serialVersionUID = -5258995676212660595L;
	private static final int GRID_SIZE = 16;
	private Field theField;
	
	/**
	 * init this with the cells data
	 * @param field
	 */
	public View(Field field) {
		theField = field;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for ( int row = 0; row<theField.getHeight(); row++ ) {
			for ( int col = 0; col<theField.getWidth(); col++ ) {
				Cell cell = theField.get(row, col);
				g.drawRect(col*GRID_SIZE, row*GRID_SIZE, GRID_SIZE, GRID_SIZE);
				if ( cell != null&&cell.isAlive()) {
					//draw a alive cell 
					g.fillRect(col*GRID_SIZE, row*GRID_SIZE, GRID_SIZE, GRID_SIZE);
					//System.out.println("draw a alive cell");
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(theField.getWidth()*GRID_SIZE+1, theField.getHeight()*GRID_SIZE+1);
	}


}
