package backend;

import javafx.beans.property.SimpleStringProperty;

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

	public String getTotalMilkWeight() {
		return totalMilkWeight.get();
	}

	public void setTotalMilkWeight(String mw) {
		totalMilkWeight.set(mw);
	}

	public String getTotalPercent() {
		return totalPercent.get();
	}

	public void setTotalPercent(String mwp) {
		totalPercent.set(mwp);
	}
	public String getFarmID() {
		return farmID.get();
	}
	public void setFarmID(String id) {
		farmID.set(id);
	}
	
	public String getMin() {
		return min.get();
	}
	
	public void setMin(String min) {
		this.min.set(min);
	}
	
	public String getMax() {
		return max.get();
	}
	
	public void setMax(String max) {
		this.max.set(max);
	}
	
	public String getAvg() {
		return avg.get();
	}
	
	public void setAvg(String avg) {
		this.avg.set(avg);
	}
}
