
package Casino;


public class Card {
    private int CardNumber;
    private String CardSuit;
    
    //constructor
    public Card(int CardNumber, String CardSuit) {
        this.CardNumber = CardNumber;
        this.CardSuit = CardSuit;
    }
    
    //getter
    public int getCardNumber() {
        return CardNumber;
    }

    public String getCardSuit() {
        return CardSuit;
    }
    
    //setter
    public void setCardNumber(int CardNumber) {
        this.CardNumber = CardNumber;
    }

    public void setCardSuit(String CardSuit) {
        this.CardSuit = CardSuit;
    }
    
    
    
}
