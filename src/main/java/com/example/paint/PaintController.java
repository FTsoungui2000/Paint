package com.example.paint;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class PaintController implements Initializable {

    Draw draw = new Draw();
    File file = new File();
    Edit edit = new Edit();
    String[] size = {"1", "2", "4", "6"};
    String[] rotateAmount = {"-90", "90", "-180", "180","-270", "270"};

    private GraphicsContext gc;
    private Timer loggerTimer;
    private TimerTask logger;

    public final static String LOGS_PATH = "logs.txt";

    @FXML
    private MenuItem open, save, saveAs, newTab, help, options;
    @FXML
    private MenuItem r9, l9, r18, l18, r27, l27;
    @FXML
    private Button pencil, pen, eraser, text, square, circle, rectangle, ellipse, triangle, dash, rotate;
    @FXML
    private ColorPicker cp;
    @FXML
    private ChoiceBox<String> drawWidth, rotateAngle;
    @FXML
    private Canvas canvas;
    @FXML
    private Slider sides, dashes;
    @FXML
    private TabPane tabPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Tab tab;
    @FXML
    private TextField canvasWidth, canvasHeight, inputText;
    @FXML
    private Label countdown;

    public void initialize(URL arg0, ResourceBundle arg1) {
        tab.setText("Canvas 1");

        //Initializes a canvas when the projects open, Image not needed
        gc = canvas.getGraphicsContext2D();
        draw.draw(canvas, cp, drawWidth);

        pencil.setTooltip(new Tooltip("Free draw"));
        pen.setTooltip(new Tooltip("Straight Line"));
        eraser.setTooltip(new Tooltip("Erase"));
        text.setTooltip(new Tooltip("Write Text"));
        square.setTooltip(new Tooltip("Square"));
        circle.setTooltip(new Tooltip("Circle"));
        rectangle.setTooltip(new Tooltip("Rectangle"));
        ellipse.setTooltip(new Tooltip("Ellipse"));
        triangle.setTooltip(new Tooltip("Triangle"));
        dash.setTooltip(new Tooltip("Dashed Lines"));
        rotate.setTooltip(new Tooltip("Rotate"));


        drawWidth.getItems().addAll(size);
        drawWidth.getSelectionModel().getSelectedItem();
        rotateAngle.getItems().addAll(rotateAmount);
        rotateAngle.getSelectionModel().getSelectedItem();

        //Initialize the Keyboard shortcuts
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
        newTab.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN));
        help.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        options.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.ALT_DOWN));

        //loggerTimer = new Timer();
        //logger = new TimerTask() {
        //    @Override
        //    public void run() {
        //        java.io.File loggerFile = new java.io.File(LOGS_PATH);
        //        try{
        //            loggerFile.createNewFile();
        //        } catch (Exception ex) {
        //            System.out.println(ex);
        //        }
        //        try {
        //            FileWriter fileWriter = new FileWriter(LOGS_PATH, true);
        //            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //            bufferedWriter.write(draw.getCurrentButton(), LocalDate.now(), LocalTime.now());
        //            bufferedWriter.newLine();
        //            bufferedWriter.close();
        //        } catch (Exception ex) {
        //            System.out.println(ex);
        //        }
        //    }
        //};
    }

    @FXML
    public  void resizeCanvas() {
        try{

            double newWidth = Double.parseDouble(canvasWidth.getText());
            double newHeigth = Double.parseDouble(canvasHeight.getText());

            if (newWidth <= 0 || newHeigth <= 0) {
                System.out.println("Invalid Dimensions");
            } else {
                canvas.setWidth(newWidth);
                canvas.setHeight(newHeigth);
            }
        } catch (NumberFormatException event) {
            System.out.println(event.getMessage());
        }
    }

    @FXML
    protected void open() {
        file.open(tabPane);
    }

    @FXML
    protected void save() {
        file.save(canvas);
    }

    @FXML
    protected void saveAs() {
        file.saveAs(canvas);
        file.autoSave(canvas, countdown);
    }

    @FXML
    protected void newTabB() {
        file.newTab(tabPane, canvas, cp, drawWidth);
    }

    @FXML
    protected void newCanvas() {
        edit.newCanvas(canvas, gc);
    }

    @FXML
    protected void pencilB() {
        draw.draw(canvas, cp, drawWidth);
    }

    @FXML
    protected void penB() {
        draw.line(canvas, cp, drawWidth);
    }

    @FXML
    protected void eraserB() {
        draw.erase(canvas, drawWidth);
    }

    @FXML
    protected void textB() {
        draw.text(canvas, cp, inputText);
    }

    @FXML
    protected void squareB() {
        draw.square(canvas, cp, drawWidth);
    }

    @FXML
    protected void circleB() {
        draw.circle(canvas, cp, drawWidth);
    }

    @FXML
    protected void rectangleB() {
        draw.rect(canvas, cp, drawWidth);
    }

    @FXML
    protected void ellipseB() {
        draw.ellipse(canvas, cp, drawWidth);
    }

    @FXML
    public void trianlgeB() {
        draw.triangle(canvas, cp, drawWidth);
    }

    @FXML
    public void polygonB() {
        draw.polygon(canvas, cp, drawWidth, sides);
    }

    @FXML
    public void dashes() {
        draw.dashes(canvas, cp, drawWidth, dashes);
    }

    @FXML
    protected void undo() {
        draw.undo();
    }

    @FXML
    protected void redo() {
        draw.redo();
    }

    @FXML
    protected void r9MI() {
        edit.rotateR9(scrollPane);
    }

    @FXML
    protected void l9MI() {
        edit.rotateL9(scrollPane);
    }

    @FXML
    protected void r18MI() {
        edit.rotateR18(scrollPane);
    }

    @FXML
    protected void l18MI() {
        edit.rotateL18(scrollPane);
    }

    @FXML
    protected void r27MI() {
        edit.rotateR27(scrollPane);
    }

    @FXML
    protected void l27MI() {
        edit.rotateL27(scrollPane);
    }

    @FXML
    protected void mirrorHB() {
        edit.mirrorH(scrollPane);
    }

    @FXML
    protected void mirrorVB() {
        edit.mirrorV(scrollPane);
    }

    @FXML
    protected void rotateB() {
        draw.rotate(scrollPane, rotateAngle);
    }
}