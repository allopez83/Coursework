package cs1c_0409week1;

public class MyInt {
	public int theInt;

	public MyInt(int n) {
		theInt = n;
	}

	public static void main(String[] args) {
		MyInt a = new MyInt(5);
		MyInt b = new MyInt(7);
		MyInt c = new MyInt(9);
	}

	static <E extends Comparable<E>> E findLargestOfThree(E x, E y, E z) {
		if (x.compareTo(y) > 0) {
			return (x.compareTo(z) > 0) ? x : z;
		} else {
			return (y.compareTo(z) > 0) ? y : z;
		}
	}

}
