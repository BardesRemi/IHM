package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {

    @FXML private Button deleteBut;
    @FXML private Button cloneBut;
    @FXML public RadioButton selectMoveBut;
    @FXML public RadioButton ellipseBut;
    @FXML public RadioButton rectBut;
    @FXML public RadioButton lineBut;
    @FXML private ColorPicker colorChose;
    @FXML private Canvas drawZone;
    private EventHandler deleteButListener;
    private EventHandler cloneButListener;
    private EventHandler selectMoveButListener;
    private EventHandler ellipseButListener;
    private EventHandler rectButListener;
    private EventHandler lineButListener;
    private EventHandler colorChoseListener;
    private EventHandler drawZonetListener;

    //On créé notre Context graphic pour le canvas
    GraphicsContext gc;

    // variable qui définit ce qu'on dessine
    private String canvaType;

    // variable qui définit la couleur du pinceau
    private Color currentColor;

    //pour grouper les radio button
    ToggleGroup group = new ToggleGroup();

    //On créer un tableau de forms qui sauvegardera tout ce qui doit etre affiché
    ArrayList<Forms> tab = new ArrayList<Forms>();

    @FXML public void initialize() {
        gc = drawZone.getGraphicsContext2D();
        initListener();

        // Permet qu'un seul radio button soit affiché comme selectionné à la fois
        selectMoveBut.setToggleGroup(group);
        ellipseBut.setToggleGroup(group);
        rectBut.setToggleGroup(group);
        lineBut.setToggleGroup(group);

        // on initialise tout les listener d'évènement.
        cloneBut.setOnAction(cloneButListener);
        deleteBut.setOnAction(deleteButListener);
        selectMoveBut.setOnAction(selectMoveButListener);
        ellipseBut.setOnAction(ellipseButListener);
        rectBut.setOnAction(rectButListener);
        lineBut.setOnAction(lineButListener);
        colorChose.setOnAction(colorChoseListener);
        drawZone.setOnMouseClicked(drawZonetListener);
    }

/** On reset le canvas afin d'afficher chaques changement **/
/*  Je constate un bug, si la couleur n'est pas initialisé , les couleurs vont changées*/

    public void Reset (){
        gc.clearRect(0,0,442,401);
        for (Forms f: tab) {
            gc.setFill(f.getColor());
            gc.setStroke(f.getColor());
            if(f.getform()=="ellipse"){
                gc.fillOval(f.getX(), f.getY(), 20, 30);
            }
            else if(f.getform()=="rect"){
                gc.fillRect(f.getX(), f.getY(), 30, 40);
            }
            else if(f.getform()=="line"){
                gc.strokeLine(f.getX(), f.getY(), f.getX()+30, f.getY());
            }
        }
    }


    public void initListener(){
        cloneButListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                if(canvaType == "Move"){
                    drawZone.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if(gc.isPointInPath(event.getX(), event.getY())){
                                    }
                                }
                            });
                }
            }
        };

        deleteButListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                if(canvaType == "Move"){
                    drawZone.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                }
                            });
                }
            }
        };

        /** On met la variable qui défini ce qu'on va pouvoir dessiner à jour **/
        selectMoveButListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                canvaType = "Move";
                System.out.println(canvaType);
            }
        };

        ellipseButListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                canvaType = "ellipse";
                System.out.println(canvaType);
            }
        };

        rectButListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                canvaType = "rect";
                System.out.println(canvaType);
            }
        };

        lineButListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                canvaType= "line";
                System.out.println(canvaType);
            }
        };

        /** On récupère la couleur demandé par l'utilisateur et on la défini comme couleur active pour le canvas**/
        colorChoseListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                currentColor = colorChose.getValue();
                System.out.println(currentColor);
            }
        };

        /** Réalisation de l'animation voulue lors du click **/
        /*  possibilité de commenter les lignes qui fonctionnes avec l'objet "Forms" (ainsi que Reset) et de
        décommenter celle qui dessinent les formes de façon directe via GC afin de ne pas avoir de bug*/
        drawZonetListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.setLineWidth(5);
                if(canvaType == "Move"){

                }
                else{
                    if(canvaType == "ellipse"){
                        tab.add(new Forms("ellipse", event.getX(), event.getY(),currentColor));
                        //gc.fillOval(event.getX(), event.getY(), 20, 30);
                    }
                    if(canvaType == "rect"){
                        tab.add(new Forms("rect", event.getX(), event.getY(),currentColor));
                        //gc.fillRect(event.getX(), event.getY(), 30, 40);
                    }
                    if(canvaType == "line"){
                        tab.add(new Forms("line", event.getX(), event.getY(),currentColor));
                        //gc.strokeLine(event.getX(), event.getY(), event.getX()+30, event.getY());
                    }
                    Reset();
                }
            }
        };
    }
}
