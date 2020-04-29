package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MilkWeightTest {
  MilkWeight mw;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  public void setUp() throws Exception {
    mw = new MilkWeight(2020, 04, 25, "Farm 1", 7777);
  }

  @AfterEach
  public void tearDown() throws Exception {
    mw = null;
  }

  /**
   * Test that the milk weight object is constructed properly.
   */
  @Test
  public void test1_milk_weight_construction() {
    try {
      assert (mw.getYear() == 2020);
      assert (mw.getMonth() == 4);
      assert (mw.getDate() == 25);
      assert (mw.getFarmId().equals("Farm 1"));
      assert (mw.getWeight() == 7777);
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

  /**
   * Tests that the toString method is returning the correct string.
   */
  @Test
  public void test2_milk_weight_to_string() {
    try {
      assert (mw.toString().equals("Farm 1,2020-4-25,7777"));
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

  /**
   * Tests compares the difference in years of two milk weight objects and checks if the correct
   * difference is returned.
   */
  @Test
  public void test3_compare_year() {
    try {
      MilkWeight mw1 = new MilkWeight(2010, 4, 29, "Farm 1", 7777);
      assert (mw.compareTo(mw1) == 10);
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

  /**
   * Tests compares the difference in months of two milk weight objects and checks if the correct
   * difference is returned.
   */
  @Test
  public void test4_compare_month() {
    try {
      MilkWeight mw1 = new MilkWeight(2020, 1, 25, "Farm 2", 7777);
      assert (mw.compareTo(mw1) == 3);
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

  /**
   * Tests compares the difference in days of two milk weight objects and checks if the correct
   * difference is returned.
   */
  @Test
  public void test5_compare_day() {
    try {
      MilkWeight mw1 = new MilkWeight(2020, 4, 10, "Farm 1", 4321);
      assert (mw.compareTo(mw1) == 15);
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

  /**
   * Tests compares the difference between the farm ID's of two milk weight objects and checks if
   * the correct difference is returned.
   */
  @Test
  public void test6_compare_farm_id() {
    try {
      MilkWeight mw1 = new MilkWeight(2020, 4, 25, "Farm 2", 7777);
      assert (mw.compareTo(mw1) < 0);
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

  /**
   * Tests compares the difference between the weights of two milk weight objects and checks if the
   * correct difference is returned.
   */
  @Test
  public void test7_compare_milk_weight() {
    try {
      MilkWeight mw1 = new MilkWeight(2020, 4, 25, "Farm 1", 4321);
      assert (mw.compareTo(mw1) == 3456);
    } catch (Exception e) {
      fail("Unexpected exception occurred at: " + e.getMessage());
    }
  }

}
