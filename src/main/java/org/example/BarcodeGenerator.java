package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.nio.file.Path;

public class BarcodeGenerator {
    public static String generateBarcode(String data) {
        try {
            // إنشاء مجلد للباركود إذا لم يكن موجوداً
            File dir = new File("barcodes");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // تحديد مسار الملف
            String fileName = "barcodes/" + data + ".png";
            Path path = new File(fileName).toPath();

            // إنشاء QR Code بدلاً من Code128
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    200,  // عرض
                    200   // ارتفاع
            );

            // حفظ الباركود كصورة
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return fileName;
        } catch (Exception e) {
            System.err.println("Error generating QR code: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}