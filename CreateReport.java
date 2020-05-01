/**
 * A-Team 121 Final Project
 * 
 * Authors: Lauren Rohr (lmrohr@wisc.edu) Kiley Smith (kasmith32@wisc.edu) Luke Le Clair
 * (lleclair@wisc.edu) Anna Keller (add email)
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

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * CreateReport - This class is the base for creating the different types of
 * reports depending on what the user wants.
 * 
 * @author Lauren Rohr, Kiley Smith, Anna Keller, Luke Le Clair
 */
public class CreateReport implements CreateReportADT {
	HashSet<Farm> farmSet;
	List<String> farmIDs;

	/**
	 * Constructor for a CreateReport object. Initializes a HashSet of Farm object.
	 */
	CreateReport() {
		farmSet = new HashSet<Farm>();
		farmIDs = new ArrayList<String>();
	}

	/**
	 * Adds a farm with the given ID to the set of farms.
	 * 
	 * @param ID - ID of the farm to add.
	 */
	public void addFarm(String ID) {
		farmSet.add(new Farm(ID));
		for(String id : farmIDs) {
		  if(id.equals(ID)) {
		    return;
		  }
		}
		farmIDs.add(ID);
	}

	/**
	 * Adds a milkWeightDay for a given Farm ID. If the farm has not already been
	 * added, then it adds the farm to the set of Farm objects.
	 * 
	 * @param ID     - ID of the Farm to add to
	 * @param month  - Month of the milk weight to add
	 * @param year   - Year of the milk weight to add
	 * @param weight - Weight of the milk to add
	 */
	public void addData(String ID, int day, int month, int year, long weight) {
		// Loop through each farm looking for a match
		for (Farm farm : farmSet) {
			if (ID.equals(farm.getFarmID())) {
				// If there is a match, add the new entry
				farm.addMilkWeight(day, month, year, weight);
				return;
			}
		}

		// If the farm is not in the set, add it and add the first milkweight
		Farm toAdd = new Farm(ID);
		toAdd.addMilkWeight(day, month, year, weight);
		farmSet.add(toAdd);
		for(String id : farmIDs) {
          if(id.equals(ID)) {
            return;
          }
        }
        farmIDs.add(ID);

	}

	/**
	 * Returns percentage share of net sales (percentage milk weight entered in
	 * given year compared to total milk weight entered that year from all farms.)
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
	 * Display a list of totals and percent of total by farm. The list must be
	 * sorted by Farm ID, or you can prompt for ascending or descending by weight.
	 */
	@Override
	public void monthlyFarmReport(int month, int year, boolean display) {
		ArrayList<String> farmID = new ArrayList<String>();
		ArrayList<Long> milkWeights = new ArrayList<Long>();
		long totalWeight = 0;

		// Iterate through all of the farms
		for (Farm farm : farmSet) {
			farmID.add(farm.getFarmID()); // Add the ID
			milkWeights.add(farm.monthlyTotal(month, year)); // Add the milkWeight
			totalWeight += farm.monthlyTotal(month, year); // Update the total weight
		}

		FileWriter f = null;

		if (display) {
			GridPane form = new GridPane();
			Scene window = new Scene(form, 600, 200);
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setScene(window);
			dialog.show();
			form.addRow(0, new Label(" Year: "), new Label(String.valueOf(year)), new Label(", Month: "),
					new Label(String.valueOf(month)));
			form.addRow(1, new Label(" Farm ID    "), new Label(" Farm Total    "), new Label(" Percent Total    "));
			for (int i = 0; i < farmID.size(); i++) {
				form.addRow((i + 1), new Label(farmID.get(i)), new Label(Long.toString(milkWeights.get(i))),
						new Label(Long.toString((100 * milkWeights.get(i) / totalWeight))));
			}
		} else {
			try {
				f = new FileWriter("report.txt");
				f.write("Year: " + year + "\t\tMonth: " + month + "\n");
				f.write("Farm ID\t\t\tFarm Total\t\tPercent of Total\n");
				for (int i = 0; i < farmID.size(); i++) {
					f.write(farmID.get(i) + "\t\t\t" + milkWeights.get(i) + "\t\t\t"
							+ (100 * milkWeights.get(i) / totalWeight));
				}
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING, "Unable to save data");
				alert.showAndWait().filter(r -> r == ButtonType.OK);
			} finally {
				if (f != null)
					try {
						f.close();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.WARNING, "Unable to save data");
						alert.showAndWait().filter(r -> r == ButtonType.OK);
					}
			}
		}
		// TODO: display results.
		// Not sure if we want these in a pop-up window or on the main screen?
	}

	/**
	 * Display list of total weight and percent of total weight of all farms by farm
	 * for the year. Sort by Farm ID, or you can allow the user to select display
	 * ascending or descending by weight.
	 */
	@Override
	public void yearlyFarmReport(int year, boolean display) {
		ArrayList<String> farmID = new ArrayList<String>();
		ArrayList<Long> milkWeights = new ArrayList<Long>();
		long totalWeight = 0;

		// Iterate through all of the farms
		for (Farm farm : farmSet) {
			farmID.add(farm.getFarmID()); // Add the ID
			milkWeights.add(farm.yearlyTotal(year)); // Add the milkWeight
			totalWeight += farm.yearlyTotal(year); // Update the total weight
		}

		FileWriter f = null;

		if (display) {
			GridPane form = new GridPane();
			Scene window = new Scene(form, 600, 200);
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setScene(window);
			dialog.show();
			form.addRow(0, new Label(" Year:"), new Label(String.valueOf(year)));
			form.addRow(1, new Label(" Farm    "), new Label(" Farm Total    "), new Label(" Percent Total    "));
			for (int i = 0; i <= farmID.size(); i++) {
				form.addRow((i + 1), new Label(farmID.get(i)), new Label(Long.toString(milkWeights.get(i))),
						new Label(Long.toString((100 * milkWeights.get(i) / totalWeight))));
			}
		} else {
			try {
				f = new FileWriter("report.txt");
				f.write("Year: " + year + "\n");
				f.write("Farm ID\t\t\tFarm Total\t\tPercent of Total\n");
				for (int i = 0; i < farmID.size(); i++) {
					f.write(farmID.get(i) + "\t\t\t" + milkWeights.get(i) + "\t\t\t"
							+ (100 * milkWeights.get(i) / totalWeight));
				}
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING, "Unable to save data");
				alert.showAndWait().filter(r -> r == ButtonType.OK);
			} finally {
				if (f != null)
					try {
						f.close();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.WARNING, "Unable to save data");
						alert.showAndWait().filter(r -> r == ButtonType.OK);
					}
			}
		}

		// TODO: display results.
		// Not sure if we want these in a pop-up window or on the main screen?
	}

	/**
	 * Display the total milk weight and percent of the total of all farm for each
	 * month. Sort, the list by month number 1-12, show total weight, then that
	 * farm's percent of the total milk received for each month.
	 */
	@Override
	public void farmReport(String id, int year, boolean display) {
		long[] allFarmTotal = new long[12];
		long[] singleFarm = new long[12];

		for (int i = 0; i < 12; i++) {
			for (Farm farm : farmSet) {
				if (farm.getFarmID().equals(id)) {
					singleFarm[i] = farm.monthlyTotal(i + 1, year);
				}
				allFarmTotal[i] += farm.monthlyTotal(i + 1, year);
			}
		}

		FileWriter f = null;

		if (display) {
			GridPane form = new GridPane();
			Scene window = new Scene(form, 600, 200);
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setScene(window);
			dialog.show();
			form.addRow(0, new Label(" Farm ID:"), new Label(id));
			form.addRow(1, new Label(" Month    "), new Label(" Farm Total    "), new Label(" Percent Total    "));
			for (int i = 0; i < 12; i++) {
				form.addRow((i + 1), new Label(String.valueOf(i + 1)), new Label(Long.toString(allFarmTotal[i])),
						new Label(Long.toString((100 * singleFarm[i] / allFarmTotal[i]))));
			}
		} else {
			try {
				f = new FileWriter("report.txt");
				f.write("Farm ID: " + id + "\n");
				f.write("Month\t\t\tFarm Total\t\tPercent of Total\n");
				for (int i = 0; i < 12; i++) {
					f.write((i + 1) + "\t\t\t" + allFarmTotal[i] + "\t\t\t" + (100 * singleFarm[i] / allFarmTotal[i])
							+ "\n");
				}
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING, "Unable to save data");
				alert.showAndWait().filter(r -> r == ButtonType.OK);
			} finally {
				if (f != null)
					try {
						f.close();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.WARNING, "Unable to save data");
						alert.showAndWait().filter(r -> r == ButtonType.OK);
					}
			}
		}

	}
	
	public List<String> farmIDlog(){
	  return farmIDs;
	}

	/**
	 * Prompt user for start date (year-month-day) and end date (year-month-day),
	 * Then display the total milk weight per farm and the percentage of the total
	 * for each farm over that date range. The list must be sorted by Farm ID, or
	 * you can prompt for ascending or descending order by weight or percentage.
	 */
	@Override
	public void Report(int day1, int month1, int year1, int day2, int month2, int year2) {
		// TODO Auto-generated method stub

	}
}
