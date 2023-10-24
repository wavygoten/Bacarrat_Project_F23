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




		TextField bankerTotal = new TextField("Banker total");
		bankerTotal.setEditable(false);
		TextField intBankerTotal = new TextField("$0");
		intBankerTotal.setEditable(false);
		TextField playerTotal = new TextField("Player total");
		playerTotal.setEditable(false);
		TextField intPlayerTotal = new TextField("$0");
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
		Button options = new Button("Options");
		VBox totalMenu = new VBox(bankerTotal, intBankerTotal, spacing1, playerTotal, intPlayerTotal, spacing2, winnings, intWinnings, spacing3, balance, intBalance);

		totalMenu.setAlignment(Pos.CENTER);
		pane.setLeft(totalMenu);
		pane.setRight(options);
		pane.setBottom(bottomBar);
		bankerTotal.getStyleClass().add("transparent_border");
		playerTotal.getStyleClass().add("transparent_border");
		winnings.getStyleClass().add("transparent_border");
		balance.getStyleClass().add("transparent_border");




		Scene scene = new Scene(pane, 700,700);
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
