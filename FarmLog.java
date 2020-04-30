/**
 * FarmLog.java created by Keller on Inspiron15 in final_ateam
 * 
 * Author: Anna Keller
 * Email: AKeller5@wisc.edu
 * Date: @date
 * 
 * Course: CS400
 * Course: Spring 2020
 * Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers
 * Version: 2019-12 (4.14.0)
 * Build id: 20191212-1212
 * 
 * Device: Inspiron15 (3000 Series)
 * OS: Windows 10 Home 
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
	private ArrayList<String> IDlog;
	private ArrayList<Farm> farmLog;

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
		if (id != null && !IDlog.contains(id)) {
			Farm toAdd = new Farm(id);
			farmLog.add(toAdd);
			IDlog.add(id.toUpperCase());
			// called editFarmNew to add data point to newly added farm
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
		if (id != null && !IDlog.contains(id)) {
			int index = IDlog.indexOf(id);
			Farm toEdit = farmLog.get(index);
			// calls editMilkWeight
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
		if (id != null && IDlog.contains(id)) {
			int index = IDlog.indexOf(id);
			Farm toEdit = farmLog.get(index);
			// Calls addMilkWeight
			toEdit.addMilkWeight(day, month, year, weight);
		}
	}

	/**
	 * get the list of years in which data is present for a given farm
	 * 
	 * @param id identifier for a particular farm
	 * @return ObservableList of years
	 */
	public ObservableList<String> getYearsforFarm(String id) {
		int index = IDlog.indexOf(id);
		return farmLog.get(index).getYears();

	}

	/**
	 * Get list of existing farms to edit
	 * 
	 * @return ObservableList of farms
	 */
	public ObservableList<String> getFarms() {
		return FXCollections.observableArrayList(IDlog);

	}

	/**
	 * Check if a farm is in the list
	 * 
	 * @param id identifier for farm (string)
	 * @return true is farm is in the list, false otherwise
	 */
	public boolean exists(String id) {
		if (IDlog.contains(id)) {
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
		if (IDlog.contains(id)) {
			int index = IDlog.indexOf(id);
			IDlog.remove(index);
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
