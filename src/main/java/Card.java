public class Card {
    private String suite;
    private int value;

    // using for debugging / testing delete later
    public String suiteValue(){
        return this.suite;
    }
    // using for debugging / testing delete later
    public int cardValue(){
        return this.value;
    }
    // 2 argument constructor that creates a card with its value and suite
    Card(String theSuite, int theValue){
        this.suite = theSuite;
        this.value = theValue;
    }
}
