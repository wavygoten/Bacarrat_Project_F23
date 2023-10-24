import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;


public class BaccaratGame extends Application {
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private double currentBet;
	private double totalWinnings;

	public double evaluateWinnings(){



		return 1;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Baccarat Game");
		BorderPane pane = new BorderPane();


		TextField bankerTotal = new TextField("Banker total");
		TextField intBankerTotal = new TextField("$0");
		TextField playerTotal = new TextField("Player total");
		TextField intPlayerTotal = new TextField("$0");
		TextField winnings = new TextField("Winnings");
		TextField intWinnings = new TextField("$0");
		TextField balance = new TextField("Balance");
		TextField intBalance = new TextField("$0");
		Button options = new Button("Options");
		VBox totalMenu = new VBox(bankerTotal, intBankerTotal, playerTotal, intPlayerTotal, winnings, intWinnings, balance, intBalance);
		totalMenu.setAlignment(Pos.CENTER);
		pane.setLeft(totalMenu);
		pane.setRight(options);
		bankerTotal.getStyleClass().add("welcome");



		Scene scene = new Scene(pane, 700,700);
		scene.getStylesheets().add("styles.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
