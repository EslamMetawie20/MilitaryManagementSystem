package org.example.view;

import javafx.collections.ObservableList;
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

        // إنشاء خانات الإدخال
        TextField nameField = new TextField("Name");
        TextField nationalIdField = new TextField("National ID");
        TextField weaponField = new TextField("Weapon");
        TextField phoneNumberField = new TextField("Phone Number");

        Button saveButton = new Button("Save");

        root.getChildren().addAll(nameField, nationalIdField, weaponField, phoneNumberField, saveButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Add Soldier");
        stage.show();

        saveButton.setOnAction(e -> {
            // إضافة البيانات إلى القائمة
            SoldierRow soldier = new SoldierRow(
                    nameField.getText(),
                    nationalIdField.getText(),
                    weaponField.getText(),
                    phoneNumberField.getText()
            );
            soldiers.add(soldier);
            stage.close();
        });
    }
}
