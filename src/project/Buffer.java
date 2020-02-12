package project;

import java.util.ArrayList;
import java.util.LinkedList;

public class Buffer {
	private int buffer_count = 0;
	private LinkedList<Object> colaEspera;
	private ArrayList<Mensaje> listaMensajes;

	public Buffer(String contador) {
		buffer_count = Integer.parseInt(contador);
		colaEspera = new LinkedList<>();
		listaMensajes = new ArrayList<>();
	}

	//Methods of the client
	
	/* Enter buffer
	 * @param Cliente i
	 * If the buffer doesn't have space for cliente to enter
	 * it puts him in colaEspera and to wait until theres space
	 */
	public synchronized void P(Cliente i) {
		buffer_count--;
		if (buffer_count < 0) {
			colaEspera.add(i);
			try {
				i.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
		if (buffer_count >= 0) {
			Object o = colaEspera.getFirst();
			o.notify();
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
		try {
			m.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Methods for servidor
	
	/* Enter buffer
	 * @param Servidor s
	 * If the buffer doesn't have space for Servidor to enter
	 * it puts him in colaEspera and to wait until theres space
	 */
	public synchronized void P(Servidor s) {
		buffer_count--;
		if (buffer_count < 0) {
			colaEspera.add(s);
			try {
				s.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/* Leave buffer
	 * @param Servidor s
	 * When servidor has finished its activity in buffer
	 * it increases the capacity and if it can, retreives
	 * an object from colaEspera
	 */
	public synchronized void V(Servidor s) {
		buffer_count++;
		if (buffer_count >= 0) {
			Object o = colaEspera.getFirst();
			o.notify();
		}
	}
	/* Gives the message to Servidor
	 * Servidor retreives a message from the listaMensajes.
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
