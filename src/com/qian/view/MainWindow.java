package com.qian.view;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.qian.model.Field;

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Field field ;
	View v;
	public static void main(String[] args)  {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setTitle("Cells");
		mainWindow.init();
		mainWindow.setVisible(true);
		mainWindow.pack();
		mainWindow.setResizable(false);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mainWindow.start();//update the status
				mainWindow.repaint();//repaint the view
			}
		}, 0,1000);
	}
	private void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		field = new Field(20,20);
		v = new View(field);
		this.add(v);
	}
	private void start(){
		field.updateData();
	}

}
