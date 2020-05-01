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

import java.util.List;
import java.util.Set;

/**
 * Filename: FarmADT.java
 * 
 * Project: ATEAM
 * 
 * Authors: Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 */
public interface FarmADT {

  /**
   * Allow user to add a valid milk weight to Farm file. The input should remain associated with
   * date of entry. If weight is not valid do not add and throw pop-up error message.
   */
  public void addMilkWeight(int day, int month, int year, long weight);

  /**
   * Allow user to replace milk weight for a given date. This new weight must be valid, if not throw
   * pop-up() and do not change data. If the date does not exist, throw appropriate pop-up message
   * and do not change data.
   */
  public void editMilkWeight(int day, int month, int year, long weight);

  /**
   * Allow user to delete milk weight for a given date. If this is the only entry for this date
   * remove date and milk weight. If the date does not exist, throw appropriate pop-up message and
   * do not change data.
   */
  public void removeMilkWeight(int day, int month, int year);

  /**
   * Return the total milk weight for given month. Check date is valid and data exists (should be
   * true because of use of ComboBoxes).
   */
  public long monthlyTotal(int month, int year);

  /**
   * Return the total milk weight for given year. Check date is valid and data exists (should be
   * true because of use of ComboBoxes).
   */
  public long yearlyTotal(int year);

}
