import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
	public String getCardImage(Card card){
		String cardValue = Integer.toString(card.value); // convert card integer value to string
		String cardString = "/" + cardValue + card.suite + ".png"; // concatenate int string to suite string to be used as a file path
		return cardString;
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

		Button playerBetButton = new Button("Player Bet");
		playerBetButton.getStyleClass().add("betbutton");
		TextField playerBetAmount = new TextField("$0");

		Button tieBetButton = new Button("Tie Bet");
		TextField tieBetAmount = new TextField("$0");
		Button bankerBetButton = new Button("Banker Bet");
		TextField bankerBetAmount = new TextField("$0");
		Button playButton = new Button("Play Game");
		playButton.getStyleClass().add("playbutton");
		Region spacing4 = new Region();
		spacing4.setPrefWidth(135);


		VBox playerVbox = new VBox(playerBetButton, playerBetAmount);
		VBox tieVbox = new VBox(tieBetButton, tieBetAmount);
		VBox bankerVbox = new VBox(bankerBetButton, bankerBetAmount);
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


		pane.setRight(options);
		pane.setBottom(bottomBar);
		bankerTotal.getStyleClass().add("transparent_border");
		playerTotal.getStyleClass().add("transparent_border");
		winnings.getStyleClass().add("transparent_border");
		balance.getStyleClass().add("transparent_border");

		Card test = new Card("Spades", 13);

		String filePath = System.getProperty("user.dir") + "/src/main/resources/cardImages" + getCardImage(test);

		InputStream playerCardOneStream = new FileInputStream(filePath);
		InputStream playerCardTwoStream = new FileInputStream(filePath);
		Image cardOne = new Image(playerCardOneStream);
		Image cardTwo = new Image(playerCardTwoStream);
		ImageView playerCardOneView = new ImageView();
		ImageView playerCardTwoView = new ImageView();

		playerCardTwoView.setImage(cardTwo);
		playerCardOneView.setImage(cardOne);

		playerCardTwoView.setX(10);
		playerCardTwoView.setY(10);
		playerCardTwoView.setFitWidth(50);
		playerCardTwoView.setPreserveRatio(true);

		playerCardOneView.setX(10);
		playerCardOneView.setY(10);
		playerCardOneView.setFitWidth(50);
		playerCardOneView.setPreserveRatio(true);

		Region space5 = new Region();
		space5.setPrefWidth(45);
		Region space6 = new Region();
		space6.setPrefWidth(20);
		Region space7 = new Region();
		space7.setPrefWidth(20);
		HBox cards1 = new HBox(playerCardOneView,space5, playerCardTwoView);
		HBox cards2 = new HBox();
		TextField playerCards = new TextField("Player");
		TextField bankerCards = new TextField("Banker");
		VBox player = new VBox(playerCards, cards1);
		VBox banker = new VBox(bankerCards, cards2);
		HBox centerScreen = new HBox(totalMenu,space6, player,space7, banker);
		centerScreen.setAlignment(Pos.CENTER);
		pane.setLeft(centerScreen);




		Scene scene = new Scene(pane, 700,700);
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
