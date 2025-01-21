package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;

import java.io.File;
import java.nio.file.Path;

public class BarcodeGenerator {
    public static String generateBarcode(String data) {
        try {
            // التأكد من أن البيانات تحتوي على أرقام فقط
            if (!data.matches("\\d+")) {
                throw new IllegalArgumentException("يجب أن يحتوي الباركود على أرقام فقط");
            }

            // إنشاء مجلد للباركود إذا لم يكن موجوداً
            File dir = new File("barcodes");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // تحديد مسار الملف
            String fileName = "barcodes/" + data + ".png";
            Path path = new File(fileName).toPath();

            Code39Writer barcodeWriter = new Code39Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(
                    data,
                    BarcodeFormat.CODE_39,
                    450,
                    150
            );

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return fileName;
        } catch (Exception e) {
            System.err.println("Error generating barcode: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}