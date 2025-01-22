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
        TextField punishmentsField = createStyledTextField("العقوبات");
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
        addFieldToGrid(formGrid, "العقوبات", punishmentsField, 8);
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

        // Create scene and show stage
        Scene scene = new Scene(mainContainer, 450, 700);
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
                            punishmentsField.getText().trim(),
                            grantField.getText().trim(),
                            militaryNumberField.getText().trim(),
                            barcodePath
                    );
                    soldiers.add(soldier);
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