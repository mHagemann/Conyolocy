
public class Apl {
	
	private static int aantalBurgers = 50;
	private static int aantalHoogwaardigheidsBekleders = 10;
	private static int aantalBewakers = 5;
	
	public static void main(String[] args) {
		
		HerdenkingsDienst dienst = new HerdenkingsDienst();
		
		for(int i = 0; i < aantalBurgers; i++) {
			new Burger(i, dienst).start();
		}
		for(int i = 0; i < aantalBewakers; i++) {
			new Bewaker(i, dienst).start();
		}
		for(int i = 0; i < aantalHoogwaardigheidsBekleders; i++) {
			new Hoogwaardigheidsbekleder(i, dienst).start();
		}
	}
}
