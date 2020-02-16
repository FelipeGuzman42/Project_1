package project;

public class Cliente extends Thread {

	private static int cliente_count = 0;
	private int mensaje_count = 0;
	private static Buffer br;
	private Mensaje men;

	public Cliente(String contador, String mensaje, Buffer br1) {
		cliente_count = Integer.parseInt(contador);
		mensaje_count = Integer.parseInt(mensaje);
		br = br1;
	}

	public void run() {
		men = new Mensaje();
		for (int i = 1; i <= mensaje_count; i++) {
			br.P(this);
			sendMessage();
		}

	}

	public void sendMessage() {
		br.sendMessage(men);
		br.V(this);
		
	}

	public int getCliente() {
		return cliente_count;
	}

	public void setCliente(int cli) {
		cliente_count = cli;
	}

	public int getMensajes() {
		return mensaje_count;
	}

	public void setMensaje(int i) {
		mensaje_count = i;
	}

}
