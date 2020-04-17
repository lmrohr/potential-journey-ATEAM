package application;

public class MilkWeightByDay {
  private int month;
  private int year;
  private long milkWeight;

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
