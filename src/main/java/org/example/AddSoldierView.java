package org.example;

import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

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

        TextField Militry_NumberField = new TextField();
        Militry_NumberField.setPromptText("الرقم العسكري");

        TextField FieldAddress = new TextField();
        FieldAddress.setPromptText("العنوان");

        ComboBox weaponField = new ComboBox<>();
        weaponField.setPromptText("السلاح");
        weaponField.getItems().addAll(
                "المركبات",
                "مدرعات",
                "أسلحة و ذخيرة",
                "مشاة",
                "مهندسين عسكريين",
                "أشغال عسكرية",
                "حرب إلكترونية",
                "حرب كيميائية",
                "إمداد وتموين",
                "شؤون مالية",
                "دفاع جوي",
                "قوات بحرية",
                "قوات جوية",
                "نقل",
                "مدفعيه "
        );

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("رقم الهاتف");

        TextField relativesField = new TextField();
        relativesField.setPromptText("الأقارب");

        TextField PunishmentsField = new TextField();
        PunishmentsField.setPromptText("العقوبات");

        TextField GrantField = new TextField();
        GrantField.setPromptText("المنح");

        DatePicker dateOfBirth =new DatePicker();
        dateOfBirth.setPromptText("تاريخ الميلاد");

        Button saveButton = new Button("حفظ");
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        root.getChildren().addAll(
                nameField, nationalIdField, FieldAddress,dateOfBirth, weaponField,
                phoneNumberField, relativesField, PunishmentsField,
                GrantField, Militry_NumberField, saveButton
        );

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("إضافة مجند جديد");
        stage.show();
        saveButton.setOnAction(e -> {
            if (nationalIdField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("خطأ");
                alert.setHeaderText("الرقم القومي مطلوب");
                alert.setContentText("الرجاء إدخال الرقم القومي");
                alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                alert.showAndWait();
                return;
            }

            try {
                String nationalId = nationalIdField.getText().trim();
                String barcodePath = BarcodeGenerator.generateBarcode(nationalId);
                if (barcodePath != null) {
                    String weapon = (String) weaponField.getValue();
                    if (weapon == null) weapon = "";
                    String dateOfBirthValue = dateOfBirth.getValue() != null ? dateOfBirth.getValue().toString() : "";

                    SoldierRow soldier = new SoldierRow(
                            nameField.getText().trim(),
                            nationalId,
                            FieldAddress.getText().trim(),
                            dateOfBirthValue,
                            weapon,
                            phoneNumberField.getText().trim(),
                            relativesField.getText().trim(),
                            PunishmentsField.getText().trim(),
                            GrantField.getText().trim(),
                            Militry_NumberField.getText().trim(),
                            barcodePath
                    );
                    soldiers.add(soldier);
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("خطأ");
                    alert.setHeaderText("فشل في إنشاء الباركود");
                    alert.setContentText("حدث خطأ أثناء إنشاء الباركود");
                    alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("خطأ");
                alert.setHeaderText("فشل في حفظ البيانات");
                alert.setContentText("حدث خطأ أثناء حفظ بيانات المجند");
                alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                alert.showAndWait();
                ex.printStackTrace();
            }
        });
    }
}
