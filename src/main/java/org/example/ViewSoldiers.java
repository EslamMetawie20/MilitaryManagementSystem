package org.example;

import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import org.example.SoldierRow;

import java.io.File;

public class ViewSoldiers {
    public void display() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(10);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        stage.setMaximized(true);
        TableView<SoldierRow> table = new TableView<>();

        // أعمدة الجدول
        TableColumn<SoldierRow, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        nameColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> idColumn = new TableColumn<>("الرقم القومي");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        idColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> addressColumn = new TableColumn<>("العنوان");
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());
        addressColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> weaponColumn = new TableColumn<>("السلاح");
        weaponColumn.setCellValueFactory(data -> data.getValue().weaponProperty());
        weaponColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> phoneColumn = new TableColumn<>("رقم الهاتف");
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());
        phoneColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> relativesColumn = new TableColumn<>("الأقارب");
        relativesColumn.setCellValueFactory(data -> data.getValue().relativesProperty());
        relativesColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> punishmentColumn = new TableColumn<>("العقوبات");
        punishmentColumn.setCellValueFactory(data -> data.getValue().punishmentProperty());
        punishmentColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> grantColumn = new TableColumn<>("المنح");
        grantColumn.setCellValueFactory(data -> data.getValue().grantProperty());
        grantColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> militaryNumberColumn = new TableColumn<>("الرقم العسكري");
        militaryNumberColumn.setCellValueFactory(data -> data.getValue().militaryNumberProperty());
        militaryNumberColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> dateOfbirthColumn = new TableColumn<>("تاريخ الميلاد");
        dateOfbirthColumn.setCellValueFactory(data -> data.getValue().dateOfBirthProperty());
        dateOfbirthColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<SoldierRow, String> barcodeColumn = new TableColumn<>("الباركود");
        barcodeColumn.setCellFactory(column -> new TableCell<SoldierRow, String>() {
            private final Pane imageContainer = new Pane();

            @Override
            protected void updateItem(String barcodePath, boolean empty) {
                super.updateItem(barcodePath, empty);

                if (empty || barcodePath == null) {
                    setGraphic(null);
                } else {
                    try {
                        Image image = new Image(new File(barcodePath).toURI().toString());
                        ImageView imageView = new ImageView(image);

                        // تكبير الباركود
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(400);
                        imageView.setPreserveRatio(true);

                        imageContainer.getChildren().clear();
                        imageContainer.getChildren().add(imageView);
                        imageContainer.setPrefSize(400, 200);

                        setGraphic(imageContainer);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        barcodeColumn.setMinWidth(400);
        barcodeColumn.setMaxWidth(500);
        barcodeColumn.setStyle("-fx-alignment: CENTER;");

        // توزيع الأعمدة بالتساوي
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
