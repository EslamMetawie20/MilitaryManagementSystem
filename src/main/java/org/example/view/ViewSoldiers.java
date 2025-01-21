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

        // إنشاء الجدول لعرض الجنود
        TableView<SoldierRow> table = new TableView<>();

        // أعمدة الجدول
        TableColumn<SoldierRow, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<SoldierRow, String> idColumn = new TableColumn<>("National ID");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());

        TableColumn<SoldierRow, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());

        TableColumn<SoldierRow, String> weaponColumn = new TableColumn<>("Weapon");
        weaponColumn.setCellValueFactory(data -> data.getValue().weaponProperty());

        TableColumn<SoldierRow, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());

        TableColumn<SoldierRow, String> relativesColumn = new TableColumn<>("Relatives");
        relativesColumn.setCellValueFactory(data -> data.getValue().relativesProperty());

        TableColumn<SoldierRow, String> punishmentColumn = new TableColumn<>("Punishment");
        punishmentColumn.setCellValueFactory(data -> data.getValue().punishmentProperty());

        TableColumn<SoldierRow, String> grantColumn = new TableColumn<>("Grant");
        grantColumn.setCellValueFactory(data -> data.getValue().grantProperty());

        TableColumn<SoldierRow, String> militaryNumberColumn = new TableColumn<>("Military Number");
        militaryNumberColumn.setCellValueFactory(data -> data.getValue().militaryNumberProperty());

        // إضافة جميع الأعمدة إلى الجدول
        table.getColumns().addAll(
                nameColumn,
                idColumn,
                addressColumn,
                weaponColumn,
                phoneColumn,
                relativesColumn,
                punishmentColumn,
                grantColumn,
                militaryNumberColumn
        );

        // زر الإغلاق
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        // إضافة الجدول وزر الإغلاق إلى الجذر
        root.getChildren().addAll(table, closeButton);

        // إعدادات المشهد
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("View Soldiers");
        stage.show();
    }
}
