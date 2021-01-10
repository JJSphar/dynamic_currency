import java.util.Arrays;

class Combinator {
	// Set of denominations (coins)
	DenomSet coins;

	//Memoization array where [n,m]=# of nth coins to sum to m 
	int[][] sums;

	Combinator(DenomSet coins){
		this.coins = coins;
		sums = new int[coins.getNumCoins()][coins.getTarget()+1];
	}

	public void findSums(){
		// Loop through every coin
		for (int i = 0; i < coins.getNumCoins(); i++) {
			int value = coins.values.get(i);
			//Start filling in values
			for(int j=0; j <= (coins.getTarget()/value); j++){
				sums[i][j*value] = j;
			}
		}
	}

	// Traverses the DP array to print out combinations
	public void findCombos(){

	}
}
