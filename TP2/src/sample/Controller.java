package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import java.util.Observable;

public class Controller {

    protected ObservableList<String> father_d;
    protected ObservableList<String> son_d;

    protected FileSelector fs = new FileSelector();

    @FXML private Button cancelbut;
    @FXML private Button openbut;
    @FXML private ComboBox<String> CB;
    @FXML private ListView<String> LV;
    private EventHandler cancelbutListener;
    private EventHandler searchFileListener;
    private EventHandler CBListener;
    private EventHandler LVListener;


    @FXML public void initialize(){

        initListener();
        cancelbut.setOnAction(cancelbutListener);
        openbut.setOnAction(searchFileListener);
        CB.setOnAction(CBListener);
        LV.setOnMouseClicked(LVListener);

        for (String test  : fs.getListRepParent(System.getProperty("user.dir"))){
            System.out.println(test);
        }

        father_d = FXCollections.observableArrayList(fs.getListRepParent(System.getProperty("user.dir")));
        son_d = FXCollections.observableArrayList(fs.getListFile(System.getProperty("user.dir")));

        CB.setItems(father_d);
        LV.setItems(son_d);
    }

    public void initListener(){
        cancelbutListener = new EventHandler(){
            @Override
            public void handle(Event event) {
                System.out.println("404");
                Platform.exit();
            }
        };

        CBListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("404");
                String selection =  CB.getSelectionModel().getSelectedItem().toString();
                father_d = FXCollections.observableArrayList(fs.getListRepParent(selection));
                son_d = FXCollections.observableArrayList(fs.getListFile(selection));

                CB.setItems(father_d);
                LV.setItems(son_d);
            }
        };

        LVListener = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if(event.getClickCount() ==2 ){
                    System.out.println("404");
                    String selection =  LV.getSelectionModel().getSelectedItem().toString();
                    father_d = FXCollections.observableArrayList(fs.getListRepParent(selection));
                    son_d = FXCollections.observableArrayList(fs.getListFile(selection));

                    CB.setItems(father_d);
                    LV.setItems(son_d);
                }
                else{
                }
            }
        };

        searchFileListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("404");
                String selection = LV.getSelectionModel().getSelectedItem().toString();
                if (selection != null) {
                    father_d = FXCollections.observableArrayList(fs.getListRepParent(selection));
                    son_d = FXCollections.observableArrayList(fs.getListFile(selection));

                    CB.setItems(father_d);
                    LV.setItems(son_d);
                } else {
                }
            }
        };


    }

}
