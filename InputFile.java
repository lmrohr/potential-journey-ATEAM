package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import application.Main.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

// call into create report to add new / more farms to the list

public class InputFile {
  CreateReport report; // access to the singular report for the system

  /**
   * Constructor for a new file with name input by user
   * 
   * Check for correctness of file name here! Must be a csv file.
   * 
   * @param fileName - name of file to read from
   */
  InputFile(String fileName, CreateReport report) {
    fileName = fileName.replaceAll(" ", ""); // get rid of inappropriate spacing

    // check for file name syntax
    if (fileName.indexOf(".csv") == -1) {
      Alert alert = new Alert(AlertType.WARNING, "Incorrect file type, must be .csv");
      alert.showAndWait().filter(r -> r == ButtonType.OK);
    }

    // Access to system report
    this.report = report;
    // file name syntax is correct, try to read file
    readData(fileName);
  }


  ArrayList<Data> toAdd = new ArrayList<Data>(); // idk what this is for

  public void readData(String fileName) {
    String line;
    String[] data; // csv fields: [date] [farm_id] [weight]
    String[] date;
    try {
      FileReader fileRead = new FileReader(fileName);
      BufferedReader bufRead = new BufferedReader(fileRead);
      line = bufRead.readLine(); // skip first line due to provided csv format 
      line = bufRead.readLine(); 
      // Read through entire data file 
      while (line != null) {
        data = line.split(","); // split at each comma to get fields
        // split for date
        date = data[0].split("-"); // [year] [month] [day]
        // Add line of data to report 
        report.addData(data[1], Integer.parseInt(date[2]), Integer.parseInt(date[1]),
            Integer.parseInt(date[0]), Long.parseLong(data[2]));
        
        System.out.println("adding data from farm: " + data[1]);
        
        line = bufRead.readLine(); // next line 
      }
      
    } catch (FileNotFoundException e) {
      Alert alert = new Alert(AlertType.WARNING, "File not found.");
      alert.showAndWait().filter(r -> r == ButtonType.OK);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
