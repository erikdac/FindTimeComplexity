package main;

public class FindTimeComplexity {

	private int[] array;
	private long[] time;

	/*
	 * Constructor.
	 */
	public FindTimeComplexity() {
		this.array = new int[3];
		this.time = new long[3];

		initiateArrays(array.length);
	}

	/*
	 * instantiating the arrays that be used when testing the methods.
	 */
	private void initiateArrays(int size) {
		int n = 20000;
		int j = 1;
		for (int i = 0; i < size; i++) {
			array[i] = n / j;
			j *= 2;
		}
	}

	private void runTests() {
		for (int i = 0; i < array.length; i++) {
			time[i] = keepTime(array[i]);
		}

		double arrayDifference = (double) array[1] / array[0];
		double timeDifference = (double) time[1] / time[0];
		double p = (double) timeDifference / arrayDifference;

		System.out.println(p);

	}

	private long keepTime(int n) {
		long startTime = System.currentTimeMillis();
		test(n);
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
	}

	private void test(long n) {
		for (int i = 0; i < n; i++)
			;
	}

	public static void main(String[] args) {
		new FindTimeComplexity().runTests();
	}
}
