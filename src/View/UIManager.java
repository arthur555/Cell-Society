package View;

import Controller.Controller;
import Controller.XMLException;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

/*
    UIManager is a class that keeps track of all UI elements and handle their actions
    @author xp19
    @author sz165
 */

public class UIManager {

    // fields with @FXML annotation are automatically populated to reference the UI element
    // with the same name as its id in fxml
    @FXML
    private GridPane layoutGridPane;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Slider slider;
    @FXML
    private Pane gridPane;
    @FXML
    private Pane pane;
    @FXML
    private LineChart lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private Controller controller;
    private static final int MAX_FPS = 30;
    private static final String DEFAULT_SIZE = "20";
    private static final int HUNDRED = 100;
    private int oldNumRows;
    private int oldNumCols;
    private int oldFPS;

    // this method will be called automatically by the FXML loader
    public void initialize(){
        forceInputToBeNumeric(widthTextField);
        forceInputToBeNumeric(heightTextField);
        //TODO: have to think about how to get around this line
//        statsGraph = new StatsGraph(lineChart);
        controller = new Controller(gridPane, lineChart);
        try
        {
            controller.start();
        }
        catch(XMLException e)
        {
            return;
        }
    }

    // restrict the textfield to contain only numbers
    private void forceInputToBeNumeric(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        tf.setText(newValue.replaceAll("[^\\d]", ""));
                    }
        });
    }

    // you can get the file chosen from this method
    public void handleChooseAFileAction(){
        controller.start();
    }

    // you can get the updated value from the user input fields from this method
    public void handleApplyButtonAction(){
        if(heightTextField.getText().length()==0||widthTextField.getText().length()==0){
            return;
        }
        Map<String, String> attributes = new HashMap<>();
        attributes.put(Controller.NUM_ROW_ATTR, heightTextField.getText() );
        attributes.put(Controller.NUM_COL_ATTR, widthTextField.getText() );
        attributes.put(Controller.FPS, String.valueOf((int)((slider.getValue()/HUNDRED)*MAX_FPS)));
        if(oldNumCols!=Integer.parseInt(widthTextField.getText())||oldNumRows!=Integer.parseInt(heightTextField.getText())){
            oldNumCols = Integer.parseInt(widthTextField.getText());
            oldNumRows = Integer.parseInt(heightTextField.getText());
            gridPane.getChildren().clear();
            controller.update(attributes);
        }
        else if(oldFPS!=(int)((slider.getValue()/HUNDRED)*MAX_FPS)){
            oldFPS = (int)((slider.getValue()/HUNDRED)*MAX_FPS);
            controller.updateFPS((int)((slider.getValue()/HUNDRED)*MAX_FPS));
        }
    }

    public void handleStartButtonAction(){
        controller.resume();
    }

    public void handleStopButtonAction(){
        controller.stop();
    }

    public void handleResetButtonAction(){
        controller.reset();
    }

    public void handleStepButtonAction(){
        controller.animationStep();
    }

    // if the input is empty, use default value
    private String getOrDefaultValue(String s){
        if(s.length()==0) return DEFAULT_SIZE;
        else return s;
    }

}
