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

/**
 * MilkWeightByDay - MilkWeightByDay stores a weight of milk as a long. Also stores ints
 * representing the month and year where that milk was weighed. This class allows the user to see
 * the month, year, and milk weight, but only edit the milk weight.
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 */
public class MilkWeightByDay {
  private int month;
  private int year;
  private long milkWeight;

  /**
   * Constructor for a MilkWeightByDay object. Initializes an object with a month, year, and weight
   * of milk.
   * 
   * @param month  - month that the milk was from
   * @param year   - year that the milk was from
   * @param weight - weight of the milk
   */
  MilkWeightByDay(int month, int year, long weight) {
    this.month = month;
    this.year = year;
    this.setMilkWeight(weight);
  }

  /**
   * Returns the month
   * 
   * @return - month that milk was weighed
   */
  public int getMonth() {
    return month;
  }

  /**
   * Returns the year
   * 
   * @return - year that milk was weighed
   */
  public int getYear() {
    return year;
  }

  /**
   * Returns the milk weighed on a given month
   * 
   * @return - milk weight for a month/year
   */
  public long getMilkWeight() {
    return milkWeight;
  }

  /**
   * Allows a user to set the milk weight
   * 
   * @param milkWeight - new milk weight for a given month/year
   */
  public void setMilkWeight(long milkWeight) {
    this.milkWeight = milkWeight;
  }

}
