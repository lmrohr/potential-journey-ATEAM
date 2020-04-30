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
	private ArrayList<String> log;
	
	public void addFarm(String id) {
		if (id != null) {
			log.add(id.toUpperCase());
		}
	}
	public ObservableList<String> getFarms() {
		 return FXCollections.observableArrayList(log);
		 
	}
	public boolean exists(String id) {
		if (log.contains(id)) {
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
