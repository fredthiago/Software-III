package com.juego;

import android.graphics.Rect;

public class BalaMarcianoMoveThread extends Thread{
	 
	 private BalaMarciano balamarciano;
	 private Rect screen;
	 private boolean run;
	 private int speedX;
	 private int speedY;
     private final int a=0;
     private final int b=1;
     private final int c=2;
     private int op;
     
	public BalaMarcianoMoveThread(BalaMarciano balamarciano ,Rect screen,int op) {
		  this.screen = screen;
		  this.balamarciano=balamarciano;
	
		  this.run = false;
		  this.speedX = 4;
		  this.speedY = 1;
		  this.op=op;
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
	    switch(op){
	    case a: 
	    balamarciano.move(speedX, speedY); 
	    break;
	    case b:
	    balamarciano.move1(speedX, speedY);
	    break;
	    case c:
		 balamarciano.move2(speedX, speedY);
		 break;
	    }
	    
	  }
	 }

}
