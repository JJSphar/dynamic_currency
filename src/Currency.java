public class Currency {
    public static void main(String[] args) {
        
        DenomSet american = new DenomSet("Quarter,4,Dime,10,Nickel,20,Penny,100");
        DenomSet anarchy = new DenomSet("Coin,1.5,Arrowhead,3,Button,150");

        Combinator combinator = new Combinator(american);
        
        System.out.println(american.toString());
        System.out.println(anarchy.toString());
        System.out.println(anarchy.getNumCoins());
        System.out.println(anarchy.getNumSmallest());
        System.out.println(anarchy.values);

    }
}