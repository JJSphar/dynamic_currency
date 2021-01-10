

class Combinator {
    // Set of coins to work with
    DenomSet coins;

    //2D Array where [i,n]=number of the ith coin needed to equal n 
    int[][] combinations;

    Combinator(DenomSet coins){
        this.coins = coins;
        combinations = new int[coins.getNumCoins()][coins.getNumSmallest()];
    }

    public void findCombinations(){
        // Loop through every coin
        for (int i = 0; i < combinations.length; i++) {
            //Start at the max value
            for(int n=combinations[i].length-1 ; n >= 0; n--){
                // TODO
            }
        }
    }
}
