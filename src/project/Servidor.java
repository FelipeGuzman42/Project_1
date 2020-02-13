package project;

public class Servidor extends Thread{
	private int servidor_count = 0;
        private Mensaje men;
        private static Buffer br;
	
	public Servidor(String contador) {
		servidor_count = Integer.parseInt(contador);
	}
        
        public void run() {
            
            br.P(this);
            men = br.receiveMessage();
            incrementer(men);
            br.returnMessage(men);
            br.V(this);
	}
        
        /**
         * Incrementer
         *
         * @param men 
         * 
         * gets the number stored in Mensaje class from the buffer,
         * increments and updates in the mensaje class to be returned.
         */
        public void incrementer (Mensaje men){
            
            int temp;
            temp = men.getNumber();
            men.setNumber(temp++);
            
        }
}
