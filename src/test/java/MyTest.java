/**************************
 *Program 2: Baccarat game - MyTest.java
 This file contains the test cases for each java file in the baccarate game, tests each method twice and makes sure they are working
 appropiately.

 Course: CS 342, Fall 2023.
 System: Windows using Intellij
 Starter Code Author: Mark Hallenbeck
 Student Authors: Adrian Lopez, Ihsaan Bijapuri
 *
 *************************/
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	// Testing card constructor to make sure it has the correct value
	 @Test
	 void cardConstructorTest1() {
	 	Card testCard = new Card("Spades",8);
		assertEquals(8, testCard.value, "Wrong card value: expecting 8");
	}
	// Testing card constructor to make sure it has the correct SUITE
	@Test
	 void cardConstructorTest2() {
		Card testCard = new Card("Spades", 8);
		assertEquals("Spades", testCard.suite, "Wrong card suite: expecting 'Spades'");
	 }
	 // Testing generate deck to make sure a deck of 52 cards is generated
	 @Test
	void generateDeckTest1() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		assertEquals(52, dealer.deckSize(), "Generate too many or few little cards: expecting 52 cards");
	 }
	 // Testing generate deck to make sure the correct card is being drawn off of the generated deck
	 @Test
	void generateDeckTest2() {
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();
		 Card testCard = new Card("Clubs", 13);
		 Card topCard = dealer.drawOne();
		 assertEquals(testCard.suite, topCard.suite, "Wrong suite for top of deck card: expecting a 'Spades'");
		 assertEquals(testCard.value, topCard.value, "Wrong value for top of deck card: expecting a 1");
	 }
	 // Testing deal hand to make sure two cards are dealt off the top of the deck
	 @Test
	void dealHandTest1() {
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();

		ArrayList<Card> testHand = dealer.dealHand();

		assertEquals("Clubs", testHand.get(0).suite, "Wrong suite for first card in hand: expecting 'Clubs'");
		assertEquals(13, testHand.get(0).value, "Wrong value for first card in hand: expecting 13");
		assertEquals("Clubs", testHand.get(1).suite, "Wrong suite for first card in hand: expecting 'Clubs'");
		assertEquals(12, testHand.get(1).value, "Wrong value for first card in hand: expecting 12");

	 }
	 // Testing deal hand to make sure exactly two cards are being dealt as a hand
	 @Test
	 void dealHandTest2() {
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();
		 ArrayList<Card> testHand = dealer.dealHand();
		 assertEquals(2, testHand.size(), "Wrong size of hand: expecting size of hand to be two");
	 }
	 @Test
	 // Testing if drawOne can be used to fully empty a deck
	 void drawOneTest1() {
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();
		 for(int i = 0; i < 52; i++){
			 dealer.drawOne();
		 }
		 assertEquals(0, dealer.deckSize(), "Wrong deck size after calling drawOne to empty deck: expecting deck size to be 0");
	 }
	 // Testing if drawOne is drawing the correct card off the top of the deck
	 @Test
	void drawOneTest2() {
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();
		 Card testCard = dealer.drawOne();
		 assertEquals(13, testCard.value, "Wrong card value after drawing first card off of deck: expecting 13");
		 assertEquals("Clubs", testCard.suite, "Wrong card value after drawing first card off of deck: expecting 'Clubs'");
	 }
	 // Testing if shuffleDeck produces the correct size of a deck
	 @Test
	void shuffleDeckTest1(){
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.shuffleDeck();
		 assertEquals(52, dealer.deckSize(), "Wrong deck size value after calling shuffleDeck: expecting deck size to be 52");
	 }
	 // Testing if shuffleDeck is randomized, meaning it should not match a in order deck of cards that is produced by generateDeck
	 @Test
	void shuffleDeckTest2(){
		BaccaratDealer regularDealer = new BaccaratDealer();
		regularDealer.generateDeck();
		BaccaratDealer shuffleDealer = new BaccaratDealer();
		shuffleDealer.shuffleDeck();
		Card shuffledCard = shuffleDealer.drawOne();
		Card regularCard = regularDealer.drawOne();
		boolean compareCards = true;
		if(shuffledCard.suite != regularCard.suite || shuffledCard.value != regularCard.value){
			compareCards = false;
		}
         assertFalse(compareCards, "Deck is not shuffled since both drawed cards from the decks are the same: expecting different card from shuffled deck");
	 }
	 // Testing the size of the deck is 52 after generateDeck is called
	 @Test
	void deckSizeTest1(){
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();
		 assertEquals(52, dealer.deckSize(), "Wrong deck size value: expecting 52");
	 }
	 // Testing if size of deck is updated to the correct value, after 10 cards are drawn from the deck, 52 - 10 = 42
	 @Test
	void deckSizeTest2(){
		 BaccaratDealer dealer = new BaccaratDealer();
		 dealer.generateDeck();
		 for(int i = 0; i < 10; i++){
			 dealer.drawOne();
		 }
		 assertEquals(42, dealer.deckSize(), "Wrong deck size value after drawOne called 10 times: expecting 42");
	 }
	 // Testing that the hand total evaluates to 8 when used on a hand of 2 4 value cards
	 @Test
	void handTotalTest1(){
		 BaccaratGameLogic logic = new BaccaratGameLogic();
		 ArrayList<Card> testHand1 = new ArrayList<>();
		 Card testCard1 = new Card("Spades", 4);
		 Card testCard2 = new Card("Spades", 4);
		 testHand1.add(testCard1);
		 testHand1.add(testCard2);
		 assertEquals(8, logic.handTotal(testHand1), "Wrong hand total value: expecting 8 when hand contains 2 4 value cards");
	 }
	 // Testing that hand total works correctly when the hand value goes over the value of 10
	@Test
	void handTotalTest2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> testHand1 = new ArrayList<>();
		Card testCard1 = new Card("Spades", 8);
		Card testCard2 = new Card("Spades", 8);
		testHand1.add(testCard1);
		testHand1.add(testCard2);
		assertEquals(6, logic.handTotal(testHand1), "Wrong hand total value: expecting 6 when hand contains 2 8 value cards");
	}
	// Testing if the banker can draw when their hand evaluates to 2, which they should be able to
	@Test
	void evaluateBankerDrawTest1(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> bankerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 1);
		Card testCard2 = new Card("Spades", 1);
		bankerHand.add(testCard1);
		bankerHand.add(testCard2);
        assertTrue(logic.evaluateBankerDraw(bankerHand, null), "Wrong evaluate banker draw: expecting true when banker hand evaluates to 2");
	}
	// Testing if the banker can draw when their hand evaluates to 6, and a player draws a 6, which they should be able to
	@Test
	void evaluateBankerDrawTest2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> bankerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 3);
		Card testCard2 = new Card("Spades", 3);
		Card playerCard = new Card("Spades", 6);
		bankerHand.add(testCard1);
		bankerHand.add(testCard2);
		assertTrue(logic.evaluateBankerDraw(bankerHand, playerCard), "Wrong evaluate banker draw: expecting true when banker hand evaluates to 6 and player draws a 6");
	}
	// Testing if the player can draw when their hand evaluates to 2, which they should be able to
	@Test
	void evaluatePlayerDrawTest1(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 1);
		Card testCard2 = new Card("Spades", 1);
		playerHand.add(testCard1);
		playerHand.add(testCard2);
		assertTrue(logic.evaluatePlayerDraw(playerHand), "Wrong evaluate player draw: expecting true when player hand evaluates to 2");
	}
	// Testing if the player can draw when their hand evaluates to 8, which they should not be able to
	@Test
	void evaluatePlayerDrawTest2(){
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 4);
		Card testCard2 = new Card("Spades", 4);
		playerHand.add(testCard1);
		playerHand.add(testCard2);
		assertFalse(logic.evaluatePlayerDraw(playerHand), "Wrong evaluate player draw: expecting false when players hand evaluates to 8");
	}
	// Testing who won when players total is 8 and bankers total is 4, player should win
	@Test
	void whoWonTest1 () {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 4);
		Card testCard2 = new Card("Spades", 4);
		playerHand.add(testCard1);
		playerHand.add(testCard2);

		ArrayList<Card> bankerHand = new ArrayList<>();
		Card testCard3 = new Card("Spades", 2);
		Card testCard4 = new Card("Spades", 2);
		bankerHand.add(testCard3);
		bankerHand.add(testCard4);
		assertEquals("Player.", logic.whoWon(playerHand, bankerHand), "Wrong person won: expecting player to win");
	}
	// Testing who won when players total is 2 and bankers total is 8, banker should win
	@Test
	void whoWonTest2 () {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 1);
		Card testCard2 = new Card("Spades", 1);
		playerHand.add(testCard1);
		playerHand.add(testCard2);
		System.out.println(logic.handTotal(playerHand));

		ArrayList<Card> bankerHand = new ArrayList<>();
		Card testCard3 = new Card("Spades", 4);
		Card testCard4 = new Card("Spades", 4);
		bankerHand.add(testCard3);
		bankerHand.add(testCard4);

		assertEquals("Banker.", logic.whoWon(playerHand, bankerHand), "Wrong person won: expecting banker to win");
	}
	// Testing who won when players total is equal to bankers total, should be a draw
	@Test
	void whoWonTest3 () {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		Card testCard1 = new Card("Spades", 4);
		Card testCard2 = new Card("Spades", 4);
		playerHand.add(testCard1);
		playerHand.add(testCard2);
		System.out.println(logic.handTotal(playerHand));

		ArrayList<Card> bankerHand = new ArrayList<>();
		Card testCard3 = new Card("Spades", 4);
		Card testCard4 = new Card("Spades", 4);
		bankerHand.add(testCard3);
		bankerHand.add(testCard4);

		assertEquals("Draw.", logic.whoWon(playerHand, bankerHand), "Wrong person won: expecting a draw");
	}
	// Testing if evaluate winnings adds to the total correctly
	@Test
	void evaluateWinningstest1(){
		BaccaratGame game =  new BaccaratGame();
		assertEquals(10, game.evaluateWinnings(10));

	}
	// Testing if evaluate winnings adds to the total correctly after some different cases
	@Test
	void evaluateWinningstest2(){
		BaccaratGame game =  new BaccaratGame();
		game.evaluateWinnings(30);
		game.evaluateWinnings(-10);
		game.evaluateWinnings(20);
		assertEquals(50, game.evaluateWinnings(10));

	}
