
/**************************
 *Program 2: Baccarat game - BaccaratDealer.java
  This file contains the implementation of the dealer in a game of Baccarat. The dealer is able to create a standard 52 card deck, shuffle the deck,
 deal a hand, and draw from the deck.

 Course: CS 342, Fall 2023.
 System: Windows using Intellij
 Starter Code Author: Mark Hallenbeck
 Student Authors: Adrian Lopez, Ihsaan Bijapuri
 *
 *************************/

import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
    private ArrayList<Card> deck = new ArrayList<>();

    // generateDeck generates a new standard 52 card deck where each card is an
    // instance of the Card class in the ArrayList<Card> deck
    // In the rules of baccarat that the prof had on the project pdf it said "Face
    // cards and 10’s count as zero (’Baccarat’ pronounced ‘Bak-Ar-Ah’)."
    // idk if we should start face cards at 0 or keep them at 10, 11, 12, 13 and
    // implement the 0 value logic orrr what. so feel free to change it if you want
    // to
    public void generateDeck() {
        deck.clear(); // make sure we have empty deck before generating
        String suite = "";
        int face10s = 0;
        for (int j = 0; j < 4; j++) {
            if (j == 0)
                suite = "Spades";
            if (j == 1)
                suite = "Hearts";
            if (j == 2)
                suite = "Diamonds";
            if (j == 3)
                suite = "Clubs";
            for (int i = 1; i < 14; i++) {
                Card newCard = new Card(suite, face10s);
                deck.add(newCard);
            }
        }
    }

    // dealHand will deal two cards off the top of the deck, and returns them in an
    // ArrayList<Card>
    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();
        int cardOne = deckSize() - 1; // value at the top of our deck
        hand.add(deck.remove(cardOne));
        int cardTwo = deckSize() - 1;
        hand.add(deck.remove(cardTwo));
        return hand;
    }

    // drawOne will deal a single card from the top of the deck and returns it
    public Card drawOne() {
        int topofDeck = deckSize() - 1; // value at the top of our deck
        return deck.remove(topofDeck); // returns card at the top of deck, and removes it
    }

    // shuffleDeck will create a new deck of 52 cards and “shuffle” and randomize
    // the cards in that ArrayList<Card>
    public void shuffleDeck() {
        generateDeck();
        Collections.shuffle(deck);
    }

    // deckSize returns how many cards are in this.deck at any given time
    public int deckSize() {
        return deck.size();
    }

    public void print() {
        for (int a = 0; a < deckSize(); a++) {
            System.out.print(deck.get(a).value);
            System.out.print(" ");
            System.out.println(deck.get(a).suite);

        }
        System.out.print("Deck Size: ");
        System.out.print(deck.size());
    }
}
