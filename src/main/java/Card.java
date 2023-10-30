/**************************
 *Program 2: Baccarat game - Card.java
 This file contains the card container which contains the cards suite and cards value, it is essential to baccarat since
 it is what builds the deck of 52 cards that the game is played with

 Course: CS 342, Fall 2023.
 System: Windows using Intellij
 Starter Code Author: Mark Hallenbeck
 Student Authors: Adrian Lopez, Ihsaan Bijapuri
 *
 *************************/
public class Card {
    public String suite;
    public int value;

    // 2 argument constructor that creates a card with its value and suite
    Card(String theSuite, int theValue){
        this.suite = theSuite;
        this.value = theValue;
    }
}
