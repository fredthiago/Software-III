package com.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BalaTerricola extends Elemento implements ElementoMovil {
    private Terricola terricola;
	
	public BalaTerricola(Coordenada origen, int ancho, int alto,Bitmap image,Terricola terricola) {
		 super(origen, ancho, alto,image);
	     this.terricola=terricola;
		 direccion=1;
		 }		 

		public void rebota(int x, int y, Rect screen) {
		    if(!puedoMover(x,y,screen)) {
		    switch(direccion) {
		    case MOVIMIENTO:
		    	if(origen.getX() + ancho + x >= screen.top)
		        direccion = 2;
		    break;
		    case POS_ACTUAL:
		    	
 	         direccion = 1;
		    
		    break;
		    }
		  }
	
		}
		 @Override
		 public void move(int x,int y) {
			 switch(direccion) {
			 case MOVIMIENTO:
			   origen.setX(origen.getX() + x);
			  break;
			 case POS_ACTUAL:
				
				// terricola.eliminarBala();
			   direccion = 1;
			  break;
			 }
		 }

		@Override
		public void onDraw(Canvas _canvas) {
			// TODO Auto-generated method stub
			
			  _canvas.drawBitmap(image,null ,getRectElemento(), paint);
			
		}
	
}
