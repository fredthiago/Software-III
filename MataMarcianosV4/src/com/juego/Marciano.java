package com.juego;

import java.util.ArrayList;
import java.util.Random;

import com.administracion.Imagen.Imagen;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

@SuppressLint({ "WrongCall", "DrawAllocation" })
public class Marciano  extends Elemento implements ElementoMovil {
	
	public static final int ARRIBA = 1;
	public static final int ABAJO = 2;
    private ArrayList<Elemento> balasMarcianos;
    private ArrayList<BalaMarcianoMoveThread> balasMarcianosMoveThread;
	private int tamano;
	public Bitmap image1;
	public Imagen vidaMarciano;
	private Rect src,dst;
	private int currentFrame=0;
	private int tLimite=5;
    private int t=0;
	private int columna; 
	private int camb;
	
	public  Marciano (Coordenada origen, int ancho, int alto,Bitmap image,Bitmap image1,ArrayList<Elemento> balasMarcianos,ArrayList<BalaMarcianoMoveThread> balasMarcianosMoveThread,int columna) {
		 super(origen, ancho, alto,image);
		 this.image1=image1;
		 this.balasMarcianos=balasMarcianos;
		 this.balasMarcianosMoveThread=balasMarcianosMoveThread;
		 this.columna=columna;
		 direccion = 1;
         vidaMarciano=new Imagen(new Coordenada(this.origen.getX(), this.origen.getY()), this.getAncho(), 5, image1);
		 camb=0;
	}	


	public void rebota(int x, int y, Rect screen) {
    if(!puedoMover(x,y,screen)) {
    switch(direccion) {
    case ARRIBA:
    	if(origen.getY()-y<=0)
        direccion = 2;
   
    break;
    case ABAJO:
    	if(origen.getY() + alto + y >= screen.bottom)
        direccion = 1;
    
    break;
    }
  }
}
	 @Override
	 public void move(int x,int y) {
		 switch(direccion) {
		 case ARRIBA:
		   origen.setY(origen.getY() - y);
		  break;
		 case ABAJO:
		   origen.setY(origen.getY() + y);
		   
		   
		  break;
		 }
	 }
	 private void secuencia(){
			if(t==tLimite){
			currentFrame = ++currentFrame % columna;
			  t=0;
		      }else t++;
		}
	 @Override
	public void onDraw(Canvas _canvas) {
		    secuencia();
			int srcX=currentFrame * ancho;
			int srcY = currentFrame*alto;
			src = new Rect(srcX,srcY,srcX + ancho,srcY + alto);
		    dst = new Rect (this.origen.getX(),this.origen.getY(),this.origen.getX()+ancho,this.origen.getY()+alto);
			_canvas.drawBitmap(image, src, dst,null);
      vidaMarciano.onDraw(_canvas);
      vidaMarciano.origen.setX(this.origen.getX());
      vidaMarciano.origen.setY(this.origen.getY()-6);
		 }
	 
	 // solo agrega una bala a mi array list de balas
		public void DispararBala(ArrayList<Marciano> _marciano,int _indice,Rect screen,Bitmap _image,boolean k){
					if(k==true){
						camb=random();
						
					}else{
					 camb=0;
					}
			ArrayList<Elemento> b = balasMarcianos;
			b.add(new BalaMarciano(new Coordenada(origen.getX(), origen.getY()), _image.getWidth(),_image.getHeight(), _image,_marciano,_indice));
			tamano=b.size();
			balasMarcianosMoveThread.add(tamano-1,new BalaMarcianoMoveThread( (BalaMarciano)b.get(tamano-1), screen,camb));
			balasMarcianosMoveThread.get(tamano-1).setRunning(true);
			balasMarcianosMoveThread.get(tamano-1).start();
			   
		}
		public void eliminarBala(int j){
		        
				balasMarcianosMoveThread.get(j).setRunning(false);
				balasMarcianosMoveThread.remove(j);
				balasMarcianos.remove(j);
		      
		}
		
		public void setDisminuirVida(int ancho){
			this.vidaMarciano.setAncho(this.vidaMarciano.getAncho()-ancho);
		}
		public int getDisminuirVida(){
			return this.vidaMarciano.getAncho();
		}
		 public int random(){
			    Random indice= new Random();
			    return indice.nextInt(3);
			    }

}
