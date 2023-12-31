import java.util.ArrayList;

public class BaccaratGameLogic {
    private boolean playerDrew = false;
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        int playerTotal = handTotal(hand1);
        int bankerTotal = handTotal(hand2);
        if(playerTotal == bankerTotal){
            return "Draw.";
        }
        else if(playerTotal > bankerTotal){
            return "Player.";
        }
        else if(playerTotal == 0){
            return "Player.";
        }
        else{
            return "Banker.";
        }
    }
    public int handTotal(ArrayList<Card> hand){
        if (hand.isEmpty()) return 0;                   // empty hand
        int total = 0;
        for(int i = 0; i < hand.size(); i++){
            total += hand.get(i).value;
        }
        if(total >= 10) return total % 10;
        if(total >= 20) return total % 20;
        return total;   // adds hand together
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){
        int total = handTotal(hand);
        if(total >= 7) return false; // do not draw, stand
        else if(total == 0 || total == 1|| total == 2) return true; // draw another card
        else if((total == 3 || total == 4 || total == 5) && playerCard.value == 0) return true;
        else if(total == 3 && (playerCard.value != 8 || !playerDrew)) return true;
        else if(total == 4 && (!playerDrew || (playerCard.value >= 2 && playerCard.value <= 7))) return true;
        else if(total == 5 && (!playerDrew || (playerCard.value >= 4 && playerCard.value <= 7))) return true;
        else if(total == 6 && (playerCard.value == 6 || playerCard.value == 7)) return true;
        return false;
    }
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
        int total = handTotal(hand);
        if(total <= 5) {playerDrew = true; return true;}
        return false;
    }
}

