package project;

import java.util.ArrayList;
import java.util.LinkedList;

public class Buffer {
	private int buffer_count = 0;
	private final int totalMessage;
	private ArrayList<Mensaje> listaMensajes;

	public Buffer(String contador) {
		buffer_count = Integer.parseInt(contador);
		totalMessage = buffer_count;
		listaMensajes = new ArrayList<>();
	}
	
	//Methods of the client
	
	/* Enter buffer
	 * @param Cliente i
	 * If the buffer doesn't have space for messages it puts
	 * cliente in colaEspera and to wait until theres space
	 */
	public void P(Cliente i) {
		while(buffer_count <= 0) {
			i.yield();
		}
	}
	/* Send message
	 * @param Mensaje m
	 * Cliente sends a message to be operated by servidor,
	 * buffer adds the message to listaMensajes and puts
	 * cliente to wait in the message.
	 */
	public synchronized void sendMessage(Mensaje m) {
		listaMensajes.add(m);
		buffer_count--;
		try {
			m.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/* Leave buffer
	 * @param Cliente i
	 * When cliente has finished its activity in buffer
	 * it increases the capacity and if it can, retreives
	 * an object from colaEspera
	 */
	public synchronized void V(Cliente i) {
		buffer_count++;
	}
	
	//Methods for servidor
	
	/* Enter buffer
	 * @param Servidor s
	 * If the buffer doesn't have space for Servidor to enter
	 * it puts him in colaEspera and to wait until theres space
	 */
	public void P(Servidor s) {
		buffer_count--;
		while(buffer_count == totalMessage) {
			s.yield();
		}
	}
	/* Gives the message to Servidor
	 * Servidor retrieves a message from the listaMensajes.
	 * @returns the message to operate
	 */
	public synchronized Mensaje receiveMessage() {
		Mensaje m = listaMensajes.remove(0);
		return m;
	}
	/* Notify cliente
	 * @param Mensaje m
	 * Servidor returns the operated message to the cliente 
	 * and notifies.
	 */
	public synchronized void returnMessage(Mensaje m) {
		m.notify();
	}
}
