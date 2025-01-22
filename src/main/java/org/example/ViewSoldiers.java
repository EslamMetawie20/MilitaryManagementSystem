package org.example;

import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;

public class ViewSoldiers {
    public void display() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(10);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        stage.setMaximized(true);

        TableView<SoldierRow> table = new TableView<>();

        // Table Columns
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

        TableColumn<SoldierRow, String> barcodeColumn = new TableColumn<>("الباركود");
        barcodeColumn.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String barcodePath, boolean empty) {
                super.updateItem(barcodePath, empty);

                if (empty || barcodePath == null) {
                    setGraphic(null);
                } else {
                    try {
                        Image image = new Image(new File(barcodePath).toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(200);
                        imageView.setPreserveRatio(true);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });
        barcodeColumn.setStyle("-fx-alignment: CENTER;");

        table.getColumns().addAll(
                nameColumn, idColumn, addressColumn, weaponColumn, phoneColumn, barcodeColumn
        );

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("لا توجد بيانات لعرضها") {{
            setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
        }});

        Button closeButton = new Button("إغلاق");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px;");
        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(table, closeButton);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("عرض المجندين");
        stage.show();
    }
}
