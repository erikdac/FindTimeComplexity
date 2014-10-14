package main;

public class FindTimeComplexity {

	private int[] array;
	private long[] time;

	/*
	 * Constructor, instantiating the arrays that be used to test the methods.
	 */
	public FindTimeComplexity() {
		this.array = new int[3];
		array[0] = 1000;
		array[1] = 2000;
		array[2] = 4000;
		
		this.time = new long[3];
	}

	private void runTests() {
		
		for(int i = 0; i < array.length; i++) {
			time[i] = keepTime(array[i]);
		}

		long p = (time[1]/time[0]) / (array[1] / array[0]); 
		System.out.println(p);
	}
	
	private long keepTime(int n) {
		long startTime = System.currentTimeMillis();
		test(n);
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
	}

	private void test(long n) {
		for (int i = 0; i < n * n; i++)
			;
	}

	public static void main(String[] args) {
		new FindTimeComplexity().runTests();
	}
}
