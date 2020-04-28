package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class processes a MilkWeightDS to get stats for a specific month and
 * year
 * 
 * @author Brian Hu
 *
 */
public class MonthlyReportProcessor {
	private Map<String, Integer> weights;
	private Map<String, Double> percents;
	private Map<String, Integer> min;
	private Map<String, Integer> max;
	private Map<String, Double> avg;
	private int month;
	private int year;
	private MilkWeightDS ds;
	private List<String> uniqueFarms;

	/**
	 * Constructs a MonthlyReportProcessor
	 * 
	 * @param ds    - the MilkWeightDS to be processed
	 * @param month - the month
	 * @param year  - the year
	 */
	public MonthlyReportProcessor(MilkWeightDS ds, int month, int year) {
		this.ds = ds;
		this.month = month;
		this.year = year;
		weights = new HashMap<String, Integer>();
		percents = new HashMap<String, Double>();
		min = new HashMap<String, Integer>();
		max = new HashMap<String, Integer>();
		avg = new HashMap<String, Double>();
		uniqueFarms = new ArrayList<String>();
		stats();
	}

	/**
	 * Processes the MilkWeightDS
	 */
	private void stats() {
		List<MilkWeight> monthData = ds.getMilkWeightMonth(month, year);
		int sum = 0;
		for (MilkWeight mw : monthData) {
			if (weights.containsKey(mw.getFarmId())) { // if the farmId has already been seen
				weights.put(mw.getFarmId(), weights.get(mw.getFarmId()) + mw.getWeight()); // update weight for the farm
				if (mw.getWeight() > max.get(mw.getFarmId()))
					max.put(mw.getFarmId(), mw.getWeight()); // update max
				if (mw.getWeight() < min.get(mw.getFarmId()))
					min.put(mw.getFarmId(), mw.getWeight()); // update min
				avg.put(mw.getFarmId(), avg.get(mw.getFarmId()) + 1); // (avg serves as temp counter) update total
																		// number of instances
			} else { // if the farmId has not been seen
				weights.put(mw.getFarmId(), mw.getWeight()); // add a new farm in the weight map
				max.put(mw.getFarmId(), mw.getWeight()); // add to max
				min.put(mw.getFarmId(), mw.getWeight()); // add to min
				uniqueFarms.add(mw.getFarmId()); // add to unique list of farms
				avg.put(mw.getFarmId(), 1.0); // (avg serves as temp counter) set counter to 1
			}
			sum += mw.getWeight();
		}

		for (String farmId : weights.keySet()) {
			avg.put(farmId, Math.round(weights.get(farmId) * 100 / avg.get(farmId)) / 100.0); // calc avg by dividing totals by counts
			percents.put(farmId, Math.round((double) weights.get(farmId) * 10000 / sum) / 100.0); // calc percent by
																									// dividing weights
		}
	}

	/**
	 * Gets the total weight for a specific farm ID
	 * 
	 * @param farmId - the farmID
	 * @return the total weight in that month and year for that farm ID
	 */
	public int getWeight(String farmId) {
		return weights.get(farmId);
	}

	/**
	 * gets the unique farms in the report
	 * 
	 * @return
	 */
	public List<String> uniqueFarms() {
		return uniqueFarms;
	}

	/**
	 * Gets a farm ID's percent of the total weight in that month and year
	 * 
	 * @param farmId - the farm ID
	 * @return the percent of the total weight for that farm ID in that month and
	 *         year
	 */
	public double getPercent(String farmId) {
		return percents.get(farmId);
	}

	/**
	 * Gets a farm ID's max milk weight in the month and year
	 * 
	 * @param farmId - the farm ID
	 * @return the max milk weight for this farm ID
	 */
	public int getMax(String farmId) {
		return max.get(farmId);
	}

	/**
	 * Gets a farm ID's min milk weight in the month and year
	 * 
	 * @param farmId - the farm ID
	 * @return the min milk weight for this farm ID
	 */
	public int getMin(String farmId) {
		return min.get(farmId);
	}

	/**
	 * Gets a farmID's average milk weight in the month and year
	 * 
	 * @param farmId - the farm ID
	 * @return the average milk weight for this farm ID
	 */
	public double getAvg(String farmId) {
		return avg.get(farmId);
	}

	/**
	 * Outputs the MonthlyReport as a CSV file
	 */
	public void toCSV() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("monthly_report.csv");
			output.println("farm_id,weight,percent,min,max,average");
			List<String> farms = new LinkedList<String>();
			for (String farmId : weights.keySet()) {
				farms.add(farmId);
			}
			Collections.sort(farms);
			for (String farmId : farms) {
				output.println(farmId + "," + weights.get(farmId) + "," + percents.get(farmId) + "," + min.get(farmId)
						+ "," + max.get(farmId) + "," + avg.get(farmId));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				output.close();
		}
	}

	// testing
	public static void main(String args[]) {
		MilkWeightDS ds = new MilkWeightDS();
		InputReader ir = new InputReader(new File("src/2019-1.csv"));
		ir.getList().forEach(mw -> ds.insert(mw));
		MonthlyReportProcessor mrp = new MonthlyReportProcessor(ds, 2, 2019);
		mrp.toCSV();
	}
}
