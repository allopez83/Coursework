package cs1b_20130128_monday;

import java.util.Arrays;

class Main {
	public static void main(String[] args) {
		Rectangle r = new Rectangle(5, 7);
		Rectangle s = new Rectangle(7, 4);
		System.out.println(r.compareTo(s));
		
		Rectangle[] array;
		array = new Rectangle[5];
		array[0] = new Rectangle(3, 6);
		array[1] = new Rectangle(4, 9);
		array[2] = new Rectangle(3, 1);
		Arrays.sort(array, 0, 3);
		for(int i=0;i<5;i++)
			System.out.println(array[i]);
	}

}
