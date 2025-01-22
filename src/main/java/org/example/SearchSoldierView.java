package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchSoldierView {

    private ObservableList<SoldierRow> soldiers;

    public SearchSoldierView(ObservableList<SoldierRow> soldiers) {
        this.soldiers = soldiers;
    }

    public void display() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(15);
        root.setStyle("-fx-padding: 20;");
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        // حقل إدخال للبحث
        TextField searchField = new TextField();
        searchField.setPromptText("أدخل اسم المجند للبحث");
        searchField.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #cccccc;
            -fx-border-radius: 5;
            -fx-background-radius: 5;
            -fx-padding: 8;
            -fx-font-size: 14px;
        """);

        // زر البحث
        Button searchButton = new Button("بحث");
        searchButton.setStyle("""
            -fx-background-color: #007BFF; 
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-padding: 10 20;
            -fx-background-radius: 5;
        """);

        // جدول لعرض النتائج
        TableView<SoldierRow> resultsTable = new TableView<>();
        TableColumn<SoldierRow, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        nameColumn.setStyle("-fx-alignment: CENTER;");
        resultsTable.getColumns().add(nameColumn);
        resultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        resultsTable.setPlaceholder(new Label("لا توجد نتائج"));

        // عند الضغط على زر البحث
        searchButton.setOnAction(e -> {
            String query = searchField.getText().trim();
            if (query.isEmpty()) {
                showAlert("خطأ", "حقل البحث فارغ", "الرجاء إدخال اسم للبحث.");
                return;
            }

            // تصفية النتائج
            ObservableList<SoldierRow> filteredSoldiers = FXCollections.observableArrayList();
            for (SoldierRow soldier : soldiers) {
                if (soldier.nameProperty().get().contains(query)) {
                    filteredSoldiers.add(soldier);
                }
            }

            resultsTable.setItems(filteredSoldiers);
            if (filteredSoldiers.isEmpty()) {
                showAlert("نتيجة", "لا توجد نتائج", "لم يتم العثور على أي مجند بهذا الاسم.");
            }
        });

        // عند الضغط على اسم مجند في الجدول
        resultsTable.setRowFactory(tv -> {
            TableRow<SoldierRow> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    SoldierRow selectedSoldier = row.getItem();
                    new ViewSoldierDetails().display(selectedSoldier);
                }
            });
            return row;
        });

        root.getChildren().addAll(searchField, searchButton, resultsTable);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("بحث عن مجند");
        stage.show();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        alert.showAndWait();
    }
}
