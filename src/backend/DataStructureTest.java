package backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class DataStructureTest {
	DataStructure ds;
	MilkWeight mw1;
	MilkWeight mw2;
	MilkWeight mw3;
	MilkWeight mw4;

	@Before
	void setUp() throws Exception {
		// initialize DataStrcuture
		ds = new DataStructure();

		// insert two MilkWeight before test starts
		mw1 = new MilkWeight(2020, 4, 24, "Farm 1", 3793);
		mw2 = new MilkWeight(2020, 4, 25, "Farm 2", 8533);
		mw3 = new MilkWeight(2019, 4, 25, "Farm 5", 19136);
		mw4 = new MilkWeight(2020, 2, 6, "Farm 2", 25678);

		ds.insert(mw1);
		ds.insert(mw2);
		ds.insert(mw3);
		ds.insert(mw4);
	}

	@After
	void tearDown() throws Exception {
	}

	/**
	 * checks if a new MilkWeight being inserted will increase size
	 */
	@Test
	public void test01_size() {
		// size of ds has to be two because only two were inserted
		if (ds.size() != 4) {
			fail("Size is incorrect");
		}
	}

	/**
	 * checks if DataStructure correctly removes the given MilkWeight and the given farmId
	 */
	@Test
	public void test02_removeEntry() {
		// check removeEntry()
		if (!ds.removeEntry(new MilkWeight(2020, 4, 24, "Farm 1", 3793))) {
			fail("removeEntry() should return true, BUT it returns false instead");
		}
	}

	/**
	 * checks if DataStructure correctly returns a sorted List based on farmId of MilkWeight with a range of dates
	 */
	@Test
	public void test03_getMilkWeightDateRange() {
		List<MilkWeight> list = ds.getMilkWeightDateRange(24, 4, 2020, 25, 4, 2020);

		// check to see if each index has the correct MilkWeight
		if (list.get(0) != mw1) {
			fail("The list is not sorted correctly");
		} else if (list.get(1)!= mw2) {
			fail("The list is not sorted correctly");
		}
	}

	/**
	 * checks if DataStructure correctly returns a sorted List based on farmId of MilkWeight with range of month and year
	 */
	@Test
	public void test04_getMilkWeightMonth() {
		List<MilkWeight> list = ds.getMilkWeightMonth(4, 2020);
		
		// check to see if each index has the correct MilkWeight
		if (list.get(0) != mw1) {
			fail("The list is not sorted correctly");
		} else if (list.get(1)!= mw2) {
			fail("The list is not sorted correctly");
		} else if (list.get(2) != mw4) {
			fail("The list is not sorted correctyl");
		}
	}

	/**
	 * checks if DataStructure correctly returns a sorted List based on farmId of MilkWeight with range of year
	 */
	@Test
	public void test05_getMilkWeightYear() {
		List<MilkWeight> list = ds.getMilkWeightYear(2020);
		
		// check to see if each index has the correct MilkWeight
		if (list.get(0) != mw1) {
			fail("The list is not sorted correctly");
		} else if (list.get(1)!= mw2) {
			fail("The list is not sorted correctly");
		} else if (list.get(2) != mw4) {
			fail("The list is not sorted correctyl");
		}
	}

	/**
	 * checks if DataStructure correctly returns a sorted List based on month of MilkWeight farmId
	 */
	@Test
	public void test06_getMilkWeightFarm() {
		List<MilkWeight> list = ds.getMilkWeightFarm("Farm 2", 2020);
		
		// check to see if each index has the correct MilkWeight
		if (list.get(0) != mw4) {
			fail("The list is not sorted correctly");
		} else if (list.get(1)!= mw2) {
			fail("The list is not sorted correctly");
		}		
		
	}

}
