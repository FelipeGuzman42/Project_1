package project;

import java.util.LinkedList;

public class Buffer {
	private int buffer_count = 0;
	private LinkedList<Object> colaEspera;

	public Buffer(String contador) {
		buffer_count = Integer.parseInt(contador);
		colaEspera = new LinkedList<>();
	}

	public synchronized void P(Cliente i, Mensaje m) {
		buffer_count--;
		if (buffer_count < 0) {
			colaEspera.add(i);
			try {
				m.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void V(Cliente i) {
		buffer_count++;
		if (buffer_count >= 0) {
			colaEspera.get(colaEspera.indexOf(i));
			i.setCliente(i.getCliente() - 1);
		}
	}
	
	public synchronized void P(Servidor s) {
		buffer_count--;
		if (buffer_count < 0) {
			colaEspera.add(s);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void V(Servidor s, Mensaje m) {
		buffer_count++;
		if (buffer_count >= 0) {
			colaEspera.get(colaEspera.indexOf(s));
			m.notify();
		}
	}

}
