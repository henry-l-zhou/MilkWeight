package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class processes a MilkWeightDS to get stats for a specific year
 * @author Brian Hu
 *
 */
public class AnnualReportProcessor {
  private Map<String, Integer> weights;
  private Map<String, Double> percents;
  private int year;
  private MilkWeightDS ds;
  private List<String> uniqueFarms;

  /**
   * Constructs an AnnualReportProcessor
   * @param ds - the MilkWeightDS to be processed
   * @param year - the desired year
   */
  public AnnualReportProcessor(MilkWeightDS ds, int year) {
    this.ds = ds;
    this.year = year;
    weights = new HashMap<String, Integer>();
    percents = new HashMap<String, Double>();
    uniqueFarms = new ArrayList<String>();
    stats();
  }

  /**
   * Processes the MilkWeightDS
   */
  private void stats() {
    List<MilkWeight> yearData = ds.getMilkWeightYear(year);
    int sum = 0;
    HashSet<String> seenFarms = new HashSet<String>();
    int count = 0;
    for (MilkWeight mw : yearData) {
      if (!seenFarms.contains(mw.getFarmId())) {
        count++;
        seenFarms.add(mw.getFarmId());
        uniqueFarms.add(mw.getFarmId());
      }
      if (weights.containsKey(mw.getFarmId())) {
        weights.put(mw.getFarmId(), weights.get(mw.getFarmId()) + mw.getWeight());
      } else {
        weights.put(mw.getFarmId(), mw.getWeight());
      }
      sum += mw.getWeight();
    }

    for (String farmId : weights.keySet()) {
      percents.put(farmId, (double) weights.get(farmId) / sum);
    }
  }

  /**
   * Gets the total weight for a specific farm ID
   * @param farmId - the farmID
   * @return the total weight in the year for that farm ID
   */
  public int getWeight(String farmId) {
    return weights.get(farmId);
  }

  /**
   * Gets a farm ID's percent of the total weight in the year
   * @param farmId - the farm ID
   * @return the percent of the total weight for that farm ID in the year
   */
  public double getPercent(String farmId) {
    return Math.round(percents.get(farmId) * 10000) / 100.0;
  }

  /**
   * gets the unique farms in the annual report
   * @return
   */
  public List<String> uniqueFarms() {
    System.out.println(uniqueFarms);
    return uniqueFarms;
  }

  /**
   * Outputs the DateRangeReport as a CSV file
   */
  public void toCSV() {
    PrintWriter output = null;
    try {
      output = new PrintWriter("annual_report.csv");
      output.println("farm_id,weight,percent");
      List<String> farms = new LinkedList<String>();
      for (String farmId : weights.keySet()) {
        farms.add(farmId);
      }
      Collections.sort(farms);
      for (String farmId : farms) {
        output.println(farmId + "," + weights.get(farmId) + "," + percents.get(farmId));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (output != null) output.close();
    }
  }

  //testing
  public static void main(String args[]) {
    MilkWeightDS ds = new MilkWeightDS();
    InputReader ir = new InputReader(new File("src/2019-1.csv"));
    ir.getList().forEach(mw -> ds.insert(mw));
    AnnualReportProcessor frp = new AnnualReportProcessor(ds, 2019);
    frp.toCSV();
  }
}
