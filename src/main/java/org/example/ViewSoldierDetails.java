package org.example;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class ViewSoldierDetails {

    public void display(SoldierRow soldier) {
        Stage stage = new Stage();
        stage.setTitle("تفاصيل المجند");

        // إنشاء قسم عرض البيانات
        GridPane detailsGrid = new GridPane();
        detailsGrid.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        detailsGrid.setHgap(10);
        detailsGrid.setVgap(10);

        // إضافة البيانات إلى الـ GridPane
        detailsGrid.add(createLabel("الاسم:"), 0, 0);
        detailsGrid.add(createValueLabel(soldier.nameProperty().get()), 1, 0);

        detailsGrid.add(createLabel("الرقم القومي:"), 0, 1);
        detailsGrid.add(createValueLabel(soldier.idProperty().get()), 1, 1);

        detailsGrid.add(createLabel("العنوان:"), 0, 2);
        detailsGrid.add(createValueLabel(soldier.addressProperty().get()), 1, 2);

        detailsGrid.add(createLabel("تاريخ الميلاد:"), 0, 3);
        detailsGrid.add(createValueLabel(soldier.dateOfBirthProperty().get()), 1, 3);

        detailsGrid.add(createLabel("السلاح:"), 0, 4);
        detailsGrid.add(createValueLabel(soldier.weaponProperty().get()), 1, 4);

        detailsGrid.add(createLabel("رقم الهاتف:"), 0, 5);
        detailsGrid.add(createValueLabel(soldier.phoneProperty().get()), 1, 5);

        detailsGrid.add(createLabel("الأقارب:"), 0, 6);
        detailsGrid.add(createValueLabel(soldier.relativesProperty().get()), 1, 6);

        detailsGrid.add(createLabel("العقوبات:"), 0, 7);
        detailsGrid.add(createValueLabel(soldier.punishmentProperty().get()), 1, 7);

        detailsGrid.add(createLabel("المنح:"), 0, 8);
        detailsGrid.add(createValueLabel(soldier.grantProperty().get()), 1, 8);

        detailsGrid.add(createLabel("الرقم العسكري:"), 0, 9);
        detailsGrid.add(createValueLabel(soldier.militaryNumberProperty().get()), 1, 9);

        // إنشاء قسم عرض الباركود
        ImageView barcodeImage = new ImageView();
        try {
            File barcodeFile = new File(soldier.getBarcode());
            Image image = new Image(barcodeFile.toURI().toString());
            barcodeImage.setImage(image);
            barcodeImage.setFitWidth(300);
            barcodeImage.setPreserveRatio(true);
        } catch (Exception e) {
            barcodeImage.setImage(null);
        }

        VBox barcodeBox = new VBox(barcodeImage);
        barcodeBox.setAlignment(Pos.CENTER);

        // أزرار التحكم
        Button backButton = new Button("عودة");
        Button printButton = new Button("طباعة");
        HBox buttonBox = new HBox(20, backButton, printButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 20;");

        // إضافة الأحداث
        backButton.setOnAction(e -> stage.close());
        printButton.setOnAction(e -> System.out.println("تم الطباعة!"));

        // تجميع المحتويات في النافذة
        BorderPane root = new BorderPane();
        root.setCenter(detailsGrid);
        root.setLeft(barcodeBox);
        root.setBottom(buttonBox);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        return label;
    }

    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 14px;");
        return label;
    }
}
