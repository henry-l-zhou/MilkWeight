package application;

import java.util.List;
import java.util.Set;
/**
 * This class sets the data structure adt that holds all the inputed data
 * 
 * @author ateam85
 *
 */
public interface MilkWeightDSADT {
  /**
   * Adds a new farmID,  date and milkWeight  entry 
   * @param mw
   */
  public void insert(MilkWeight mw);

  /**
   * If a mw entry is found, remove it  
   * @param mw
   * @return true if found, false if not
   */
  public boolean removeEntry(MilkWeight mw);

  /**
   * Returns the set of farms 
   * @return set of farms
   */
  public Set<String> getFarms();
  
  /**
   * Returns the number of farms 
   * @return int # of farms
   */
  public int getFarmNumber();
  /**
   * Return the number of entries
   */
  public int getEntries();

  /**
   *  Returns list of  MilkWeightItems with  that date  
   * @param date
   * @return
   */
  public List<MilkWeight> getMilkWeightDateRange(int dateFrom, int monthFrom, int yearFrom, int dateTo, int monthTo,
      int yearTo);

  /**
   * checks if a MW is in the MilkWeightDS
   * @param mw
   * @return
   */
  public boolean contains(MilkWeight mw);
  
  /**
   * Returns the list of MilkWeights for that month and year
   * @param month
   * @param year
   * @return List of MW
   */
  public List<MilkWeight> getMilkWeightMonth(int month, int year);
  /**
   * Returns the list of MilkWeights for that year
   * @param year
   * @return List of MW
   */
  public List<MilkWeight> getMilkWeightYear(int year);
  /**
   * Returns the list of MilkWeights for that farm for the year
   * @param year
   * @param farmId
   * @return List of MW
   */
  public List<MilkWeight> getMilkWeightFarm(String farmID, int year);

}
