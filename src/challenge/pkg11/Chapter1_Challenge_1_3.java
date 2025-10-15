
package challenge.pkg11;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Chapter1_Challenge_1_3 extends Application {

    private int health = 100;
    private int currentRoom = 0;
    private final int TOTAL_ROOMS = 5;
    private final Random rand = new Random();

    @Override
    public void start(Stage stage) {
        Label title = new Label("🧙‍♂️ Dungeon Game Adventure");
        title.setFont(Font.font("Arial Rounded MT Bold", 22));
        title.setTextFill(Color.DARKRED);

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setFont(Font.font("Consolas", 14));
        output.setPrefHeight(300);
        output.setWrapText(true);

        TextField guessField = new TextField();
        guessField.setPromptText("Enter your guess (1-5) for monsters");

        Button nextRoomButton = new Button("Enter Next Room ⚔️");
        nextRoomButton.setFont(Font.font("Arial", 14));

        nextRoomButton.setOnAction(e -> enterNextRoom(output, guessField, nextRoomButton));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, output, guessField, nextRoomButton);

        Scene scene = new Scene(layout, 600, 450);
        stage.setScene(scene);
        stage.setTitle("Chapter1_Challenge_1_3 - Dungeon Game");
        stage.show();

        output.appendText("🏁 Welcome to the Dungeon Game!\n");
        output.appendText("You start with 100 health. Survive through 5 rooms!\n");
    }

    private void enterNextRoom(TextArea output, TextField guessField, Button nextRoomButton) {
        if (health <= 0 || currentRoom >= TOTAL_ROOMS) {
            output.appendText("\nGame over! Restart to play again.\n");
            nextRoomButton.setDisable(true);
            return;
        }

        currentRoom++;
        int event = rand.nextInt(3) + 1;
        output.appendText("\n🏰 Room " + currentRoom + ":\n");

        switch (event) {
            case 1:
                output.appendText("💀 It's a trap! You lose 20 health.\n");
                health -= 20;
                if (health < 0) health = 0;
                output.appendText("Your health: " + health + "\n");
                break;
            case 2:
                output.appendText("🧪 You found a healing potion! +15 health.\n");
                health += 15;
                if (health > 100) health = 100;
                output.appendText("Your health: " + health + "\n");
                break;
            case 3:
                final int monsterNumber = rand.nextInt(5) + 1;
                output.appendText("👹 A monster appears! Guess a number (1-5) to defeat it.\n");
                guessField.setDisable(false);
                nextRoomButton.setDisable(true);

                guessField.setOnAction(ae -> {
                    try {
                        int guess = Integer.parseInt(guessField.getText());
                        if (guess < 1 || guess > 5) {
                            output.appendText("⚠️ Please guess a number between 1 and 5.\n");
                            guessField.clear();
                            return;
                        }

                        if (guess == monsterNumber) {
                            output.appendText("🎯 Correct! You defeated the monster!\n");
                            guessField.clear();
                            guessField.setDisable(true);
                            nextRoomButton.setDisable(false);
                            output.appendText("Your health: " + health + "\n");
                            if (currentRoom == TOTAL_ROOMS && health > 0) victory(output, nextRoomButton);
                        } else {
                            output.appendText("❌ Wrong! Try again.\n");
                            health -= 5;
                            if (health <= 0) {
                                output.appendText("💀 You were defeated by the monster!\n");
                                guessField.clear();
                                guessField.setDisable(true);
                                nextRoomButton.setDisable(true);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        output.appendText("⚠️ Enter a valid number!\n");
                        guessField.clear();
                    }
                });
                break;
        }

        if (health <= 0) {
            output.appendText("\n💀 You have been defeated! Game over.\n");
            nextRoomButton.setDisable(true);
        }

        if (currentRoom == TOTAL_ROOMS && health > 0 && event != 3) {
            victory(output, nextRoomButton);
        }
    }

    private void victory(TextArea output, Button nextRoomButton) {
        output.appendText("\n🎉 Congratulations! You escaped the dungeon!\n");
        output.appendText("Your final health: " + health + "\n");
        nextRoomButton.setDisable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

