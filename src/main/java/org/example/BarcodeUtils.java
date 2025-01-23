package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.common.BitMatrix;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeUtils {

    public static void saveBarcodePDF(Stage stage, SoldierRow soldier) {
        try {
            String militaryNumber = soldier.militaryNumberProperty().get();
            String militaryName = soldier.nameProperty().get();
            BufferedImage barcodeImage = generateBarcodeBufferedImage(militaryNumber);
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            float textStartY = pageHeight - 200;
            contentStream.beginText();
            contentStream.setFont(PDType0Font.load(document, new File("C:\\Windows\\Fonts\\arial.ttf")), 16);
            String militaryInfo = "Military Number: " + militaryNumber;
            float militaryTextWidth = (float) (16 * militaryInfo.length() * 0.5);
            float militaryTextStartX = (pageWidth - militaryTextWidth) / 2;
            contentStream.newLineAtOffset(militaryTextStartX, textStartY);
            contentStream.showText(militaryInfo);
            contentStream.endText();
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, barcodeImage);
            float imageWidth = 400;
            float imageHeight = (imageWidth * pdImage.getHeight()) / pdImage.getWidth();
            float x = (pageWidth - imageWidth) / 2;
            float y = (pageHeight - imageHeight) / 2 - 150;
            contentStream.drawImage(pdImage, x, y, imageWidth, imageHeight);
            contentStream.close();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Barcode as PDF");
            fileChooser.setInitialFileName( militaryName+ "_"+militaryNumber+".pdf");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf")
            );
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    document.save(file);
                    document.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Barcode saved successfully!");
                    alert.showAndWait();
                } catch (Exception e) {
                    System.err.println("Error saving file: " + e.getMessage());
                    e.printStackTrace();
                    throw e;
                }
            } else {
                document.close();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while saving the barcode: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private static BufferedImage generateBarcodeBufferedImage(String data) throws Exception {
        try {
            Code39Writer barcodeWriter = new Code39Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.CODE_39, 600, 200);
            BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            return image;
        } catch (Exception e) {
            System.err.println("Error generating barcode: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
