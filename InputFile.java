package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import application.Main.Data;
import javafx.scene.control.TextField;

public class InputFile {
  ArrayList<Data> toAdd = new ArrayList<Data>();
  public void readData(TextField input) {
    String[] data;
    try {
      FileReader fileRead = new FileReader(input.toString());
      BufferedReader bufRead = new BufferedReader(fileRead);
      String temp = bufRead.readLine();
      Data add = null;
      while (temp != null) {
        if (temp.compareTo("date,farm_id,weight") != 0) {
          data = temp.split(", "); // splits at each , 
          add = new Data(data[0], data[1], data[2]);
          toAdd.add(add); 
        }
        temp = bufRead.readLine();
      }
      add.addList(toAdd);
    } catch (FileNotFoundException e) {
      // TODO add popup if file is not found.
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
