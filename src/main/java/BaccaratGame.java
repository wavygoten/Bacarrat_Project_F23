import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaccaratGame extends Application {

	/* Data Members */
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private int roundNumber;
	private double currentBalance;
	private double totalWinnings;
	private String currentMessage;

	/* Layout Components */

	private Button playerBet;
	private Button tieBet;
	private Button bankerBet;
	private Button playButton;

	private TextField playerBetAmount;
	private TextField tieBetAmount;
	private TextField bankerBetAmount;
	private TextArea message;

	private MenuItem exit;
	private MenuItem re;

	private Label intBankerTotal;
	private Label intPlayerTotal;
	private Label intWinnings;
	private Label intBalance;
	private Label rN;

	private ImageView playerCardOneView;
	private ImageView playerCardTwoView;
	private ImageView playerCardThreeView;
	private ImageView bankerCardOneView;
	private ImageView bankerCardTwoView;
	private ImageView bankerCardThreeView;

	// constructor
	public BaccaratGame() {
		// initialize all components
		playerHand = new ArrayList<>();
		bankerHand = new ArrayList<>();
		theDealer = new BaccaratDealer();
		roundNumber = 1;
		currentBalance = 100;
		totalWinnings = 0;
		currentMessage = "";
		theDealer.shuffleDeck();

		playerBet = new Button("Player Bet");
		playerBetAmount = new TextField("");
		playerBetAmount.setPromptText("$0");
		playerBetAmount.setDisable(true);
		tieBet = new Button("Tie Bet");
		tieBetAmount = new TextField("");
		tieBetAmount.setPromptText("$0");
		tieBetAmount.setDisable(true);
		bankerBet = new Button("Banker Bet");
		bankerBetAmount = new TextField("");
		bankerBetAmount.setPromptText("$0");
		bankerBetAmount.setDisable(true);
		playButton = new Button("Play Game");
		playButton.setDisable(true);
		TextField bankerBetAmount = new TextField("");
		bankerBetAmount.setPromptText("$0");
		bankerBetAmount.setDisable(true);
		playButton = new Button("Play Game");
		playButton.setDisable(true);
		exit = new MenuItem("Exit");
		re = new MenuItem("Fresh Start");
		intBankerTotal = new Label("0");
		intPlayerTotal = new Label("0");
		intWinnings = new Label("$" + String.valueOf(totalWinnings));
		intBalance = new Label("$" + String.valueOf(currentBalance));
		rN = new Label(String.valueOf(roundNumber));
		message = new TextArea(currentMessage);
		message.setDisable(true);
		message.setStyle("-fx-opacity: 1.0;");
		playerCardOneView = new ImageView();
		playerCardTwoView = new ImageView();
		playerCardThreeView = new ImageView();
		bankerCardOneView = new ImageView();
		bankerCardTwoView = new ImageView();
		bankerCardThreeView = new ImageView();
		playerCardTwoView.setX(10);
		playerCardTwoView.setY(10);
		playerCardTwoView.setFitWidth(75);
		playerCardTwoView.setPreserveRatio(true);
		playerCardOneView.setX(10);
		playerCardOneView.setY(10);
		playerCardOneView.setFitWidth(75);
		playerCardOneView.setPreserveRatio(true);
		playerCardThreeView.setX(10);
		playerCardThreeView.setY(10);
		playerCardThreeView.setFitWidth(75);
		playerCardThreeView.setPreserveRatio(true);
		bankerCardOneView.setX(10);
		bankerCardOneView.setY(10);
		bankerCardOneView.setFitWidth(75);
		bankerCardOneView.setPreserveRatio(true);
		bankerCardTwoView.setX(10);
		bankerCardTwoView.setY(10);
		bankerCardTwoView.setFitWidth(75);
		bankerCardTwoView.setPreserveRatio(true);
		bankerCardThreeView.setX(10);
		bankerCardThreeView.setY(10);
		bankerCardThreeView.setFitWidth(75);
		bankerCardThreeView.setPreserveRatio(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	// feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Baccarat Game");

		/* Layout */

		// 1 vbox in scene
		// 4 hboxes - options, game panel, message box, better area
		// options - menu bar
		// game panel - vbox data, vbox + gridpane
		// message box - message dialog box?
		// bets area - 3 vbox each bet, play button
		// do spacing later
		MenuBar mb = new MenuBar();
		Menu options = new Menu("Options");
		mb.getMenus().add(options);
		options.getItems().addAll(exit, re);
		Label bankerTotal = new Label("Banker total");
		Label playerTotal = new Label("Player total");
		Label winnings = new Label("Winnings");
		Label balance = new Label("Balance");
		Label round = new Label("Round");
		VBox bankerMenuItem = new VBox(10, bankerTotal, intBankerTotal);
		VBox playerMenuItem = new VBox(10, playerTotal, intPlayerTotal);
		VBox winningsMenuItem = new VBox(10, winnings, intWinnings);
		VBox balanceMenuItem = new VBox(10, balance, intBalance);
		VBox roundMenuItem = new VBox(10, round, rN);
		VBox totalMenu = new VBox(30, bankerMenuItem, playerMenuItem, winningsMenuItem, balanceMenuItem, roundMenuItem);
		HBox cards1 = new HBox(10, playerCardOneView, playerCardTwoView, playerCardThreeView);
		HBox cards2 = new HBox(10, bankerCardOneView, bankerCardTwoView, bankerCardThreeView);
		Label playerCards = new Label("Player");
		Label bankerCards = new Label("Banker");
		VBox player = new VBox(playerCards, cards1);
		VBox banker = new VBox(bankerCards, cards2);
		HBox innerGamePanel = new HBox(40, player, banker);
		HBox gamePanel = new HBox(40, totalMenu, innerGamePanel);
		gamePanel.setPadding(new Insets(0, 0, 50, 20));

		VBox playerVbox = new VBox(10, playerBet, playerBetAmount);
		VBox tieVbox = new VBox(10, tieBet, tieBetAmount);
		VBox bankerVbox = new VBox(10, bankerBet, bankerBetAmount);
		HBox betsPanel = new HBox(20, playerVbox, tieVbox, bankerVbox, playButton);
		betsPanel.setPadding(new Insets(50, 0, 0, 0));

		VBox mainPanel = new VBox(10, mb, gamePanel, message, betsPanel);

		/* Layout */

		Scene scene = new Scene(mainPanel, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

		// button/textfield handlers below
		playerBetAmount.textProperty().addListener((observable, oldVal, newVal) -> {
			playButton.setDisable(false);
		});
		bankerBetAmount.textProperty().addListener((observable, oldVal, newVal) -> {
			playButton.setDisable(false);
		});
		tieBetAmount.textProperty().addListener((observable, oldVal, newVal) -> {
			playButton.setDisable(false);
		});
		playButton.setOnAction(e -> playButton(e));
		playerBet.setOnAction(e -> playerBetButton(e));
		tieBet.setOnAction(e -> tieBetButton(e));
		bankerBet.setOnAction(e -> bankerBetButton(e));
		re.setOnAction(e -> restartButton(e));
		exit.setOnAction(e -> exitButton(e));
	}

	/* Button Functions */

	private void playerBetButton(ActionEvent event) {
		playerBetAmount.setPromptText("");
		playerBetAmount.setDisable(false);
		tieBetAmount.setPromptText("$0");
		tieBetAmount.setDisable(true);
		tieBetAmount.setText(null);
		bankerBetAmount.setPromptText("$0");
		bankerBetAmount.setDisable(true);
		bankerBetAmount.setText(null);
	}

	private void tieBetButton(ActionEvent event) {
		playerBetAmount.setPromptText("$0");
		playerBetAmount.setDisable(true);
		playerBetAmount.setText(null);
		tieBetAmount.setPromptText("");
		tieBetAmount.setDisable(false);
		bankerBetAmount.setPromptText("$0");
		bankerBetAmount.setDisable(true);
		bankerBetAmount.setText(null);
	}

	private void bankerBetButton(ActionEvent event) {
		playerBetAmount.setPromptText("$0");
		playerBetAmount.setDisable(true);
		playerBetAmount.setText(null);
		tieBetAmount.setPromptText("$0");
		tieBetAmount.setDisable(true);
		tieBetAmount.setText(null);
		bankerBetAmount.setPromptText("");
		bankerBetAmount.setDisable(false);
	}

	private void playButton(ActionEvent event) {
		message.setText("");
		BaccaratGameLogic logic = new BaccaratGameLogic();
		String betOn = "";
		double currentBet = 0;
		try {
			if (playerBetAmount.getText() == null && tieBetAmount.getText() == null) {
				currentBet = Double.valueOf(bankerBetAmount.getText());
				betOn = "B";
			} else if (playerBetAmount.getText() == null && bankerBetAmount.getText() == null) {
				currentBet = Double.valueOf(tieBetAmount.getText());
				betOn = "T";
			} else {
				currentBet = Double.valueOf(playerBetAmount.getText());
				betOn = "P";
			}
		} catch (NumberFormatException e) {
			System.out.println("Must enter a bet");
			message.setText("Must enter a bet");
			// this exits the event early
			event.consume();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if no bet was entered
		if (currentBet <= 0) {
			System.out.println("Must enter an amount greater than 0");
			message.setText("Must enter an amount greater than 0");
			// this exits the event early
			event.consume();
			return;
		}
		if (currentBet <= currentBalance) {
			if (theDealer.deckSize() < 6) {
				theDealer.shuffleDeck();
			}
			playerHand = theDealer.dealHand();
			bankerHand = theDealer.dealHand();
			intBankerTotal.setText(String.valueOf(logic.handTotal(bankerHand)));
			intPlayerTotal.setText(String.valueOf(logic.handTotal(playerHand)));
			playerCardOneView.setImage(null);
			playerCardTwoView.setImage(null);
			playerCardThreeView.setImage(null);
			bankerCardOneView.setImage(null);
			bankerCardTwoView.setImage(null);
			bankerCardThreeView.setImage(null);

			try {
				InputStream playerCardOneStream = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/cardImages"
								+ getCardImage(playerHand.get(0)));
				InputStream playerCardTwoStream = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/cardImages"
								+ getCardImage(playerHand.get(1)));
				InputStream bankerCardOneStream = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/cardImages"
								+ getCardImage(bankerHand.get(0)));
				InputStream bankerCardTwoStream = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/cardImages"
								+ getCardImage(bankerHand.get(1)));
				Image playerCardOne = new Image(playerCardOneStream);
				Image playerCardTwo = new Image(playerCardTwoStream);
				Image bankerCardOne = new Image(bankerCardOneStream);
				Image bankerCardTwo = new Image(bankerCardTwoStream);
				playerCardOneView.setImage(playerCardOne);
				playerCardTwoView.setImage(playerCardTwo);
				bankerCardOneView.setImage(bankerCardOne);
				bankerCardTwoView.setImage(bankerCardTwo);

				// player
				if (logic.evaluatePlayerDraw(playerHand)) {
					playerHand.add(theDealer.drawOne());
					InputStream playerCardThreeStream = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/resources/cardImages"
									+ getCardImage(playerHand.get(2)));
					Image cardThree = new Image(playerCardThreeStream);
					playerCardThreeView.setImage(cardThree);
					intPlayerTotal.setText(String.valueOf(logic.handTotal(playerHand)));
				}

				// banker
				if (playerHand.size() > 2) { // player drew
					if (logic.evaluateBankerDraw(playerHand, playerHand.get(2))) {
						bankerHand.add(theDealer.drawOne());
						InputStream bankerCardThreeStream = new FileInputStream(
								System.getProperty("user.dir") + "/src/main/resources/cardImages"
										+ getCardImage(bankerHand.get(2)));
						Image bankerCardThree = new Image(bankerCardThreeStream);
						bankerCardThreeView.setImage(bankerCardThree);
						intBankerTotal.setText(String.valueOf(logic.handTotal(bankerHand)));
					}
				} else { // player didnt draw
					if (logic.evaluateBankerDraw(playerHand, null)) {
						bankerHand.add(theDealer.drawOne());
						InputStream bankerCardThreeStream = new FileInputStream(
								System.getProperty("user.dir") + "/src/main/resources/cardImages"
										+ getCardImage(bankerHand.get(2)));
						Image bankerCardThree = new Image(bankerCardThreeStream);
						bankerCardThreeView.setImage(bankerCardThree);
						intBankerTotal.setText(String.valueOf(logic.handTotal(bankerHand)));
					}
				}
			} catch (FileNotFoundException error) {
				error.printStackTrace();
				System.out.println("File not found");
			} catch (Exception error) {
				error.printStackTrace();
				System.out.println("Some Error");
			} finally {
				String whoWon = logic.handTotal(bankerHand) > logic.handTotal(playerHand) ? "B"
						: logic.handTotal(bankerHand) < logic.handTotal(playerHand) ? "P" : "T";
				System.out.println("who won: " + whoWon + "\nbet on: " + betOn);

				// need to handle full logic not finished
				if (whoWon.equals(betOn) && whoWon.equals("T")) {
					currentBalance += (currentBet * 8);
					totalWinnings += (currentBet * 8);
					message.setText("Player Total: " + logic.handTotal(playerHand) + " Banker Total: "
							+ logic.handTotal(bankerHand) + "\n" + //
							"It's a tie\n" + //
							"Congrats, you bet on a Tie! You win!");
				} else if (whoWon.equals(betOn) && whoWon.equals("P")) {
					currentBalance += currentBet;
					totalWinnings += currentBet;
					message.setText("Player Total: " + logic.handTotal(playerHand) + " Banker Total: "
							+ logic.handTotal(bankerHand) + "\n" + //
							"Player wins\n" + //
							"Congrats, you bet Player! You win!");

				} else if (whoWon.equals(betOn) && whoWon.equals("B")) {
					currentBalance += (currentBet * 0.95);
					totalWinnings += (currentBet * 0.95);
					message.setText("Player Total: " + logic.handTotal(playerHand) + " Banker Total: "
							+ logic.handTotal(bankerHand) + "\n" + //
							"Banker wins\n" + //
							"Congrats, you bet Banker! You win!");

				} else if (whoWon.equals("T") && !betOn.equals(whoWon)) {
					String t = betOn.equals("P") ? "Player" : "Banker";
					message.setText("Player Total: " + logic.handTotal(playerHand) + " Banker Total "
							+ logic.handTotal(bankerHand) + "\n" + //
							"It's a tie" + "\n" + //
							"Sorry, you bet " + t + "! You lost your bet!");
					// do nothing its a push
					// if tie player banker lose no money
				} else if (whoWon.equals("P") && !betOn.equals(whoWon)) {
					String t = betOn.equals("B") ? "Banker" : "Tie";
					currentBalance -= currentBet;
					message.setText("Player Total: " + logic.handTotal(playerHand) + " Banker Total "
							+ logic.handTotal(bankerHand) + "\n" + //
							"Player wins" + "\n" + //
							"Sorry, you bet " + t + "! You lost your bet!");
				} else if (whoWon.equals("B") && !betOn.equals(whoWon)) {
					String t = betOn.equals("P") ? "Player" : "Tie";
					currentBalance -= currentBet;
					message.setText("Player Total: " + logic.handTotal(playerHand) + " Banker Total "
							+ logic.handTotal(bankerHand) + "\n" + //
							"Banker wins" + "\n" + //
							"Sorry, you bet " + t + "! You lost your bet!");
				} else {
					currentBalance -= currentBet;
				}
				intBalance.setText("$" + String.valueOf(currentBalance));
				intWinnings.setText("$" + String.valueOf(totalWinnings));
				roundNumber++;
				rN.setText(String.valueOf(roundNumber));
				for (int i = 0; i < playerHand.size(); i++) {
					System.out.println(
							"Player Hand: " + playerHand.get(i).value + " of " + playerHand.get(i).suite);
				}
				for (int i = 0; i < bankerHand.size(); i++) {
					System.out.println("Banker Hand: " + bankerHand.get(i).value + " of "
							+ bankerHand.get(i).suite);
				}
				System.out.println("Round: " + roundNumber);
				System.out.println("Current Balance: " + String.valueOf(currentBalance));
				System.out.println("Total Winnings: " + String.valueOf(totalWinnings));
			}
		} else {
			System.out.println("You don't have enough money to play this bet amount.");
			message.setText("You don't have enough money to play this bet amount.");

		}
	}

	private void restartButton(ActionEvent event) {
		currentBalance = 100;
		totalWinnings = 0;
		bankerHand.clear();
		playerHand.clear();
		roundNumber = 1;
		intWinnings.setText("$" + String.valueOf(totalWinnings));
		intBalance.setText("$" + String.valueOf(currentBalance));
		rN.setText(String.valueOf(roundNumber));
		message.setText("");
		intBankerTotal.setText("0");
		intPlayerTotal.setText("0");
		bankerBetAmount.setPromptText("$0");
		bankerBetAmount.setText("");
		bankerBetAmount.setDisable(true);
		tieBetAmount.setPromptText("$0");
		tieBetAmount.setText("");
		tieBetAmount.setDisable(true);
		playerBetAmount.setPromptText("$0");
		playerBetAmount.setText("");
		playerBetAmount.setDisable(true);
		playerCardOneView.setImage(null);
		playerCardTwoView.setImage(null);
		playerCardThreeView.setImage(null);
		bankerCardOneView.setImage(null);
		bankerCardTwoView.setImage(null);
		bankerCardThreeView.setImage(null);
		playButton.setDisable(true);
	}

	private void exitButton(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	/* Helper Functions */
	private String getCardImage(Card card) {
		String cardValue = Integer.toString(card.value); // convert card integer value to string
		String cardString = "/" + cardValue + card.suite + ".png"; // concatenate int string to suite string to be used
																	// as a file path
		return cardString;
	}
}
