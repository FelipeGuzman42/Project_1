package project;

import java.io.BufferedReader;
import java.io.FileReader;

public class Principal {
	static Buffer buf;
	static Cliente cli;
	static Mensaje men;

	// Is static necesarry for servers?
	static Servidor ser;

	// Number of servers to be initialised in the for loop.
	static String numSer;

	public static void main(String[] args) {
		lectorArchivo();
		startServers();

	}

	// All servers are initialised here with the input of the counter starting from
	// 0,
	// and the static buffer
	public static void startServers() {
		if(Integer.parseInt(numSer) != 0)
            	{
             		for (int i = 0; i < Integer.parseInt(numSer); i++)
                	{
                    		Thread t = new Servidor(Integer.toString(i), buf);
				t.start();
                	}
            	}
			
		} else {
			System.out.println("No servers specified in file");
		}
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
				switch (a[0]) {
				case "clientes":
					clientes = a[1];
					break;
				case "mensajes":
					mensajes = a[1];
					break;
				case "servidores":
					ser = new Servidor(a[1], buf);
					numSer = a[1];
					break;

				// Is it correct to initialise server here?
				case "tam_buffer":
					buf = new Buffer(a[1]);
					break;
				}
			}

			// cli = new Cliente(clientes,mensajes,buf);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
