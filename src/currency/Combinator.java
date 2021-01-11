package currency;

class Combinator {
	// Set of denominations (coins)
	private DenomSet coins;

	/**
	 * Memoization array where [n,m]=# of ways to create m from the (0...n)th coins
	 * The first row and column are for no coins / no value, respectively
	 * For example, element 5 of row 2 would be:
	 * the number of ways to create the value 4 using only the 1st and 2nd coins
	 * This allows the solution to be built 'bottom-up'
	 */
	private int[][] combos;

	Combinator(DenomSet coins){
		this.coins = coins;

		combos = new int[coins.getNumDenoms()+1][coins.getTarget()+1];
	}

	/**
	 * Uses a bottom-up, memoized, Dynamic Programming approach to calculate the total number of combos for each value/set of coins
	 * Memoization array can later be traversed to find the best / all solutions  
	 */
	public void findCombos(){
		// Set base case - 0 value can always be made using 0 coins (1 solution)
		for (int i = 0; i < combos.length; i++) {
			combos[i][0] = 1;
		}

		// Increment through the coins we're allowed to use
		for (int i = 1; i <= coins.getNumDenoms(); i++) {
			int value = coins.values.get(i-1);
			
			//Try to create each j value using this + previous coins
			for(int j=1; j <= coins.getTarget(); j++){
				if(value <= j){
					// Add in the # of smaller combos using these coins
					combos[i][j] = combos[i-1][j] + combos[i][j-value];
				}else{
					// Coin is bigger than j - can't make new combos with this coin 
					combos[i][j] = combos[i-1][j];
				}
			}
		}
	}

	/**
	 * Executes findCombos then traverses the memoization array in order to print all possible combinations of the input set to screen
	 */
	public void printCombos(){
		findCombos();
		
		printHeader();

		//Start at the bottom-right corner of the array to print all combinations using all coins
		printCombosHelper(coins.getNumDenoms(), coins.getTarget(), new int[coins.getNumDenoms()]);
		
		// The total number of combinations is stored in the bottom-right corner
		System.out.println("Count: " + combos[coins.getNumDenoms()][coins.getTarget()]);
	}
	
	/**
	 * Traverses the memoization "combos" array in order to print all possible ways to combine a set of denominations
	 * @param coinInd the row-index of combos (represents using the nth coin in the input set)
	 * @param target the column-index of combos (represents the value we are trying to reach)
	 * @param coinsUsed a numCoins-size array of how many of each denomination is used for this combo so far
	 */
	private void printCombosHelper(int coinInd, int target, int[] coinsUsed){
		// No coins left to try - exit 
		if(coinInd == 0){
			return;
		}
		
		// Found a combo that evenly sums up to target - print and exit
		if(target == 0){
			printOneCombo(coinsUsed);
			return;
		}

		// The number of combos that can be made using this coin
		int combosWithCoin = combos[coinInd][target];
		// The number of combos using only previous coins
		int combosWithoutCoin = combos[coinInd-1][target];

		// Can this value be made using this coin?		
		if(combosWithCoin != combosWithoutCoin){
			// Don't use this coin - use an equivalent amount of smaller denoms
			printCombosHelper(coinInd-1, target, coinsUsed.clone());

			// Use this coin in combos
			coinsUsed[coinInd-1] += 1;
			printCombosHelper(coinInd, target-coins.values.get(coinInd-1), coinsUsed.clone());

		}else{
			// This coin cannot be used to make this value - traverse combos not including this coin
			printCombosHelper(coinInd-1, target, coinsUsed.clone()); 
		}	
	}

	/**
	 * Prints the names of the denominations in a table format
	 */
	private void printHeader(){
		for (String name : coins.names) {
			System.out.printf("%-10s",name);
		}
		System.out.println();
	}

	/**
	 * Prints a single combination of denominations
	 * @param coinsUsed the array of # of each denomination used
	 */
	private void printOneCombo(int[] coinsUsed){
		for (int numCoin : coinsUsed) {
			System.out.printf("%-10d",numCoin);
		}
		System.out.println();
	}
	
}
