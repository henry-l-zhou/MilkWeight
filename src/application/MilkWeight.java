package application;

/**
 * This class represents a single Milk Weight Item with a
 * @author Joonbo Shim
 *
 */
public class MilkWeight {
  // prototype class to display data to the table
  private String date;
  private String farmId;
  private String weight;

  MilkWeight(String date, String farmId, String weight) {
    this.date = date;
    this.farmId = farmId;
    this.weight = weight;
  }

  public String getDate() {
    return date;
  }

  public void setYear(String date) {
    this.date = date;
  }

  public String getFarmId() {
    return farmId;
  }

  public void setFarmId(String farmId) {
    this.farmId = farmId;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

}
