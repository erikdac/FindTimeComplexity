package main;

/**
 * This program is used to approximate the time complexity of a method.
 * 
 * @author Erik Dackander
 *
 */
public class FindTimeComplexity {

	private int[] array;
	private long[] time;

	/*
	 * Constructor.
	 */
	public FindTimeComplexity() {
		this.array = new int[2];
		this.time = new long[array.length * 2];

		initiateArrays(array.length);
	}

	/*
	 * instantiating the arrays that is used when testing the methods.
	 */
	private void initiateArrays(int size) {
		int n = 44000;
		int j = 1;
		for (int i = 0; i < size; i++) {
			array[i] = n / j;
			j *= 2;
		}
	}

	private void runTests() {
		time[0] = keepTime(array[0]);
		time[1] = keepTime(array[0]);
		time[2] = keepTime(array[1]);
		time[3] = keepTime(array[1]);

		double t1 = (double) time[2] / time[0];
		double t2 = (double) time[3] / time[1];
		double arrayDifference = (double) array[1] / array[0];
		double q1 = (double) arrayDifference / t1;
		double q2 = (double) arrayDifference / t2;
		double quote = (double) (q1 + q2) / 2;

		System.out.println("QUOTE: " + quote);

		int p = (int) (quote + 0.5);
		switch (p) {
		case 0:
			System.out.println("O(1)");
			break;
		case 1:
			System.out.println("O(n)");
			break;
		default:
			System.out.println("O(n^" + p + ")");
		}
	}

	/*
	 * Takes the time for how long it takes to run the method.
	 */
	private long keepTime(int n) {
		long startTime = System.currentTimeMillis();
		test(n);
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
	}

	/*
	 * The method to be tested.
	 */
	private void test(long n) {
		for (int i = 0; i < n*n; i++)
			;
	}

	/**
	 * main-method. Creates a object of the program and then runs it. 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new FindTimeComplexity().runTests();
	}
}
