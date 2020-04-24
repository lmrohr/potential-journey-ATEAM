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

import java.util.HashSet;

/**
 * CreateReport - TODO Describe purpose of this user defined type
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 */
public class CreateReport implements CreateReportADT {
  HashSet<Farm> farmSet;

  /**
   * Constructor for a CreateReport object. Initializes a HashSet of Farm object.
   */
  CreateReport() {
    farmSet = new HashSet<Farm>();
  }

  /**
   * Adds a farm with the given ID to the set of farms.
   * 
   * @param ID - ID of the farm to add.
   */
  public void addFarm(String ID) {
    farmSet.add(new Farm(ID));
  }

  /**
   * Returns percentage share of net sales (percentage milk weight entered in given year compared to
   * total milk weight entered that year from all farms.)
   */
  @Override
  public int netSales(String id, int year) {
    long totalWeight = 0;
    long selectedFarmWeight = 0;

    // Add each farm's weight to the total
    for (Farm farm : farmSet) {
      totalWeight += farm.yearlyTotal(year);

      // If the farm is the selected farm, save the farm's weight
      if (farm.getFarmID().equals(id))
        selectedFarmWeight = farm.yearlyTotal(year);
    }

    // Convert the percentage to an int
    return (int) (100 * selectedFarmWeight / totalWeight);
  }

  /**
   * Display a list of totals and percent of total by farm. The list must be sorted by Farm ID, or
   * you can prompt for ascending or descending by weight.
   */
  @Override
  public void monthlyFarmReport(int month, int year) {
    // TODO Auto-generated method stub

  }

  /**
   * Display list of total weight and percent of total weight of all farms by farm for the year.
   * Sort by Farm ID, or you can allow the user to select display ascending or descending by weight.
   */
  @Override
  public void yearlyFarmReport(int year) {
    // TODO Auto-generated method stub

  }

  /**
   * Display the total milk weight and percent of the total of all farm for each month. Sort, the
   * list by month number 1-12, show total weight, then that farm's percent of the total milk
   * received for each month.
   */
  @Override
  public void FarmReport(String id, int year) {
    // TODO Auto-generated method stub

  }

  /**
   * Prompt user for start date (year-month-day) and end date (year-month-day), Then display the
   * total milk weight per farm and the percentage of the total for each farm over that date range.
   * The list must be sorted by Farm ID, or you can prompt for ascending or descending order by
   * weight or percentage.
   */
  @Override
  public void Report(int day1, int month1, int year1, int day2, int month2, int year2) {
    // TODO Auto-generated method stub

  }

}
