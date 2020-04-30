/**
 * FarmLog.java created by Keller on Inspiron15 in final_ateam
 * 
 * Author: Anna Keller Email: AKeller5@wisc.edu Date: @date
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FarmLog - stores all the Farm IDs
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 *
 */
public class FarmLog {
  private ArrayList<String> IDLog; // contains Strings of all Farm ID, also denotes index of
                                   // corresponding Farm
                                   // objects in farmLog
  private ArrayList<Farm> farmLog; // contains Farm objects for all farms added

  /**
   * Constructor for the FarmLog class. Initializes the ID log and the farm log.
   */
  FarmLog() {
    IDLog = new ArrayList<String>();
    farmLog = new ArrayList<Farm>();
  }

  /**
   * get the list of years in which data is present for a given farm
   * 
   * @param id identifier for a particular farm
   * @return ObservableList of years
   */
  public ObservableList<String> getYearsofFarm(String id) {
    int index = IDLog.indexOf(id);
    return farmLog.get(index).getYears();

  }

  /**
   * Get list of existing farms to edit
   * 
   * @return ObservableList of farms
   */
  public ObservableList<String> getFarms() {
    if (IDLog.isEmpty()) {
      return FXCollections.observableArrayList("None");
    }
    return FXCollections.observableArrayList(IDLog);

  }

  /**
   * Add a farm ID to existing farms
   * 
   * @param id     - farm ID (any string)
   * @param day    - day that the milk was from
   * @param month  - month that the milk was from
   * @param year   - year that the milk was from
   * @param weight - weight of the milk
   */
  public void addFarm(String id, int day, int month, int year, long weight) {
    if (id != null && !IDLog.contains(id)) {
      Farm toAdd = new Farm(id);
      farmLog.add(toAdd);
      IDLog.add(id.toUpperCase());
      // called editFarmNew to add data point to newly added farm
      editFarmNew(id, day, month, year, weight);
    } else if (id != null) {
      editFarmNew(id, day, month, year, weight);
    }
  }

  /**
   * Edit existing data point for a given farm
   * 
   * @param id     - farm ID (any string)
   * @param day    - day that the milk was from
   * @param month  - month that the milk was from
   * @param year   - year that the milk was from
   * @param weight - weight of the milk
   */
  public void editFarmEntry(String id, int day, int month, int year, long weight) {
    if (id != null && IDLog.contains(id)) {
      int index = IDLog.indexOf(id);
      Farm toEdit = farmLog.get(index);
      // calls editMilkWeight on farm entry
      toEdit.editMilkWeight(day, month, year, weight);
    }
  }

  /**
   * Add new data point for a given farm
   * 
   * @param id     - farm ID (any string)
   * @param day    - day that the milk was from
   * @param month  - month that the milk was from
   * @param year   - year that the milk was from
   * @param weight - weight of the milk
   */
  public void editFarmNew(String id, int day, int month, int year, long weight) {
    if (id != null && IDLog.contains(id)) {
      Farm toEdit = farmLog.get(IDLog.indexOf(id));
      // Calls addMilkWeight on farm
      toEdit.addMilkWeight(day, month, year, weight);
    }
  }

  /**
   * Remove existing data point for a given farm
   * 
   * @param id     - farm ID (any string)
   * @param day    - day that the milk was from
   * @param month  - month that the milk was from
   * @param year   - year that the milk was from
   * @param weight - weight of the milk
   */
  public void removeFarmEntry(String id, int day, int month, int year, long weight) {
    if (id != null && IDLog.contains(id)) {
      int index = IDLog.indexOf(id);
      Farm toEdit = farmLog.get(index);
      // calls removeMilkWeight on farm entry
      toEdit.removeMilkWeight(day, month, year, weight);
    }
  }

  /**
   * Check if a farm is in the list
   * 
   * @param id identifier for farm (string)
   * @return true is farm is in the list, false otherwise
   */
  public boolean exists(String id) {
    if (IDLog.contains(id)) {
      return true;
    }
    return false;
  }

  /**
   * Remove all function, delete farm from log
   * 
   * @param id identifier for farm
   */
  public void removeFarm(String id) {
    if (IDLog.contains(id)) {
      int index = IDLog.indexOf(id);
      IDLog.remove(index);
      farmLog.remove(index);
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
