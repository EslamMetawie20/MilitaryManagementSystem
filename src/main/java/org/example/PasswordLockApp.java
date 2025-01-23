package org.example;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Line;

public class PasswordLockApp extends Application {
    private final String correctPassword = "qwer56qwer";

    @Override
    public void start(Stage primaryStage) {
        // Create main container
        VBox root = new VBox(15);
        root.setStyle("-fx-background-color: #1a1a1a; -fx-padding: 30;");
        root.setAlignment(Pos.CENTER_RIGHT); // تعديل المحاذاة إلى اليمين
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(500);
        root.setPrefHeight(400);

        // Add Egyptian flag colors as decorative elements
        HBox flagColors = new HBox(0);
        Rectangle redRect = new Rectangle(500, 5, Color.web("#CE1126"));
        Rectangle whiteRect = new Rectangle(500, 5, Color.WHITE);
        Rectangle blackRect = new Rectangle(500, 5, Color.BLACK);

        // Add eagle symbol placeholder (you should replace this with actual Egyptian army logo)
        Label eagleSymbol = new Label("🦅");
        eagleSymbol.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        eagleSymbol.setTextFill(Color.GOLD);

        // Add military-style title
        Label titleLabel = new Label("نظام الدخول العسكري");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.GOLD);
        titleLabel.setEffect(new DropShadow(10, Color.GOLD));

        // Style the message label
        Label messageLabel = new Label("أدخل كلمة السر العسكرية:");
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        messageLabel.setTextFill(Color.WHITE);

        // Style the password field with military theme
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("كلمة السر");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #2a2a2a; " +
                "-fx-text-fill: #00ff00; " +
                "-fx-border-color: #3a3a3a; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5; " +
                "-fx-padding: 10; " +
                "-fx-font-size: 14px;");

        // Military-style login button
        Button loginButton = new Button("تسجيل الدخول");
        loginButton.setStyle("-fx-background-color: #004d1a; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 15 30; " +
                "-fx-border-color: #00ff00; " +
                "-fx-border-width: 2px; " +
                "-fx-font-size: 16px;");
        loginButton.setPrefWidth(250);

        // Status label
        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Arial", 14));
        statusLabel.setTextFill(Color.WHITE);

        // Add radar scanning animation
        Rectangle radar = new Rectangle(400, 2, Color.GREEN);
        radar.setOpacity(0.7);
        Timeline radarAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(radar.translateYProperty(), -100)),
                new KeyFrame(Duration.seconds(2), new KeyValue(radar.translateYProperty(), 100))
        );
        radarAnimation.setCycleCount(Timeline.INDEFINITE);
        radarAnimation.setAutoReverse(true);
        radarAnimation.play();

        // Add login button animations and effects
        loginButton.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), loginButton);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });

        loginButton.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), loginButton);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        // Add login authentication animation and logic
        loginButton.setOnAction(e -> {
            String enteredPassword = passwordField.getText();
            if (enteredPassword.equals(correctPassword)) {
                // Success animation
                ParallelTransition pt = new ParallelTransition();

                // Fade out all controls
                root.getChildren().forEach(node -> {
                    FadeTransition ft = new FadeTransition(Duration.seconds(1), node);
                    ft.setToValue(0);
                    pt.getChildren().add(ft);
                });

                // Add success effect
                Rectangle flash = new Rectangle(500, 400, Color.GREEN);
                flash.setOpacity(0);
                root.getChildren().add(flash);

                FadeTransition flashFade = new FadeTransition(Duration.millis(500), flash);
                flashFade.setFromValue(0);
                flashFade.setToValue(0.7);
                flashFade.setCycleCount(2);
                flashFade.setAutoReverse(true);

                pt.getChildren().add(flashFade);

                pt.setOnFinished(event -> {
                    statusLabel.setTextFill(Color.GREEN);
                    statusLabel.setText("تم التحقق من الهوية بنجاح!");
                    openMainView(primaryStage);
                });

                pt.play();
            } else {
                // Error animation
                RotateTransition rt = new RotateTransition(Duration.millis(100), passwordField);
                rt.setFromAngle(-5);
                rt.setToAngle(5);
                rt.setCycleCount(6);
                rt.setAutoReverse(true);

                passwordField.setStyle(passwordField.getStyle() + "-fx-border-color: red;");
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("كلمة السر غير صحيحة. حاول مرة أخرى.");

                rt.play();
            }
        });

        // Add decorative military lines
        Line leftLine = new Line(0, 0, 0, 300);
        Line rightLine = new Line(0, 0, 0, 300);
        leftLine.setStroke(Color.GREEN);
        rightLine.setStroke(Color.GREEN);
        leftLine.getStrokeDashArray().addAll(25d, 10d);
        rightLine.getStrokeDashArray().addAll(25d, 10d);

        // Animate decorative lines
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftLine.strokeDashOffsetProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(leftLine.strokeDashOffsetProperty(), -35))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightLine.strokeDashOffsetProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rightLine.strokeDashOffsetProperty(), -35))
        );
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();

        // Layout assembly
        HBox militaryFrame = new HBox(10);
        militaryFrame.setAlignment(Pos.CENTER);
        militaryFrame.getChildren().addAll(leftLine,
                new VBox(10, eagleSymbol, titleLabel, messageLabel, passwordField, loginButton, statusLabel),
                rightLine);

        root.getChildren().addAll(
                new VBox(0, redRect, whiteRect, blackRect),
                militaryFrame,
                radar
        );

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/example/styles.css");

        primaryStage.setTitle("نظام الدخول العسكري");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openMainView(Stage primaryStage) {
        MainView mainView = new MainView();
        try {
            mainView.start(primaryStage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}