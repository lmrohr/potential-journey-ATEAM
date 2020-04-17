/**
 * Farm.java created by Keller on Inspiron15 in ATEAM
 * 
 * Author: Anna Keller Email: AKeller5@wisc.edu Date: 4/17/2020
 * 
 * Course: CS400 Course: Spring 2020 Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers Version: 2019-12 (4.14.0) Build id: 20191212-1212
 * 
 * Device: Inspiron15 (3000 Series) OS: Windows 10 Home
 * 
 * Collaborators: N/A
 * 
 * Other Credits: N/A
 * 
 * Known Bugs: N/A
 */
package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * @author keller
 *
 */
public class Farm implements FarmADT {
  private String FarmID;
  private ArrayList<MilkWeightByDay> milkWeights;

  /**
   * Accept any string as a farm id. Allow the user to enter the farm ID, and save and store as all
   * CAPS. Determine what should happen if the user enters a non-valid ID (such as null). Must
   * handle bad user input without crashing and with reasonable error messages for the user.
   * 
   * @param ID - ID of the farm to add
   */
  public Farm(String ID) {
    FarmID = null;

    if (ID != null) {
      FarmID = ID.toUpperCase();
    }

    milkWeights = new ArrayList<MilkWeightByDay>();
  }

  /**
   * Allow user to add a valid milk weight to Farm file. The input should remain associated with
   * date of entry. If weight is not valid do not add and throw pop-up error message.
   * 
   * @param month  - month that milk was added
   * @param year   - year that milk was added
   * @param weight - weight of milk that was added
   */
  @Override
  public void addMilkWeight(int month, int year, long weight) {
    // Pop-up message if the weight is incorrect
    if (weight < 0) {
      Alert alert = new Alert(AlertType.WARNING, "Weight cannot be negative");
      alert.showAndWait().filter(r -> r == ButtonType.OK);
      return;
    }

    // Add a new entry
    milkWeights.add(new MilkWeightByDay(month, year, weight));
  }

  /**
   * Allow user to replace milk weight for a given date. This new weight must be valid, if not throw
   * pop-up() and do not change data. If the date does not exist, throw appropriate pop-up message
   * and do not change data.
   */
  @Override
  public void editMilkWeight(int month, int year, long weight) {
    // Pop-up message if the weight is incorrect
    if (weight < 0) {
      Alert alert = new Alert(AlertType.WARNING, "Weight cannot be negative");
      alert.showAndWait().filter(r -> r == ButtonType.OK);
      return;
    }

    // Loop through each entry until a matching day/year is found
    for (MilkWeightByDay day : milkWeights) {
      if (day.getMonth() == month && day.getYear() == year) {
        day.setMilkWeight(weight);
        return;
      }
    }

    // Pop-up message if the date was not found
    Alert alert = new Alert(AlertType.WARNING, "Date does not exist. Please try again.");
    alert.showAndWait().filter(r -> r == ButtonType.OK);
  }

  /**
   * Allow user to delete milk weight for a given date. If this is the only entry for this date
   * remove date and milk weight. If the date does not exist, throw appropriate pop-up message and
   * do not change data.
   */
  @Override
  public void removeMilkWeight(int month, int year, long weight) {
    // Loop through each entry until a matching month/year is found
    for (MilkWeightByDay day : milkWeights) {
      if (day.getMonth() == month && day.getYear() == year) {
        milkWeights.remove(day);
        return;
      }
    }

    // Pop-up message if the date was not found
    Alert alert = new Alert(AlertType.WARNING, "Date does not exist. Please try again.");
    alert.showAndWait().filter(r -> r == ButtonType.OK);
  }

  @Override
  public void monthlyTotal(int month, int year) {
    // TODO Auto-generated method stub

  }

  @Override
  public void yearlyTotal(int year) {
    // TODO Auto-generated method stub

  }
}
