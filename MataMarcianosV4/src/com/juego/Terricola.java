package com.juego;

import java.util.ArrayList;

import com.Opciones.Opciones;
import com.matamarcianosv1.R;




import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;

import android.os.Vibrator;

public class Terricola   extends Elemento implements ElementoMovil {
   private ArrayList<BalaTerricola> balaTerricola;
   private ArrayList<BalaTerricolaMoveThread> balasTerricolaMoveThread;
   public Vibrator v = null;
   public MediaPlayer mp = null;
   //private static final int BMP_COLUMNS = 4;
   //private static final int BMP_ROWS = 4;
   //public int width;
   //public int height;
   private Rect src,dst;
   private int currentFrame=0;
   private int tLimite=5;
   private int t=0;
   
   
	public Terricola(Coordenada origen, int ancho, int alto,Bitmap image,ArrayList<BalaTerricola> balaTerricola,ArrayList<BalaTerricolaMoveThread> balasTerricolaMoveThread,Context context) {
		 super(origen, ancho, alto,image);
		
		 this.balaTerricola=balaTerricola;
		 this.balasTerricolaMoveThread=balasTerricolaMoveThread;
		 this.v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		 this.mp = MediaPlayer.create(context,R.raw.shoot);
		 //this.width=image.getWidth() / BMP_COLUMNS;
		 //this.height=image.getHeight() /BMP_ROWS;
	}		 
	@Override
	public void move(int x,int y) {
		 origen.setY(origen.getY() + y);
		 origen.setX(origen.getX() + x);
		
	}
	private void secuencia(){
		if(t==tLimite){
		currentFrame = ++currentFrame % 4;
		  t=0;
	      }else t++;
	}
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas _canvas) {
		 secuencia();
	     int srcX=currentFrame * ancho;
		 int srcY = currentFrame*alto;
		 src = new Rect(srcX,srcY,srcX + ancho,srcY + alto);
		 dst = new Rect (this.origen.getX(),this.origen.getY(),this.origen.getX()+ancho,this.origen.getY()+alto);
		 _canvas.drawBitmap(image, src, dst,null);
			
		
	}
	///solo agragamos una bala
	public void DispararBala(Rect screen,Bitmap _image,Context context){
		if(Opciones.getInstance().soundEnabled())
		mp.start();
		balaTerricola.add(new BalaTerricola(new Coordenada(origen.getX()+image.getWidth()/4, origen.getY()+5), _image.getWidth(),_image.getHeight(), _image,this));
		int tamano=balaTerricola.size();
		balasTerricolaMoveThread.add(new BalaTerricolaMoveThread(balaTerricola.get(tamano-1), screen, context));
		balasTerricolaMoveThread.get(tamano-1).setRunning(true);
		balasTerricolaMoveThread.get(tamano-1).start();
	}
	public void eliminarBala(int i){
		
		balasTerricolaMoveThread.get(0).setRunning(false);
		balasTerricolaMoveThread.remove(0);
		balaTerricola.remove(0);
		
	}

}
