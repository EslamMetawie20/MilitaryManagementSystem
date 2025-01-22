package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBarcodeView {

    public void display(String barcodeData) {
        Stage stage = new Stage();
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        WritableImage barcodeImage = generateBarcodeImage(barcodeData);

        if (barcodeImage != null) {
            ImageView imageView = new ImageView(barcodeImage);
            imageView.setFitWidth(500);
            imageView.setPreserveRatio(true);

            Button backButton = new Button("رجوع");
            backButton.setOnAction(e -> stage.close());

            root.getChildren().addAll(imageView, backButton);

            Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
            stage.setTitle("عرض الباركود");
            stage.show();
        } else {
            System.out.println("خطأ أثناء توليد الباركود.");
        }
    }

    private WritableImage generateBarcodeImage(String data) {
        try {
            Code39Writer barcodeWriter = new Code39Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.CODE_39, 600, 200);

            WritableImage image = new WritableImage(bitMatrix.getWidth(), bitMatrix.getHeight());
            PixelWriter pixelWriter = image.getPixelWriter();

            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    pixelWriter.setColor(x, y, bitMatrix.get(x, y) ? javafx.scene.paint.Color.BLACK : javafx.scene.paint.Color.WHITE);
                }
            }

            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
