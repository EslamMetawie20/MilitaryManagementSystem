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
        nameColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        idColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());
        addressColumn.setMinWidth(150);

        TableColumn<SoldierRow, String> weaponColumn = new TableColumn<>("Weapon");
        weaponColumn.setCellValueFactory(data -> data.getValue().weaponProperty());
        weaponColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());
        phoneColumn.setMinWidth(150);

        TableColumn<SoldierRow, String> relativesColumn = new TableColumn<>("Relatives");
        relativesColumn.setCellValueFactory(data -> data.getValue().relativesProperty());
        relativesColumn.setMinWidth(150);

        TableColumn<SoldierRow, String> punishmentColumn = new TableColumn<>("Punishment");
        punishmentColumn.setCellValueFactory(data -> data.getValue().punishmentProperty());
        punishmentColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> grantColumn = new TableColumn<>("Grant");
        grantColumn.setCellValueFactory(data -> data.getValue().grantProperty());
        grantColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> militaryNumberColumn = new TableColumn<>("Military Number");
        militaryNumberColumn.setCellValueFactory(data -> data.getValue().militaryNumberProperty());
        militaryNumberColumn.setMinWidth(150);

        // إضافة الأعمدة إلى الجدول
        table.getColumns().addAll(
                nameColumn, idColumn, addressColumn, weaponColumn,
                phoneColumn, relativesColumn, punishmentColumn,
                grantColumn, militaryNumberColumn
        );

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
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Soldiers Management");
        primaryStage.show();

        // إضافة حدث زر "Add Soldier"
        addButton.setOnAction(e -> {
            AddSoldierView addView = new AddSoldierView(soldiers);
            addView.display();
        });

        // إضافة حدث زر "Delete Soldier"
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
