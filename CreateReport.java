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

  @Override
  public void monthlyFarmReport(int month, int year) {
    // TODO Auto-generated method stub

  }

  @Override
  public void yearlyFarmReport(int year) {
    // TODO Auto-generated method stub

  }

  @Override
  public void FarmReport(String id, int year) {
    // TODO Auto-generated method stub

  }

  @Override
  public void Report(int day1, int month1, int year1, int day2, int month2, int year2) {
    // TODO Auto-generated method stub

  }

}
