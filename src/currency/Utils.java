package currency;

import java.util.ArrayList;

/**
 * Helper utilities for finding the greatest common denominator and least common multiple for an array of values
*/
public class Utils {
	/**
	 * Returns the Greatest Common Denominator of A and B
	 */
	public static double gcd(double a, double b){
		while(b != 0){
			double rem = a % b;
			a = b;
			b = rem;
		}
		return a;
	}

	/**
	 * Returns the Least Common Multiple of A and B
	 */
	public static int lcm(double a, double b){
		return (int)((a*b) / gcd(a, b));
	}

	/**
	 * Returns the Least Common Multiple of the entire values array
	 */
	public static int lcm(ArrayList<Double> values){
		double result = 1;
		for (double d : values) {
			result = lcm(result, d);
		}

		return (int)result;
	}
}
