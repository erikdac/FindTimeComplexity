package main;

/**
 * This program is used to approximate the time complexity of a method.
 * 
 * @author Erik Dackander
 * 
 */
public class FindTimeComplexity {

	private int SIZE = 7; // Must be equally divided be two.
	private int[] array;
	private int[] time;

	/*
	 * Constructor.
	 */
	public FindTimeComplexity() {
		this.array = new int[SIZE];
		this.time = new int[SIZE * 2];

		initiateArrays(SIZE);
	}

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
		int n = 44000;
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
			t[i] = (double) time[i + 2] / time[i];

		// Calculates the quote between arrays.
		double[] a = new double[array.length / 2];
		for (int i = 0; i < a.length; i++)
			a[i] = (double) array[i + 1] / array[i];

		// Calculates the quotes.
		double[] q = new double[array.length];
		for (int i = 0; i < q.length; i++)
			q[i] = a[i / 2] / t[i];

		// Calculates the average quote.
		double sum = 0;
		for (int i = 0; i < q.length; i++)
			sum += (double) q[i];
		double quote = (double) sum / q.length;

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
	 * The method that runs all the tests and collects the times it has taken
	 * for each test to be run.
	 */
	private void runTests(int[] array, int[] time) {
		for (int i = 0; i < time.length; i++) {
			time[i] = keepTime(array[i / 2]);
		}
	}

	/*
	 * Takes the time for how long it takes to run the method.
	 */
	private int keepTime(int n) {
		int startTime = (int) System.currentTimeMillis();
		test(n);
		int estimatedTime = (int) (System.currentTimeMillis() - startTime);
		return estimatedTime;
	}

	/*
	 * The method to be tested.
	 */
	private void test(long n) {
		for (int i = 0; i < n * n; i++)
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
