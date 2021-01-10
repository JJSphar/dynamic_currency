import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class DenomSet{
	// Set of input coins: name -> # needed 
	private HashMap<String,Double> denominations = new HashMap<>();

	public ArrayList<String> names = new ArrayList<>();
	private ArrayList<Double> numCoins = new ArrayList<>();
	public ArrayList<Double> values =  new ArrayList<>();

	// How many of the smallest coin are needed to reach the total
	private int numSmallest;

	DenomSet(String input){
		parseInput(input);
		findnumSmallest();
		setCoinValues();
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

	// Returns the number of the smallest-denomination coin 
	// This is the "max value" that will be summed to
	private void findnumSmallest(){
		double max = 0;
		for (double numCoins : numCoins) {
			if (numCoins > max){
				max = numCoins;
			}
		}
		numSmallest = (int)max;

	}

	private void setCoinValues(){
		for (int i = 0; i < numCoins.size(); i++) {
			values.add(numSmallest / numCoins.get(i));
		}
	}

	public int getNumSmallest(){
		return numSmallest;
	}

	public int getNumCoins(){
		return denominations.keySet().size();
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();

		for (Entry<String,Double> entry : denominations.entrySet()) {
			sb.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
		}

		return sb.toString();
	}
}