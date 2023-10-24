import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private double currentBalance = 0;
	private double currentBet = 0;
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

		TextField playerBet = new TextField("Player Bet");
		TextField playerBetAmount = new TextField("$0");
		TextField tieBet = new TextField("Tie Bet");
		TextField tieBetAmount = new TextField("$0");
		TextField bankerBet = new TextField("Banker Bet");
		TextField bankerBetAmount = new TextField("$0");
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
		TextField bankerTotal = new TextField("Banker total");
		bankerTotal.setEditable(false);
		TextField intBankerTotal = new TextField("0");
		intBankerTotal.setEditable(false);
		TextField playerTotal = new TextField("Player total");
		playerTotal.setEditable(false);
		TextField intPlayerTotal = new TextField("0");
		intPlayerTotal.setEditable(false);
		TextField winnings = new TextField("Winnings");
		winnings.setEditable(false);
		TextField intWinnings = new TextField("$0");
		intWinnings.setEditable(false);
		TextField balance = new TextField("Balance");
		balance.setEditable(false);
		TextField intBalance = new TextField("$0");
		intBalance.setEditable(false);
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
		// pane.setRight(options);
		pane.setBottom(bottomBar);
		bankerTotal.getStyleClass().add("transparent_border");
		playerTotal.getStyleClass().add("transparent_border");
		winnings.getStyleClass().add("transparent_border");
		balance.getStyleClass().add("transparent_border");

		Scene scene = new Scene(pane, 700, 700);
		scene.getStylesheets().add("styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		// button functions below
		playButton.setOnAction(e -> {
			BaccaratGameLogic logic = new BaccaratGameLogic();

			this.theDealer.shuffleDeck();
			// fix error here
			this.playerHand = this.theDealer.dealHand(); // 4

			this.bankerHand = this.theDealer.dealHand(); // 3
			// this.theDealer.print();
			// for (int i = 0; i < this.playerHand.size(); i++) {
			// System.out.println(this.playerHand.get(i).value);
			// }
			intBankerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
			intPlayerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));
			// check draw one
			// player
			if (logic.evaluatePlayerDraw(this.playerHand)) {
				this.playerHand.add(this.theDealer.drawOne());
				intPlayerTotal.setText(String.valueOf(logic.handTotal(this.bankerHand)));

			}

			// banker
			if (this.playerHand.size() > 2) {
				if (logic.evaluateBankerDraw(this.playerHand, this.playerHand.get(2))) {
					this.bankerHand.add(this.theDealer.drawOne());
					intBankerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
				}
			} else {
				if (logic.evaluateBankerDraw(this.playerHand, null)) {
					this.bankerHand.add(this.theDealer.drawOne());
					intBankerTotal.setText(String.valueOf(logic.handTotal(this.playerHand)));
				}
			}

		});

		exit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		re.setOnAction(e -> {
			this.currentBalance = 100;
			this.currentBet = 0;
			this.totalWinnings = 0;
			this.bankerHand.clear();
			this.playerHand.clear();
			playButton.fire();
		});

	}

	private void play() {

		// bankTextField.setText(String.valueOf(bankerTotal));
		// playerTextField.setText(String.valueOf(playerTotal));

	}

	private void freshRestart() {

		this.currentBalance = 100;
		this.theDealer.generateDeck();
		this.theDealer.shuffleDeck();
		this.currentBet = 0;
		this.totalWinnings = 0;
		this.bankerHand.clear();
		this.playerHand.clear();
		// this.play();
		// do new play game

	}
	// private BorderPane setupWelcomePage() {

	// BorderPane pane = new BorderPane();
	// pane.getStyleClass().add("pane");

	// return pane;
	// }

}
