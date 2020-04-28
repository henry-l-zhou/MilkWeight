package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * This class processes a MilkWeightDS to get stats for a specific month and year
 * @author Brian Hu
 *
 */
public class MonthlyReportProcessor {
	private Map<String, Integer> weights;
	private Map<String, Double> percents;
	private int month; 
	private int year;
	private MilkWeightDS ds;
	
	/**
	 * Constructs a MonthlyReportProcessor
	 * @param ds - the MilkWeightDS to be processed
	 * @param month - the month
	 * @param year - the year
	 */
	public MonthlyReportProcessor(MilkWeightDS ds, int month, int year) {
		this.ds = ds;
		this.month = month;
		this.year = year;
		weights = new HashMap<String, Integer>();
		percents = new HashMap<String, Double>();
		stats();
	}
	
	/**
	 * Processes the MilkWeightDS
	 */
	private void stats() {
		List<MilkWeight> monthData = ds.getMilkWeightMonth(month, year);
		int sum = 0;
		for(MilkWeight mw : monthData) {
			if(weights.containsKey(mw.getFarmId())) {
				weights.put(mw.getFarmId(), weights.get(mw.getFarmId()) + mw.getWeight());
			}
			else {
				weights.put(mw.getFarmId(), mw.getWeight());
			}
			sum += mw.getWeight();
		}
		
		for(String farmId : weights.keySet()) {
			percents.put(farmId, (double) weights.get(farmId) / sum);
		}
	}
	
	/**
	 * Gets the total weight for a specific farm ID
	 * @param farmId - the farmID
	 * @return the total weight in that month and year for that farm ID
	 */
	public int getWeight(String farmId) {
		return weights.get(farmId);
	}
	
	/**
	 * Gets a farm ID's percent of the total weight in that month and year
	 * @param farmId - the farm ID
	 * @return the percent of the total weight for that farm ID in that month and year
	 */
	public double getPercent(String farmId) {
		return Math.round(percents.get(farmId) * 10000) / 100.0;
	}
	
	/**
	 * Outputs the DateRangeReport as a CSV file
	 */
	public void toCSV() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("monthly_report.csv");
			output.println("farm_id,weight,percent");
			List<String> farms = new LinkedList<String>();
			for(String farmId : weights.keySet()) {
				farms.add(farmId);
			}
			Collections.sort(farms);
			for(String farmId : farms) {
				output.println(farmId + "," + weights.get(farmId) + "," + percents.get(farmId));
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(output != null) output.close();
		}
	}
	
	//testing
		public static void main(String args[]) {
			MilkWeightDS ds = new MilkWeightDS();
			InputReader ir = new InputReader(new File("src/2019-1.csv"));
			ir.getList().forEach(mw -> ds.insert(mw));
			MonthlyReportProcessor mrp = new MonthlyReportProcessor(ds, 2, 2019);
			mrp.toCSV();
		}
}
