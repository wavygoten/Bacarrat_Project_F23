import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaccaratGame extends Application {
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private double currentBet;
	private double totalWinnings;

	public double evaluateWinnings() {

		return 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	// feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Adrian Lopez Homework 3");
		VBox mainMenu = new VBox();
		TextField welcome = new TextField("Welcome to Baccarat");
		welcome.setStyle("-fx-control-inner-background: derive(-fx-base,80%);");

		TextField bet = new TextField("0$");
		Button info = new Button("?");
		Button playGame = new Button("Play Game");
		mainMenu.getChildren().addAll(welcome, bet, playGame);
		info.setStyle(
				"-fx-background-radius: 50em; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px;");
		bet.setStyle("-fx-pref-width: 50; -fx-pref-height: 40;");
		BorderPane pane = new BorderPane();
		pane.setStyle(
				"-fx-border-color: #00008B;" + // navy blue border
						"-fx-background-color: linear-gradient(to bottom, #3498db, #ffffff);" + // blue gradient bg
						"-fx-border-width: 4px;" // thickness of border
		);
		pane.setCenter(mainMenu);
		pane.setBottom(info);
		Scene scene = new Scene(pane, 700, 700);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
