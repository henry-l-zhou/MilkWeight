package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
/**
 * This class processes a MilkWeightDS to get stats for a specific farm and year
 * @author ateam85
 *
 */
public class FarmReportProcessor {
	private int[] weights;
	private double[] percents;
	private int[] min;
	private int[] max;
	private double[] avg;
	private String farmId;
	private MilkWeightDS ds;
	private int year;
	
	/**
	 * Constructs a FarmReportProcessor
	 * @param ds - the MilkWeightDS to be processed
	 * @param farmId - the farm
	 * @param year - the year
	 */
	public FarmReportProcessor(MilkWeightDS ds, String farmId, int year) {
		this.ds = ds;
		this.farmId = farmId;
		this.year = year;
		weights = new int[12];
		percents = new double[12];
		min = new int[12];
		Arrays.fill(min, Integer.MAX_VALUE);
		max = new int[12];
		avg = new double[12];
		stats();
	}
	
	/**
	 * Processes the MilkWeightDS
	 */
	private void stats() {
		List<MilkWeight> farmReport = ds.getMilkWeightFarm(farmId, year);
		for(MilkWeight mw : farmReport) { //iterate through all the data
			weights[mw.getMonth() - 1] += mw.getWeight(); //add weight to corresponding month
			if(mw.getWeight() > max[mw.getMonth() - 1]) max[mw.getMonth() - 1] = mw.getWeight(); //update max weight of month
			if(mw.getWeight() < min[mw.getMonth() - 1]) min[mw.getMonth() - 1] = mw.getWeight(); //update min weight of month
			avg[mw.getMonth() - 1] += 1; //(avg serves as temp counter) counts the number of MW in that month
		}
		
		for(int month = 1; month <= 12; month++) {
			List<MilkWeight> monthReport = ds.getMilkWeightMonth(month, year);
			int sum = 0; //stores the sum of all weights from all farms in the month
			for(MilkWeight mw : monthReport) {
				sum += mw.getWeight();
			}
			percents[month - 1] = Math.round((double) weights[month - 1] * 10000 / sum) / 100.0; //calculate farm's share for the month
			avg[month - 1] = Math.round(weights[month - 1] * 100 / avg[month - 1]) / 100.0; //calculate farm's average weight for the month
		}
	}
	
	/**
	 * Gets the total weight for a specific month
	 * @param month - the month
	 * @return the total weight for that month
	 */
	public int getWeight(int month) {
		return weights[month - 1];
	}
	
	/**
	 * Gets the percent of total weight for a specific month
	 * @param month - the month
	 * @return the percent of total weight for that month
	 */
	public double getPercent(int month) {
		return percents[month - 1];
	}
	
	/**
	 * Gets the max milk weight of the month
	 * 
	 * @param month - the month
	 * @return the max milk weight for this month
	 */
	public int getMax(int month) {
		return max[month - 1];
	}

	/**
	 * Gets the min milk weight of the month
	 * 
	 * @param month - the month
	 * @return the min milk weight for this month
	 */
	public int getMin(int month) {
		return min[month - 1];
	}

	/**
	 * Gets the average milk weight for this month
	 * 
	 * @param month - the month
	 * @return the average milk weight for this month
	 */
	public double getAvg(int month) {
		return avg[month - 1];
	}
	
	/**
	 * Outputs the FarmReport as a CSV file
	 */
	public void toCSV() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("farm_report.csv");
			output.println("month,weight,percent,min,max,average"); //headers of the csv
			for(int month = 1; month <= 12; month++) { //adds data to the csv month by month
				String out = month + "," + weights[month - 1] + "," + percents[month - 1] + "," + min[month - 1] +
						"," + max[month - 1] + "," + avg[month - 1];
				output.println(out);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(output != null) output.close();
		}
	}
}
