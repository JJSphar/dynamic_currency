public class Currency {
	public static void main(String[] args) {
		
		DenomSet american = new DenomSet("Quarter,4,Dime,10,Nickel,20,Penny,100");
		DenomSet anarchy = new DenomSet("Coin,1.5,Arrowhead,3,Button,150");
		DenomSet mine = new DenomSet("A,2,B,5,C,10");

		Combinator combinator = new Combinator(mine);
		
		System.out.println(mine.toString());
		System.out.println(mine.getNumCoins());
		System.out.println(mine.getTarget());
		System.out.println(mine.values);

		combinator.findSums();

	}
}