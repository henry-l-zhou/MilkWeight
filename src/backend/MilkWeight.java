package backend;

/**
 * This class represents a single Milk Weight Item with a
 * 
 * @author Joonbo Shim
 *
 */
public class MilkWeight implements Comparable<MilkWeight>{
	// prototype class to display data to the table
	private int date;
	private int month;
	private int year;
	private String farmId;
	private int weight;

	public MilkWeight(int year, int month, int date, String farmId, int weight) {
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

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
		if(yearDiff != 0) {
			return yearDiff;
		}
		else {
			if(monthDiff != 0) {
				return monthDiff;
			}
			else {
				if(dateDiff != 0) {
					return dateDiff;
				}
				else {
					if(farmDiff != 0) {
						return farmDiff;
					}
					else return (weightDiff != 0) ? weightDiff : 0;
				}
			}
		}
	}

}
