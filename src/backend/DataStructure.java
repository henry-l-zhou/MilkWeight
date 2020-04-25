package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStructure implements DataStructureADT {
  private List<MilkWeight> mwList;
  private Map<String, List<MilkWeight>> mwMap;
  
  public DataStructure() {
    mwList = new ArrayList<MilkWeight>();
    mwMap = new HashMap<String, List<MilkWeight>>();
    mwList.add(new MilkWeight(1999, 1, 10, "Farm 0", 69));
    mwList.add(new MilkWeight(2000, 1, 10, "Farm 1", 69));
    mwList.add(new MilkWeight(2000, 2, 11, "Farm 2", 69));
    mwList.add(new MilkWeight(1949, 1, 10, "Farm 3", 69));
    mwList.add(new MilkWeight(1599, 1, 10, "Farm 0", 69));
    mwList.add(new MilkWeight(2000, 3, 11, "Farm 5", 69));
    Collections.sort(mwList);
    
  }

  @Override
  public void insert(MilkWeight mw) {
    

  }

  @Override
  public boolean removeEntry(MilkWeight mw) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeFarm(String farmId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean contains(String farmId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<MilkWeight> getMilkWeightDate(int date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MilkWeight> getMilkWeightMonth(int date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MilkWeight> getMilkWeightYear(int date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MilkWeight> getMilkWeightFarm(String farmID) {
    // TODO Auto-generated method stub
    return null;
  }

  public static void main(String[] args) {
    DataStructure ds = new DataStructure();
    System.out.println(ds.mwList);
    System.out.println(Collections.binarySearch(ds.mwList, new MilkWeight(1699, 3, 11, "Farm 5", 69)));
    
  }
}
