package org.example;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.File;

public class ViewSoldierDetails {

    public void display(SoldierRow soldier) {
        Stage stage = new Stage();
        stage.setTitle("تفاصيل المجند");

        // إنشاء قسم عرض البيانات
        GridPane detailsGrid = new GridPane();
        detailsGrid.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        detailsGrid.setHgap(15);
        detailsGrid.setVgap(15);
        detailsGrid.setPadding(new Insets(20));
        detailsGrid.setStyle("-fx-background-color: white;");

        // إضافة البيانات إلى الـ GridPane
        addDetailsToGrid(detailsGrid, soldier);

        // إنشاء قسم عرض الباركود مع تحسينات
        VBox barcodeSection = createBarcodeSection(soldier);

        // تحسين أزرار التحكم
        HBox buttonBox = createEnhancedButtons(stage);

        // إنشاء حاوية رئيسية مع خلفية وتأثيرات
        VBox mainContainer = new VBox(20);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");
        mainContainer.setPadding(new Insets(20));

        // إنشاء بطاقة للتفاصيل
        VBox detailsCard = new VBox(detailsGrid);
        detailsCard.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 10;
            -fx-padding: 20;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);
        """);

        // تجميع المحتويات في النافذة
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(barcodeSection, detailsCard);
        contentBox.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(contentBox, buttonBox);

        // إضافة ScrollPane للتمرير عند الحاجة
        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f5f5f5; -fx-background-color: #f5f5f5;");

        // تحسين حجم النافذة
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth() * 0.8;
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight() * 0.8;
        Scene scene = new Scene(scrollPane, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    private void addDetailsToGrid(GridPane grid, SoldierRow soldier) {
        String[][] details = {
                {"الاسم:", soldier.nameProperty().get()},
                {"الرقم القومي:", soldier.idProperty().get()},
                {"العنوان:", soldier.addressProperty().get()},
                {"تاريخ الميلاد:", soldier.dateOfBirthProperty().get()},
                {"السلاح:", soldier.weaponProperty().get()},
                {"رقم الهاتف:", soldier.phoneProperty().get()},
                {"الأقارب:", soldier.relativesProperty().get()},
                {"العقوبات:", soldier.punishmentProperty().get()},
                {"المنح:", soldier.grantProperty().get()},
                {"الرقم العسكري:", soldier.militaryNumberProperty().get()}
        };

        for (int i = 0; i < details.length; i++) {
            grid.add(createLabel(details[i][0]), 0, i);
            grid.add(createValueLabel(details[i][1]), 1, i);
        }
    }

    private VBox createBarcodeSection(SoldierRow soldier) {
        ImageView barcodeImage = new ImageView();
        try {
            File barcodeFile = new File(soldier.getBarcode());
            Image image = new Image(barcodeFile.toURI().toString());
            barcodeImage.setImage(image);
            barcodeImage.setFitWidth(250);
            barcodeImage.setPreserveRatio(true);
        } catch (Exception e) {
            barcodeImage.setImage(null);
        }

        VBox barcodeCard = new VBox(15, barcodeImage);
        barcodeCard.setAlignment(Pos.CENTER);
        barcodeCard.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 10;
            -fx-padding: 20;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);
        """);

        return barcodeCard;
    }

    private HBox createEnhancedButtons(Stage stage) {
        Button backButton = new Button("عودة");
        Button printButton = new Button("طباعة");

        String buttonStyle = """
            -fx-background-color: #2196F3;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-padding: 10 20;
            -fx-background-radius: 5;
            -fx-cursor: hand;
        """;

        backButton.setStyle(buttonStyle);
        printButton.setStyle(buttonStyle.replace("#2196F3", "#4CAF50"));

        // إضافة تأثير hover
        backButton.setOnMouseEntered(e ->
                backButton.setStyle(buttonStyle.replace("#2196F3", "#1976D2")));
        backButton.setOnMouseExited(e ->
                backButton.setStyle(buttonStyle));

        printButton.setOnMouseEntered(e ->
                printButton.setStyle(buttonStyle.replace("#2196F3", "#388E3C")));
        printButton.setOnMouseExited(e ->
                printButton.setStyle(buttonStyle.replace("#2196F3", "#4CAF50")));

        backButton.setOnAction(e -> stage.close());
        printButton.setOnAction(e -> System.out.println("تم الطباعة!"));

        HBox buttonBox = new HBox(20, backButton, printButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

        return buttonBox;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("""
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-text-fill: #1976D2;
        """);
        return label;
    }

    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.setStyle("""
            -fx-font-size: 14px;
            -fx-text-fill: #424242;
        """);
        return label;
    }
}