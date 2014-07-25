package com.juego;

import android.content.Context;
import android.graphics.Rect;

public class MarcianoMoveThread extends Thread {

	 private Marciano marciano;
	 private Rect screen;
	 private boolean run;
	 private int speed;

	 public MarcianoMoveThread(Marciano marciano,Rect screen,Context context) {
	  this.marciano = marciano;
	  this.screen = screen;
	  
	  this.run = false;
	  this.speed = 1;
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
	   if(!marciano.puedoMover(speed, speed, screen))
		   marciano.rebota(speed, speed, screen);
		   marciano.move(speed, speed);
	  }
	 }
	}


