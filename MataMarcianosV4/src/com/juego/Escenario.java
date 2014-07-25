package com.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Escenario extends Elemento implements ElementoMovil {
	private Elemento[] escenario;
	
	public Escenario(Coordenada origen, int ancho, int alto,Bitmap image,Elemento[] escenario) {
		 super(origen, ancho, alto,image);
		 this.escenario=escenario;
	
		 direccion=1;
		 }		 

		 @Override
		 public void move(int x,int y) {
			 switch(direccion) {
			 case MOVIMIENTO:
				 if((origen.getX()+ancho)-20 <= 0)
					 direccion=2;
				 else
					  origen.setX(origen.getX()-x);
				      
					
			  break;
			 case POS_ACTUAL:
				
			   origen.setX(ancho-20);
			  
			   direccion = 1;
			  break;
			 }
		 }

		@Override
		public void onDraw(Canvas _canvas) {
			// TODO Auto-generated method stub
			 _canvas.drawBitmap(image,null , getRectElemento(), paint);
		    
		}

	
}
