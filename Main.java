/**
 * Main.java created by laurenrohr on Macbook Pro in MilkWeight
 * 
 * A-Team 121 Final Project
 * 
 * Authors: Lauren Rohr (lmrohr@wisc.edu) Kiley Smith (add email) Luke Le Clair (add email) Anna
 * Keller (add email)
 * 
 * Date: 4/16/2020
 * 
 * Course: CS 400 Semester: Spring 2020
 * 
 * IDE: Eclipse IDE for Java Developers Version: Build id:
 * 
 * List Collaborators: Name, email@wisc.edu, lecture number
 * 
 * Other Credits: describe other source or people
 * 
 * Known Bugs: describe known unresolved bugs here
 */
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main - TODO Describe purpose of this user defined type
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 *
 */
public class Main extends Application {

  private static final int WINDOW_WIDTH = 1000;
  private static final int WINDOW_HEIGHT = 800;
  private static final String APP_TITLE = "Milk Weight";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    /*
     * Left panel: data input options stored in a vertical box
     */
    // Create vertical box
    VBox dataInputOptions = new VBox();
    dataInputOptions.setPrefWidth(200);
    // Create buttons and format
    Button newDataFile = buttonFormat("Upload Data File", 1);
    newDataFile.setMinWidth(dataInputOptions.getPrefWidth());
    Button addDataField = buttonFormat("Add Data Field", 1);
    addDataField.setMinWidth(dataInputOptions.getPrefWidth());
    Button removeData = buttonFormat("Remove Data Field", 1);
    removeData.setMinWidth(dataInputOptions.getPrefWidth());
    Button editDataField = buttonFormat("Edit Data Field", 1);
    editDataField.setMinWidth(dataInputOptions.getPrefWidth());
    // Formatting
    dataInputOptions.setAlignment(Pos.BASELINE_CENTER);
    dataInputOptions.getChildren().addAll(newDataFile, addDataField, removeData, editDataField);
    dataInputOptions.setSpacing(10);
    dataInputOptions.setPadding(new Insets(20, 20, 20, 20));
    // Add to main scene
    root.setLeft(dataInputOptions);

    /*
     * Center panel: view data on all farms
     */
    ToggleButton byYear = new ToggleButton("By year");
    ToggleButton byMonth = new ToggleButton("By month");
    final ToggleGroup group = new ToggleGroup();
    byYear.setToggleGroup(group);
    byMonth.setToggleGroup(group);
    byYear.setSelected(true); // start with year selected 
    // selection is an inner state of a toggle button, can access to see if button is selected
    // https://docs.oracle.com/javafx/2/ui_controls/toggle-button.htm#CACJDICF
    // https://docs.oracle.com/javafx/2/api/javafx/scene/control/ToggleGroup.html
    // can also access which toggle button of a group is selected ^
    HBox timeSelect = new HBox(new Label("All Farms: "), byYear, byMonth);
    VBox allFarms = new VBox();
    allFarms.getChildren().add(timeSelect);
    // Add to main scene
    root.setCenter(allFarms);
    
    

    // Create main scene
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Button constructor to keep formatting and styling consistent
   * 
   * @param label - text to be displayed on the button
   * @param color - 1 = red, 2 = green
   * @return a new button with specified text and color
   */
  private Button buttonFormat(String label, int color) {
    Button newButton = new Button(label);
    String styles = "-fx-font-size: 17px;" + "-fx-padding: 5;" + "-fx-border-width: 3px;";
    // + "-fx-border-color: #f00000;";

    if (color == 1)
      styles = styles + "-fx-background-color: #DFB951; "; // yellow
    else if (color == 2)
      styles = styles + "-fx-background-color: #00ff00; "; // green

    newButton.setStyle(styles);
    return newButton;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
