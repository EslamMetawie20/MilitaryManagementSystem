package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableCell;
import java.io.File;

public class MainView extends Application {

    private ObservableList<SoldierRow> soldiers = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        TableView<SoldierRow> table = new TableView<>(soldiers);
        primaryStage.setMaximized(true);
        TableColumn<SoldierRow, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        nameColumn.setMinWidth(150);

        TableColumn<SoldierRow, String> idColumn = new TableColumn<>("الرقم القومي");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        idColumn.setMinWidth(150);

        TableColumn<SoldierRow, String> addressColumn = new TableColumn<>("العنوان");
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());
        addressColumn.setMinWidth(120);

        TableColumn<SoldierRow, String> weaponColumn = new TableColumn<>("السلاح");
        weaponColumn.setCellValueFactory(data -> data.getValue().weaponProperty());
        weaponColumn.setMinWidth(90);

        TableColumn<SoldierRow, String> phoneColumn = new TableColumn<>("رقم الهاتف");
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());
        phoneColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> relativesColumn = new TableColumn<>("الأقارب");
        relativesColumn.setCellValueFactory(data -> data.getValue().relativesProperty());
        relativesColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> punishmentColumn = new TableColumn<>("العقوبات");
        punishmentColumn.setCellValueFactory(data -> data.getValue().punishmentProperty());
        punishmentColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> grantColumn = new TableColumn<>("المنح");
        grantColumn.setCellValueFactory(data -> data.getValue().grantProperty());
        grantColumn.setMinWidth(100);

        TableColumn<SoldierRow, String> militaryNumberColumn = new TableColumn<>("الرقم العسكري");
        militaryNumberColumn.setCellValueFactory(data -> data.getValue().militaryNumberProperty());
        militaryNumberColumn.setMinWidth(150);

        TableColumn<SoldierRow, String> dateOfBirthColumn = new TableColumn<>("تاريخ الميلاد");
        dateOfBirthColumn.setCellValueFactory(data -> data.getValue().dateOfBirthProperty());
        dateOfBirthColumn.setMinWidth(150);


        TableColumn<SoldierRow, String> barcodeColumn = new TableColumn<>("الباركود");
        barcodeColumn.setCellValueFactory(data -> data.getValue().barcodeProperty());
        barcodeColumn.setMinWidth(150);
        barcodeColumn.setCellFactory(column -> {
            return new TableCell<>() {
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
                            imageView.setFitHeight(40);
                            imageView.setFitWidth(120);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                        } catch (Exception e) {
                            setGraphic(null);
                        }
                    }
                }
            };
        });
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setRowFactory(tv -> {
            TableRow<SoldierRow> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem viewItem = new MenuItem("عرض");
            viewItem.setOnAction(e -> {
                SoldierRow selectedSoldier = row.getItem();
                if (selectedSoldier != null) {
                    new ViewSoldierDetails().display(selectedSoldier);
                }
            });

            MenuItem editItem = new MenuItem("تعديل");
            editItem.setOnAction(e -> {
                System.out.println("تعديل المجند: " + row.getItem().nameProperty().get());
            });

            contextMenu.getItems().addAll(viewItem, editItem);
            row.setContextMenu(contextMenu);

            return row;
        });

        nameColumn.setStyle("-fx-alignment: CENTER;");
        idColumn.setStyle("-fx-alignment: CENTER;");
        addressColumn.setStyle("-fx-alignment: CENTER;");
        weaponColumn.setStyle("-fx-alignment: CENTER;");
        phoneColumn.setStyle("-fx-alignment: CENTER;");
        relativesColumn.setStyle("-fx-alignment: CENTER;");
        punishmentColumn.setStyle("-fx-alignment: CENTER;");
        grantColumn.setStyle("-fx-alignment: CENTER;");
        militaryNumberColumn.setStyle("-fx-alignment: CENTER;");
        dateOfBirthColumn.setStyle("-fx-alignment: CENTER;");
        barcodeColumn.setStyle("-fx-alignment: CENTER;");

        table.getColumns().addAll(
                nameColumn, idColumn, addressColumn,dateOfBirthColumn, weaponColumn,
                phoneColumn, relativesColumn, punishmentColumn,
                grantColumn, militaryNumberColumn, barcodeColumn
        );

        table.setPlaceholder(new Label("لا يوجد أي مدخلات") {{
            setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
        }});

        Button addButton = new Button("أضف مجند");
        addButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 50px;");

        Button deleteButton = new Button("أحذف مجند");
        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 50px;");


        HBox buttonBox = new HBox(10, addButton, deleteButton);
        buttonBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonBox);

        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("إدارة المجندين");
        primaryStage.show();

        addButton.setOnAction(e -> {
            AddSoldierView addView = new AddSoldierView(soldiers);
            addView.display();
        });

        deleteButton.setOnAction(e -> {
            if (soldiers.isEmpty()) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("تحذير");
                emptyAlert.setHeaderText(null);
                emptyAlert.setContentText("لا توجد أي عناصر للحذف.");
                emptyAlert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                emptyAlert.showAndWait();
            } else {
                SoldierRow selected = table.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("تأكيد الحذف");
                    confirmationAlert.setHeaderText("هل أنت متأكد من الحذف؟");
                    confirmationAlert.setContentText("سيتم حذف المجند: " + selected.nameProperty().get());
                    confirmationAlert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

                    confirmationAlert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            soldiers.remove(selected);
                        }
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("تحذير");
                    alert.setHeaderText(null);
                    alert.setContentText("قم بإختيار عنصر في الأول");
                    alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    alert.showAndWait();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
