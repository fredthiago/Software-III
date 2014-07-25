package com.juego;

import android.content.Context;
import android.graphics.Rect;

public class EscenarioMoveThread extends Thread{
	
	 private Escenario escenario;
	 private Rect screen;
	 private boolean run;
	 private int speed;
	 
	public EscenarioMoveThread(Escenario escenario ,Rect screen) {
		  
		  this.screen = screen;
		  this.escenario=escenario;
		  this.run = false;
		  this.speed = 3;
	}
	
	public void setRunning(boolean run) {
		  this.run = run;
		 }
	 @Override
	 public void run() {
	  while(run) {
	   try {
	    Thread.sleep(20);
	   } catch (InterruptedException e) {
	    e.printStackTrace();
	   }
			   escenario.move(speed, speed);
	  }
	 }

}
