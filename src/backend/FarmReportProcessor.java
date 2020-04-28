package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
/**
 * This class processes a MilkWeightDS to get stats for a specific farm and year
 * @author Brian Hu
 *
 */
public class FarmReportProcessor {
	private int[] weights;
	private double[] percents;
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
		stats();
	}
	
	/**
	 * Processes the MilkWeightDS
	 */
	private void stats() {
		List<MilkWeight> farmReport = ds.getMilkWeightFarm(farmId, year);
		for(MilkWeight mw : farmReport) {
			weights[mw.getMonth() - 1] += mw.getWeight();
		}
		
		for(int month = 1; month <= 12; month++) {
			List<MilkWeight> monthReport = ds.getMilkWeightMonth(month, year);
			int sum = 0;
			for(MilkWeight mw : monthReport) {
				sum += mw.getWeight();
			}
			percents[month - 1] = (double) weights[month - 1] / sum;
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
		return Math.round(percents[month - 1] * 10000) / 100.0;
	}
	
	/**
	 * Outputs the DateRangeReport as a CSV file
	 */
	public void toCSV() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("farm_report.csv");
			output.print("month,weight,percent\n");
			for(int month = 1; month <= 12; month++) {
				String out = month + "," + weights[month - 1] + "," + percents[month - 1] + "\n";
				output.print(out);
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
		FarmReportProcessor frp = new FarmReportProcessor(ds, "Farm 100", 2019);
		frp.toCSV();
	}
}
