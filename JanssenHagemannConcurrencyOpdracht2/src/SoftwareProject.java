import java.util.concurrent.Semaphore;

/**
 * 
 * @author Hagemann & Janssen
 *
 */
public class SoftwareProject {
	
	private static final int NR_OF_USERS = 5;
	private static final int NR_OF_DEVELOPERS = 7;
	private int developersAppliedForMeeting = 0;
	private int usersAppliedForMeeting = 0;
	private boolean leaderInAMeeting = false;
	
	private Semaphore inMeetingRoom, backToWork, backToJustLiving, invitation, applyForUserMeeting, applyForDeveloperMeeting;
	private Semaphore mutex;
	
	private Thread[] users;
	private Thread[] developers;
	private ProjectLeader leader;
	
	public SoftwareProject() {		
		users = new Thread[NR_OF_USERS];
		developers = new Thread[NR_OF_DEVELOPERS];
		
		backToWork = new Semaphore(0);
		backToJustLiving = new Semaphore(0);
		invitation = new Semaphore(0, true);
		applyForDeveloperMeeting = new Semaphore(0, true);
		applyForUserMeeting = new Semaphore(0, true);
		mutex = new Semaphore(1);
		inMeetingRoom = new Semaphore(0);
		
		leader = new ProjectLeader("Klaas");
		leader.start();
		
		for(int i = 0; i < NR_OF_USERS; i++) {
			users[i] = new User("user" + i);
			users[i].start();
		}

		for(int i = 0; i < NR_OF_DEVELOPERS; i++) {
			developers[i] = new SoftwareDeveloper("developer" + i);
			developers[i].start();
		}
		
	}
	
	/**
	 * Inner class voor de gebruiker
	 * @author Hagemann & Janssen
	 *
	 */
	class User extends Thread{
		
		private String name;
		
		public User(String name) {
			this.name = name;
		}

		public void run() {
			while (true) {		
				try {
					justLive();
					applyForUserMeeting.release();
					System.out.println(name + " has applied for a meeting.");
									
					mutex.acquire();
					usersAppliedForMeeting++;
					mutex.release();
					
					invitation.acquire();
					System.out.println(name + " has acquired an invitation for a meeting.");
					
					System.out.println(name +" has arrived in the meeting room");
					inMeetingRoom.release();
					
					backToJustLiving.acquire();
					
				} catch(InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}
		}
		
		// Gebruiker leeft
		private void justLive() {
			try {
				System.out.println(name + " is just living");
				Thread.sleep((int)(Math.random() * 20000));
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Inner class voor de projectleider
	 * @author Hagemann & Janssen
	 *
	 */
	class ProjectLeader extends Thread {
		
		private String name;
		
		public ProjectLeader(String name) {
			this.name = name;
		}
		
		public void run() {
			while (true) {
				try {
					
					// doe werk
                	mutex.acquire();
                	if(usersAppliedForMeeting>=1){
                		
                		// wacht tot ontwikkelaar beschikbaar is
                		if(developersAppliedForMeeting>=1){
                			leaderInAMeeting = true;
                			
                			//verstuurd uitnodiging en wacht tot iedereen er is.
                			invitation.release(usersAppliedForMeeting + 1);
                			inMeetingRoom.acquire(usersAppliedForMeeting + 1);

                			// start vergadering met klant
                			System.out.println("\n>> Usermeeting\n");
                        	// vergadering
                        	Thread.sleep((long) (Math.random() * 5000));
                        	System.out.println("\n>> End of usermeeting\n");
                        	
                        	backToWork.release(developersAppliedForMeeting);
                        	backToJustLiving.release(usersAppliedForMeeting);
                        	
                        	developersAppliedForMeeting=0;
                        	usersAppliedForMeeting=0;
                		}
                	}
                	else if(developersAppliedForMeeting>2){
            			leaderInAMeeting = true;
            			
            			//Verstuurd uitnodiging en wacht tot iedereen er is.
                		invitation.release(3);
            			inMeetingRoom.acquire(3);
            			
                    	System.out.println("\n>> Developer meeting\n");
                    	// vergadering
                    	Thread.sleep((long) (Math.random() * 5000));
                    	System.out.println("\n>> End of developer meeting\n");
                    	
                    	backToWork.release(developersAppliedForMeeting);
                    	developersAppliedForMeeting=0;
                    	
    				}
        			leaderInAMeeting = false;
                    mutex.release();
					
				} catch(InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	/**
	 * Innerclass voor de softwareDevelopers
	 * @author Hagemann & Janssen
	 *
	 */
	class SoftwareDeveloper extends Thread {

		private String name;
		
		public SoftwareDeveloper(String name) {
			this.name = name;
		}
		
		public void run() {
			while (true) {		
				try {
					work();
					/**
					 * Als de leider niet in een meeting zit zegt de developer pas dat hij beschikbaar is.
					 * Als de leider al in een meeting zit, dan gaat hij weer aan het werk. 
					 */
					mutex.acquire();
					if(!leaderInAMeeting) {
						mutex.release();
						
						applyForDeveloperMeeting.release();	//Zegt tegen de projectleider dat hij beschikbaar is voor een vergadering
						System.out.println(name + " has applied for a meeting.");
						
						mutex.acquire();
						developersAppliedForMeeting++;
						mutex.release();
						
						invitation.acquire();	//Krijgt uitnodiging van leider voor een meeting
						System.out.println(name + " has acquired an invitation for a meeting.");
							
						System.out.println(name +" has arrived in the meeting room");
						inMeetingRoom.release(); //Zegt tegen projectleider dat hij in de vergader zaal zit.
						
						backToWork.acquire(); //Krijgt opdracht om terug aan het werk te gaan.
					}
					
				} catch(InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		
		// Developer werkt
		private void work() {
			try {
				System.out.println(name + " is working");
				Thread.sleep((int)(Math.random() * 10000));
			} catch (InterruptedException e) {}
		}
	}

}
