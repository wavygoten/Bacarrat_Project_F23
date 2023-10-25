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
		// intBalance.setT
		Region spacing1 = new Region();
		spacing1.setPrefHeight(20);
		Region spacing2 = new Region();
		spacing2.setPrefHeight(20);
		Region spacing3 = new Region();
		spacing3.setPrefHeight(20);
		VBox totalMenu = new VBox(bankerTotal, intBankerTotal, spacing1, playerTotal, intPlayerTotal, spacing2,
				winnings, intWinnings, spacing3, balance, intBalance);

		totalMenu.setAlignment(Pos.CENTER);
		pane.setLeft(totalMenu);
		pane.setTop(mb);
		pane.setBottom(bottomBar);
		Scene scene = new Scene(pane, 700, 700);
		scene.getStylesheets().add("styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		// button functions below
		playButton.setOnAction(e -> {
			BaccaratGameLogic logic = new BaccaratGameLogic();
			String betOn = "";
			double currentBet = 0;
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
			// System.out.println(betOn);
			// this.currentBet = playerBetAmount.getText() != null ?
			// Integer.valueOf(playerBetAmount.getText())
			// : tieBetAmount.getText() != null ? Integer.valueOf(tieBetAmount.getText())
			// : bankerBetAmount.getText() != null ?
			// Integer.valueOf(bankerBetAmount.getText()) : 0;
			this.theDealer.shuffleDeck();
			this.playerHand = this.theDealer.dealHand();
			this.bankerHand = this.theDealer.dealHand();
			// this.theDealer.print();
			intBankerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
			intPlayerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
			// check draw one
			// player
			if (logic.evaluatePlayerDraw(this.playerHand)) {
				this.playerHand.add(this.theDealer.drawOne());
				intPlayerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
			}

			// banker
			if (this.playerHand.size() > 2) {
				if (logic.evaluateBankerDraw(this.playerHand, this.playerHand.get(2))) {
					this.bankerHand.add(this.theDealer.drawOne());
					intBankerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
				}
			} else {
				if (logic.evaluateBankerDraw(this.playerHand, null)) {
					this.bankerHand.add(this.theDealer.drawOne());
					intBankerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
				}
			}

			// calculate who won and either append or dont append winnings to current
			// balance.
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
				this.currentBalance += (currentBet * 1.05);
				this.totalWinnings += (currentBet * 1.05);
			} else {
				this.currentBalance -= currentBet;
			}
			intBalance.setText("$" + String.valueOf(this.currentBalance));
			intWinnings.setText("$" + String.valueOf(this.totalWinnings));
			this.roundNumber++;

			for (int i = 0; i < this.playerHand.size(); i++) {
				System.out.println("Player Hand: " + this.playerHand.get(i).value);
			}
			for (int i = 0; i < this.bankerHand.size(); i++) {
				System.out.println("Banker Hand: " + this.bankerHand.get(i).value);
			}
			System.out.println("Round: " + this.roundNumber);

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
			// playButton.fire();
		});

	}

}
