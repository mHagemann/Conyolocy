import java.util.concurrent.Semaphore;
/**
 * 
 * @author Jan Stroet
 *
 */
public class Barbershop3 {	
	/* Customers go on when 
	 * the waiting room is full
	 */
	private static final int NR_OF_SEATS = 3;
	private static final int NR_OF_CUSTOMERS = 8;
	private int customersInWaitingRoom = 0;

	private Thread [] customer;

	private Semaphore 	freeSeat, 
						nextCustomer, 
						hairCut, 
						barberInvitation,
						seated;
	private Semaphore mutex;
	
	public Barbershop3(){
		customer = new Thread[NR_OF_CUSTOMERS];
		
		freeSeat = new Semaphore(NR_OF_SEATS);
		seated = new Semaphore(0, true);
		nextCustomer = new Semaphore(0); /* initially no customers in shop */
		hairCut = new Semaphore(0); /* nobody is cut */
		barberInvitation = new Semaphore(0);
		mutex = new Semaphore(1);
		
		
		for(int i = 0; i<NR_OF_CUSTOMERS; i++){
			customer[i] = new Customer ("c"+i, i);
			customer[i].start();
		}
		Barber barber = new Barber();
		barber.start();
	}
	class Barber extends Thread{
		public void run(){
			while (true) {			
				try {
					nextCustomer.acquire();
					barberInvitation.release();
					seated.acquire();
					cut();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

				
				hairCut.release(); /* Cutting is ready */
			}
		}
		private void cut(){
			try {
				System.out.println("barber cuts");
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}
			
		}
	}
	
	class Customer extends Thread{

		private int myId;
		public Customer(String name, int id){
			super(name);
			myId = id;
		}
		public void run(){
			while (true) {			
				justLive();
				
				try {
					mutex.acquire();

					if (customersInWaitingRoom < NR_OF_SEATS){
						customersInWaitingRoom ++;
						mutex.release();

						freeSeat.acquire(); /* room in barbershop */
						nextCustomer.release(); /* tell  barber I am in */
						barberInvitation.acquire(); /* sit in waiting room */

						mutex.acquire();
						customersInWaitingRoom --;
						mutex.release();

						freeSeat.release(); /* leave waiting room */

						seated.release(); /* in barber chair */
						System.out.println("Customer "+ myId + "is cut");
						/* sitInBarberChair */
						hairCut.acquire(); /* cut! */
					} 
					else mutex.release();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		
		private void justLive(){
			try {
				System.out.println(getName() + " living");
				Thread.sleep((int)(Math.random() * 10000));
			} catch (InterruptedException e) {}
			
		}
	}

}
