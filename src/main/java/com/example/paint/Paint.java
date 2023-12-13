package com.example.paint;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class Paint extends Application {

    private Stage stage;
    private FileChooser fc;
    private Canvas canvas;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Paint.class.getResource("Paint.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("Paint.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setOnCloseRequest(e -> {
            smartSave();
            e.consume();
        });

        stage.setTitle("Paint");
        stage.setScene(scene);
        stage.show();
    }

    public void smartSave() {
        Alert exit = new Alert(Alert.AlertType.WARNING);
        exit.setTitle("Warning");
        exit.setHeaderText("Are you sure?");
        String warning = "Would you like to save before you exit?";
        exit.setContentText(warning);

        ButtonType saveB = new ButtonType("Save");
        ButtonType exitB = new ButtonType("Exit");
        ButtonType cancelB = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        exit.getButtonTypes().setAll(saveB, exitB, cancelB);
        Optional<ButtonType> result = exit.showAndWait();

        if (result.get() == saveB) {
            fc = new FileChooser();
            fc.setTitle("Save Image");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG File", "*.PNG"),
                    new FileChooser.ExtensionFilter("JPG Files", "*.JPG"),
                    new FileChooser.ExtensionFilter("BMP Files", "*.BMP")
            );
            java.io.File saveFile = fc.showSaveDialog(null);
            if (saveFile != null) {
                try {
                    WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                    canvas.snapshot(null, wi);
                    RenderedImage ri = SwingFXUtils.fromFXImage(wi, null);
                    ImageIO.write(ri, "png", saveFile);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            Platform.exit();
            System.exit(0);
            stage.close();
        } else if (result.get() == exitB) {
            Platform.exit();
            System.exit(0);
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}