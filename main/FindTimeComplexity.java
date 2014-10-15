package main;

/**
 * This program is used to approximate the time complexity of a method.
 * 
 * @author Erik Dackander
 * 
 */
public class FindTimeComplexity {

	private int arraySize = 1288;
	private int[] array;
	private long[] time;

	/*
	 * Constructor.
	 */
	public FindTimeComplexity() {
		this.array = new int[2];
		this.time = new long[array.length * 2];

		initiateArrays();
	}

	/*
	 * Instantiating the arrays that is used when testing the methods.
	 */
	private void initiateArrays() {
		array[0] = arraySize/2;
		array[1] = arraySize;
	}

	private void start() {
		runTests(array, time);

		// Calculates the quote between times.
		double[] t = new double[time.length / 2];
		for (int i = 0; i < t.length; i++)
			t[i] = (double) time[i + 2] / time[i];

		double sum = 0;
		for (int i = 0; i < t.length; i++) {
			sum += t[i];
		}
		sum /= t.length;

		int p = Math.getExponent(sum);
		int testP = Math.getExponent(sum * 1.5);
		if (testP > p)
			p++;

		switch (p) {
		case 0:
			System.out.println("O(1)");			// DOES NOT WORK
			break;
		case 1:
			System.out.println("O(n)");
			break;
		default:
			System.out.println("O(n^" + p + ")");
		}
	}

	/*
	 * The method that runs all the tests and collects the times it has taken
	 * for each test to be run.
	 */
	private void runTests(int[] array, long[] time) {
		for (int i = time.length - 1; i >= 0; i--) {
			time[i] = keepTime(array[i / 2]);
			if (time[i] < Math.pow(10, 7)) {
				arraySize *= 1.1;
				initiateArrays();
				i = time.length;
			}
		}
	}

	/*
	 * Takes the time for how long it takes to run the method.
	 */
	private long keepTime(int n) {
		long startTime = System.nanoTime();
		test(n);
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		return runTime;
	}

	/*
	 * The method to be tested.
	 */
	private void test(long n) {
		for (int i = 0; i < n*n; i++)
			;
	}

	/**
	 * main-method. Creates a object of the program and then runs it and checks
	 * that its size-value is correct. If the size-value is correct it will run
	 * the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new FindTimeComplexity().start();
	}
}
