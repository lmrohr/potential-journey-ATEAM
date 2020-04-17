/**
 * A-Team 121 Final Project
 * 
 * Authors: Lauren Rohr (lmrohr@wisc.edu) Kiley Smith (add email) Luke Le Clair (lleclair@wisc.edu)
 * Anna Keller (add email)
 * 
 * Date: 4/17/2020
 * 
 * Course: CS 400 Semester: Spring 2020
 * 
 * IDE: Eclipse IDE for Java Developers Version: Build id:
 * 
 * List Collaborators: N/A
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
 * Farm - TODO Describe purpose of this user defined type Farm creates a farm with an ID and a list
 * of milk weights for different months and years.
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
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

  /**
   * Display the total milk weight for given month. Check date is valid and data exists.
   */
  @Override
  public void monthlyTotal(int month, int year) {
    // Loop through each entry until a matching month/year is found
    for (MilkWeightByDay day : milkWeights) {
      if (day.getMonth() == month && day.getYear() == year) {

        // Pop-up message if the date was not found
        Alert displayTotal = new Alert(AlertType.WARNING,
            "Weight for " + month + "/" + year + " is: " + day.getMilkWeight());
        displayTotal.showAndWait().filter(r -> r == ButtonType.OK);
        return;
      }
    }

    // Pop-up message if the date was not found
    Alert alert = new Alert(AlertType.WARNING, "Date does not exist. Please try again.");
    alert.showAndWait().filter(r -> r == ButtonType.OK);

  }

  /**
   * Display the total milk weight for given year. Check date is valid and data exists (should be
   * true because of use of ComboBoxes).
   */
  @Override
  public void yearlyTotal(int year) {
    long total = 0; // Yearly total

    // Loop through each entry until a matching month/year is found
    for (MilkWeightByDay day : milkWeights) {
      if (day.getYear() == year) {
        // Add the monthly weight to the total
        total += day.getMilkWeight();
      }
    }

    // Pop-up message to display the weights
    Alert alert = new Alert(AlertType.WARNING, "Yearly total for " + year + " was: " + total);
    alert.showAndWait().filter(r -> r == ButtonType.OK);
  }
}
