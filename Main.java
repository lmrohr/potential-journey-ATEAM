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
 * List Collaborators: N/A
 * 
 * Other Credits: https://docs.oracle.com/javafx/2/ui_controls/table-view.htm (how to use table view
 * to add rows) TA office hours
 * 
 * Known Bugs: N/A
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main - TODO Describe purpose of this user defined type Main sets up the GUI.
 * It includes formatting of components, creation of add, edit, and remove
 * buttons, and graphic displays for visualizing data.
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 *
 */
public class Main extends Application implements EventHandler<ActionEvent> {

	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 700;
	private static final String APP_TITLE = "Milk Weight";
	private int counter = 2010; // Default year setting for 'Add data'

	// TODO get list of available years and Farms from farm class
	private FarmLog log = new FarmLog();
	private ObservableList<String> Farms = FXCollections.observableArrayList("F004", "F001", "F005"); //log.getFarms(); // TODO is this right
	private ObservableList<String> years = FXCollections.observableArrayList("2020", "2015", "2000");
	private static final ObservableList<String> months = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
			"7", "8", "9", "10", "11", "12");
	private static final ObservableList<String> days = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
			"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
			"25", "26", "27", "28", "29", "30", "31");
	private CreateReport farmList;
	// Sample Output Data
	final ObservableList<Result> data = FXCollections.observableArrayList(new Result("Report Type", "Year"),
			new Result("Date", "2018"), new Result("Farm ID(s)", "F005"), new Result("Milk Amount", "23400"));
	// Sample data for the farm chart
//  final ObservableList<Data> data2 = FXCollections.observableArrayList(
//      new Data("06/08/2010", "F001", "25.00"), new Data("09/24/2012", "F002", "32.00", "8"),
//      new Data("07/12/2014", "F005", "22.76", "19"));
	public static ObservableList<Data> dataList = FXCollections.observableArrayList();

	/**
	 * Data type that stores the resulting processed data to be displayed
	 */
	public static class Result {
		private final SimpleStringProperty label;
		private final SimpleStringProperty result;

		private Result(String lbl, String rslt) {
			this.label = new SimpleStringProperty(lbl);
			this.result = new SimpleStringProperty(rslt);
		}

		public String getLabel() {
			return label.get();
		}

		public void setLabel(String lbl) {
			label.set(lbl);
		}

		public String getResult() {
			return result.get();
		}

		public void setResult(String rslt) {
			result.set(rslt);
		}
	}

	/**
	 * Turns the data into a format that can be displayed in the table
	 * 
	 */
	public static class Data {
		private final SimpleStringProperty date;
		private final SimpleStringProperty farmID;
		private final SimpleStringProperty milk;

		Data(String date, String farm, String milk) {
			this.date = new SimpleStringProperty(date);
			this.farmID = new SimpleStringProperty(farm);
			this.milk = new SimpleStringProperty(milk);
		}

		public String getDate() {
			return date.get();
		}

		public String getFarmID() {
			return farmID.get();
		}

		public String getMilk() {
			return milk.get();
		}

		public void addList(ArrayList<Data> toAdd) {
			for (int i = 0; i < toAdd.size(); i++) {
				dataList.add(toAdd.get(i));
			}
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialize the CreateReport object
		farmList = new CreateReport();

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
		Button addDataPoint = buttonFormat("Add Data Field", 1);
		addDataPoint.setMinWidth(dataInputOptions.getPrefWidth());
		Button editDataField = buttonFormat("Edit Data Field", 1);
		editDataField.setMinWidth(dataInputOptions.getPrefWidth());
		Button removeData = buttonFormat("Remove Data Field", 1);
		removeData.setMinWidth(dataInputOptions.getPrefWidth());
		// New table to store the results of the report
		TableView<Result> results = new TableView<>();
		TableColumn<Result, String> label = new TableColumn("Label");
		TableColumn<Result, String> output = new TableColumn("Result");

		label.setMinWidth(100);
		output.setMinWidth(100);
		// sets each type of the different columns
		label.setCellValueFactory(new PropertyValueFactory<Result, String>("label"));
		output.setCellValueFactory(new PropertyValueFactory<Result, String>("result"));
		results.setItems(data);
		results.getColumns().addAll(label, output);
		root.setLeft(dataInputOptions);
		// Add buttons
		dataInputOptions.getChildren().addAll(newDataFile, addDataPoint, editDataField, removeData, results);
		// Add to main scene

		/*
		 * Handle button presses
		 */
		newDataFile.setOnAction(e -> newDataFile(primaryStage));
		addDataPoint.setOnAction(e -> addDataField(primaryStage));
		editDataField.setOnAction(e -> editDataField(primaryStage));
		removeData.setOnAction(e -> removeDataField(primaryStage));

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
		// selection is an inner state of a toggle button, can access to see if button
		// is selected
		// https://docs.oracle.com/javafx/2/ui_controls/toggle-button.htm#CACJDICF
		// https://docs.oracle.com/javafx/2/api/javafx/scene/control/ToggleGroup.html
		// can also access which toggle button of a group is selected ^
		HBox timeSelect = hboxFormat();
		timeSelect.setPrefWidth(150); // Preferred with of each element in the hbox
		timeSelect.getChildren().addAll(allFarmLabel, byYear, byMonth);
		// TODO create display tables
		TableView<Data> table = new TableView<Data>();
		TableColumn<Data, String> column1 = new TableColumn<>("Date");
		TableColumn<Data, String> column2 = new TableColumn<>("Farm ID");
		TableColumn<Data, String> column3 = new TableColumn<>("Milk Amount (lbs)");
		TableColumn<Data, String> column4 = new TableColumn<>("Percent Share");
		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column3.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column4.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column1.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));
		column2.setCellValueFactory(new PropertyValueFactory<Data, String>("farmID"));
		column3.setCellValueFactory(new PropertyValueFactory<Data, String>("milk"));
		column4.setCellValueFactory(new PropertyValueFactory<Data, String>("percent"));
		// table.setItems(data2);
		table.getColumns().addAll(column1, column2, column3, column4);

		VBox allFarms = vboxFormat();
		allFarms.getChildren().addAll(timeSelect, table);
		// Add to main scene
		root.setCenter(allFarms);

		/*
		 * Bottom panel:
		 */
		// Statistics Per Farm
		ComboBox<String> s1FarmID = new ComboBox<String>(Farms);
		ComboBox<String> s1Year = new ComboBox<String>(years);
		Button eb1 = new Button("Enter");
		// TextField s1FarmID = new TextField();
		// TextField s1Year = new TextField();
		VBox statPerFarm = bottomStatTitles("Statistics Per Farm", "Farm ID: ", "Year: ", s1FarmID, s1Year, eb1);
		// TODO add tables to vbox

		// Statistics Per All Farms
		ComboBox<String> s2Month = new ComboBox<String>(months);
		ComboBox<String> s2Year = new ComboBox<String>(years);
		Button eb2 = new Button("Enter");
		// TextField s2Month = new TextField();
		// TextField s2Year = new TextField();
		VBox statAllFarm = bottomStatTitles("Statistics Per All Farms", "Month: ", "Year: ", s2Month, s2Year, eb2);
		// TODO add tables to vbox

		// Farm Share of Net Sales
		ComboBox<String> s3FarmID = new ComboBox<String>(Farms);
		ComboBox<String> s3Year = new ComboBox<String>(years);
		Button eb3 = new Button("Enter");
		// TextField s3FarmID = new TextField();
		// TextField s3Year = new TextField();
		VBox statNetSales = bottomStatTitles("Farm Share of Net Sales", "Farm ID: ", " Year: ", s3FarmID, s3Year, eb3);
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
	 * 
	 * @param primaryStage - main GUI
	 */
	public void newDataFile(Stage primaryStage) {
		String title = "Upload Data File";

		VBox vbox = vboxFormat();
		// Label direction1 = new Label("Enter Farm ID");
		// direction1.setFont(new Font("Arial", 15));
		// TextField farmID = new TextField();
		// HBox hbox = hboxFormat();
		// hbox.getChildren().addAll(direction1, farmID);

		Label title1 = new Label("Input File Name with Extension");
		title1.setFont(new Font("Arial", 15));
		Label direction2 = new Label("enter file name, hit enter, then done");
		direction2.setFont(new Font("Arial", 10));
		TextField userInput = new TextField();
		// TODO program text field event, this is where the file name will be collected,
		// send to another
		// class to handle! Below is a possible method call from the event
		// userInput.setOnAction(e -> newDataFile(userInput.getText()));

		// Add done button
		Button done = buttonFormat("Done", 3);

		vbox.getChildren().addAll(title1, userInput, direction2, done);
		showDialogWindow(primaryStage, vbox, title, done);
	}

	public void addDataField(Stage primaryStage) {
		String title = "ADD Data Field";
		VBox vbox = vboxFormat();

		Label title1 = new Label("Input Data Details");
		title1.setFont(new Font("Arial", 15));

		HBox hbox1 = hboxFormat();
		Label direction1 = new Label("Farm ID");
		direction1.setFont(new Font("Arial", 12));
		TextField farmID = new TextField();
		hbox1.getChildren().addAll(direction1, farmID);

		HBox hbox2 = hboxFormat();
		Label direction2 = new Label("Date (Month / Day / Year)");
		direction2.setFont(new Font("Arial", 12));
		ComboBox<String> month = new ComboBox<String>(months);
		ComboBox<String> day = new ComboBox<String>(days);
		// controlled year entry by incrementing with +,- buttons
		Button yearUp = new Button("+");
		Button yearDown = new Button("-");
		Button year = new Button("2010");
		yearUp.setOnAction(e -> year.setText("" + (++counter) + ""));
		yearDown.setOnAction(e -> year.setText("" + (--counter) + ""));
		// TextField year = new TextField();
		hbox2.getChildren().addAll(direction2, month, day, year, yearDown, yearUp);

		HBox hbox3 = hboxFormat();
		Label direction3 = new Label("Milk Weight (lbs)");
		direction3.setFont(new Font("Arial", 12));
		TextField milkWeight = new TextField();
		hbox3.getChildren().addAll(direction3, milkWeight);

		// Add done button
		Button done = buttonFormat("Done", 3);

		// Add to the list of farms
		done.setOnAction(e -> farmList.addData(farmID.getText(), Integer.parseInt(day.getValue()),
				Integer.parseInt(month.getValue()), Integer.parseInt(year.getText()),
				Long.parseLong(milkWeight.getText())));

		// Add to layout
		vbox.getChildren().addAll(title1, hbox1, hbox2, hbox3, done);
		showDialogWindow(primaryStage, vbox, title, done);
	}

	/**
	 * Handles the second dialog window: Add / Edit Data Field
	 * 
	 * Given a FarmID and Month / year, if the data point exists then the weight
	 * will be changed / updated. Otherwise, a new data point of the specified month
	 * and year will be added to the FarmID.
	 * 
	 * @param primaryStage - main GUI
	 */
	public void editDataField(Stage primaryStage) {
		String title = "Edit Data Field of Existing Farm";
		VBox vbox = vboxFormat();

		Label title1 = new Label("Input Data Details");
		title1.setFont(new Font("Arial", 15));

		HBox hbox1 = hboxFormat();
		Label direction1 = new Label("Farm ID");
		direction1.setFont(new Font("Arial", 12));
		ComboBox<String> farmID = new ComboBox<String>(Farms);
		Button next = buttonFormat("Next", 3);
		hbox1.getChildren().addAll(direction1, farmID, next);

		HBox hbox2 = hboxFormat();
		Label direction2 = new Label("Date (Month / Day / Year)");
		direction2.setFont(new Font("Arial", 12));
		ComboBox<String> month = new ComboBox<String>(months);
		ComboBox<String> day = new ComboBox<String>(days);
		ComboBox<String> year = new ComboBox<String>(years);
		hbox2.getChildren().addAll(direction2, month, day, year);

		HBox hbox3 = hboxFormat();
		Label direction3 = new Label("Milk Weight (lbs)");
		direction3.setFont(new Font("Arial", 12));
		TextField milkWeight = new TextField();
		hbox3.getChildren().addAll(direction3, milkWeight);

		// Add done button
		Button done = buttonFormat("Done", 3);
		// TODO create new data field in farm class based on selections
		// done.setOnActions(e -> addDataField(farmID.getSelection(),
		// month.getSelection(),
		// year.getSelection(), year.getSelection(), milkWeight.getText());

		vbox.getChildren().addAll(title1, hbox1, hbox2, hbox3, done);
		showDialogWindow(primaryStage, vbox, title, done);
	}

	public void removeDataField(Stage primaryStage) {
		String title = "Remove Data Field";
		VBox vbox = vboxFormat();

		Label title1 = new Label("Remove Existing Data Field");
		title1.setFont(new Font("Arial", 15));

		HBox hbox1 = hboxFormat();
		Label direction1 = new Label("Farm ID");
		direction1.setFont(new Font("Arial", 12));
		ComboBox<String> farmID = new ComboBox<String>(Farms);
		Button rButton = buttonFormat("Remove All", 3);
		hbox1.getChildren().addAll(direction1, farmID, rButton);

		HBox hboxtitle = hboxFormat();
		Label t = new Label("Remove specific data:");
		hboxtitle.getChildren().addAll(t);
		
		HBox hbox2 = hboxFormat();
		Label direction2 = new Label("Date (Month / Day / Year)");
		direction2.setFont(new Font("Arial", 12));
		ComboBox<String> month = new ComboBox<String>(months);
		ComboBox<String> day = new ComboBox<String>(days);
		ComboBox<String> year = new ComboBox<String>(years);
		hbox2.getChildren().addAll(direction2, month, day, year);

		// Add done button
		Button done = buttonFormat("Done", 3);
		// TODO create new data field in farm class based on selections
		// done.setOnActions(e -> addDataField(farmID.getSelection(),
		// month.getSelection(),
		// year.getSelection(), year.getSelection(), milkWeight.getText());

		vbox.getChildren().addAll(title1, hbox1, hboxtitle, hbox2, done);
		showDialogWindow(primaryStage, vbox, title, done);
	}

	public void Label() {

	}

	/**
	 * Show when event occurs
	 * 
	 * @param primaryStage of main GUI
	 * @param vbox         - box containing the window content
	 * @param title        - title of the window
	 */
	public void showDialogWindow(Stage primaryStage, VBox vbox, String title, Button done) {
		// Create layout
		BorderPane layout = new BorderPane();
		layout.setCenter(vbox);

		// Show Modal Dialog Window
		Scene inputScene = new Scene(layout, 350, 250);
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
		// vbox.setAlignment(Pos.BASELINE_CENTER);
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

	private VBox bottomStatTitles(String title1, String in1, String in2, TextField userInput1, TextField userInput2) {
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

	private VBox bottomStatTitles(String title1, String in1, String in2, ComboBox<String> userInput1,
			ComboBox<String> userInput2, Button eb) {
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

		vbox.getChildren().addAll(title, hbox1, hbox2, eb);

		return vbox;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		
	}
}
