package com.administracion.Imagen;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.juego.Coordenada;
import com.juego.Elemento;

public class Imagen extends Elemento{

	
	public Imagen(Coordenada origen, int ancho, int alto, Bitmap image) {
		super(origen, ancho, alto, image);
	}
	@Override
	public void onDraw(Canvas _canvas) {
		// TODO Auto-generated method stub
	 _canvas.drawBitmap(image, null,getRectElemento() , paint);
	}

}
