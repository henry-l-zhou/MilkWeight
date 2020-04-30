package application;

/**
 * This class represents a single Milk Weight Item with a
 * 
 * @author ateam85
 *
 */
public class MilkWeight implements Comparable<MilkWeight> {
  // prototype class to display data to the table
  private int date;
  private int month;
  private int year;
  private String farmId;
  private int weight;

  /**
   * initializes MW object
   * @param year
   * @param month
   * @param date
   * @param farmId
   * @param weight
   */
  public MilkWeight(int year, int month, int date, String farmId, int weight) {
    this.date = date;
    this.year = year;
    this.month = month;
    this.farmId = farmId;
    this.weight = weight;
  }

  /**
   * gets date
   * @return date
   */
  public int getDate() {
    return date;
  }

  /**
   * gets month
   * @return month
   */
  public int getMonth() {
    return month;
  }

  /**
   * gets year
   * @return year
   */
  public int getYear() {
    return year;
  }

  /**
   * gets farmId
   * @return farmId
   */
  public String getFarmId() {
    return farmId;
  }

  /**
   * gets Weight
   * @return weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * used for print statements to show what's in the mw object
   */
  public String toString() {
    return this.farmId + "," + this.year + "-" + this.month + "-" + this.date + "," + this.weight;
  }

  @Override
  public int compareTo(MilkWeight o) {
    int yearDiff = this.getYear() - o.getYear();
    int monthDiff = this.getMonth() - o.getMonth();
    int dateDiff = this.getDate() - o.getDate();
    int farmDiff = this.getFarmId().compareTo(o.getFarmId());
    int weightDiff = this.getWeight() - o.getWeight();
    if (yearDiff != 0) {
      return yearDiff;
    } else {
      if (monthDiff != 0) {
        return monthDiff;
      } else {
        if (dateDiff != 0) {
          return dateDiff;
        } else {
          if (farmDiff != 0) {
            return farmDiff;
          } else
            return (weightDiff != 0) ? weightDiff : 0;
        }
      }
    }
  }

}
