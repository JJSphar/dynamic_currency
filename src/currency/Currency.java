package currency;

/**
 * Finds and prints all the combinations of arbitrary denominations that sum to an arbitrary value
 */
public class Currency {
	public static void main(String[] args) {
		// Print help
		if(args.length == 0){
			printFormat();
			return;
		}

		String input = args[0];
		DenomSet inputSet = null;

		if(input.equals("-a") || input.equals("--american")){
			inputSet = new DenomSet("Quarter,4,Dime,10,Nickel,20,Penny,100");
		}else{
			try {
				inputSet= new DenomSet(input);
			} catch (IllegalArgumentException e) {
				System.out.println("Bad input");
				printFormat();	
				return;
			}
		}

		Combinator combos = new Combinator(inputSet);
		combos.printCombos();
	}

	/**
	 * Prints the required format and input parameters to the screen
	 */
	private static void printFormat(){
		System.out.println("To print the possible ways to combine a set of denominations: ");
		System.out.println("Pass in the denominations with names and the # of each needed to reach an arbitrary sum. Argument format: ");
		System.out.println("\t\"n1,v1[,n2,v2][...]\"");
		System.out.println("\tWhere 'n' are denomination names and 'v' are the # needed (well formatted decimals or integers)");
		System.out.println("\nAlternatively, pass -a or --american to use the 4 American coin denominations:  ");
		System.out.println("\t\"Quarter,4,Dime,10,Nickel,20,Penny,100\"");
	}
}