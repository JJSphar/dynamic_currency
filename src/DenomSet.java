import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

// Represents a set of named denominations and their relative values
class DenomSet{
	// Number of distinct denominations
	public int numDenoms;

	// Names of the denominations 
	public ArrayList<String> names = new ArrayList<>();
	
	// How many of each denom is needed to reach the target value
	private ArrayList<Double> numCoins = new ArrayList<>();

	// Normalized values in terms of the smallest denom
	public ArrayList<Integer> values =  new ArrayList<>();

	// Target value (this is equal to the required # of the smallest-denomination element)
	private int target;
	private int lcm;

	DenomSet(String input){
		parseInput(input);
		setDenomValues();
	}

	private void parseInput(String input){
		String[] pieces = input.split(",");
		
		String name = "";
		for (String piece : pieces) {
			if(name.isEmpty()){
				name = piece;
				names.add(name);
			}else{
				numCoins.add(Double.parseDouble(piece));
				name="";
			}
		}	
	}

	// Finds the required number of the lowest-value denomination    
	private double findMaxCoins(){
		double max = 0;
		for (double numCoins : numCoins) {
			if (numCoins > max){
				max = numCoins;
			}
		}
		return max;
	}

	// Calculates the relative value of each denomination
	// Normalizes using the LCM to make sure the values are all integers
	private void setDenomValues(){
		int lcm = Utils.lcm(numCoins);
		double max = findMaxCoins();
		for (int i = 0; i < numCoins.size(); i++) {
			values.add((int)((max / numCoins.get(i))*lcm));
		}

		target=(int)(max*lcm);
	}

	public int getTarget(){
		return target;
	}

	public int getNumCoins(){
		return names.size();
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();

		for (int i=0; i<names.size(); i++){
			sb.append(names.get(i)).append("\t\t")
			.append(numCoins.get(i)).append("\t")
			.append(values.get(i)).append("\n");
		}

		return sb.toString();
	}
}