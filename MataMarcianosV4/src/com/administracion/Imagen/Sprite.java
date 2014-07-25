package com.administracion.Imagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Opciones.Opciones;
import com.Pintado.movimiento.MarcianosView;
import com.matamarcianosv1.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;

@SuppressLint("DrawAllocation")
public class Sprite {
	

	private static final int BMP_COLUMNS = 5;
	private static final int BMP_ROWS = 3;
	
	private int x;
	private int y;

	private Rect src,dst;
	private Bitmap bmp;
	private int width;
	private int height;
	private int currentFrame=0;
	private int life = 15;
	private int tLimite=5;
    private int t=0;
	private List<Sprite> temps;
	public  MediaPlayer audioExplocion = null;
	
	public Sprite(List<Sprite> temps, int x, int y, Bitmap bmp,Context context)
	{
		this.temps = temps;
		this.x = x;
        this.y = y;
		this.bmp=bmp;
		this.width=bmp.getWidth() / BMP_COLUMNS;
		this.height=bmp.getHeight() /BMP_ROWS;
		this.audioExplocion = MediaPlayer.create(context,R.raw.explo);
			if(Opciones.getInstance().soundEnabled())
				audioExplocion.start();
	}
	 private void secuencia(){
			if(t==tLimite){
			currentFrame = ++currentFrame % BMP_COLUMNS;
			  t=0;
		      }else t++;
		}
	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas)
	{
		secuencia();
		update();
		int srcX=currentFrame * width;
		int srcY = currentFrame*height;
		src = new Rect(srcX,srcY,srcX + width,srcY + height);
	    dst = new Rect (x,y,x+width,y+height);
		canvas.drawBitmap(bmp, src, dst,null);
		
	}
	  private void update() {
          if (--life < 1) {
                 temps.remove(this);
          }
    }
	
}
