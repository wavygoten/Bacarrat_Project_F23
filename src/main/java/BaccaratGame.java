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
		welcome.getStyleClass().add("welcome");
		TextField bet = new TextField("0$");
		bet.getStyleClass().add("bet");
		Button info = new Button("?");
		info.getStyleClass().add("info");
		Button playGame = new Button("Play Game");
		mainMenu.getChildren().addAll(welcome, bet, playGame);
		BorderPane pane = new BorderPane();
		pane.getStyleClass().add("pane");
		pane.setCenter(mainMenu);
		pane.setBottom(info);
		Scene scene = new Scene(pane, 700, 700);
		scene.getStylesheets().add("styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
