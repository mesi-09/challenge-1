
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

import java.io.File;
import java.io.IOException;

public class Chapter1_Challenge_1_4 extends Application {

    @Override
    public void start(Stage stage) {
        // --- UI Components ---
        Label title = new Label("📄 Robust Config Reader (User Input)");
        title.setFont(Font.font("Arial Rounded MT Bold", 22));
        title.setTextFill(Color.DARKBLUE);

        Label versionLabel = new Label("Enter Config Version:");
        TextField versionField = new TextField();
        versionField.setPromptText("e.g., 1 or 2");

        Label pathLabel = new Label("Enter File Path:");
        TextField pathField = new TextField();
        pathField.setPromptText("e.g., C:\\folder\\file.txt");

        Button checkButton = new Button("✅ Validate Config");
        checkButton.setFont(Font.font("Arial", 14));

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setFont(Font.font("Consolas", 14));
        output.setPrefHeight(250);

        // --- Button Action ---
        checkButton.setOnAction(e -> {
            output.clear();
            validateConfig(versionField.getText().trim(), pathField.getText().trim(), output);
        });

        // --- Layout ---
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, versionLabel, versionField, pathLabel, pathField, checkButton, output);

        Scene scene = new Scene(layout, 600, 450);
        stage.setScene(scene);
        stage.setTitle("Chapter1_Challenge_1_4 - User Input Config Reader");
        stage.show();
    }

    private void validateConfig(String versionInput, String filePath, TextArea output) {
        try {
            // Validate version
            int version = Integer.parseInt(versionInput);
            if (version < 2) {
                throw new Exception("Config version too old!");
            }
            output.appendText("✅ Config version: " + version + "\n");

            // Validate file path
            if (filePath.isEmpty()) {
                throw new IOException("File path cannot be empty!");
            }

            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("The file at path does not exist: " + filePath);
            }

            output.appendText("✅ Config file exists: " + filePath + "\n");

        } catch (NumberFormatException nfe) {
            output.appendText("❌ Number format error: Version must be an integer!\n");
        } catch (IOException ioe) {
            output.appendText("❌ IO Error: " + ioe.getMessage() + "\n");
        } catch (Exception ex) {
            output.appendText("❌ " + ex.getMessage() + "\n");
        } finally {
            output.appendText("\n🔹 Config read attempt finished.\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

