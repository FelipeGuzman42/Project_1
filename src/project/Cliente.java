package project;

public class Cliente extends Thread{
	
	private int cliente_count = 0;
	private int mensaje_count = 0;
	private static Buffer br;
	
	public Cliente(String contador,String mensaje, Buffer br1) {
		cliente_count = Integer.parseInt(contador);
		mensaje_count = Integer.parseInt(mensaje);
		br = br1;
	}
	

	public void sendMessage() {
				
	}
	
	public int getMensajes()
	{
		return mensaje_count;
	}
	
	public int setMensaje(int i){
		mensaje_count = i;
	}
	
	public void run()
	{
		for (int i = 1; i <= mensaje_count; i++) {
			
			br.P(this);
			}
		
	}
	
}
