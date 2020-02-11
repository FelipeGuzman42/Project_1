package project;

public class Servidor extends Thread{
	private int servidor_count = 0;
	
	public Servidor(String contador) {
		servidor_count = Integer.parseInt(contador);
	}
}
