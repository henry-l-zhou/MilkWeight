package application;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class is used to format data in a observable arraylist for the gui tables
 * 
 * @author ateam85
 *
 */
public class MilkWeightData {
  private final SimpleStringProperty totalMilkWeight;
  private final SimpleStringProperty totalPercent;
  private final SimpleStringProperty farmID;
  private final SimpleStringProperty min;
  private final SimpleStringProperty max;
  private final SimpleStringProperty avg;

  /**
   * constructor method that instantiates the weight/percent
   * 
   * @param milkWeight
   * @param milkWeightPercent
   */
  public MilkWeightData(String ID, String milkWeight, String milkWeightPercent, String min, String max, String avg) {
    this.totalMilkWeight = new SimpleStringProperty(milkWeight);
    this.totalPercent = new SimpleStringProperty(milkWeightPercent);
    this.farmID = new SimpleStringProperty(ID);
    this.min = new SimpleStringProperty(min);
    this.max = new SimpleStringProperty(max);
    this.avg = new SimpleStringProperty(avg);
  }

  /**
   * gets total milkweight
   * @return milk weight
   */
  public String getTotalMilkWeight() {
    return totalMilkWeight.get();
  }

  /**
   * sets total milkweight
   * @param mw
   */
  public void setTotalMilkWeight(String mw) {
    totalMilkWeight.set(mw);
  }

  /**
   * gets percent of that farm's share 
   * @return percent
   */
  public String getTotalPercent() {
    return totalPercent.get();
  }

  /**
   * sets percent of that farm's share
   * @param mwp
   */
  public void setTotalPercent(String mwp) {
    totalPercent.set(mwp);
  }

  /**
   * gets farmId of the farm
   * @return farmId
   */
  public String getFarmID() {
    return farmID.get();
  }

  /**
   * sets farm ID
   * @param id
   */
  public void setFarmID(String id) {
    farmID.set(id);
  }

  /**
   * gets the min weight of the milk for that farm
   * @return
   */
  public String getMin() {
    return min.get();
  }

  /**
   * sets min weight for the farm 
   * @param min
   */
  public void setMin(String min) {
    this.min.set(min);
  }

  /**
   * gets max weight of the farm
   * @return weight
   */
  public String getMax() {
    return max.get();
  }

  /**
   * sets max weight for the farm
   * @param max
   */
  public void setMax(String max) {
    this.max.set(max);
  }

  /**
   * gets avg weight for the farm milk
   * @return avg
   */
  public String getAvg() {
    return avg.get();
  }

  /**
   * sets avg weight for the farm
   * @param avg
   */
  public void setAvg(String avg) {
    this.avg.set(avg);
  }
}
