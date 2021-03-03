package Games;

import Casino.*;

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

    public String PrintCard() {
        if (CardNumber < 1 && CardNumber > 11) {
            return CardNumber + ", " + CardSuit;
        }
        if (CardNumber == 1) {
            return "Ace" + ", " + CardSuit;
        }
        if (CardNumber == 11) {
            return "Jack" + ", " + CardSuit;
        }
        if (CardNumber == 12) {
            return "Queen" + ", " + CardSuit;
        }
        if (CardNumber == 13) {
            return "King" + ", " + CardSuit;
        }
        return CardNumber + ", " + CardSuit;
    }

}
