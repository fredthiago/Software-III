package com.Pintado.movimiento;


import java.util.ArrayList;
import java.util.Random;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.Opciones.Opciones;
import com.activity.Nivel1Activity;
import com.administracion.Imagen.Imagen;
import com.administracion.Imagen.Sprite;
import com.juego.BalaMarcianoMoveThread;
import com.juego.BalaTerricola;
import com.juego.BalaTerricolaMoveThread;
import com.juego.Coordenada;
import com.juego.Elemento;
import com.juego.Escenario;
import com.juego.EscenarioMoveThread;
import com.juego.Marciano;
import com.juego.MarcianoMoveThread;
import com.juego.Terricola;
import com.matamarcianosv1.R;

	 
	@SuppressLint("WrongCall")
	public class MarcianosView extends SurfaceView implements SurfaceHolder.Callback {
	 
	 private Canvas canvas;
	 private MarcianosView marcianosView;
	 private MarcianosThread paintThread;
	 private Elemento[] escenario;
	 private EscenarioMoveThread[] escenarioThread;
	 private ArrayList<MarcianoMoveThread> marcianoThread;
	 
	 private ArrayList<BalaTerricolaMoveThread> balaterricolaThread;
	 private ArrayList<BalaTerricola> balaterricola;
	 
	 private ArrayList<Elemento> balamarciano;
	 private ArrayList<BalaMarcianoMoveThread> balamarcianoThread;
	 
	 private Terricola terricola;
	 private ArrayList<Marciano> marciano;
	 private final int izquierda=1;
	 private final int derecha=2;
	 private final int arriba=3;
	 private final int abajo=4;
	 private int direccion=0;
     
	 private int abanzar=5;
	 private int puntos=0;
	
	 private Elemento vidaTerricola,comandoDisparar,flechaDerecha,flechaIzquierda,flechaArriba,flechaAbajo,gameOver,Mision1;
	
	 private Paint paint;
	 private Bitmap image_escenario,image_terricola,image_marciano,image_balaterricola,image_balamarciano,image_barframe,image_vidadframe1,image_vidaframe;
	 private Bitmap image_boton_disparar,flecha_arriba,flecha_abajo,flecha_derecha,flecha_izquierda,game_aver,mision1;
	 private Bitmap bmp,image_marciano2,image_disparo;
	 //animacion disparo
	 private Rect src,dst;
	 private int currentFrame=0;
	 private int width;
	 private int height;
	 //
	 private ArrayList<Sprite> explocion;
	 private Elemento elementoActivo = null;
	 private int origenY;
	 private int origenX;
	 
	 private int tLimite=80;//controlar disparo de los marcianos
	 private int t=0;
	 private int sizemarciano;
	 private boolean a=false,contDisp=false;
	 private int op=1;
	 public MarcianosView(Context context) {
	  super(context);
	  getHolder().addCallback(this);
	  image_escenario=BitmapFactory.decodeResource(getResources() , R.drawable.escenario );
	  image_terricola=BitmapFactory.decodeResource(getResources() , R.drawable.marco );
	  image_marciano=BitmapFactory.decodeResource(getResources() , R.drawable.marciano );
	  image_balaterricola=BitmapFactory.decodeResource(getResources() , R.drawable.balaterricola );
	  image_balamarciano=BitmapFactory.decodeResource(getResources() , R.drawable.balamarciano );
	  image_barframe=BitmapFactory.decodeResource(getResources() , R.drawable.barframe );
	  image_vidaframe=BitmapFactory.decodeResource(getResources() , R.drawable.vidaframe );
	  image_vidadframe1=BitmapFactory.decodeResource(getResources() , R.drawable.vidaframe1 );
	  image_boton_disparar=BitmapFactory.decodeResource(getResources() , R.drawable.boton_disparar );
	  
	  flecha_arriba=BitmapFactory.decodeResource(getResources() , R.drawable.fleha_arriba);
	  flecha_abajo=BitmapFactory.decodeResource(getResources() , R.drawable.fleha_abajo);
	  flecha_derecha=BitmapFactory.decodeResource(getResources() , R.drawable.fleha_derecha);
	  flecha_izquierda=BitmapFactory.decodeResource(getResources() , R.drawable.fleha_izquierda);
	  game_aver=BitmapFactory.decodeResource(getResources() , R.drawable.game_over);
	  mision1=BitmapFactory.decodeResource(getResources() , R.drawable.mision1);
	  bmp=BitmapFactory.decodeResource(getResources() , R.drawable.explosion1);
	  image_marciano2=BitmapFactory.decodeResource(getResources(), R.drawable.marcy);
	  image_disparo=BitmapFactory.decodeResource(getResources(), R.drawable.es);
	  
	  paint = new Paint();
	  paint.setColor(Color.WHITE);

	  escenario=new Escenario[2];
	  escenarioThread=new EscenarioMoveThread[2];
	  marciano=new ArrayList<Marciano>();
	  marcianoThread=new ArrayList<MarcianoMoveThread>();
	  balaterricola=new ArrayList<BalaTerricola>();
	  balaterricolaThread=new ArrayList<BalaTerricolaMoveThread>();
	  balamarciano=new ArrayList<Elemento>();
	  balamarcianoThread=new ArrayList<BalaMarcianoMoveThread>();
	  explocion=new ArrayList<Sprite>();
	  
       //medisdas de la imge de animacion disparo
	  this.width=image_disparo.getWidth() / 3;
	  this.height=image_disparo.getHeight() /2;
	 }
	 @Override
	 public void surfaceChanged(SurfaceHolder holder, int format,int width, int height) { 
		 
	 }
	 
	 @Override
	 public void surfaceCreated(SurfaceHolder holder) {
		
	//creamos a los personajes, terricola, marciano y los comandos	 
	  escenario[0] = new Escenario(new Coordenada(0, 0),getWidth()+20, getHeight(), image_escenario,escenario);
	  escenario[1] = new Escenario(new Coordenada(getWidth(), 0),getWidth()+20, getHeight(), image_escenario,escenario);
	  
	  vidaTerricola=new Imagen(new Coordenada(10, 10), 150, 20, image_vidaframe);
	 
	  comandoDisparar=new Imagen(new Coordenada(getWidth()-120, getHeight()-110),100,100, image_boton_disparar);
	  
	  if(Opciones.getInstance().controlEnabled()){
	  flechaArriba=new Imagen(new Coordenada(70, getHeight()-180), 70, 70, flecha_arriba);
	  flechaAbajo=new Imagen(new Coordenada(70, getHeight()-70),70, 70, flecha_abajo);
	  flechaDerecha=new Imagen(new Coordenada(140, getHeight()-120), 70, 70, flecha_derecha);
	  flechaIzquierda=new Imagen(new Coordenada(2, getHeight()-120), 70, 70, flecha_izquierda);
	  }
	  gameOver=new Imagen(new Coordenada(getWidth()/2-game_aver.getWidth()/2, getHeight()/2-game_aver.getHeight()/2),301,78, game_aver);
	  Mision1=new Imagen(new Coordenada(getWidth()/2- mision1.getWidth()/2, getHeight()/2-mision1.getHeight()/2), 320,78, mision1);
	  
	  terricola = new Terricola(new Coordenada(45,getHeight()/2-image_terricola.getHeight()/2),image_terricola.getWidth()/4,image_terricola.getHeight()/4,image_terricola,balaterricola,balaterricolaThread,this.getContext());
	  
	  //creamos el Thread de todos aquellos que se muevan y lo arrancamos 
	  paintThread = new MarcianosThread(getHolder(), this);
	  paintThread.setRunning(true);
	  paintThread.setPriority(Thread.MIN_PRIORITY);
	  paintThread.start();
	  
	  escenarioThread[0] = new EscenarioMoveThread((Escenario)escenario[0], new Rect(0,0,getWidth(),getHeight()));
	  escenarioThread[0].setRunning(true);
	  escenarioThread[0].start();
	  
	  escenarioThread[1] = new EscenarioMoveThread((Escenario)escenario[1], new Rect(0,0,getWidth(),getHeight()));
	  escenarioThread[1].setRunning(true);
	  escenarioThread[1].start();
	  /// creamos los marcianos
	  crearMarcianos(3,image_marciano,5,4);  
	 }

	 @Override
	 public void surfaceDestroyed(SurfaceHolder arg0) {
	  boolean retry = true;
	  for(int i=0; i<marcianoThread.size();i++){
	   marcianoThread.get(i).setRunning(false);
	  }
	  for(int i=0; i<balamarcianoThread.size();i++){
		  balamarcianoThread.get(i).setRunning(false);
		  }

	  for(int i=0; i<balaterricolaThread.size();i++){
		  balaterricolaThread.get(i).setRunning(false);  
	  }
	  escenarioThread[0].setRunning(false);
	  escenarioThread[1].setRunning(false);
	  ///
	  paintThread.setRunning(false);

	  while (retry) {
	   try {
		   for(int i=0; i<marcianoThread.size();i++){
			   marcianoThread.get(i).join();
			  }
		   for(int i=0; i<balamarcianoThread.size();i++){
				  balamarcianoThread.get(i).join();
				  }
		   for(int i=0; i<balaterricolaThread.size();i++){
				  balaterricolaThread.get(i).join();  
			  }
		   if(marciano.size()>0){
			   for(int i=0; i<marciano.size();i++){
				   marciano.remove(i);
			   }
		   }
		   if(balamarciano.size()>0){
			   for(int i=0; i<balamarciano.size();i++){
				   balamarciano.remove(i);
			   }
		   }
		   if(balaterricola.size()>0){
			   for(int i=0; i<balaterricola.size();i++){
				   balaterricola.remove(i);
			   }
		   }
		   if(explocion.size()>0){
			   for(int i=0; i<explocion.size();i++){
				   explocion.remove(i);
			   }
		   }
		   if(terricola!=null){
			   terricola=null;
		   }
		   if(vidaTerricola!=null){
			   vidaTerricola=null;
		   }
		   if(comandoDisparar!=null){
			   comandoDisparar=null;
		   }
		   if(flechaDerecha!=null){
			   flechaDerecha=null;
		   }
		   if( flechaIzquierda!=null){
			   flechaIzquierda=null;
		   }
		   if( flechaArriba!=null){
			   flechaArriba=null;
		   }
		   if(flechaAbajo!=null){
			  flechaAbajo=null;
		   }
		   if(gameOver!=null){
			   gameOver=null;
			   }
		    if(Mision1!=null){
		    	Mision1=null;
			   }
		    if( elementoActivo!=null){
		    	 elementoActivo=null;
			   }
		   paint=null;
		 ////  
	    escenarioThread[0].join();
	    escenarioThread[1].join();
	    
			   for(int i=0; i<escenario.length;i++){
				   escenario[i]=null;
			   }
		   
	    paintThread.join();
	    this.marcianosView=null;
	    retry = false;
	   } catch (InterruptedException e) { }
	  }
	    //finish();
		System.exit(0);
	 }
	 
	 @SuppressLint("DrawAllocation")
	@Override
	 public void onDraw(Canvas _canvas) {
          canvas=_canvas;
	      escenario[0].onDraw(canvas);
	      escenario[1].onDraw(canvas);
	      paint.setColor(Color.BLUE);
		  paint.setTextSize(30);
	      canvas.drawBitmap(image_vidaframe, null,vidaTerricola.getRectElemento(), paint);
	      canvas.drawBitmap(image_barframe, 8, 10, paint);
	      canvas.drawText("puntos : "+puntos, 220,30 , paint);
	      verificarVidas();
	     
	        moverNave(); 
	   
	      destruirMarcianos();
	      destruirTerricola();
	      colicionNaveTerricola();
	      if(t==tLimite){
	      dispararBalasMarcianos();
	    	  t=0;
	      }else t++;
	      eliminarBalasFueraPantalla();
	      
	      for (int i = explocion.size() - 1; i >= 0; i--)
	    	  explocion.get(i).onDraw(canvas);
			
	      comandoDisparar.onDraw(canvas);
	      if(Opciones.getInstance().controlEnabled()){
	      flechaAbajo.onDraw(canvas);
	      flechaArriba.onDraw(canvas);
	      flechaDerecha.onDraw(canvas);
	      flechaIzquierda.onDraw(canvas);
	      }
	      if(terricola==null)
		    	gameOver.onDraw(canvas);
	      if(op==1){
	         if(marciano.size()==0){
	        	 a=true;
	        	 marciano2();
	        	 op=2;
	            }
	      }
	      if(op==2){
	    	  if(marciano.size()==0);
	    	   //Mision1.onDraw(canvas);
	      }
	         
	 }

	 public boolean onTouchEvent(MotionEvent event) {
	 
	  int x = (int) event.getX();
	  int y = (int) event.getY();

	  if(terricola!=null){
		  if(Opciones.getInstance().controlEnabled()){
			
			  //control del terricola con flechas
			   switch(event.getAction()) {
			   
			   case MotionEvent.ACTION_DOWN:
				   if(direccion==0){
					   if(flechaArriba.getRectElemento().contains(x, y))
						   direccion=arriba;
					
					   if(flechaAbajo.getRectElemento().contains(x, y))
						   direccion=abajo;
						 
					   if(flechaDerecha.getRectElemento().contains(x, y))
						   direccion=derecha;
						
					   if(flechaIzquierda.getRectElemento().contains(x, y))
						   direccion=izquierda;
				   }
				       if(comandoDisparar.getRectElemento().contains(x, y)){
				    	   contDisp=true;
				          dispararBalasTerricola();
				          }
				       break;
			   case MotionEvent.ACTION_MOVE:
				    direccion=0;
				    
				   break;
			   case MotionEvent.ACTION_UP:
				   direccion=0;
				   contDisp=false;
				   break;
		   }
		  }else{
			  ///control del terricola arrastrando la imagen
			  switch(event.getAction()) {
			  case MotionEvent.ACTION_DOWN:
				  // hemos pulsado
				  if(terricola.getRectElemento().contains(x, y)) {
				   elementoActivo = terricola;
				   origenY = y;
				   origenX = x;
				   break;
				  }
				  //aki al hacer chick en el inoco disparamos una bala
				  if(comandoDisparar.getRectElemento().contains(x, y)){
					  contDisp=true;
					  dispararBalasTerricola();
				  }
				  break;
			  case MotionEvent.ACTION_MOVE:
				  // hemos arrastrado
				  if(elementoActivo != null) {
				   Terricola r = (Terricola) elementoActivo;
				   if(r.puedoMover(x - origenX, y - origenY, new Rect(0, 0, getWidth(), getHeight()))==true)
				   r.move(x-origenX, y - origenY);
				  }
				  origenY = y;
				  origenX = x;
				  break;
			  case MotionEvent.ACTION_UP:
			   // hemos levantado
				  elementoActivo = null;
				  contDisp=false;
			   break;
			  }
			 
		      }
	 }
	  return true;
	 }

	 private void verificarVidas(){
		 for(int i=0;i<balaterricola.size();i++){
			if(balaterricola.get(i)!=null){
				try {
					balaterricola.get(i).onDraw(canvas);
			 
				} catch (Exception e) {
					System.out.print("ERROR");
				}
			}	
			 } 
		
		 for(int i=0;i<marciano.size();i++){
			if(marciano.get(i)!=null){
				try {
			   marciano.get(i).onDraw(canvas);
			   for(int j=0;j<balamarciano.size();j++){
			   if(balamarciano.get(j)!=null)
			   balamarciano.get(j).onDraw(canvas);
			   }
				} catch (Exception e) {
					System.out.print("ERROR");
				}
			}	
			 } 
		   if(terricola!=null){
			  terricola.onDraw(canvas);
			  animarDisparo(contDisp);
		   }
		    
		     
		 }
	 //METODO QUE DETECTARA LA COLISION DE A BALA DEL TERRICOLA CONTRA EL MARCIANO
	 private void destruirMarcianos(){
		 
		  for(int j=0;j<balaterricola.size();j++){
			 if(balaterricola.get(j)!=null){    
		        for(int i=0;i<marciano.size();i++){
				       if(marciano.get(i)!=null){
				          if(marciano.get(i).getRectElemento().intersect(balaterricola.get(j).getRectElemento())){
				        	  ///*
				        	   	 explocion.add(new Sprite(explocion,balaterricola.get(j).origen.getX()-image_balaterricola.getWidth()/2, balaterricola.get(j).origen.getY()-image_balaterricola.getHeight()/2, bmp,getContext()));
					             balaterricolaThread.get(j).setRunning(false);
								 balaterricolaThread.remove(j);
								 balaterricola.remove(j);
				        	  if(marciano.get(i).getDisminuirVida()<=0){
								   marcianoThread.get(i).setRunning(false);
								   marcianoThread.remove(i);
								   marciano.remove(i);
							 }else{
								 puntos++;
								 marciano.get(i).setDisminuirVida(5);
								
							}
				        	
							 break;
						
						  } 
				    }
		       }
	        }
	     }
	
	 }
	 private void destruirTerricola(){
		 	 for(int i=0;i<balamarciano.size();i++){
			 if(balamarciano.get(i)!=null){
					 if(terricola!=null){
						if(balamarciano.get(i).getRectElemento().intersect(terricola.getRectElemento())){
							explocion.add(new Sprite(explocion,balamarciano.get(i).origen.getX()-image_balamarciano.getWidth()/2, balamarciano.get(i).origen.getY()-image_balamarciano.getHeight()/2, bmp,getContext()));	  
							if(Opciones.getInstance().vibrationEnabled())
							terricola.v.vibrate(50);
							
							balamarcianoThread.get(i).setRunning(false);
							balamarcianoThread.remove(i);
							balamarciano.remove(i);
							if(vidaTerricola.getAncho()<1){
							  terricola=null;
							}else{
							vidaTerricola.setAncho(vidaTerricola.getAncho()-15);
							}
							break;
				
						}
					 }
				 
			 }
		 }
	
	 }
	 private void colicionNaveTerricola(){
		  
			 for(int i=0;i<marciano.size();i++){
				 if(marciano.get(i)!=null){
					 if(terricola!=null){
						 if(marciano.get(i).getRectElemento().intersect(terricola.getRectElemento())){
							 explocion.add(new Sprite(explocion,marciano.get(i).origen.getX()-image_marciano.getWidth()/2, marciano.get(i).origen.getY()-image_marciano.getHeight()/2, bmp,getContext()));       
							 if(Opciones.getInstance().vibrationEnabled())
							  terricola.v.vibrate(50);
							 if(vidaTerricola.getAncho()<=0){
								  terricola=null;
								}else{
								vidaTerricola.setAncho(vidaTerricola.getAncho()-15);
								}
						     break;
						 }
					 }
					 
				 }
				 
			 }
	 }
	 private void dispararBalasMarcianos(){
		
			 for(int i=0;i<marciano.size();i++){
				if(marciano.get(i)!=null)
				marciano.get(i).DispararBala(marciano,i,new Rect(0, 0,getWidth(),getHeight()),image_balamarciano,a);
			 } 
			  
	 }
     private void dispararBalasTerricola(){
    		  Terricola s = terricola;
 			  s.DispararBala(new Rect(0, 0,getWidth(),getHeight()),image_balaterricola, getContext());
 			  	   
     }
	 private void eliminarBalasFueraPantalla(){
		                ///eliminar balas fuera de la pantalla
			            for(int j=0;j<balamarciano.size();j++){
			    		   if(balamarciano.get(j)!=null){
			    	          if(balamarciano.get(j).origen.getX() <= 0 || balamarciano.get(j).origen.getY()<=0 || balamarciano.get(j).origen.getY()>= getHeight()){
			    	        	  balamarcianoThread.get(j).setRunning(false);
			    	        	  balamarcianoThread.remove(j);
			    	              balamarciano.remove(j);
			        			 
			                  }
			    	       }
			    	    }
			           
			            for(int i=0;i<balaterricola.size();i++){
				    		   if(balaterricola.get(i)!=null){
				    	          if(balaterricola.get(i).origen.getX()+image_balaterricola.getWidth() >=getWidth()){
				    	        	  balaterricolaThread.get(i).setRunning(false);
				    	        	  balaterricolaThread.remove(i);
				    	              balaterricola.remove(i);
				        			 
				                  }
				    	       }
				    	    }
			            
	 }

	 public int random(){
		    Random indice= new Random();
		    return indice.nextInt(getHeight());
		    }
	
	private void moverNave(){
       if(terricola!=null){
		       switch(direccion){
			    case izquierda: 
			    	if(terricola.origen.getX()+3>=0)
			    	  terricola.origen.setX(terricola.origen.getX()-abanzar);break;
			    case derecha: 
			    	if(terricola.origen.getX()+image_terricola.getWidth()/4<=getWidth())
			    	terricola.origen.setX(terricola.origen.getX()+abanzar);break;
			    case arriba: 
			    	if(terricola.origen.getY()+3>=0)
			    	terricola.origen.setY(terricola.origen.getY()-abanzar);break;
			    case abajo: 
			    	if(terricola.origen.getY()+image_terricola.getHeight()/4<=getHeight())
			    	terricola.origen.setY(terricola.origen.getY()+abanzar);break;
		        }
          } 
		    }
	private  void marciano2(){
		crearMarcianos(2,image_marciano2,3,3);
	  
  	}
	private void crearMarcianos(int cantidad,Bitmap _image,int width,int height){
		int n=0;
		while(n<=cantidad){
		    marciano.add(new Marciano(new Coordenada((getWidth()-_image.getWidth()/width)-n*130,random()),_image.getWidth()/width,_image.getHeight()/height,_image,image_vidadframe1,balamarciano,balamarcianoThread,width));
		    sizemarciano=marciano.size();
			marcianoThread.add(sizemarciano-1,new MarcianoMoveThread(marciano.get(sizemarciano-1),new Rect(0,0,getWidth(),getHeight()),this.getContext()));
			marcianoThread.get(sizemarciano-1).setRunning(true);
			marcianoThread.get(sizemarciano-1).start();
			n++;
		}
	}
	private void animarDisparo(boolean _contDisp){
		if(_contDisp){
	    int x=terricola.origen.getX()+image_terricola.getWidth()/4;
	    int y=terricola.origen.getY()+5;
		currentFrame = ++currentFrame % 3;
		int srcX=currentFrame * width;
		int srcY = currentFrame*height;
		src = new Rect(srcX,srcY,srcX + width,srcY + height);
	    dst = new Rect (x,y,x+image_terricola.getWidth()/4,y+image_terricola.getHeight()/4);
	    canvas.drawBitmap(image_disparo, src, dst,null);
		}
		
	}
	}