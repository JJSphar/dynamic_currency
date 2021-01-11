package currency;

import java.util.ArrayList;

/**
 * Represents a set of named denominations and their relative values.
 *  Scales the relative values using the Least Common Multiple to align everything to integers 
 */
class DenomSet{
	// Names of the denominations 
	public ArrayList<String> names = new ArrayList<>();
	
	// How many of each denom is needed to reach the target value
	private ArrayList<Double> numCoins = new ArrayList<>();

	/**
	 * Normalized values in terms of the smallest denom
	 * The smallest-value denom will have a value of 1
	 * Other entry represent value in terms of the smallest denom 
	 */
	public ArrayList<Integer> values =  new ArrayList<>();

	public int numDenoms;
	private int target;

	/**
	 * Creates a new set of denominations 
	 * @param input the user-entered CSV string
	 * @throws IllegalArgumentException
	 */
	DenomSet(String input) throws IllegalArgumentException {
		parseInput(input);
		setDenomValues();
	}

	/**
	 * Parses a CSV input string into arrays of names and required # of each denomination
	 * @param input the user-entered CSV string
	 * @throws IllegalArgumentException if the input is uneven or the values are not well-formed doubles
	 */
	private void parseInput(String input) throws IllegalArgumentException {
		String[] pieces = input.split(",");
		
		String name = "";
		for (String piece : pieces) {
			if(name.isEmpty()){
				name = piece;
				names.add(name);
			}else{
				try {
					numCoins.add(Double.parseDouble(piece));
					name="";					
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}

			}
		}	

		// Need an equal number of names and values
		if(names.size() != numCoins.size()){
			throw new IllegalArgumentException();
		}

		numDenoms = names.size();
	}

	/**
	 * 	Finds the required number of the lowest-value denomination
	 * @return how many of the lowest-value denomination is needed to reach the target sum
	 */ 
	private double findMaxCoins(){
		double max = 0;
		for (double numCoins : numCoins) {
			if (numCoins > max){
				max = numCoins;
			}
		}
		return max;
	}

	/**
	 * Calculates the relative value of each denom in terms of the least-valuable one 
	 * Scales everything using the LCM to make sure the values are all integers
	 */
	private void setDenomValues(){
		int lcm = Utils.lcm(numCoins);
		double max = findMaxCoins();

		for (int i = 0; i < numCoins.size(); i++) {
			values.add((int)((max / numCoins.get(i))*lcm));
		}

		target=(int)(max*lcm);
	}

	/**
	 * Returns the relative target value, equal to the required # of the smallest-denomination element scaled by the LCM
	 * @return the target value in terms of the lowest-denomination element
	 */
	public int getTarget(){
		return target;
	}

	/**
	 * 
	 * @return the distinct number of denomations in this set
	 */
	public int getNumDenoms(){
		return numDenoms;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();

		for (int i=0; i<names.size(); i++){
			sb.append(names.get(i)).append("\t\t")
			.append(numCoins.get(i)).append("\t");
			if(values.size() > i){
				sb.append(values.get(i)).append("\n");
			}
		}

		return sb.toString();
	}
}