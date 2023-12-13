package com.example.paint;

import java.util.Optional;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

public class Edit {

    public void newCanvas(Canvas canvas, GraphicsContext graphicsContext) {
        Alert clear = new Alert(Alert.AlertType.WARNING);
        clear.setTitle("Confirmation");
        clear.setHeaderText("Clear Canvas");
        clear.setContentText("Are you sure?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        clear.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = clear.showAndWait();
        if (result.get() == yes) {
            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    public void rotateR9(ScrollPane scrollPane) {
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() + 90);
    }

    public void rotateL9(ScrollPane scrollPane) {
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() - 90);
    }

    public void rotateR18(ScrollPane scrollPane) {
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() + 180);
    }

    public void rotateL18(ScrollPane scrollPane) {
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() - 180);
    }

    public void rotateR27(ScrollPane scrollPane) {
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() + 270);
    }

    public void rotateL27(ScrollPane scrollPane) {
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() - 270);
    }

    public void mirrorH(ScrollPane scrollPane) {
        scrollPane.getContent().setScaleX(-scrollPane.getContent().getScaleX());
    }

    public void mirrorV(ScrollPane scrollPane) {
        scrollPane.getContent().setScaleY(-scrollPane.getContent().getScaleY());
    }
}