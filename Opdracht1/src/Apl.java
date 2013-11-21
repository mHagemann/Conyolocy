import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apl {
	
	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst;
	private static int aantalGetallen = 20;
	
	public static void main (String[] args) {
		heleGetallen = new int[aantalGetallen];
		vulArray();
	}
	
	/**
	 * Methode voor het vullen van de array met willekeurige hele getallen.
	 */
	public static void vulArray() {
		for (int i = 0; i < aantalGetallen; i++) {
			
			//random getal tussen 0 en 100
			int getal = randInt(0, 100);
			
			heleGetallen[i] = getal;
			System.out.println("Getal: " + heleGetallen[i]);
		}
	}
	
	/**
	 * Methode voor het genereren van een radom getal tussen twee grenzen.
	 * @param min - ondergrens
	 * @param max - bovengrens
	 * @return het gegenereerde random getal
	 */
	public static int randInt(int min, int max) {
		return min + (int) (Math.random() * max); 
	}
}
