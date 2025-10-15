import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chapter1_Challenge_1_1 extends Application {

    @Override
    public void start(Stage stage) {
        // --- UI Controls ---
        Label title = new Label("🔐 The Cryptic Message Decoder");
        Label instruction = new Label("Enter a positive integer:");
        TextField inputField = new TextField();
        Button decodeButton = new Button("Decode Message");
        Label resultLabel = new Label();

        // --- Button Action ---
        decodeButton.setOnAction(e -> {
            try {
                int number = Integer.parseInt(inputField.getText().trim());

                // --- Perform the operations ---
                int digits = (int) Math.log10(number) + 1;
                int firstDigit = (int) (number / Math.pow(10, digits - 1));
                int lastDigit = number % 10;
                int secondDigit = (int) (number / Math.pow(10, digits - 2)) % 10;
                int secondLastDigit = (number / 10) % 10;

                int product = firstDigit * lastDigit;
                int sum = secondDigit + secondLastDigit;

                String finalCode = "" + product + sum;

                resultLabel.setText("The decrypted code is: " + finalCode);
            } catch (Exception ex) {
                resultLabel.setText("Please enter a valid positive integer.");
            }
        });

        // --- Layout ---
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(title, instruction, inputField, decodeButton, resultLabel);

        Scene scene = new Scene(layout, 350, 220);
        stage.setTitle("Chapter1_Challenge_1_1 - Cryptic Message Decoder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
