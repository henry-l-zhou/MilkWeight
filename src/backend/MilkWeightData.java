package backend;

import javafx.beans.property.SimpleStringProperty;

public class MilkWeightData {
	private final SimpleStringProperty totalMilkWeight;
	private final SimpleStringProperty totalPercent;
	private final SimpleStringProperty farmID;

	/**
	 * constructor method that instantiates the weight/percent
	 * 
	 * @param milkWeight
	 * @param milkWeightPercent
	 */
	public MilkWeightData(String ID, String milkWeight, String milkWeightPercent) {
		this.totalMilkWeight = new SimpleStringProperty(milkWeight);
		this.totalPercent = new SimpleStringProperty(milkWeightPercent);
		this.farmID = new SimpleStringProperty(ID);
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

}
