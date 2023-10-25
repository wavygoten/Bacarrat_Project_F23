import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaccaratGame extends Application {
	private ArrayList<Card> playerHand = new ArrayList<>();
	private ArrayList<Card> bankerHand = new ArrayList<>();
	private BaccaratDealer theDealer = new BaccaratDealer();
	private int roundNumber = 1;
	private double currentBalance = 100;
	private double totalWinnings = 0;

	public double evaluateWinnings() {

		double win = 0;

		return win;

		// do this bc ration multiplication
	}

	private String getCardImage(Card card) {
		String cardValue = Integer.toString(card.value); // convert card integer value to string
		String cardString = "/" + cardValue + card.suite + ".png"; // concatenate int string to suite string to be used
																	// as a file path
		return cardString;
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
		BorderPane pane = new BorderPane();

		Button playerBet = new Button("Player Bet");
		TextField playerBetAmount = new TextField("");
		playerBetAmount.setPromptText("$0");
		playerBetAmount.setDisable(true);
		Button tieBet = new Button("Tie Bet");
		TextField tieBetAmount = new TextField("");
		tieBetAmount.setPromptText("$0");
		tieBetAmount.setDisable(true);
		Button bankerBet = new Button("Banker Bet");
		TextField bankerBetAmount = new TextField("");
		bankerBetAmount.setPromptText("$0");
		bankerBetAmount.setDisable(true);
		Button playButton = new Button("Play Game");
		playButton.setDisable(true);

		Region spacing4 = new Region();
		spacing4.setPrefWidth(40);

		VBox playerVbox = new VBox(playerBet, playerBetAmount);
		VBox tieVbox = new VBox(tieBet, tieBetAmount);
		VBox bankerVbox = new VBox(bankerBet, bankerBetAmount);
		HBox bottomBar = new HBox(playerVbox, tieVbox, bankerVbox, spacing4, playButton);
		MenuBar mb = new MenuBar();
		Menu options = new Menu("Options");
		MenuItem exit = new MenuItem("Exit");
		MenuItem re = new MenuItem("Fresh Start");
		mb.getMenus().add(options);
		options.getItems().addAll(exit, re);
		Label bankerTotal = new Label("Banker total");
		Label intBankerTotal = new Label("0");
		Label playerTotal = new Label("Player total");
		Label intPlayerTotal = new Label("0");
		Label winnings = new Label("Winnings");
		Label intWinnings = new Label("$0.0");
		Label balance = new Label("Balance");
		Label intBalance = new Label("$" + String.valueOf(this.currentBalance));
		Label round = new Label("Round");
		Label roundNumber = new Label(String.valueOf(this.roundNumber));
		// intBalance.setT
		Region spacing1 = new Region();
		spacing1.setPrefHeight(20);
		Region spacing2 = new Region();
		spacing2.setPrefHeight(20);
		Region spacing3 = new Region();
		spacing3.setPrefHeight(20);
		Region sp4 = new Region();
		sp4.setPrefHeight(20);
		VBox totalMenu = new VBox(bankerTotal, intBankerTotal, spacing1, playerTotal, intPlayerTotal, spacing2,
				winnings, intWinnings, spacing3, balance, intBalance, sp4, round, roundNumber);

		// Card test = new Card("Spades", 13);

		ImageView playerCardOneView = new ImageView();
		ImageView playerCardTwoView = new ImageView();
		ImageView playerCardThreeView = new ImageView();
		ImageView bankerCardOneView = new ImageView();
		ImageView bankerCardTwoView = new ImageView();
		ImageView bankerCardThreeView = new ImageView();

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
		Region space5 = new Region();
		space5.setPrefWidth(45);
		Region space6 = new Region();
		space6.setPrefWidth(20);
		Region space7 = new Region();
		space7.setPrefWidth(20);
		HBox cards1 = new HBox(playerCardOneView, playerCardTwoView, playerCardThreeView);
		HBox cards2 = new HBox(bankerCardOneView, bankerCardTwoView, bankerCardThreeView);
		Label playerCards = new Label("Player");
		Label bankerCards = new Label("Banker");
		VBox player = new VBox(playerCards, cards1);
		VBox banker = new VBox(bankerCards, cards2);
		HBox centerScreen = new HBox(totalMenu, space6, player, space7, banker);
		centerScreen.setAlignment(Pos.CENTER);
		pane.setLeft(centerScreen);
		// totalMenu.setAlignment(Pos.CENTER);
		pane.setTop(mb);
		pane.setBottom(bottomBar);
		Scene scene = new Scene(pane, 700, 700);
		scene.getStylesheets().add("styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		// button functions below
		playButton.setOnAction(event -> {
			BaccaratGameLogic logic = new BaccaratGameLogic();
			String betOn = "";
			double currentBet = 0;
			try {
				if (playerBetAmount.getText() == null && tieBetAmount.getText() == null) {
					currentBet = Integer.valueOf(bankerBetAmount.getText());
					betOn = "B";
				} else if (playerBetAmount.getText() == null && bankerBetAmount.getText() == null) {
					currentBet = Integer.valueOf(tieBetAmount.getText());
					betOn = "T";
				} else {
					currentBet = Integer.valueOf(playerBetAmount.getText());
					betOn = "P";
				}
			} catch (NumberFormatException e) {
				System.out.println("Must enter a bet");
				event.consume();
				return;
				// TODO: handle exception
			}

			if (currentBet <= this.currentBalance) {
				this.theDealer.shuffleDeck();
				this.playerHand = this.theDealer.dealHand();
				this.bankerHand = this.theDealer.dealHand();
				intBankerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
				intPlayerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
				playerCardOneView.setImage(null);
				playerCardTwoView.setImage(null);
				playerCardThreeView.setImage(null);
				bankerCardOneView.setImage(null);
				bankerCardTwoView.setImage(null);
				bankerCardThreeView.setImage(null);

				try {
					InputStream playerCardOneStream = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/resources/cardImages"
									+ getCardImage(this.playerHand.get(0)));
					InputStream playerCardTwoStream = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/resources/cardImages"
									+ getCardImage(this.playerHand.get(1)));
					InputStream bankerCardOneStream = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/resources/cardImages"
									+ getCardImage(this.bankerHand.get(0)));
					InputStream bankerCardTwoStream = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/resources/cardImages"
									+ getCardImage(this.bankerHand.get(1)));
					Image playerCardOne = new Image(playerCardOneStream);
					Image playerCardTwo = new Image(playerCardTwoStream);
					Image bankerCardOne = new Image(bankerCardOneStream);
					Image bankerCardTwo = new Image(bankerCardTwoStream);
					playerCardOneView.setImage(playerCardOne);
					playerCardTwoView.setImage(playerCardTwo);
					bankerCardOneView.setImage(bankerCardOne);
					bankerCardTwoView.setImage(bankerCardTwo);
					// player
					if (logic.evaluatePlayerDraw(this.playerHand)) {
						this.playerHand.add(this.theDealer.drawOne());
						InputStream playerCardThreeStream = new FileInputStream(
								System.getProperty("user.dir") + "/src/main/resources/cardImages"
										+ getCardImage(this.playerHand.get(2)));
						Image cardThree = new Image(playerCardThreeStream);
						playerCardThreeView.setImage(cardThree);
						intPlayerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
					}

					// banker
					if (this.playerHand.size() > 2) { // player drew
						if (logic.evaluateBankerDraw(this.playerHand, this.playerHand.get(2))) {
							this.bankerHand.add(this.theDealer.drawOne());
							InputStream bankerCardThreeStream = new FileInputStream(
									System.getProperty("user.dir") + "/src/main/resources/cardImages"
											+ getCardImage(this.bankerHand.get(2)));
							Image bankerCardThree = new Image(bankerCardThreeStream);
							bankerCardThreeView.setImage(bankerCardThree);
							intBankerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
						}
					} else { // player didnt draw
						if (logic.evaluateBankerDraw(this.playerHand, null)) {
							this.bankerHand.add(this.theDealer.drawOne());
							InputStream bankerCardThreeStream = new FileInputStream(
									System.getProperty("user.dir") + "/src/main/resources/cardImages"
											+ getCardImage(this.bankerHand.get(2)));
							Image bankerCardThree = new Image(bankerCardThreeStream);
							bankerCardThreeView.setImage(bankerCardThree);
							intBankerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
						}
					}
				} catch (FileNotFoundException error) {
					error.printStackTrace();
					System.out.println("File not found");
					// TODO: handle exception
				} catch (IOException error) {
					error.printStackTrace();
					System.out.println("IO Error");
				} finally {
					String whoWon = logic.handTotal(this.bankerHand) > logic.handTotal(this.playerHand) ? "B"
							: logic.handTotal(this.bankerHand) < logic.handTotal(this.playerHand) ? "P" : "T";
					System.out.println("who won: " + whoWon + "\nbet on: " + betOn);
					if (whoWon.equals(betOn) && whoWon.equals("T")) {
						this.currentBalance += (currentBet * 8);
						this.totalWinnings += (currentBet * 8);
					} else if (whoWon.equals(betOn) && whoWon.equals("P")) {
						this.currentBalance += currentBet;
						this.totalWinnings += currentBet;
					} else if (whoWon.equals(betOn) && whoWon.equals("B")) {
						this.currentBalance += (currentBet * 0.95);
						this.totalWinnings += (currentBet * 0.95);
					} else if (whoWon.equals("T")) {
						// do nothing its a push
					} else {
						this.currentBalance -= currentBet;
					}
					// if tie player banker lose no money
					intBalance.setText("$" + String.valueOf(this.currentBalance));
					intWinnings.setText("$" + String.valueOf(this.totalWinnings));
					this.roundNumber++;
					roundNumber.setText(String.valueOf(this.roundNumber));
					for (int i = 0; i < this.playerHand.size(); i++) {
						System.out.println(
								"Player Hand: " + this.playerHand.get(i).value + " of " + this.playerHand.get(i).suite);
					}
					for (int i = 0; i < this.bankerHand.size(); i++) {
						System.out.println("Banker Hand: " + this.bankerHand.get(i).value + " of "
								+ this.bankerHand.get(i).suite);
					}
					System.out.println("Round: " + this.roundNumber);
				}
			} else {
				System.out.println("DONT HAVE ENOUGH BREAD U GAMBLING DEGENERATE");
			}
		});
		playerBetAmount.textProperty().addListener((observable, oldVal, newVal) -> {
			playButton.setDisable(false);
		});
		bankerBetAmount.textProperty().addListener((observable, oldVal, newVal) -> {
			playButton.setDisable(false);
		});
		tieBetAmount.textProperty().addListener((observable, oldVal, newVal) -> {
			playButton.setDisable(false);
		});
		playerBet.setOnAction(e -> {
			playerBetAmount.setPromptText("");
			playerBetAmount.setDisable(false);
			tieBetAmount.setPromptText("$0");
			tieBetAmount.setDisable(true);
			tieBetAmount.setText(null);
			bankerBetAmount.setPromptText("$0");
			bankerBetAmount.setDisable(true);
			bankerBetAmount.setText(null);
		});
		tieBet.setOnAction(e -> {
			playerBetAmount.setPromptText("$0");
			playerBetAmount.setDisable(true);
			playerBetAmount.setText(null);
			tieBetAmount.setPromptText("");
			tieBetAmount.setDisable(false);
			bankerBetAmount.setPromptText("$0");
			bankerBetAmount.setDisable(true);
			bankerBetAmount.setText(null);
		});
		bankerBet.setOnAction(e -> {
			playerBetAmount.setPromptText("$0");
			playerBetAmount.setDisable(true);
			playerBetAmount.setText(null);
			tieBetAmount.setPromptText("$0");
			tieBetAmount.setDisable(true);
			tieBetAmount.setText(null);
			bankerBetAmount.setPromptText("");
			bankerBetAmount.setDisable(false);
		});
		exit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		re.setOnAction(e -> {
			this.currentBalance = 100;
			this.totalWinnings = 0;
			this.bankerHand.clear();
			this.playerHand.clear();
			this.roundNumber = 1;
			intWinnings.setText("$" + String.valueOf(this.totalWinnings));
			intBalance.setText("$" + String.valueOf(this.currentBalance));
			roundNumber.setText(String.valueOf(this.roundNumber));
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
			// playButton.fire();
		});

	}

}
