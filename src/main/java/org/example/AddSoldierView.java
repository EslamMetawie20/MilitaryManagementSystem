package org.example;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.example.database.DatabaseHelper;

import java.time.LocalDate;

public class AddSoldierView {
    private ObservableList<SoldierRow> soldiers;
    private static final String FIELD_STYLE = """
            -fx-background-color: white;
            -fx-border-color: #cccccc;
            -fx-border-radius: 5;
            -fx-background-radius: 5;
            -fx-padding: 8;
            -fx-font-size: 14px;
            """;

    private static final String LABEL_STYLE = """
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-text-fill: #2c3e50;
            """;

    public AddSoldierView(ObservableList<SoldierRow> soldiers) {
        this.soldiers = soldiers;
    }

    public void display() {
        Stage stage = new Stage();

        // Create main container
        VBox mainContainer = new VBox();
        mainContainer.setStyle("""
            -fx-background-color: #f8f9fa;
            -fx-padding: 20;
            -fx-spacing: 15;
            """);
        mainContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        // Title
        Label titleLabel = new Label("إضافة مجند جديد");
        titleLabel.setStyle("""
            -fx-font-size: 24px;
            -fx-font-weight: bold;
            -fx-text-fill: #2c3e50;
            -fx-padding: 0 0 20 0;
            """);

        // Create grid for form fields
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);

        // Create and style form fields
        TextField nameField = createStyledTextField("الاسم");
        TextField nationalIdField = createStyledTextField("الرقم القومي");
        TextField militaryNumberField = createStyledTextField("الرقم العسكري");
        TextField addressField = createStyledTextField("العنوان");
        TextField phoneNumberField = createStyledTextField("رقم الهاتف");
        TextField relativesField = createStyledTextField("الأقارب");
        NumberedTextArea numberedPunishments = new NumberedTextArea();
        numberedPunishments.getTextArea().setPromptText("العقوبات");
        numberedPunishments.getTextArea().setStyle(FIELD_STYLE);
        numberedPunishments.getTextArea().setPrefWidth(200);
        numberedPunishments.getTextArea().setPrefHeight(100);

        TextField grantField = createStyledTextField("المنح");
        ComboBox<String> weaponField = new ComboBox<>();
        weaponField.setPromptText("السلاح");
        weaponField.setStyle(FIELD_STYLE);
        weaponField.setPrefWidth(200);
        weaponField.getItems().addAll(
                "المركبات", "مدرعات", "أسلحة و ذخيرة", "مشاة",
                "مهندسين عسكريين", "أشغال عسكرية", "حرب إلكترونية",
                "حرب كيميائية", "إمداد وتموين", "شؤون مالية",
                "دفاع جوي", "قوات بحرية", "قوات جوية", "نقل", "مدفعيه"
        );

        DatePicker dateOfBirth = new DatePicker();

        dateOfBirth.setPromptText("تاريخ الميلاد");
        dateOfBirth.setStyle(FIELD_STYLE);
        dateOfBirth.setPrefWidth(250);
        dateOfBirth.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #d3d3d3;");
                }
            }
        });

        dateOfBirth.setValue(LocalDate.of(2000, 1, 1));
        // Add fields to grid
        addFieldToGrid(formGrid, "الاسم", nameField, 0);
        addFieldToGrid(formGrid, "الرقم القومي", nationalIdField, 1);
        addFieldToGrid(formGrid, "الرقم العسكري", militaryNumberField, 2);
        addFieldToGrid(formGrid, "العنوان", addressField, 3);
        addFieldToGrid(formGrid, "تاريخ الميلاد", dateOfBirth, 4);
        addFieldToGrid(formGrid, "السلاح", weaponField, 5);
        addFieldToGrid(formGrid, "رقم الهاتف", phoneNumberField, 6);
        addFieldToGrid(formGrid, "الأقارب", relativesField, 7);
        addFieldToGrid(formGrid, "العقوبات",numberedPunishments.getTextArea() , 8);
        addFieldToGrid(formGrid, "المنح", grantField, 9);

        // Create and style save button
        Button saveButton = new Button("حفظ");
        saveButton.setStyle("""
            -fx-background-color: #2ecc71;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 10 30;
            -fx-background-radius: 5;
            -fx-cursor: hand;
            """);
        saveButton.setOnMouseEntered(e ->
                saveButton.setStyle(saveButton.getStyle() + "-fx-background-color: #27ae60;"));
        saveButton.setOnMouseExited(e ->
                saveButton.setStyle(saveButton.getStyle() + "-fx-background-color: #2ecc71;"));

        HBox buttonContainer = new HBox(saveButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20, 0, 0, 0));

        // Add all components to main container
        mainContainer.getChildren().addAll(titleLabel, formGrid, buttonContainer);

        Scene scene = new Scene(mainContainer, 600, 700);

        stage.setScene(scene);
        stage.setTitle("إضافة مجند جديد");
        stage.show();

        // Save button action
        saveButton.setOnAction(e -> {
            if (nameField.getText().trim().isEmpty()) {
                showAlert("خطأ", "اسم المجند مطلوب", "الرجاء إدخال اسم المجند");
                return;
            }
            if (nationalIdField.getText().trim().isEmpty()) {
                showAlert("خطأ", "الرقم القومي مطلوب", "الرجاء إدخال الرقم القومي");
                return;
            }
            if (!nationalIdField.getText().trim().matches("\\d+")) {
                showAlert("خطأ", "الرقم القومي غير صحيح", "يجب أن يحتوي الرقم القومي على أرقام فقط بدون أي حروف أو رموز.");
                return;
            }
            if (militaryNumberField.getText().trim().isEmpty()) {
                showAlert("خطأ", "الرقم العسكري مطلوب", "الرجاء إدخال الرقم العسكري");
                return;
            }

            if (!militaryNumberField.getText().trim().matches("\\d+")) {
                showAlert("خطأ", "الرقم العسكري غير صحيح", "يجب أن يحتوي الرقم العسكري على أرقام فقط بدون أي حروف أو رموز.");
                return;
            }
            if (dateOfBirth.getValue() == null) {
                showAlert("خطأ", "تاريخ الميلاد مطلوب", "الرجاء إدخال تاريخ الميلاد");
                return;
            }
            if (weaponField.getValue().isEmpty()) {
                showAlert("خطأ", "نوع سلاح المجتد مطلوب", "الرجاء إدخال نوع السلاح الخاص بالمجند");
                return;
            }
            try {
                String nationalId = nationalIdField.getText().trim();
                String barcodePath = BarcodeGenerator.generateBarcode(nationalId);
                if (barcodePath != null) {
                    String weapon = weaponField.getValue();
                    if (weapon == null) weapon = "";
                    String dateOfBirthValue = dateOfBirth.getValue() != null ?
                            dateOfBirth.getValue().toString() : "";

                    SoldierRow soldier = new SoldierRow(
                            nameField.getText().trim(),
                            nationalId,
                            addressField.getText().trim(),
                            dateOfBirthValue,
                            weapon,
                            phoneNumberField.getText().trim(),
                            relativesField.getText().trim(),
                            numberedPunishments.getTextArea().getText().trim(),
                            grantField.getText().trim(),
                            militaryNumberField.getText().trim(),
                            barcodePath
                    );

                    DatabaseHelper.saveSoldier(soldier); // حفظ الجندي في قاعدة البيانات
                    soldiers.add(soldier);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("تم بنجاح");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("تم إضافة المجند بنجاح!");
                    successAlert.showAndWait();
                    stage.close();
                } else {
                    showAlert("خطأ", "فشل في إنشاء الباركود", "حدث خطأ أثناء إنشاء الباركود");
                }
            } catch (Exception ex) {
                showAlert("خطأ", "فشل في حفظ البيانات", "حدث خطأ أثناء حفظ بيانات المجند");
                ex.printStackTrace();
            }

        });
    }

    public void displayForEdit(SoldierRow soldier) {
        Stage stage = new Stage();
        stage.setMinWidth(700);
        stage.setMaxWidth(800);

        // Create main container
        VBox mainContainer = new VBox();
        mainContainer.setStyle("""
        -fx-background-color: #f8f9fa;
        -fx-padding: 20;
        -fx-spacing: 15;
        """);
        mainContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        mainContainer.setMaxWidth(500);
        // Title
        Label titleLabel = new Label("تعديل بيانات المجند");
        titleLabel.setStyle("""
        -fx-font-size: 24px;
        -fx-font-weight: bold;
        -fx-text-fill: #2c3e50;
        -fx-padding: 0 0 20 0;
        """);

        // Create grid for form fields
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);

        // Create and style form fields
        TextField nameField = createStyledTextField("الاسم");
        TextField nationalIdField = createStyledTextField("الرقم القومي");
        TextField militaryNumberField = createStyledTextField("الرقم العسكري");
        TextField addressField = createStyledTextField("العنوان");
        TextField phoneNumberField = createStyledTextField("رقم الهاتف");
        TextField relativesField = createStyledTextField("الأقارب");
        NumberedTextArea punishmentsField=new NumberedTextArea();
        TextField grantField = createStyledTextField("المنح");

        ComboBox<String> weaponField = new ComboBox<>();
        weaponField.setPromptText("السلاح");
        weaponField.setStyle(FIELD_STYLE);
        weaponField.setPrefWidth(200);
        weaponField.getItems().addAll(
                "المركبات", "مدرعات", "أسلحة و ذخيرة", "مشاة",
                "مهندسين عسكريين", "أشغال عسكرية", "حرب إلكترونية",
                "حرب كيميائية", "إمداد وتموين", "شؤون مالية",
                "دفاع جوي", "قوات بحرية", "قوات جوية", "نقل", "مدفعيه"
        );

        DatePicker dateOfBirth = new DatePicker();
        dateOfBirth.setPromptText("تاريخ الميلاد");
        dateOfBirth.setStyle(FIELD_STYLE);
        dateOfBirth.setPrefWidth(250);

        // Fill fields with existing data
        nameField.setText(soldier.nameProperty().get());
        nationalIdField.setText(soldier.idProperty().get());
        militaryNumberField.setText(soldier.militaryNumberProperty().get());
        addressField.setText(soldier.addressProperty().get());
        phoneNumberField.setText(soldier.phoneProperty().get());
        relativesField.setText(soldier.relativesProperty().get());
        punishmentsField.getTextArea().setText(soldier.punishmentProperty().get());
        grantField.setText(soldier.grantProperty().get());
        weaponField.setValue(soldier.weaponProperty().get());
        dateOfBirth.setValue(LocalDate.parse(soldier.dateOfBirthProperty().get()));

        // Create and style save button
        Button saveButton = new Button("تحديث");
        saveButton.setStyle("""
        -fx-background-color: #2ecc71;
        -fx-text-fill: white;
        -fx-font-size: 16px;
        -fx-font-weight: bold;
        -fx-padding: 10 30;
        -fx-background-radius: 5;
        -fx-cursor: hand;
        """);

        saveButton.setOnAction(e -> {
            // Validate fields
            if (nameField.getText().trim().isEmpty()) {
                showAlert("خطأ", "اسم المجند مطلوب", "الرجاء إدخال اسم المجند");
                return;
            }

            // Update soldier details
            soldier.nameProperty().set(nameField.getText().trim());
            soldier.militaryNumberProperty().set(militaryNumberField.getText().trim());
            soldier.addressProperty().set(addressField.getText().trim());
            soldier.phoneProperty().set(phoneNumberField.getText().trim());
            soldier.relativesProperty().set(relativesField.getText().trim());
            soldier.punishmentProperty().set(punishmentsField.getTextArea().getText().trim());
            soldier.grantProperty().set(grantField.getText().trim());
            soldier.weaponProperty().set(weaponField.getValue());
            soldier.dateOfBirthProperty().set(dateOfBirth.getValue().toString());

            // Update soldier in database
            DatabaseHelper.updateSoldier(soldier);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("تم التحديث");
            successAlert.setHeaderText(null);
            successAlert.setContentText("تم تحديث بيانات المجند بنجاح!");
            successAlert.showAndWait();

            stage.close();
        });

        // Add fields to grid and show stage
        addFieldToGrid(formGrid, "الاسم", nameField, 0);
        addFieldToGrid(formGrid, "الرقم القومي", nationalIdField, 1);
        addFieldToGrid(formGrid, "الرقم العسكري", militaryNumberField, 2);
        addFieldToGrid(formGrid, "العنوان", addressField, 3);
        addFieldToGrid(formGrid, "تاريخ الميلاد", dateOfBirth, 4);
        addFieldToGrid(formGrid, "السلاح", weaponField, 5);
        addFieldToGrid(formGrid, "رقم الهاتف", phoneNumberField, 6);
        addFieldToGrid(formGrid, "الأقارب", relativesField, 7);
        addFieldToGrid(formGrid, "العقوبات", punishmentsField.getTextArea(), 8);
        addFieldToGrid(formGrid, "المنح", grantField, 9);

        mainContainer.getChildren().addAll(titleLabel, formGrid, saveButton);

        Scene scene = new Scene(mainContainer, 450, 700);
        stage.setScene(scene);
        stage.setTitle("تعديل بيانات مجند");
        stage.show();
    }

    private TextField createStyledTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setStyle(FIELD_STYLE);
        field.setPrefWidth(200);
        return field;
    }

    private void addFieldToGrid(GridPane grid, String labelText, Control field, int row) {
        Label label = new Label(labelText);
        label.setStyle(LABEL_STYLE);
        grid.add(label, 0, row);
        grid.add(field, 1, row);
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        alert.getDialogPane().setStyle("""
            -fx-font-family: 'Arial';
            -fx-font-size: 14px;
            """);
        alert.showAndWait();
    }
}