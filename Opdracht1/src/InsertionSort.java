import java.util.ArrayList;

public class InsertionSort extends Thread {

	private int[] a; // de array die gesorteerd moet worden
	private ArrayList<Integer> gl; // de gesorteerde lijst

	public InsertionSort(int[] a, ArrayList<Integer> gl) {
		this.a = a;
		this.gl = gl;
	}

	public ArrayList<Integer> sort() {
		for (int i = 1; i < a.length; i++) {
			int getal = a[i];
			int x;
			for (x = i; x > 0 && getal < a[x - 1]; x--) {
				a[x] = a[x - 1];
				gl.set(x, a[x - 1]);
			}
			a[x] = getal;
			gl.set(x, getal);
		}

		return gl;
	}

	public void run() {
		
		sort();

	}
	 	
	public ArrayList<Integer> getSortedList() {
		return gl;
	}

}
