package application;

import java.util.List;
import java.util.Set;

/**
 * Filename: FarmADT.java Project: 
 * 
 * ATEAM Authors: Lauren Rohr, Kiley Smith, Anna
 * Keller, Luke Le Clair
 * 
 */
public interface CreateReportADT {
	// TODO: none of these are void, just wasn't sure the best return type.
	// TODO: some of our buttons on the GUI are repetitive (could change to align
	// better with rubric?)

	/**
	 * Returns percentage share of net sales (percentage milk weight entered in
	 * given year compared to total milk weight entered that year from all farms.)
	 */
	public int netSales(String id, int year);

	/**
	 * Display a list of totals and percent of total by farm. The list must be
	 * sorted by Farm ID, or you can prompt for ascending or descending by weight.
	 */
	public void monthlyFarmReport(int month, int year); // All Farms by month and Statistics Per All Farms

	/**
	 * Display list of total weight and percent of total weight of all farms by farm
	 * for the year. Sort by Farm ID, or you can allow the user to select display
	 * ascending or descending by weight.
	 */
	public void yearlyFarmReport(int year); // All Farms by year

	/**
	 * Display the total milk weight and percent of the total of all farm for each
	 * month. Sort, the list by month number 1-12, show total weight, then that
	 * farm's percent of the total milk received for each month.
	 */
	public void FarmReport(String id, int year); // Statistics Per Farm

	/**
	 * Prompt user for start date (year-month-day) and end date (year-month-day),
	 * Then display the total milk weight per farm and the percentage of the total
	 * for each farm over that date range. The list must be sorted by Farm ID, or
	 * you can prompt for ascending or descending order by weight or percentage.
	 */
	public void Report(int day1, int month1, int year1, int day2, int month2, int year2); // change input?

}
