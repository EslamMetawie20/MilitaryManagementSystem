package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends Application {

    private ObservableList<SoldierRow> soldiers = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // إنشاء الجدول
        TableView<SoldierRow> table = new TableView<>(soldiers);

        // إنشاء الأعمدة
        TableColumn<SoldierRow, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<SoldierRow, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());

        TableColumn<SoldierRow, String> weaponColumn = new TableColumn<>("Weapon");
        weaponColumn.setCellValueFactory(data -> data.getValue().weaponProperty());

        TableColumn<SoldierRow, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());

        table.getColumns().addAll(nameColumn, idColumn, weaponColumn, phoneColumn);

        // إعداد الجدول ليعرض فقط الأعمدة الفارغة إذا لم يكن هناك بيانات
        table.setPlaceholder(new Label("No soldiers available"));

        // إنشاء الأزرار
        Button addButton = new Button("Add Soldier");
        Button deleteButton = new Button("Delete Soldier");

        // ترتيب الأزرار في الأسفل
        HBox buttonBox = new HBox(10, addButton, deleteButton);
        buttonBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // إنشاء التخطيط الرئيسي
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonBox);

        // إعداد النافذة
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Soldiers Management");
        primaryStage.show();

        // الأحداث
        addButton.setOnAction(e -> {
            AddSoldierView addView = new AddSoldierView(soldiers);
            addView.display();
        });

        deleteButton.setOnAction(e -> {
            SoldierRow selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                soldiers.remove(selected);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a soldier to delete.");
                alert.showAndWait();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
