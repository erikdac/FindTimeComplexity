package main;

/**
 * This program is used to approximate the time complexity of a method.
 * 
 * @author Erik Dackander
 * 
 */
public class FindTimeComplexity {

	private int arraySize = 1170;
	private int[] array;
	private long[] time;

	/*
	 * Constructor.
	 */
	public FindTimeComplexity() {
		this.array = new int[2];
		array = initiateArrays(array);

		this.time = new long[array.length * 2];
	}

	/*
	 * Instantiating the arrays that is used when testing the methods.
	 */
	private int[] initiateArrays(int[] array) {
		array[0] = arraySize / 2;
		array[1] = arraySize;
		return array;
	}

	private void start() {
		long[] times = runTests(array, time);
		double quote = averageQuote(times);
		int p = getExponent(quote);
		printTimeComplexity(p);
	}

	/*
	 * Calculates the average quote between the times.
	 */
	private double averageQuote(long[] time) {
		double quote = 0;
		int len = time.length / 2;
		for (int i = 0; i < len; i++)
			quote += (double) time[i + 2] / time[i];

		quote /= len;
		return quote;
	}

	/**
	 * Calculates the correct exponent.
	 * 
	 * @param quote
	 * @return The exponent.
	 */
	private int getExponent(double quote) {
		int p = Math.getExponent(quote);
		int testP = Math.getExponent(quote * 1.5);
		if (testP > p)
			p++;

		return p;
	}

	/**
	 * Prints out the time-complexity that the tested method has. Uses
	 * ordo-notation.
	 * 
	 * @param p
	 *            The exponent.
	 */
	private void printTimeComplexity(int p) {
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
	 * The method that runs all the tests and collects the times it has taken
	 * for each test to be run.
	 */
	private long[] runTests(int[] array, long[] time) {
		for (int i = time.length - 1; i >= 0; i--) {
			time[i] = keepTime(array[i / 2]);
			if (time[i] < Math.pow(10, 8)) {
				arraySize *= 1.1;
				array = initiateArrays(array);
				if (arraySize > Math.pow(10, 9)) {
					i = 0;
					for (int j = 0; j < time.length; j++)
						time[j] = 1;

				} else
					i = time.length;
			}
		}
		return time;
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
		for (int i = 0; i < n * n * n; i++)
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
