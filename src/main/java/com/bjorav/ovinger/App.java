package com.bjorav.ovinger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private DeckOfCards deck;
    private List<PlayingCard> currentHand;

    private TextArea handDisplay;
    private Label sumOfFacesLabel;
    private Label heartsLabel;
    private Label flushLabel;
    private Label queenOfSpadesLabel;

    @Override
    public void start(Stage primaryStage) {
        deck = new DeckOfCards();
        deck.shuffle();

        BorderPane root = new BorderPane();


        handDisplay = new TextArea();
        handDisplay.setEditable(false);
        handDisplay.setPrefRowCount(10);
        handDisplay.setPrefColumnCount(30);
        //handDisplay.setText("Display the hand of cards here in whatever way you like.");
        root.setCenter(handDisplay);

        //buttons r
        VBox buttonBox = new VBox(10);
        buttonBox.setPadding(new Insets(10));
        Button dealButton = new Button("Deal hand");
        Button checkButton = new Button("Check hand");
        buttonBox.getChildren().addAll(dealButton, checkButton);
        root.setRight(buttonBox);

        //buttons bottom
        HBox statsBox = new HBox(20);
        statsBox.setPadding(new Insets(10));
        sumOfFacesLabel = new Label("Sum of the faces: ");
        heartsLabel = new Label("Cards of hearts: ");
        flushLabel = new Label("Flush: ");
        queenOfSpadesLabel = new Label("Queen of spades: ");
        statsBox.getChildren().addAll(
                sumOfFacesLabel,
                heartsLabel,
                flushLabel,
                queenOfSpadesLabel
        );
        root.setBottom(statsBox);

        //button actions
        dealButton.setOnAction(e -> dealHand());
        checkButton.setOnAction(e -> checkHand());


        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Game GUI");
        primaryStage.show();
    }

    private void dealHand() {
        currentHand = deck.dealHand(5);

        //display in text (no time for images)
        StringBuilder sb = new StringBuilder();
        for (PlayingCard card : currentHand) {
            sb.append(card.getAsString()).append(" ");
        }
        handDisplay.setText(sb.toString());

        //label reset
        sumOfFacesLabel.setText("Sum of the faces: ");
        heartsLabel.setText("Cards of hearts: ");
        flushLabel.setText("Flush: ");
        queenOfSpadesLabel.setText("Queen of spades: ");
    }

    private void checkHand() {
        if (currentHand == null || currentHand.isEmpty()) {
            return;
        }

        //sum of faces
        int sum = 0;
        for (PlayingCard card : currentHand) {
            sum += card.getFace();
        }
        sumOfFacesLabel.setText("Sum of the faces: " + sum);

        //cards of hearts
        StringBuilder hearts = new StringBuilder();
        for (PlayingCard card : currentHand) {
            if (card.getSuit() == 'H') {
                hearts.append(card.getAsString()).append(" ");
            }
        }
        heartsLabel.setText("Cards of hearts: " + hearts.toString());

        //check flush (all suits the same)
        char firstSuit = currentHand.get(0).getSuit();
        boolean isFlush = true;
        for (PlayingCard card : currentHand) {
            if (card.getSuit() != firstSuit) {
                isFlush = false;
                break;
            }
        }
        flushLabel.setText("Flush: " + (isFlush ? "Yes" : "No"));

        //check queen of spades
        boolean hasQueenOfSpades = false;
        for (PlayingCard card : currentHand) {
            if (card.getSuit() == 'S' && card.getFace() == 12) {
                hasQueenOfSpades = true;
                break;
            }
        }
        queenOfSpadesLabel.setText("Queen of spades: " + (hasQueenOfSpades ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
