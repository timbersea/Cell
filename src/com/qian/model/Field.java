package com.qian.model;

import java.util.ArrayList;

public class Field {
	private int width;
	private int height;
	private Cell[][] cells;

	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new Cell[height][width];
		this.init();
		this.initSomeAlive();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Cell place(int row, int col, Cell o) {
		Cell ret = cells[row][col];
		cells[row][col] = o;
		return ret;
	}

	public Cell get(int row, int col) {
		return cells[row][col];
	}

	// get a cell's neighbour
	public Cell[] getNeighbour(int row, int col) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int r = row + i;
				int c = col + j;
				if (r > -1 && r < height && c > -1 && c < width && !(r == row && c == col)) {
					list.add(cells[r][c]);
				}
			}
		}
		return list.toArray(new Cell[list.size()]);
	}

	public void clear() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = null;
			}
		}
	}

	/**
	 * it's died or alive depend on the neighbour amount
	 */
	public void updateData() {
		//System.out.print("current:\n");
		//outCells();
		for ( int i=0; i<1000; i++ ) {
			for ( int row = 0; row<this.getHeight(); row++ ) {
				for ( int col = 0; col<this.getWidth(); col++ ) {
					Cell cell = this.get(row, col);
					Cell[] neighbour = this.getNeighbour(row, col);
					int numOfLive = 0;
					for ( Cell c : neighbour ) {
						if ( c.isAlive() ) {
							numOfLive++;
						}
					}
					//System.out.print("["+row+"]["+col+"]:is ");
					//System.out.print(cell.isAlive()?"live":"dead ");
					//System.out.print("surrounded with"+numOfLive+"--> then change to ");
					if ( cell.isAlive() ) {
						if ( numOfLive <2 || numOfLive >3 ) {
							cell.die();
							//System.out.print("die");
						}
					} else if ( numOfLive == 3||Math.random()<0.2) {
						cell.reborn();
						//System.out.print("live");
					}
					//System.out.println();
				}
			}
			//System.out.println("UPDATE ONCE　DATA");
			//System.out.print("after update\n");
			//outCells();
		}
	}
	//set every cell not null
	private void init(){
		for ( int row = 0; row<this.getHeight(); row++ ) {
			for ( int col = 0; col<this.getWidth(); col++ ) {
				this.place(row, col, new Cell());
			}
		}
	}
	//set some cells alive
	public void initSomeAlive(){
		for ( int row = 0; row<this.getHeight(); row++ ) {
			for ( int col = 0; col<this.getWidth(); col++ ) {
				if ( Math.random() < 0.2 ) {//0.2的概率是活的
					cells[row][col].reborn();
				}
			}
		}
	}
	@SuppressWarnings("unused")
	private void outCells(){
		for (int i = 0; i < cells.length; i++) {
			for(int j=0;j<cells[i].length;j++){
				System.out.print(cells[i][j].isAlive()?"a":"d");
			}
			System.out.println();
		}
	}
}
