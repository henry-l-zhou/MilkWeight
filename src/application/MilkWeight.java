package application;

/**
 * This class represents a single Milk Weight Item with a
 * 
 * @author Joonbo Shim
 *
 */
public class MilkWeight {
	// prototype class to display data to the table
	private int date;
	private int month;
	private int year;
	private String farmId;
	private String weight;

	MilkWeight(int year, int month, int date, String farmId, String weight) {
		this.date = date;
		this.year = year;
		this.month = month;
		this.farmId = farmId;
		this.weight = weight;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
