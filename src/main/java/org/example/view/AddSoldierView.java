package org.example.view;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.Date;

public class AddSoldierView {
    private ObservableList<SoldierRow> soldiers;

    public AddSoldierView(ObservableList<SoldierRow> soldiers) {
        this.soldiers = soldiers;
    }

    public void display() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(10);

        // إنشاء خانات الإدخال
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField nationalIdField = new TextField();
        nationalIdField.setPromptText("National ID");

        TextField FieldAddress = new TextField();
        FieldAddress.setPromptText("Address");

        TextField weaponField = new TextField();
        weaponField.setPromptText("Weapon");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        TextField relativesField = new TextField();
        relativesField.setPromptText("Relatives");

        TextField PunishmentsField = new TextField();
        PunishmentsField.setPromptText("Punishments");

        TextField GrantField = new TextField();
        GrantField.setPromptText("Grant");

        TextField Militry_NumberField = new TextField();
        Militry_NumberField.setPromptText("Military Number");

        Button saveButton = new Button("Save");

        root.getChildren().addAll(
                nameField, nationalIdField, FieldAddress, weaponField,
                phoneNumberField, relativesField, PunishmentsField,
                GrantField, Militry_NumberField, saveButton
        );

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Add Soldier");
        stage.show();

        saveButton.setOnAction(e -> {
            // إضافة البيانات إلى القائمة
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
