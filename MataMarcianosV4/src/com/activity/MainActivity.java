package com.activity;



import com.Opciones.Opciones;
import com.matamarcianosv1.R;

import android.app.Activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public MediaPlayer audiochick = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Con esto estamos pidiendo al sistema de ventanas de Android que nos quite el nombre de la aplicación y 
		//que además oculte la barra de notificaciones
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
	    setContentView(R.layout.menu_principal);
	    audio();
	  ///funcionalidad de los botones
		//boton de PLAY
		TextView play = (TextView)findViewById(R.id.play_button);
		play.setTextColor(Color.YELLOW);
		play.setOnClickListener(new OnClickListener() {
		 @Override
		 public void onClick(View v) {
		  Toast.makeText(getApplicationContext(), R.string.menu_play,Toast.LENGTH_SHORT).show();
		  if(Opciones.getInstance().soundEnabled())
	        audiochick.start();
		  empiezaJuego();
		 }
		});
		//boton de OPCIONESS
		  TextView options = (TextView)findViewById(R.id.options_button);
		     options.setTextColor(Color.YELLOW);
		    options.setOnClickListener(new OnClickListener() {
		     @Override
		     public void onClick(View v) {
		      Toast.makeText(getApplicationContext(), R.string.menu_options, Toast.LENGTH_SHORT).show();
		      if(Opciones.getInstance().soundEnabled())
			    audiochick.start();
		      muestraOpciones();
		     }
		    });
		  //boton de SALIR
		    TextView exit = (TextView)findViewById(R.id.exit_button);
		    exit.setTextColor(Color.YELLOW);
		    exit.setOnClickListener(new OnClickListener() {
	
		     @Override
		     public void onClick(View v) {
		      Toast.makeText(getApplicationContext(),R.string.menu_exit, Toast.LENGTH_SHORT).show();
		      if(Opciones.getInstance().soundEnabled())
			    audiochick.start();
		      finish();
		     }
		    });
		//click en la imagen y te muestra una url
		    ImageView logo = (ImageView)findViewById(R.id.imageView1);
		    logo.setOnClickListener(new OnClickListener() {
		     @Override
		     public void onClick(View v) {
		    	 if(Opciones.getInstance().soundEnabled())
		 	        audiochick.start();
		      Intent viewIntent = new Intent("android.intent.action.VIEW",
		               Uri.parse("https://www.facebook.com/benjamin.cardenastaype"));
		      startActivity(viewIntent);
		     }
		    });
	}
		private void empiezaJuego() {
			 Intent juego = new Intent(this, Nivel1Activity.class);
			 this.startActivity(juego);
			}
		private void muestraOpciones() {
			Intent opciones = new Intent(this, OpcionesActivity.class);
			this.startActivity(opciones);
		}
       private void audio(){
    	 this.audiochick = MediaPlayer.create(getBaseContext(),R.raw.chikc);
       }


}
