package project;

import java.util.LinkedList;

public class Buffer {
	private int buffer_count = 0;
	private LinkedList<Object>colaEspera  ;
	
	public Buffer(String contador) {
		buffer_count = Integer.parseInt(contador);
		colaEspera = new LinkedList<>();
	}
	
	public synchronized void P(Cliente i)
	{
		int a = i.setMensaje(i.getMensajes()-1);
		if(a<0)
		{
			colaEspera.add(i);
		}
		
	}
	
}


