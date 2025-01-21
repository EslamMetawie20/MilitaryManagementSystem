package org.example;

import javafx.geometry.NodeOrientation;
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
        root.setSpacing(10);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        TableView<SoldierRow> table = new TableView<>();

        TableColumn<SoldierRow, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<SoldierRow, String> idColumn = new TableColumn<>("الرقم القومي");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());

        TableColumn<SoldierRow, String> addressColumn = new TableColumn<>("العنوان");
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());

        TableColumn<SoldierRow, String> weaponColumn = new TableColumn<>("السلاح");
        weaponColumn.setCellValueFactory(data -> data.getValue().weaponProperty());

        TableColumn<SoldierRow, String> phoneColumn = new TableColumn<>("رقم الهاتف");
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());

        TableColumn<SoldierRow, String> relativesColumn = new TableColumn<>("الأقارب");
        relativesColumn.setCellValueFactory(data -> data.getValue().relativesProperty());

        TableColumn<SoldierRow, String> punishmentColumn = new TableColumn<>("العقوبات");
        punishmentColumn.setCellValueFactory(data -> data.getValue().punishmentProperty());

        TableColumn<SoldierRow, String> grantColumn = new TableColumn<>("المنح");
        grantColumn.setCellValueFactory(data -> data.getValue().grantProperty());

        TableColumn<SoldierRow, String> militaryNumberColumn = new TableColumn<>("الرقم العسكري");
        militaryNumberColumn.setCellValueFactory(data -> data.getValue().militaryNumberProperty());

        TableColumn<SoldierRow, String> dateOfbirthColumn = new TableColumn<>("تاريخ الميلاد");
        dateOfbirthColumn.setCellValueFactory(data -> data.getValue().dateOfBirthProperty());

        TableColumn<SoldierRow, String> barcodeColumn = new TableColumn<>("الباركود");
        barcodeColumn.setCellValueFactory(data -> data.getValue().barcodeProperty());
        table.getColumns().add(barcodeColumn);

        table.getColumns().addAll(
                nameColumn,
                idColumn,
                addressColumn,
                dateOfbirthColumn,
                weaponColumn,
                phoneColumn,
                relativesColumn,
                punishmentColumn,
                grantColumn,
                militaryNumberColumn,
                barcodeColumn
        );

        Button closeButton = new Button("إغلاق");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(table, closeButton);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("عرض المجندين");
        stage.show();
    }
}
