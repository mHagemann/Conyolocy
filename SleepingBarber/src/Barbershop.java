import java.util.concurrent.Semaphore;
/**
 * 
 * @author Jan Stroet
 *
 */

public class Barbershop {	
	/* Customers wait outside the shop until there is 
	 * a free seat in the waiting room 
	 */
	private static final int NR_OF_SEATS = 3;
	private static final int NR_OF_CUSTOMERS = 8;

	private Thread [] customer;

	private Semaphore freeSeat, nextCustomer, hairCut, barberInvitation;
	
	public Barbershop(){
		customer = new Thread[NR_OF_CUSTOMERS];
		
		freeSeat = new Semaphore(NR_OF_SEATS, true);
		nextCustomer = new Semaphore(0, true); /* initially no customers in shop */
		hairCut = new Semaphore(0, true); /* nobody is cut */
		barberInvitation = new Semaphore(0, true);
		
		
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
					
					cut();
					
					hairCut.release(); /* Cutting is ready */
				} catch (InterruptedException e) {}
			}
		}
		private void cut(){
			try {
				System.out.println("barber cuts");
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
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
					freeSeat.acquire();	/* room in barbershop ? */
					nextCustomer.release(); /* tell  barber I am in */
					barberInvitation.acquire(); /* sit in waiting room */
					freeSeat.release(); /* leave waiting room */
					
					/* sitInBarberChair */
					hairCut.acquire();		/* cut! */
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

			}
		}
		
		private void justLive(){
			try {
				System.out.println(getName() + " living");
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}			
		}
	}
}
