package application;

import javafx.beans.property.SimpleStringProperty;

public class MilkWeight {
  // prototype class to display data to the table
  private final SimpleStringProperty year;
  private final SimpleStringProperty farmId;
  private final SimpleStringProperty weight;

  MilkWeight(String year, String farmId, String weight) {
    this.year = new SimpleStringProperty(year);
    this.farmId = new SimpleStringProperty(farmId);
    this.weight = new SimpleStringProperty(weight);
  }

  public String getYear() {
    return year.get();
  }

  public void setYear(String year) {
    this.year.set(year);
  }

  public String getFarmId() {
    return farmId.get();
  }

  public void setFarmId(String farmId) {
    this.farmId.set(farmId);
  }

  public String getWeight() {
    return weight.get();
  }

  public void setEmail(String weight) {
    this.weight.set(weight);
  }

}
