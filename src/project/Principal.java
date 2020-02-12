package project;

import java.io.BufferedReader;
import java.io.FileReader;

public class Principal {
	static Buffer buf;
	static Cliente cli;
	static Mensaje men;
	static Servidor ser;
	
	public static void main(String[] args) {
		lectorArchivo();
	}
	
	private static void lectorArchivo() {
		FileReader fr = null;
		BufferedReader br = null;
		String st, mensajes = "", clientes = "";
		String[] a = new String[2];
		try {
			fr = new FileReader("./data/examplefile.txt");
			br = new BufferedReader(fr);
			br.readLine();
			while ((st = br.readLine()) != null) {
				a = st.split("=");
				switch(a[0]) {
				case "clientes":
					clientes = a[1];
					break;
				case "mensajes":
					mensajes = a[1];
					break;
				case "servidores":
					ser = new Servidor(a[1]);
					break;
				case "tam_buffer":
					buf = new Buffer(a[1]);
					break;
				}
			}
			
			cli = new Cliente(clientes,mensajes,buf);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
