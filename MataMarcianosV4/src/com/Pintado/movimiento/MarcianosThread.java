package com.Pintado.movimiento;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
	 
	@SuppressLint("WrongCall")
	public class MarcianosThread extends Thread {
	 //static final long FPS = 10;
	 private SurfaceHolder sh;
	 private MarcianosView view;
	 private boolean run;
	 
	 public MarcianosThread(SurfaceHolder sh, MarcianosView view) {
	  this.sh = sh;
	  this.view = view;
	  run = false;
	 }
	 
	 public void setRunning(boolean run) {
	  this.run = run;
	 }
	 
	 @Override
	public void run() {
		 //long ticksPS = 1000 / FPS;
		 //long startTime;
		 //long sleepTime;
	  Canvas canvas;
	  while(run) {
	   canvas = null;
	   //startTime = System.currentTimeMillis();
	   try {
	    canvas = sh.lockCanvas(null);
	    synchronized(sh) {
	     view.onDraw(canvas);
	    }
	   } finally {
	    if(canvas != null)
	     sh.unlockCanvasAndPost(canvas);
	   }
	   //sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
		//try {
			//if (sleepTime > 0)
				//sleep(sleepTime);
			//else
				//sleep(10);
			//} catch (Exception e) {}
	  }
	 }
	}
