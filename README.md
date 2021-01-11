# Currency.java
Given a list of denominations and their relative values, such as `"Quarter,4,Dime,10,Nickel,20,Penny,100"`, Currency will print out all possible combinations of denominations that sum to a target value:

```
Quarter   Dime      Nickel    Penny     
4         0         0         0         
2         5         0         0
...
0         0         0         100
Count: 242 
```

## Build
From the root folder with a JDK in your PATH:

`javac -d bin src/currency/*.java`

## Usage  
You can either pass in your own custom list of denominations, or you can use the American 4-coin set (shown above). From the root folder:

Print all combinations of the American 4-coin set using the --a or --american param (problem #1):

`java -cp ./bin/ currency.Currency --american`


Print all combinations of a custom denominations set (problem #2):

`java -cp ./bin/ currency.Currency "n1,v1[,n2,v2][...]"`

Where 'n' are denomination names and 'v' represent the number (as well-formatted decimals / integers) of this denomination needed to reach a target sum.
For example: `"Coin,1.5,Arrowhead,3,Button,150"`.

Which would be run as:

`java -cp ./bin/ currency.Currency "Coin,1.5,Arrowhead,3,Button,150"`


Print the help / input format:

`java -cp ./bin/ currency.Currency`
