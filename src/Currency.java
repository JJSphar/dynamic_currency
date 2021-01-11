import java.util.ArrayList;
import java.util.List;

import jdk.jshell.execution.Util;

public class Currency {
	public static void main(String[] args) {
		
		DenomSet fullamerican = new DenomSet("Dollar,1,Half,2,Quarter,4,Dime,10,Nickel,20,Penny,100");
		DenomSet anarchy = new DenomSet("Coin,1.5,Arrowhead,3,Button,150");
		DenomSet american =  new DenomSet("Quarter,4,Dime,10,Nickel,20,Penny,100");
		DenomSet mine = new DenomSet("A,1,B,2");
		DenomSet theirs = new DenomSet("C,10,B,5,A,2");


		Combinator myCombos = new Combinator(mine);
		myCombos.printCombos();

		Combinator anarchyCombos = new Combinator(anarchy);
		anarchyCombos.printCombos();

		Combinator americanCombos = new Combinator(american);
		americanCombos.printCombos();
	}
}