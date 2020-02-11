package project;

public class Mensajes {
	private int mensaje = 0;
	
	public Mensajes() {
		mensaje = (int)Math.random();
	}
	
	public int getNumber()
	{
		return mensaje;
	}
}
