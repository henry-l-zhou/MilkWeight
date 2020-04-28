package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MonthlyReportProcessor {
	private Map<String, Integer> weights;
	private Map<String, Double> percents;
	private int month; 
	private int year;
	private MilkWeightDS ds;
	
	
	public MonthlyReportProcessor(MilkWeightDS ds, int month, int year) {
		this.ds = ds;
		this.month = month;
		this.year = year;
		weights = new HashMap<String, Integer>();
		percents = new HashMap<String, Double>();
		stats();
	}
	
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
	
	public int getWeight(String farmId) {
		return weights.get(farmId);
	}
	
	public double getPercent(String farmId) {
		return percents.get(farmId);
	}
	
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
