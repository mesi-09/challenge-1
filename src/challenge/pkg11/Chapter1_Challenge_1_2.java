package challenge.pkg11;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Chapter1_Challenge_1_2 extends Application {

    @Override
    public void start(Stage stage) {
        // Title Label
        Label title = new Label("🎰 Lottery Number Analyzer");
        title.setFont(Font.font("Arial Rounded MT Bold", 22));
        title.setTextFill(Color.DARKBLUE);

        // Instruction Label
        Label instruction = new Label("Analyze predefined winning numbers below:");
        instruction.setFont(Font.font("Arial", 14));
        instruction.setTextFill(Color.DARKGREEN);

        // Result TextArea
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setFont(Font.font("Consolas", 14));
        resultArea.setStyle("-fx-control-inner-background: #f9f9f9; "
                + "-fx-text-fill: #333333; -fx-border-color: #ccc;");
        resultArea.setPrefHeight(260);

        // Analyze Button
        Button analyzeButton = new Button("🔍 Analyze Winning Numbers");
        analyzeButton.setFont(Font.font("Arial", 14));
        analyzeButton.setStyle("-fx-background-color: #0078D7; "
                + "-fx-text-fill: white; -fx-background-radius: 6;");
        analyzeButton.setOnMouseEntered(e -> analyzeButton.setStyle("-fx-background-color: #005FA3; "
                + "-fx-text-fill: white; -fx-background-radius: 6;"));
        analyzeButton.setOnMouseExited(e -> analyzeButton.setStyle("-fx-background-color: #0078D7; "
                + "-fx-text-fill: white; -fx-background-radius: 6;"));

        // Button Action
        analyzeButton.setOnAction(e -> {
            String[] winningNumbers = {
                "12-34-56-78-90",
                "33-44-11-66-22",
                "01-02-03-04-05"
            };

            StringBuilder output = new StringBuilder();
            double highestAverage = 0;
            String highestNumber = "";

            // Process each winning number
            for (String num : winningNumbers) {
                output.append("Analyzing: ").append(num).append("\n");

                // Remove dashes and get digits
                String cleaned = num.replace("-", "");
                char[] chars = cleaned.toCharArray();

                int[] digits = new int[chars.length];
                int sum = 0;
                for (int i = 0; i < chars.length; i++) {
                    digits[i] = Character.getNumericValue(chars[i]);
                    sum += digits[i];
                }

                double avg = (double) sum / digits.length;

                output.append("Digit Sum: ").append(sum)
                      .append(", Digit Average: ").append(String.format("%.1f", avg))
                      .append("\n");

                // Track highest average
                if (avg > highestAverage) {
                    highestAverage = avg;
                    highestNumber = num;
                }
            }

            // Final summary
            output.append("The winning number with the highest average is: ")
                  .append(highestNumber)
                  .append(" with an average of ")
                  .append(String.format("%.1f", highestAverage));

            resultArea.setText(output.toString());
        });

        // Layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(25));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E3F2FD, #BBDEFB);");
        layout.getChildren().addAll(title, instruction, analyzeButton, resultArea);

        // Scene Setup
        Scene scene = new Scene(layout, 520, 420);
        stage.setTitle("Chapter1_Challenge_1_2 - Lottery Number Analyzer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
