package com.juego;

import android.content.Context;
import android.graphics.Rect;

public class BalaTerricolaMoveThread extends Thread{
	
	 private BalaTerricola balaterricola;
	 private Rect screen;
	 private boolean run;
	 private int speed;
	
	public BalaTerricolaMoveThread(BalaTerricola balaterricola ,Rect screen,Context context) {
		  
		  this.screen = screen;
		  this.balaterricola=balaterricola;
		
		  this.run = false;
		  this.speed = 4;
		  
	}
	
	public void setRunning(boolean run) {
		  this.run = run;
		 }
	 @Override
	 public void run() {
	  while(run) {
	   try {
	    Thread.sleep(10);
	   } catch (InterruptedException e) {
	    e.printStackTrace();
	   }
	
	   //if(!balaterricola.puedoMover(speed, speed, screen)){
		 //  balaterricola.rebota(speed, speed, screen);}
		   balaterricola.move(speed, speed);

	   
	  }
	 }

}
