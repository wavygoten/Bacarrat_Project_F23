import java.util.ArrayList;

public class BaccaratGameLogic {

    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int playerTotal = handTotal(hand1);
        int bankerTotal = handTotal(hand2);
        if (playerTotal == bankerTotal) {
            return "T";
        } else if (playerTotal > bankerTotal) {
            return "P";
        } else {
            return "B";
        }
    }

    public int handTotal(ArrayList<Card> hand) {
        if (hand.isEmpty())
            return 0; // empty hand
        int total = 0;
        for (int i = 0; i < hand.size(); i++) {
            total += hand.get(i).value >= 10 ? 0 : hand.get(i).value;
        }
        return total % 10; // adds hand together
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        int total = handTotal(hand);

        if (playerCard != null) {
            if ((playerCard.value >= 10 || playerCard.value == 1) && total <= 3) {
                return true;
            }
            if ((playerCard.value == 2 || playerCard.value == 3) && total <= 4) {
                return true;
            }
            if ((playerCard.value == 4 || playerCard.value == 5) && total <= 5) {
                return true;
            }
            if ((playerCard.value == 6 || playerCard.value == 7) && total <= 6) {
                return true;
            }
            if (playerCard.value == 8 && total <= 2) {
                return true;
            }
            if (playerCard.value == 9 && total <= 3) {
                return true;
            }
        } else {
            if (total <= 5) {
                return true;
            }
        }

        return false;
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        int total = handTotal(hand);
        if (total <= 5) {
            return true;
        }
        return false;
    }
}
