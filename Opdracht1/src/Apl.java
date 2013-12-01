import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apl {

	private static int aantalGetallen = 1000;
	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst = new ArrayList<Integer>(
			aantalGetallen);

	public static void main(String[] args) {
		
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		list1.add(4);
		list1.add(20);
		list1.add(33);
		list1.add(55);
		
		list2.add(5);
		list2.add(47);
		list2.add(48);
		list2.add(49);
		list2.add(50);
		list2.add(53);
		list2.add(59);
		
		
		System.out.println(merge(list1, list2).toString());
		
		
		

		// for (int i = 0; i < 5; i++) {
		// heleGetallen = new int[aantalGetallen];
		// gesorteerdeLijst = new ArrayList<Integer>(aantalGetallen);
		//
		// heleGetallen = vulArray(heleGetallen, aantalGetallen);
		//
		// for (int j = 0; j < aantalGetallen; j++) {
		// gesorteerdeLijst.add(0);
		// }
		//
		// opdracht1(heleGetallen);
		// }

//		for (int i = 0; i < 5; i++) {
//			opdracht2();
//		}
	}

	public static void opdracht1(int[] array) {

		long start = System.currentTimeMillis();

		Thread t = new InsertionSort(array, gesorteerdeLijst);
		t.start();

		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long eind = System.currentTimeMillis();

		System.out.println("Sorteren opdracht 1 duurde: " + (eind - start)
				+ " ms.");

	}

	public static void opdracht2() {

		int n = aantalGetallen / 2;

		int[] a1 = new int[n];
		int[] a2 = new int[n];
		a2 = vulArray(a2, n);
		a1 = vulArray(a1, n);

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		for (int j = 0; j < n; j++) {
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
			e.printStackTrace();
		}

		assert list1.size() == n : "wrong size list 1 " + list1.size();

		ArrayList<Integer> result = merge(list1, list2);

		long eind = System.currentTimeMillis();

		System.out.println("Sorteren opdracht 2 duurde: " + (eind - start) + " ms.");
		System.out.println(result.toString());

	}
	
	public static void opdracht3() {

		
		
	}

	/**
	 * Methode die 2 lijsten op de goede volgorde samenvoegt
	 * @param list1
	 * @param list2
	 * @return de samengevoegde lijst
	 */
	public static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		int index1 = 0;
		int index2 = 0;

		while (index1 < list1.size() && index2 < list2.size()) {
		
			if (list1.get(index1) < list2.get(index2)) {
				result.add(list1.get(index1));
				index1++;
			} else {
				result.add(list2.get(index2));
				index2++;
			}
		}
		
		if (list1.size() <= index1) {
			for (int i = index2; i < list2.size(); i++) {
				result.add(list2.get(i));
			}
		} else if (list2.size() <= index2) {
			for (int i = index1; i < list1.size(); i++) {
				result.add(list1.get(i));
			}
		}
		
		return result;

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
	 * @param min - ondergrens
	 * @param max - bovengrens
	 * @return het gegenereerde random getal
	 */
	public static int randInt(int min, int max) {
		assert min < max : "Het maximum is kleiner dan het minimum";

		return min + (int) (Math.random() * max);
	}

}
