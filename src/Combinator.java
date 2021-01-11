import java.util.Arrays;

class Combinator {
	// Set of denominations (coins)
	private DenomSet coins;

	// Memoization array where [n,m]=# of ways to create m from the (0...n)th coins
	private int[][] combos;

	Combinator(DenomSet coins){
		this.coins = coins;

		combos = new int[coins.getNumCoins()+1][coins.getTarget()+1];
	}

	public void findCombos(){
		// Set base case - 0 can always be made using 0 coins (1 solution)
		for (int i = 0; i < combos.length; i++) {
			combos[i][0] = 1;
		}

		// Loop through every coin
		for (int i = 1; i <= coins.getNumCoins(); i++) {
			int value = coins.values.get(i-1);
			
			//Try to create each j using this + previous coin combos
			for(int j=1; j <= coins.getTarget(); j++){
				if(value <= j){
					// Add in # smaller combos using these coins
					combos[i][j] = combos[i-1][j] + combos[i][j-value];
				}else{
					// Coin is bigger than j - no new combos 
					combos[i][j] = combos[i-1][j];
				}
			}
		}
	}

	public void printCombos(){
		findCombos();
		printHeader();
		printCombosHelper(coins.getNumCoins(), coins.getTarget(), new int[coins.getNumCoins()]);
		System.out.println("Count: " + combos[coins.getNumCoins()][coins.getTarget()]);
	}
	
	private void printCombosHelper(int coinInd, int target, int[] coinsUsed){
		// No coins left to try - exit 
		if(coinInd == 0){
			return;
		}
		
		// Found a combo that sums up to target
		if(target == 0){
			printOneCombo(coinsUsed);
			return;
		}

		int combosWithCoin = combos[coinInd][target];
		int combosWithoutCoin = combos[coinInd-1][target];

		// Can this value be made using this coin?		
		if(combosWithCoin != combosWithoutCoin){
			// Don't use this coin - try smaller denoms
			printCombosHelper(coinInd-1, target, coinsUsed.clone());

			// Use this coin in combos
			coinsUsed[coinInd-1] += 1;
			printCombosHelper(coinInd, target-coins.values.get(coinInd-1), coinsUsed.clone());

		}else{
			// This coin cannot be used to make this value - traverse combos not including this coin
			printCombosHelper(coinInd-1, target, coinsUsed.clone()); 
		}	
	}

	private void printHeader(){
		for (String name : coins.names) {
			System.out.printf("%-10s",name);
		}
		System.out.println();
	}

	private void printOneCombo(int[] coinsUsed){
		for (int numCoin : coinsUsed) {
			System.out.printf("%-10d",numCoin);
		}
		System.out.println();
	}
	
}
