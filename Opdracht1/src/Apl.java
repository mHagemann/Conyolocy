import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apl {

	private static int aantalGetallen = 400000;
	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst = new ArrayList<Integer>(
			aantalGetallen);

	public static void main(String[] args) {

//		for (int i = 0; i < 5; i++) {
//			heleGetallen = new int[aantalGetallen];
//			gesorteerdeLijst = new ArrayList<Integer>(aantalGetallen);
//
//			heleGetallen = vulArray(heleGetallen, aantalGetallen);
//
//			for (int j = 0; j < aantalGetallen; j++) {
//				gesorteerdeLijst.add(0);
//			}
//
//			opdracht1(heleGetallen);
//		}
		
		for(int i = 0; i < 5; i++){
			opdracht2();
		}
	}

	/**
	 * Methode voor het vullen van de array met willekeurige hele getallen.
	 */
	public static int[] vulArray(int[] array, int aantalGetallen) {
		for (int i = 0; i < aantalGetallen; i++) {

			// random getal tussen 0 en 100
			int getal = randInt(0, 100);

			array[i] = getal;
		}
		return array;
	}

	/**
	 * Methode voor het genereren van een radom getal tussen twee grenzen.
	 * 
	 * @param min
	 *            - ondergrens
	 * @param max
	 *            - bovengrens
	 * @return het gegenereerde random getal
	 */
	public static int randInt(int min, int max) {
		assert min < max : "Het maximum is kleiner dan het minimum";

		return min + (int) (Math.random() * max);
	}

	public static void opdracht1(int[] array) {
		
		long start = System.currentTimeMillis();
		
		Thread is = new InsertionSort(array, gesorteerdeLijst);
		is.start();
		
		try {
			is.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long eind = System.currentTimeMillis();
		
		System.out.println("Sorteren opdracht 1 duurde: " + (eind - start) + " ms.");
		
	}

	public static void opdracht2() {
	
		int n = aantalGetallen / 2;

		int[] a1 = new int[n];
		int[] a2 = new int[n];
		a2 = vulArray(a2, n);
		a1 = vulArray(a1, n);

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		for (int j = 0; j < (n); j++) {
			list1.add(0);
			list2.add(0);
		}
		
		long start = System.currentTimeMillis();

		Thread t1 = new InsertionSort(a1, list1);
		Thread t2 = new InsertionSort(a2, list2);
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		
		assert list1.size() == n : "wrong size list 1" + list1.size();
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int index1 = 0;
		int index2 = 0;
		
		for(int i = 0; i < (list1.size() + list2.size()); i++) {
			
			if(list1.get(index1) < list2.get(index2)) {
				result.add(list1.get(index1));
				if(index1 + 1 < list1.size()){
					index1++;
				}
			} else {
				result.add(list2.get(index2));
				if(index2 + 1 < list2.size()){
					index2++;
				}
			}
		}
		
		long eind = System.currentTimeMillis();
		
		System.out.println("Sorteren opdracht 2 duurde: " + (eind - start) + " ms.");

	}

	public static void opdracht3() {

	}

}
