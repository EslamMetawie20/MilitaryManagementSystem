package org.example;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NumberedTextArea extends StackPane {
    private TextArea textArea;
    private VBox lineNumbers;

    public NumberedTextArea() {
        textArea = new TextArea();
        lineNumbers = new VBox();

        lineNumbers.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 5;");
        lineNumbers.setPrefWidth(40);

        textArea.textProperty().addListener((obs, oldText, newText) -> updateLineNumbers());

        ScrollPane scrollPane = new ScrollPane(lineNumbers);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1);

        StackPane.setAlignment(scrollPane, javafx.geometry.Pos.TOP_LEFT);

        getChildren().addAll(textArea, scrollPane);
    }

    private void updateLineNumbers() {
        lineNumbers.getChildren().clear();
        int lines = textArea.getText().split("\n").length;
        for (int i = 1; i <= lines; i++) {
            Text lineNumber = new Text(String.valueOf(i));
            lineNumber.setStyle("-fx-font-size: 12px; -fx-fill: gray;");
            lineNumbers.getChildren().add(new TextFlow(lineNumber));
        }
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
