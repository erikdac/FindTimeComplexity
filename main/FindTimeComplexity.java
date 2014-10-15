package main;

/**
 * This program is used to approximate the time complexity of a method.
 * 
 * @author Erik Dackander
 * 
 */
public class FindTimeComplexity {

	private int SIZE = 2; // Must be equally divided be two.
	private int[] array;
	private long[] time;

	/*
	 * Constructor.
	 */
	public FindTimeComplexity() {
		
		this.array = new int[SIZE];
		this.time = new long[SIZE * 2];

		initiateArrays(SIZE);
	}

	/*
	 * Returns whether the SIZE-variable has a correct value or not. The value
	 * needs to be equally divided by two.
	 */
	private boolean isCorrectSize() {
		if (SIZE % 2 == 0)
			return true;
		else
			return false;
	}

	/*
	 * instantiating the arrays that is used when testing the methods.
	 */
	private void initiateArrays(int size) {
		int n = 210;
		int j = 1;
		for (int i = 0; i < size; i++) {
			array[i] = n / j;
			j *= 2;
		}
	}

	private void start() {
		runTests(array, time);

		// Calculates the quote between times.
		double[] t = new double[time.length / 2];
		for (int i = 0; i < t.length; i++)
			t[i] = (double) time[i] / time[i + 2];

		int[] quote = new int[time.length/2];
		for(int i = 0; i < quote.length; i++) {
			quote[i] = Math.getExponent(t[0]);
			int tmp = (int) Math.pow(t[i], quote[i]);
			int tmp2 = Math.getExponent(t[i] - tmp);
			if(tmp2 == 1) {
				quote[i]++;
			}
		}

		int p = Math.getExponent(t[0]);
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
	private void runTests(int[] array, long[] time) {
		for (int i = 0; i < time.length; i++) {
			time[i] = keepTime(array[i / 2]);
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
		for (int i = 0; i < n * n*n*n; i++)
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
		FindTimeComplexity program = new FindTimeComplexity();
		if (program.isCorrectSize())
			program.start();
		else
			System.err.println("Size must be equally divided my two. ");
	}
}
