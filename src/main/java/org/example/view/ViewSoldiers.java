package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewSoldiers {
    public void display() {
        Stage stage = new Stage();
        VBox root = new VBox();

        // جدول لعرض الجنود
        TableView<String> table = new TableView<>();
        TableColumn<String, String> nameColumn = new TableColumn<>("Name");
        TableColumn<String, String> idColumn = new TableColumn<>("National ID");

        table.getColumns().addAll(nameColumn, idColumn);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(table, closeButton);

        // إعدادات النافذة
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("View Soldiers");
        stage.show();
    }
}
