package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class processes a MilkWeightDS to get stats for a specific date range
 * 
 * @author ateam85
 *
 */
public class DateRangeReportProcessor {
	private Map<String, Integer> weights;
	private Map<String, Double> percents;
	private Map<String, Integer> min;
	private Map<String, Integer> max;
	private Map<String, Double> avg;
	private int dateFrom, monthFrom, yearFrom, dateTo, monthTo, yearTo;
	private MilkWeightDS ds;
	private List<String> uniqueFarms;

	/**
	 * Constructs a DateRangeReportProcessor
	 * 
	 * @param ds        - the data structure being processed
	 * @param dateFrom  - the start day
	 * @param monthFrom - the start month
	 * @param yearFrom  - the start year
	 * @param dateTo    - the end day
	 * @param monthTo   - the end month
	 * @param yearTo    - the end year
	 */
	public DateRangeReportProcessor(MilkWeightDS ds, int dateFrom, int monthFrom, int yearFrom, int dateTo, int monthTo,
			int yearTo) {
		this.ds = ds;
		this.dateFrom = dateFrom;
		this.monthFrom = monthFrom;
		this.yearFrom = yearFrom;
		this.dateTo = dateTo;
		this.monthTo = monthTo;
		this.yearTo = yearTo;
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
		List<MilkWeight> rangeData = ds.getMilkWeightDateRange(dateFrom, monthFrom, yearFrom, dateTo, monthTo, yearTo);
		int sum = 0;
		for (MilkWeight mw : rangeData) {
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
			avg.put(farmId, Math.round(weights.get(farmId) * 100 / avg.get(farmId)) / 100.0); // calc avg by dividing
																								// totals by counts
			percents.put(farmId, Math.round((double) weights.get(farmId) * 10000 / sum) / 100.0); // calc percent by
																									// dividing weights
		}
	}

	/**
	 * Gets the total weight for a specific farm ID
	 * 
	 * @param farmId - the farmID
	 * @return the total weight in the range for that farm ID
	 */
	public int getWeight(String farmId) {
		return weights.get(farmId);
	}

	/**
	 * Gets a farm ID's percent of the total weight in the range
	 * 
	 * @param farmId - the farm ID
	 * @return the percent of the total weight for that farm ID in the range
	 */
	public double getPercent(String farmId) {
		return percents.get(farmId);
	}

	/**
	 * Gets a farm ID's max milk weight in the range
	 * 
	 * @param farmId - the farm ID
	 * @return the max milk weight for this farm ID
	 */
	public int getMax(String farmId) {
		return max.get(farmId);
	}

	/**
	 * Gets a farm ID's min milk weight in the range
	 * 
	 * @param farmId - the farm ID
	 * @return the min milk weight for this farm ID
	 */
	public int getMin(String farmId) {
		return min.get(farmId);
	}

	/**
	 * Gets a farmID's average milk weight in the range
	 * 
	 * @param farmId - the farm ID
	 * @return the average milk weight for this farm ID
	 */
	public double getAvg(String farmId) {
		return avg.get(farmId);
	}

	/**
	 * gets the unique farms in the report
	 * 
	 * @return
	 */
	public List<String> uniqueFarms() {
		Collections.sort(uniqueFarms);
		return uniqueFarms;
	}

	/**
	 * Outputs the DateRangeReport as a CSV file
	 */
	public void toCSV() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("sample_output_csv/date_range_report.csv");
			output.println("farm_id,weight,percent,min,max,average"); // headers of the csv
			List<String> farms = new LinkedList<String>();
			for (String farmId : weights.keySet()) { // adds all the farms to a list to be sorted
				farms.add(farmId);
			}
			Collections.sort(farms); // sort the list
			for (String farmId : farms) { // iterate through sorted list
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
}
