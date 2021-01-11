import java.util.ArrayList;

public class Utils {
	public static double gcd(double a, double b){
		while(b != 0){
			double rem = a % b;
			a = b;
			b = rem;
		}
		return a;
	}

	public static int lcm(double a, double b){
		return (int)((a*b) / gcd(a, b));
	}

	public static int lcm(ArrayList<Double> values){
		double result = 1;
		for (double d : values) {
			result = lcm(result, d);
		}

		return (int)result;
	}
}
