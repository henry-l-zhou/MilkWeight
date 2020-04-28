package backend;

import java.util.List;
import java.util.Set;

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
   * 
   * @param date
   * @return
   */
  public List<MilkWeight> getMilkWeightMonth(int month, int year);

  public List<MilkWeight> getMilkWeightYear(int year);

  public List<MilkWeight> getMilkWeightFarm(String farmID, int year);

}
