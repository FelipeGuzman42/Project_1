package project;

import java.util.ArrayList;

public class Buffer {
	private int buffer_count = 0;
	private final int totalMessage;
	private ArrayList<Mensaje> listaMensajes;
	private int numCliente = 0;

	public Buffer(String contador, String n) {
		buffer_count = Integer.parseInt(contador);
		totalMessage = buffer_count;
		listaMensajes = new ArrayList<>();
		numCliente = Integer.parseInt(n);
	}

	// Methods of the client

	/*
	 * Enter buffer
	 * 
	 * @param Cliente i If the buffer doesn't have space for messages it puts
	 * cliente in colaEspera and to wait until theres space
	 */
	public void P(Cliente i) {
		while (buffer_count <= 0) {
			i.yield();
		}
	}

	/*
	 * S end message
	 * 
	 * @param Mensaje m Cliente sends a message to be operated by servidor, buffer
	 * adds the message to listaMensajes and puts cliente to wait in the message.
	 */
	public void sendMessage(Mensaje m) {
		try {
			synchronized (m) {
				listaMensajes.add(m);
				buffer_count--;
				m.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Leave buffer
	 * 
	 * @param Cliente i When cliente has finished its activity in buffer it
	 * increases the capacity and if it can, retreives an object from colaEspera
	 */
	public synchronized void V(Cliente i) {
		buffer_count++;
	}

	public void endCliente() {
		synchronized (this) {
			numCliente--;
			System.out.println("Client satified");
		}
	}

	// Methods for servidor
	/*
	 * Gives the message to Servidor Servidor retrieves a message from the
	 * listaMensajes.
	 * 
	 * @returns the message to operate
	 */
	public Mensaje receiveMessage(Servidor s) {
		synchronized (s) {
			if (numCliente == 0) {
				s.setEnd();
			}
			if(listaMensajes.isEmpty()) {
				Mensaje fin = new Mensaje();
				fin.setNumber(-1);
				return fin;
			}
			Mensaje m = listaMensajes.remove(0);
			while (m == null) {
				s.yield();
				m = listaMensajes.remove(0);
			}
			return m;
		}
	}

	/*
	 * Notify cliente
	 * 
	 * @param Mensaje m Servidor returns the operated message to the cliente and
	 * notifies.
	 */
	public void returnMessage(Mensaje m) {
		synchronized (m) {
			m.notify();
		}
	}
}
