import java.util.ArrayList;

public class Apl {
	
	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst;
	private static int aantalGetallen = 0;
	
	public static void main (String[] args) {
		//random getal tussen 0 en N
		int ti = randInt(0, 100);
	}
	
	public void vulArray() {
		
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
