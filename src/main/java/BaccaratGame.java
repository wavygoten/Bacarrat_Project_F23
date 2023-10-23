import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaccaratGame extends Application {
	private ArrayList<Card> playerHand = new ArrayList<>();
	private ArrayList<Card> bankerHand = new ArrayList<>();
	private BaccaratDealer theDealer = new BaccaratDealer();
	private double currentBalance = 0;
	private double currentBet = 0;
	private double totalWinnings = 0;
	private BaccaratGameLogic logic = new BaccaratGameLogic();

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
		TextField bankerTotal = new TextField("Banker total");
		TextField intBankerTotal = new TextField("$0");
		TextField playerTotal = new TextField("Player total");
		TextField intPlayerTotal = new TextField("$0");
		TextField winnings = new TextField("Winnings");
		TextField intWinnings = new TextField("$0");
		TextField balance = new TextField("Balance");
		TextField intBalance = new TextField("$0");
		// Button Exit = new Button("Exit");
		// Button Re = new Button("Fresh Start");
		MenuBar mb = new MenuBar();
		Menu options = new Menu("Options");
		MenuItem exit = new MenuItem("Exit");
		MenuItem re = new MenuItem("Restart");
		// Menu re = new Menu("Restart");
		exit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		re.setOnAction(e -> freshRestart());
		mb.getMenus().add(options);
		options.getItems().addAll(exit, re);

		// HBox topBar = new HBox(tb);
		VBox leftSide = new VBox(bankerTotal, intBankerTotal, playerTotal, intPlayerTotal, winnings, intWinnings,
				balance, intBalance);
		HBox gamePane = new HBox();
		HBox bottomBar = new HBox();
		// totalMenu.setAlignment(Pos.CENTER);
		BorderPane pane = new BorderPane(null, mb, gamePane, bottomBar, leftSide);

		Scene scene = new Scene(pane, 700, 700);
		scene.getStylesheets().add("styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void play() {
		this.theDealer.generateDeck();
		this.bankerHand = this.theDealer.dealHand();
		this.playerHand = this.theDealer.dealHand();
		int playerTotal = logic.handTotal(playerHand);
		int bankerTotal = logic.handTotal(bankerHand);

	}

	private void freshRestart() {

		this.currentBalance = 100;
		this.theDealer.generateDeck();
		this.theDealer.shuffleDeck();
		this.currentBet = 0;
		this.totalWinnings = 0;
		this.bankerHand.clear();
		this.playerHand.clear();
		this.play();
		// do new play game

	}
	// private BorderPane setupWelcomePage() {

	// BorderPane pane = new BorderPane();
	// pane.getStyleClass().add("pane");

	// return pane;
	// }

}
