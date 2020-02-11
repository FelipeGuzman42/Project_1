package project;

public class Cliente extends Thread{
	private int cliente_count = 0;
	
	public Cliente(String contador) {
		cliente_count = Integer.parseInt(contador);
	}
}
