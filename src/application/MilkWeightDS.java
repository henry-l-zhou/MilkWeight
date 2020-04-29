package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MilkWeightDS implements MilkWeightDSADT {
  private TreeMap<MilkWeight, MilkWeight> mwTreeMap;
  private Map<String, TreeMap<MilkWeight, MilkWeight>> mwHashMap;

  public MilkWeightDS() {
    mwTreeMap = new TreeMap<MilkWeight, MilkWeight>();
    mwHashMap = new HashMap<String, TreeMap<MilkWeight, MilkWeight>>();

  }

  @Override
  public void insert(MilkWeight mw) {
    if (mwTreeMap.containsKey(mw)) {
      
      return;
    }
    //TREE MAP INSERTION
    mwTreeMap.put(mw, mw);

    //HASHMAP INSERTION
    if (mwHashMap.containsKey(mw.getFarmId())) {
      mwHashMap.get(mw.getFarmId()).put(mw, mw);
    } else {
      mwHashMap.put(mw.getFarmId(), new TreeMap<MilkWeight, MilkWeight>());
      mwHashMap.get(mw.getFarmId()).put(mw, mw);
    }
  }

  @Override
  public boolean removeEntry(MilkWeight mw) {
    mwHashMap.get(mw.getFarmId()).remove(mw);
    //mwTreeMap.remove(mw);
    return mwTreeMap.remove(mw) != null;
  }

  @Override
  public int getFarmNumber() {
    return mwHashMap.size();
  }

  @Override 
  public Set<String> getFarms(){
    return mwHashMap.keySet();
    
  }
  @Override
  public int getEntries() {
    return mwTreeMap.size();
  }
  
  @Override
  public boolean contains(MilkWeight mw) {
    return mwTreeMap.containsKey(mw);
  }
  @Override
  public List<MilkWeight> getMilkWeightDateRange(int dateFrom, int monthFrom, int yearFrom, int dateTo, int monthTo,
      int yearTo) {
    ArrayList<MilkWeight> lst = new ArrayList<MilkWeight>();
    for (Entry<MilkWeight, MilkWeight> item : mwTreeMap
        .subMap(new MilkWeight(yearFrom, monthFrom, dateFrom, "", 0), new MilkWeight(yearTo, monthTo, dateTo, "", 0))
        .entrySet()) {
      lst.add(item.getKey());
      
    }
    return lst;
  }

  @Override
  public List<MilkWeight> getMilkWeightMonth(int month, int year) {
    ArrayList<MilkWeight> lst = new ArrayList<MilkWeight>();
    for (Entry<MilkWeight, MilkWeight> item : mwTreeMap
        .subMap(new MilkWeight(year, month, 0, "", 0), new MilkWeight(year, month, 32, "", 0)).entrySet()) {
      lst.add(item.getKey());
      
    }
    return lst;
  }

  @Override
  public List<MilkWeight> getMilkWeightYear(int year) {
    ArrayList<MilkWeight> lst = new ArrayList<MilkWeight>();
    for (Entry<MilkWeight, MilkWeight> item : mwTreeMap
        .subMap(new MilkWeight(year, 0, 0, "", 0), new MilkWeight(year, 13, 32, "", 0)).entrySet()) {
      lst.add(item.getKey());
      
    }
    return lst;
  }

  @Override
  public List<MilkWeight> getMilkWeightFarm(String farmID, int year) {
    if (mwHashMap.get(farmID) == null) return null;
    ArrayList<MilkWeight> lst = new ArrayList<MilkWeight>();
    for (Entry<MilkWeight, MilkWeight> item : mwHashMap.get(farmID)
        .subMap(new MilkWeight(year, 0, 0, "", 0), new MilkWeight(year, 13, 32, "", 0)).entrySet()) {
      lst.add(item.getKey());
      
    }
    return lst;
  }

  public static void main(String[] args) {
    MilkWeightDS ds = new MilkWeightDS();
    ds.insert(new MilkWeight(1699, 3, 11, "Farm 5", 69));
    ds.insert(new MilkWeight(1699, 4, 11, "Farm 5", 619));
    ds.insert(new MilkWeight(1699, 5, 11, "Farm 5", 169));
    ds.insert(new MilkWeight(1699, 6, 11, "Farm 4", 469));
    ds.insert(new MilkWeight(1699, 3, 15, "Farm 1", 619));
    ds.insert(new MilkWeight(1699, 3, 12, "Farm 4", 619));

    ds.getMilkWeightFarm("Farm 4", 1699);



  }
}
