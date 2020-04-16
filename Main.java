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

import java.io.File;
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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main - TODO Describe purpose of this user defined type
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 *
 */
public class Main extends Application {

  private static final int WINDOW_WIDTH = 900;
  private static final int WINDOW_HEIGHT = 700;
  private static final String APP_TITLE = "Milk Weight";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    /*
     * Left panel: data input options stored in a vertical box
     */
    // Create vertical box
    VBox dataInputOptions = vboxFormat();
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
    // Add buttons
    dataInputOptions.getChildren().addAll(newDataFile, addDataField, removeData, editDataField);
    // Add to main scene
    root.setLeft(dataInputOptions);

    /*
     * Handle button presses
     */
    newDataFile.setOnAction(e -> newDataFile(primaryStage));



    /*
     * Center panel: view data on all farms
     */
    Label allFarmLabel = new Label("All Farms: ");
    allFarmLabel.setFont(new Font("Arial", 15));
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
    HBox timeSelect = hboxFormat();
    timeSelect.setPrefWidth(150); // Preferred with of each element in the hbox
    timeSelect.getChildren().addAll(allFarmLabel, byYear, byMonth);
    VBox allFarms = vboxFormat();
    allFarms.getChildren().add(timeSelect);
    // Add to main scene
    root.setCenter(allFarms);

    /*
     * Bottom panel:
     */
    // Statistics Per Farm
    TextField s1FarmID = new TextField();
    TextField s1Year = new TextField();
    VBox statPerFarm =
        bottomStatTitles("Statistics Per Farm", "Farm ID: ", "Year: ", s1FarmID, s1Year);
    // TODO add tables to vbox

    // Statistics Per All Farms
    TextField s2Month = new TextField();
    TextField s2Year = new TextField();
    VBox statAllFarm =
        bottomStatTitles("Statistics Per All Farms", "Month: ", "Year: ", s2Month, s2Year);
    // TODO add tables to vbox

    // Farm Share of Net Sales
    TextField s3FarmID = new TextField();
    TextField s3Year = new TextField();
    VBox statNetSales =
        bottomStatTitles("Farm Share of Net Sales", "Farm ID: ", " Year: ", s3FarmID, s3Year);
    // TODO add tables to vbox

    // Add to bottom pane in horizontal box
    HBox bottomPane = hboxFormat();
    bottomPane.setAlignment(Pos.BASELINE_CENTER);
    bottomPane.getChildren().addAll(statPerFarm, statAllFarm, statNetSales);
    root.setBottom(bottomPane);



    // Create main scene
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }


  /**
   * Handles the first dialog window: Upload Data File
   */
  public void newDataFile(Stage primaryStage) {
    String title = "Upload Data File";
    
    VBox vbox = vboxFormat();

    Label title1 = new Label("Input File Name with Extension");
    title1.setFont(new Font("Arial", 15));
    Label direction = new Label("enter file name, hit enter, then done");
    direction.setFont(new Font("Arial", 10));
    TextField userInput = new TextField();
    // TODO program text field event, this is where the file name will be collected, send to another
    // class to handle! Below is a possible method call from the event
    // userInput.setOnAction(e -> newDataFile(userInput.getText()));

    vbox.getChildren().addAll(title1, userInput, direction);
    showDialogWindow(primaryStage, vbox, title);
  }

  /**
   * Show when event occurs
   * 
   * @param primaryStage
   * @param display
   */
  public void showDialogWindow(Stage primaryStage, VBox vbox, String title) {
    // Create layout 
    BorderPane layout = new BorderPane();
    // Add upload button
    Button done = buttonFormat("Done", 3);
    vbox.getChildren().add(done);
    layout.setCenter(vbox);
    
    // Show Modal Dialog Window
    Scene inputScene = new Scene(layout, 300, 200);
    final Stage userInputs = new Stage();
    userInputs.initModality(Modality.APPLICATION_MODAL);
    userInputs.initOwner(primaryStage);
    userInputs.setScene(inputScene);
    userInputs.setTitle(title);
    userInputs.show();
    
    // Add functionality to upload button 
    done.setOnAction(e -> userInputs.close());
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
    newButton.setFont(new Font("Arial", 15));
    String styles = "-fx-padding: 5;" + "-fx-border-width: 3px;";

    if (color == 1)
      styles = styles + "-fx-background-color: #DFB951; "; // yellow
    else if (color == 2)
      styles = styles + "-fx-background-color: #00ff00; "; // green

    newButton.setStyle(styles);
    return newButton;
  }

  /**
   * Consistent formatting for all vertical boxes.
   * 
   * @return a formatted VBox
   */
  private VBox vboxFormat() {
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.BASELINE_CENTER);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(20, 20, 20, 20));
    return vbox;
  }

  /**
   * Consistent formatting for all horizontal boxes.
   * 
   * @return formatted hbox
   */
  private HBox hboxFormat() {
    HBox hbox = new HBox(10); // 10 = spacing between elements
    return hbox;
  }
  

  private VBox bottomStatTitles(String title1, String in1, String in2, TextField userInput1,
      TextField userInput2) {
    VBox vbox = vboxFormat();
    Label title = new Label(title1);
    title.setFont(new Font("Arial", 15));
    title.setStyle("-fx-font-weight: bold");
    // Input 1
    HBox hbox1 = hboxFormat();
    Label input1 = new Label(in1);
    input1.setFont(new Font("Arial", 15));
    hbox1.getChildren().addAll(input1, userInput1);

    // Input 2
    HBox hbox2 = hboxFormat();
    Label input2 = new Label(in2);
    input2.setFont(new Font("Arial", 15));
    hbox2.getChildren().addAll(input2, userInput2);

    vbox.getChildren().addAll(title, hbox1, hbox2);

    return vbox;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
