import java.util.Arrays;

class Combinator {
	// Set of denominations (coins)
	private DenomSet coins;

	//Memoization array where [n,m]=# of ways to create m from the (0...n)th coins 
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
		System.out.println(Arrays.deepToString(combos));
	}

	public void printCombos(){
		findCombos();
		printHeader();
		printCombosHelper(coins.getTarget(), coins.getNumCoins());
	}
	
	private void printCombosHelper(int target, int coinInd){
		
	}

	private void printHeader(){
		for (String name : coins.names) {
			System.out.printf("%5s",name);
		}
		System.out.println();
	}

	private void printOneCombo(int[] coinsUsed){
		for (int numCoin : coinsUsed) {
			System.out.printf("%5d",numCoin);
		}
		System.out.println();
	}
	
}
