package com.example.paint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.shape.*;

import java.util.Stack;

/**
 * The Draw class implements the code required to facilitate drawing on the canvas.
 * Whether it's freedrawing, drawing shapes, or even adding text, all functionality is
 * added in here.
 * Sidenote: more commenting still needs to be added to make it more user-friendly.
 *
 * @author Franck Tsoungui
 * @version 1.05
 * @since 2023-10-6
 */

public class Draw {

    public double startX, startY, endX, endY, width, height;
    public static Stack<Shape> undoHistory, redohistory = new Stack<>();

    private GraphicsContext gc;
    private static int currentTool = 0;
    private final static String[] TOOL = {"Draw", "Line", "Square", "Circle", "Rectangle", "Ellipse", "Triangle", "Eraser", "Text", "Dashes", "rotate"};


    public void draw(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 1;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();

            undoHistory.push(new Line(startX, startY, endX, endY));
        });
    }

    public void line(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 2;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            endX = e.getX();
            endY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.strokeLine(startX, startY, endX, endY);

            undoHistory.push(new Line(startX, startY, endX, endY));
        });
    }

    public void square(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 3;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            width = Math.abs((e.getX() - startX));

            gc.fillRect(startX, startY, width, width);
            gc.strokeRect(startX, startY, width, width);

            undoHistory.push(new Rectangle(startX, startY, width, width));
        });
    }

    public void circle(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 4;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());


            width = Math.abs((e.getX() - startX));

            gc.fillOval(startX, startY, width, width);
            gc.strokeOval(startX, startY, width, width);

            undoHistory.push(new Ellipse(startX, startY, width, width));
        });
    }

    public void rect(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 5;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            width = Math.abs((e.getX() - startX));
            height = Math.abs((e.getY() - startY));

            gc.fillRect(startX, startY, width, height);
            gc.strokeRect(startX, startY, width, height);

            undoHistory.push(new Rectangle(startX, startY, width, height));
        });
    }

    public void ellipse(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 6;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            width = Math.abs((e.getX() - startX));
            height = Math.abs((e.getY() - startY));

            gc.fillOval(startX, startY, width, height);
            gc.strokeOval(startX, startY, width, height);

            undoHistory.push(new Ellipse(startX, startY, width, height));
        });
    }

    public void triangle(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox) {
        currentTool = 7;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());

            double[] xpoints = {startX, e.getX(), startX - (e.getX() - startX)};
            double[] ypoints = {startY, e.getY(), startY};

            gc.fillPolygon(xpoints, ypoints, 3);
            gc.strokePolygon(xpoints, ypoints, 3);
        });
    }

    public void erase(Canvas canvas, ChoiceBox choiceBox) {
        currentTool = 8;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(e -> {
            gc.clearRect(e.getX(), e.getY(), choiceBox.getSelectionModel().getSelectedIndex(), choiceBox.getSelectionModel().getSelectedIndex());
        });

        canvas.setOnMouseDragged(e -> {
            gc.clearRect(e.getX(), e.getY(), choiceBox.getSelectionModel().getSelectedIndex(), choiceBox.getSelectionModel().getSelectedIndex());
        });
    }

    public void text(Canvas canvas, ColorPicker colorPicker, TextField textField) {
        currentTool = 9;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(e -> {
            gc.setStroke(colorPicker.getValue());
            gc.strokeText(textField.getText(), e.getX(), e.getY());
        });
    }
    public void dashes(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox, Slider slider) {
        currentTool = 10;
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setLineDashes(slider.getValue());
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMousePressed(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setLineDashes(slider.getValue());
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.setStroke(colorPicker.getValue());
            gc.setLineDashes(slider.getValue());
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });
    }

    public void polygon(Canvas canvas, ColorPicker colorPicker, ChoiceBox choiceBox, Slider slider) {
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            int sides = (int) slider.getValue();
            double centerX = e.getX();
            double centerY = e.getY();
            double radius = Math.min(centerX, centerY) - 20;
            double angleIn = 2.0 * Math.PI / sides;

            gc.setStroke(colorPicker.getValue());
            gc.setLineWidth(choiceBox.getSelectionModel().getSelectedIndex());
            gc.beginPath();

            double x = centerX + radius;
            double y = centerY;

            for (int i = 1; i <= sides; i++) {
                x = centerX + radius * Math.cos(i * angleIn);
                y = centerY +  radius * Math.sin(i * angleIn);
                gc.lineTo(x, y);
            }

            gc.closePath();
            gc.stroke();
        });
    }

    public void rotate(ScrollPane scrollPane, ChoiceBox choiceBox) {
        currentTool = 11;
        scrollPane.getContent().setRotate(scrollPane.getContent().getRotate() + choiceBox.getSelectionModel().getSelectedIndex());
    }

    public static String getCurrentButton() {
        return TOOL[currentTool];
    }
    public void undo() {
        if (!undoHistory.empty()) {
            gc.clearRect(0, 0, 200, 200);
            Shape remove = undoHistory.lastElement();
            if (remove.getClass() == Line.class) {
                Line temLine = (Line) remove;
                temLine.setFill(gc.getFill());
                temLine.setStroke(gc.getStroke());
                temLine.setStrokeWidth(gc.getLineWidth());
                redohistory.push(new Line(temLine.getStartX(), temLine.getStartY(), temLine.getEndX(), temLine.getEndY()));
            } else if (remove.getClass() == Rectangle.class) {
                Rectangle temRect = (Rectangle) remove;
                temRect.setFill(gc.getFill());
                temRect.setStroke(gc.getStroke());
                temRect.setStrokeWidth(gc.getLineWidth());
                redohistory.push(new Rectangle(temRect.getX(), temRect.getY(),  temRect.getWidth(), temRect.getHeight()));
            }  else if (remove.getClass() == Ellipse.class) {
                Ellipse temElps = (Ellipse) remove;
                temElps.setFill(gc.getFill());
                temElps.setStroke(gc.getStroke());
                temElps.setStrokeWidth(gc.getLineWidth());
                redohistory.push(new Ellipse(temElps.getCenterX(), temElps.getCenterY(), temElps.getRadiusX(), temElps.getRadiusY()));
            }
            Shape lastRedo = redohistory.lastElement();
            lastRedo.setFill(gc.getFill());
            lastRedo.setStroke(gc.getStroke());
            lastRedo.setStrokeWidth(gc.getLineWidth());
            undoHistory.pop();

            for (int i = 0; i < undoHistory.size(); i++) {
                Shape shape = undoHistory.elementAt(i);
                if (shape.getClass() == Line.class) {
                    Line temL = (Line) shape;
                    gc.setFill(temL.getFill());
                    gc.setStroke(temL.getStroke());
                    gc.setLineWidth(temL.getStrokeWidth());
                    gc.strokeLine(temL.getStartX(), temL.getStartY(), temL.getEndX(), temL.getEndY());
                } else if (shape.getClass() == Rectangle.class) {
                    Rectangle temR = (Rectangle) shape;
                    gc.setFill(temR.getFill());
                    gc.setStroke(temR.getStroke());
                    gc.setLineWidth(temR.getStrokeWidth());
                    gc.fillRect(temR.getX(), temR.getY(), temR.getWidth(), temR.getHeight());
                    gc.strokeRect(temR.getX(), temR.getY(), temR.getWidth(), temR.getHeight());
                }  else if (shape.getClass() == Ellipse.class) {
                    Ellipse temE = (Ellipse) shape;
                    gc.setFill(temE.getFill());
                    gc.setStroke(temE.getStroke());
                    gc.setLineWidth(temE.getStrokeWidth());
                    gc.fillOval(temE.getCenterX(), temE.getCenterY(), temE.getRadiusX(), temE.getRadiusY());
                    gc.strokeOval(temE.getCenterX(), temE.getCenterY(), temE.getRadiusX(), temE.getRadiusY());
                }
            }
        } else {
            System.out.println("No undo action");
        }
    }

    public void redo() {

        if (!redohistory.empty()) {
            Shape shape = redohistory.lastElement();
            gc.setFill(shape.getFill());
            gc.setStroke(shape.getStroke());
            gc.setLineWidth(shape.getStrokeWidth());

            redohistory.pop();

            if (shape.getClass() == Line.class) {
                Line temL = (Line) shape;
                gc.strokeLine(temL.getStartX(), temL.getStartY(), temL.getEndX(), temL.getEndY());

                undoHistory.push(new Line(temL.getStartX(), temL.getStartY(), temL.getEndX(), temL.getEndY()));
            } else if (shape.getClass() == Rectangle.class) {
                Rectangle temR = (Rectangle) shape;
                gc.fillRect(temR.getX(), temR.getY(), temR.getWidth(), temR.getHeight());
                gc.strokeRect(temR.getX(), temR.getY(), temR.getWidth(), temR.getHeight());

                undoHistory.push(new Rectangle(temR.getX(), temR.getY(), temR.getWidth(), temR.getHeight()));
            } else if (shape.getClass() == Ellipse.class) {
                Ellipse temE = (Ellipse) shape;
                gc.fillOval(temE.getCenterX(), temE.getCenterY(), temE.getRadiusX(), temE.getRadiusY());
                gc.strokeOval(temE.getCenterX(), temE.getCenterY(), temE.getRadiusX(), temE.getRadiusY());

                undoHistory.push(new Ellipse(temE.getCenterX(), temE.getCenterY(), temE.getRadiusX(), temE.getRadiusY()));
            }

            Shape lastUndo = undoHistory.lastElement();
            lastUndo.setFill(gc.getFill());
            lastUndo.setStroke(gc.getStroke());
            lastUndo.setStrokeWidth(gc.getLineWidth());
        } else {
            System.out.println("No redo action");
        }
    }
}
