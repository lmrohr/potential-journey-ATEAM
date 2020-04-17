package application;

import java.util.List;
import java.util.Set;

/**
 * Filename: FarmADT.java Project: ATEAM Authors: Lauren Rohr, Kiley Smith, Anna
 * Keller, Luke Le Clair
 * 
 */
public interface FarmADT {

	/**
	 * Allow user to add a valid milk weight to Farm file. The input should remain
	 * associated with date of entry. If weight is not valid do not add and throw
	 * pop-up error message.
	 */
	public void addMilkWeight(int month, int year, long weight);

	/**
	 * Allow user to replace milk weight for a given date. This new weight must be
	 * valid, if not throw pop-up() and do not change data. If the date does not
	 * exist, throw appropriate pop-up message and do not change data.
	 */
	public void editMilkWeight(int month, int year, long weight);

	/**
	 * Allow user to delete milk weight for a given date. If this is the only entry
	 * for this date remove date and milk weight. If the date does not exist, throw
	 * appropriate pop-up message and do not change data.
	 */
	public void removeMilkWeight(int month, int year, long weight);

	/**
	 * Display the total milk weight for given month. Check date is valid and data
	 * exists (should be true because of use of ComboBoxes).
	 */
	public void monthlyTotal(int month, int year);

	/**
   * Display the total milk weight for given year. Check date is valid and data exists (should be
   * true because of use of ComboBoxes).
   */
  public void yearlyTotal(int year);

}
