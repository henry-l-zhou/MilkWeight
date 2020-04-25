package backend;

import java.util.List;
import application.MilkWeight;

public interface DataStructureADT {
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
   * If farmId is found remove all farms  associated with it
   * @param farmId
   * @return true if found farmId, false if not found
   */
  public boolean removeFarm(String farmId);
  
  /**
   * Returns true if the ID  is in the data  structure  
   * @param farmId
   * @return true if farmId i
   */
  public boolean contains(String farmId);
  
  /**
   * Returns the number of  farms 
   * @return int # of farms
   */
  public int size();
  
  /**
   *  Returns list of  MilkWeightItems with  that date  
   * @param date
   * @return
   */
  public List<MilkWeight> getMilkWeightDate(int date);
  
  /**
   * 
   * @param date
   * @return
   */
  public List<MilkWeight> getMilkWeightMonth(int date);
  
  public List<MilkWeight> getMilkWeightYear(int date);
  
  public List<MilkWeight> getMilkWeightFarm(String farmID);
  
}
