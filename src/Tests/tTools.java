package Tests;

import static org.junit.Assert.*;
import Tools.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class tTools {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_isANumber() {		
		Assert.assertEquals(Tools.isNumeric("123"), true);
		Assert.assertEquals(Tools.isNumeric("12e4"), false);
		Assert.assertEquals(Tools.isNumeric("123456789012345678901234567890123"), true);
		//fail("Not yet implemented");
	}
	@Test
	public void test_configurationToString ()
	{
		Assert.assertEquals(Tools.configurationToString(new int[0]), "[]");
		Assert.assertEquals(Tools.configurationToString(new int[]{1}), "[ 1 ]");
		Assert.assertEquals(Tools.configurationToString(new int[]{1, 2, 3}), "[ 1, 2, 3 ]");		
	}
	@Test
	public void test_segmentIntersection ()
	{
		int[] a1 = new int[] { -1, 0, 0, 1, 4};
		int[] b1 = new int[] { 4, 2, 3, 2, 5};
		int[] a2 = new int[] { 0, 1, 2, 3, 4};
		int[] b2 = new int[] { 3, 3, 2, 4, 5};
		int[] rs = new int[] { 3, 1, 0, 0, 1};
		for(int i = 0; i < a1.length; i++)
			Assert.assertEquals(Tools.segmentIntersection(a1[i], b1[i], a2[i], b2[i]), rs[i]);
	}
	@Test
	public void test_mismatches ()
	{
		int[] v123 = new int[] { 1, 2, 3};
		int[] v113 = new int[] { 1, 1, 3};
		int[] v456 = new int[] { 4, 5, 6};
		int[] v458 = new int[] { 4, 5, 8};
		int[] v124 = new int[] { 1, 2, 4};
		int[] v1 = new int[] { 1 };

		Assert.assertEquals(Tools.mismatches(v123, v113), 1);
		Assert.assertEquals(Tools.mismatches(v123, v123), 0);
		Assert.assertEquals(Tools.mismatches(v123, v456), 3);
		Assert.assertEquals(Tools.mismatches(v123, v1), 0);			

		Assert.assertEquals(Tools.mismatches(v123, v113, 0), 1);
		Assert.assertEquals(Tools.mismatches(v123, v458, 4), 1);
		Assert.assertEquals(Tools.mismatches(v123, v124, 0), 1);
		Assert.assertEquals(Tools.mismatches(v123, v1, 1), 0);		

		Assert.assertEquals(Tools.mismatches(v123, v113, 2, 0), 1);
		Assert.assertEquals(Tools.mismatches(v123, v458, 2, 4), 0);
		Assert.assertEquals(Tools.mismatches(v123, v124, 2, 0), 0);
		
		Assert.assertEquals(Tools.mismatches(v123, v1, 1, 0), 0);
		Assert.assertEquals(Tools.mismatches(v123, v123, 4, 0), 0);		
	}
	@Test
	public void test_generateMonotonyN ()
	{
		Assert.assertEquals(Tools.generateMonotony(0), null);
		Assert.assertArrayEquals(Tools.generateMonotony(1), new int[]{0});
		Assert.assertArrayEquals(Tools.generateMonotony(3), new int[]{0, 1, 2});		
	}
	@Test
	public void test_generateMonotonyAB ()
	{
		Assert.assertEquals(Tools.generateMonotony(1, 0), null);
		Assert.assertArrayEquals(Tools.generateMonotony(1, 1), new int[]{1});
		Assert.assertArrayEquals(Tools.generateMonotony(1, 3), new int[]{1, 2, 3});
	}
	@Test
	public void test_sortAsc ()
	{
		int[] arr = new int[] { 5, 7, 3};
		Tools.sortAscendent (arr);
		Assert.assertArrayEquals(arr, new int[]{3, 5, 7});
	}
	@Test
	public void test_norm ()
	{
		int[] v123 = new int[] { 1, 2, 3};
		int[] v111 = new int[] { 1, 1, 1};
		int[] v235 = new int[] { 2, 3, 5};
		int[] v12 = new int[] { 1, 2};
		// Norm 1
		double eps = 0.001;
		Assert.assertEquals(Tools.norm1(v123, v123), 0, eps);
		Assert.assertEquals(Tools.norm1(v235, v111), 7, eps);
		Assert.assertEquals(Tools.norm1(v123, v12), 0, eps);		
		// Norm 2
		Assert.assertEquals(Tools.norm2(v123, v123), 0, eps);
		Assert.assertEquals(Tools.norm2(v235, v111), (float)Math.sqrt(21), eps);
		Assert.assertEquals(Tools.norm2(v123, v12), 0, eps);		
		// Norm Inf
		Assert.assertEquals(Tools.norm8(v123, v123), 0, eps);
		Assert.assertEquals(Tools.norm8(v235, v111), 4, eps);
		Assert.assertEquals(Tools.norm8(v123, v12), 0, eps);		
	}
	@Test
	public void test_sum ()
	{
		int[] v123 = new int[] { 1, 2, 3, 4};
		Assert.assertEquals(Tools.sum(v123, 3), 6);
		Assert.assertEquals(Tools.sum(v123, 5), 0);		
	}
	@Test
	public void Test_eq ()
	{
		int[] v123 = new int[] { 1, 2, 3};
		int[] v111 = new int[] { 1, 1, 1};
		int[] v213 = new int[] { 2, 1, 3};
		Assert.assertEquals(Tools.equals_vectors(v123, v111), false);
		Assert.assertEquals(Tools.equals_vectors(v123, v213), false);
	}
	@Test
	public void Test_Bits ()
	{
		PW_Integer int_ = new PW_Integer(8);
		Tools.activateBit (int_, 1); 
		Assert.assertEquals(int_.getValue(), 10);
		Tools.activateBit (int_, 1); 
		Assert.assertEquals(int_.getValue(), 10);
		int pot = 16 - 1;
		Assert.assertEquals(Tools.bitsCount(pot), 4);
		Assert.assertEquals(Tools.bitsCount(pot+1), 1);
		Assert.assertEquals(Tools.bitsCount(int_.getValue()), 2);
	}
	@Test
	public void Test_fillArray ()
	{
		int[] arr = new int[3];
		Tools.fill (arr, 3);
		Assert.assertArrayEquals(arr, new int[]{3, 3, 3});
		int[] v = new int[0];
		Tools.fill (v, 3);
		Assert.assertArrayEquals(v, new int[]{});
	}
	@Test
	public void Test_copy ()
	{
		int[] source = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		int[] destination = new int[] { 8, 9, 10, 11, 12, 13, 14, 15 };
		Tools.copy (source, 0, 7, destination, 1);
		Assert.assertArrayEquals(destination, new int[]{8, 1, 2, 3, 4, 5, 6, 7});
	}
}
