package com.Opciones;

public class Opciones {
	private static Opciones opciones = null;
	private boolean sonido;
	private boolean vibracion;
	private boolean control;

	private Opciones() {
		sonido = true;
		vibracion = true;
		control= true;
	}

	public static synchronized Opciones getInstance() {
		if(opciones == null)
			opciones = new Opciones();
		return opciones;
	}

	public void toggleSound() {
		sonido = !sonido;
	}

	public void toggleVibration() {
		vibracion = !vibracion;
	}
	
	public void toggleControl() {
		control = !control;
	}
	
	public boolean soundEnabled() {
		return sonido;
	}

	public boolean vibrationEnabled() {
		return vibracion;
	}
	public boolean controlEnabled() {
		return control;
	}
}
