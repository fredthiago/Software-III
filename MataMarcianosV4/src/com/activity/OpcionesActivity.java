package com.activity;

import com.Opciones.Opciones;
import com.matamarcianosv1.R;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
public class OpcionesActivity extends Activity{
	public MediaPlayer audiocheck = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.options);
		audio();
		
		 TextView textSonido = (TextView)findViewById(R.id.labelSonido);
		 textSonido.setTextColor(Color.YELLOW);
		 TextView textVibracion = (TextView)findViewById(R.id.labelVibracion);
		 textVibracion.setTextColor(Color.YELLOW);
		 TextView textcontrol= (TextView)findViewById(R.id.labelcontrol);
		 textcontrol.setTextColor(Color.YELLOW);
		 
		CheckBox sonido = (CheckBox) findViewById(R.id.checkBoxSonido);
		sonido.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(Opciones.getInstance().soundEnabled())
		 	        audiocheck.start();
				Opciones.getInstance().toggleSound();
			}
		});

		CheckBox vibracion = (CheckBox) findViewById(R.id.checkBoxVibracion);
		vibracion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(Opciones.getInstance().soundEnabled())
		 	        audiocheck.start();
				Opciones.getInstance().toggleVibration();
			}
		});

		CheckBox control = (CheckBox) findViewById(R.id.checkBoxcontrol);
		control.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(Opciones.getInstance().soundEnabled())
		 	        audiocheck.start();
				Opciones.getInstance().toggleControl();
			}
		});
		sonido.setChecked(Opciones.getInstance().soundEnabled());
		vibracion.setChecked(Opciones.getInstance().vibrationEnabled());
		control.setChecked(Opciones.getInstance().controlEnabled());
	}
	 private void audio(){
    	 this.audiocheck= MediaPlayer.create(getBaseContext(),R.raw.check);
       }


}
