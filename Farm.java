/**
 * Farm.java created by Keller on Inspiron15 in ATEAM
 * 
 * Author: Anna Keller
 * Email: AKeller5@wisc.edu
 * Date: 4/17/2020
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author keller
 *
 */
public class Farm {
	private String FarmID;

	/**
	 * Accept any string as a farm id. Allow the user to enter the farm ID, and save
	 * and store as all CAPS. Determine what should happen if the user enters a
	 * non-valid ID (such as null). Must handle bad user input without crashing and
	 * with reasonable error messages for the user.
	 */
	public Farm(String ID) {
		if (ID != null) {
			FarmID = ID.toUpperCase();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
