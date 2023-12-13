package com.example.paint;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

public class File extends Tab {

    private FileChooser fc;
    private Tab tab;
    private ScrollPane scrollPane;
    private Canvas newCanvas;
    private GraphicsContext gc;
    private Timer timer = null;
    TimerTask aSave;
    Draw draw = new Draw();
    int tabNum = 2;
    int delay = 30000;
    private Integer time = 30;

    public void open(TabPane tabpane) {
       tab = tabpane.getSelectionModel().getSelectedItem();
       scrollPane = (ScrollPane) tab.getContent();
       newCanvas = (Canvas) scrollPane.getContent();
        //Opens a file chooser window that allows the listed file types
        fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*"),
                new FileChooser.ExtensionFilter("JPG Files", "*.JPG"),
                new FileChooser.ExtensionFilter("BMP Files", "*.BMP"),
                new FileChooser.ExtensionFilter("PNG Files", "*.PNG")
        );
        java.io.File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            //Resizes the canvas to fit the entire image
            Image pic = new Image(selectedFile.getAbsolutePath());
            newCanvas.setWidth(pic.getWidth());
            newCanvas.setHeight(pic.getHeight());
            gc = newCanvas.getGraphicsContext2D();
            gc.drawImage(pic, 0, 0);
        }
    }

    public void save(Canvas canvas) {
        fc = new FileChooser();
        String saveLocation = fc.getInitialDirectory().getPath();
        java.io.File saveFile = new java.io.File(saveLocation);
        try {
            //Creates an image with the parameters of the canvas, then saves the snapshot of the enter canvas as a "png" file
            WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, wi);
            RenderedImage ri = SwingFXUtils.fromFXImage(wi, null);
            ImageIO.write(ri, "png", saveFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String newLocation = saveFile.getParent();
        fc.setInitialDirectory(new java.io.File(newLocation));
    }

    public void saveAs(Canvas canvas) {
        fc = new FileChooser();
        fc.setTitle("Save Image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG File", "*.PNG"),
                new FileChooser.ExtensionFilter("JPG Files", "*.JPG"),
                new FileChooser.ExtensionFilter("BMP Files", "*.BMP"),
                new FileChooser.ExtensionFilter("PDF File", "*.PDF")
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
    }
    public void newTab(TabPane tabPane,Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        tab = new Tab();
        scrollPane = new ScrollPane();
        canvas = new Canvas(200, 200);

        gc = canvas.getGraphicsContext2D();
        draw.draw(canvas, colorPicker, choiceBox);

        scrollPane.setContent(canvas);
        tab.setContent(scrollPane);
        tab.setText("Canvas " + tabNum);
        tabNum ++;
        tabPane.getTabs().add(tab);
    }

    public void autoSave(Canvas canvas, Label label) {
        label.setText(time.toString());
        aSave = new TimerTask() {
            @Override
            public void run() {
                time--;
                label.setText(time.toString());
                    save(canvas);
            }
        };
        timer.scheduleAtFixedRate(aSave, delay, delay);
    }
}
