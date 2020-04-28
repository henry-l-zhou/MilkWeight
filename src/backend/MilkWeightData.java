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
	private MilkWeightData(String milkWeight, String milkWeightPercent, String ID) {
		this.totalMilkWeight = new SimpleStringProperty(milkWeight);
		this.totalPercent = new SimpleStringProperty(milkWeightPercent);
		this.farmID = new SimpleStringProperty(ID);
	}

	private String getMilkWeight() {
		return totalMilkWeight.get();
	}

	private void setMilkWeight(String mw) {
		totalMilkWeight.set(mw);
	}

	private String getTotalPercent() {
		return totalPercent.get();
	}

	private void setTotalPercent(String mwp) {
		totalPercent.set(mwp);
	}
	private String getFarmID() {
		return farmID.get();
	}
	private void setFarmID(String id) {
		farmID.set(id);
	}

}
