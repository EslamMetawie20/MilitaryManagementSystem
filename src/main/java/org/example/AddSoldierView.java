package org.example;

import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddSoldierView {
    private ObservableList<SoldierRow> soldiers;

    public AddSoldierView(ObservableList<SoldierRow> soldiers) {
        this.soldiers = soldiers;
    }

    public void display() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(10);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        TextField nameField = new TextField();
        nameField.setPromptText("الاسم");

        TextField nationalIdField = new TextField();
        nationalIdField.setPromptText("الرقم القومي");

        TextField FieldAddress = new TextField();
        FieldAddress.setPromptText("العنوان");

        TextField weaponField = new TextField();
        weaponField.setPromptText("السلاح");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("رقم الهاتف");

        TextField relativesField = new TextField();
        relativesField.setPromptText("الأقارب");

        TextField PunishmentsField = new TextField();
        PunishmentsField.setPromptText("العقوبات");

        TextField GrantField = new TextField();
        GrantField.setPromptText("المنح");

        TextField Militry_NumberField = new TextField();
        Militry_NumberField.setPromptText("الرقم العسكري");

        Button saveButton = new Button("حفظ");
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        root.getChildren().addAll(
                nameField, nationalIdField, FieldAddress, weaponField,
                phoneNumberField, relativesField, PunishmentsField,
                GrantField, Militry_NumberField, saveButton
        );

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("إضافة مجند جديد");
        stage.show();

        saveButton.setOnAction(e -> {
            SoldierRow soldier = new SoldierRow(
                    nameField.getText(),
                    nationalIdField.getText(),
                    FieldAddress.getText(),
                    weaponField.getText(),
                    phoneNumberField.getText(),
                    relativesField.getText(),
                    PunishmentsField.getText(),
                    GrantField.getText(),
                    Militry_NumberField.getText()
            );

            soldiers.add(soldier);
            stage.close();
        });
    }
}
