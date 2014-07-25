package com.juego;

import java.util.ArrayList;

import android.R.bool;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BalaMarciano extends Elemento implements ElementoMovil {
	private ArrayList<Marciano> marciano;
	private int indice; 
    //private boolean a;
	private final int a=1;
	private final int b=2;
	private final int c=3;
	private int op=1;
	public BalaMarciano(Coordenada origen, int ancho, int alto,Bitmap image,ArrayList<Marciano> marciano,int indice) {
		 super(origen, ancho, alto,image);
	     this.marciano=marciano;
	     this.indice=indice;
	 	    		 
	     //this.a=a;
	      
		 }		 
	
		 
		@Override
		public void onDraw(Canvas _canvas) {
			// TODO Auto-generated method stub
			 _canvas.drawBitmap(image,null , getRectElemento(), paint);		
		}
		 @Override
		 public void move(int x,int y) {
			//derecho
		    this.origen.setX(this.origen.getX() - x);
		 }
		 public void move1(int x,int y) {
				//arriba
			    this.origen.setX(this.origen.getX() - x);
			    this.origen.setY(this.origen.getY() - y);
			 }
		 public void move2(int x,int y) {
				//abajo
			    this.origen.setX(this.origen.getX() - x);
			    this.origen.setY(this.origen.getY() + y);
			 }
	
}
