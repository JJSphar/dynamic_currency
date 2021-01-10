import java.util.ArrayList;

// Represents a set of named denominations and their relative values
class DenomSet{
	// Names of the denominations 
	public ArrayList<String> names = new ArrayList<>();
	
	// How many of each denom is needed to reach the target value
	private ArrayList<Double> numCoins = new ArrayList<>();

	// Normalized values in terms of the smallest denom
	public ArrayList<Integer> values =  new ArrayList<>();

	// Target value (this is equal to the required # of the smallest-denomination element)
	private int target;

	DenomSet(String input){
		parseInput(input);
		findTarget();
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
	private void findTarget(){
		double max = 0;
		for (double numCoins : numCoins) {
			if (numCoins > max){
				max = numCoins;
			}
		}
		target = (int)max;
	}

	private void setDenomValues(){
		for (int i = 0; i < numCoins.size(); i++) {
			values.add((int)(target / numCoins.get(i)));
		}
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