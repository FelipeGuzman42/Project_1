package project;

public class Servidor extends Thread {
	private int servidor_count = 0;
	private Mensaje men;
	private static Buffer br;

	public Servidor(String contador, Buffer br) {
		servidor_count = Integer.parseInt(contador);
		this.br = br;

	}

	public void run() {
		System.out.println("Server number" + servidor_count + "Started");
		br.P(this);
		System.out.println("Resource requested");
		men = br.receiveMessage();
		incrementer(men);
		br.returnMessage(men);
	}

	/**
	 * Incrementer
	 *
	 * @param men
	 * 
	 * gets the number stored in Mensaje class from the buffer,
	 * increments and updates in the mensaje class to be returned.
	 */
	public void incrementer(Mensaje men) {

		int temp;
		temp = men.getNumber();
		men.setNumber(temp++);

	}
}
