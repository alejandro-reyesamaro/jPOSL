/**
 * 
 */
package Tools;

import java.lang.Math.*;
import java.util.*;

/*!
 * \class PoslTools 
 * \brief Static class containing auxiliary methods
 * \author Alejandro Reyes
	 * \date 2017-05-17
 */
public class Tools {
	
	//! Verifies whether a string str is a number
	public static boolean isNumeric(String str)
	{
		return str.matches("^-?\\d+$");
	}
	
	//! Configuration into a string 
	public static String configurationToString(int [] config)
	{
		if (config == null || config.length == 0)
			return "[]";
		String txt = "[ " + config[0];
		for (int i = 1; i < config.length; i++) 
			txt += ", " + config[i];
		return txt + " ]";
	}

	//! Width of an intersection between two segments
	public static int segmentIntersection(int a1, int b1, int a2, int b2)
	{
		if (a1 > b1 || a2 > b2)
			return 0;
		int A = Math.max(a1, a2);
		int b = Math.min(b1, b2);
		return Math.max(0, b - A);
	}
	
	//! Generates a vector 0...N-1
	public static int[] generateMonotony(int N)
	{
		if (N <= 0)
			return null;
		int[] v = new int[N];
		for (int i = 0; i < N; i++)
			v [i] = i;
		return v;
	}
	
	//! Generates a vector a...b
	public static int[] generateMonotony(int a, int b)
	{
		if (a > b)
			return null;
		int N = b - a + 1;
		int[] v = new int[N];
		for (int i = 0; i < N; i++)
			v [i] = a++;
		return v;
	}
	
	//! Sort a vector in ascendent way
	public static void sortAscendent(int[] v)
	{
		Arrays.sort(v);
	}
	
	//! Difference between two vector w.r.t Norm 1
	public static float norm1(int[] v1, int[] v2)
	{
		if(v1.length != v2.length)
			return 0;
		int sum = 0;
		for (int i = 0; i < v1.length; i++)
			sum += Math.abs(v1[i] - v2[i]);
		return sum;
	}
	
	//! Difference between two vector w.r.t Norm 2
	public static float norm2(int[] v1, int[] v2)
	{
		if(v1.length != v2.length)
			return 0;
		int sum = 0;
		for (int i = 0; i < v1.length; i++)
			sum += (v1[i] - v2[i]) * (v1[i] - v2[i]);
		return (float)Math.sqrt(sum);
	}
	
	//! Difference between two vector w.r.t Norm Inf
	public static float norm8(int[] v1, int[] v2)
	{
		if(v1.length != v2.length)
			return 0;
		int max = Math.abs(v1[0] - v2[0]);
		int diff;
		for (int i = 1; i < v1.length; i++)
		{
			diff = Math.abs(v1[i] - v2[i]);
			if (diff > max) max = diff;
		}
		return max;
	}
	
	//! How many index have different values
	public static int mismatches(int[] v1, int[] v2)
	{
		if (v1.length != v2.length)
			return 0;
		int c = 0;
		for(int i = 0; i < v1.length; i++)
			if(v1[i] != v2[i])
				c++;
		return c;
	}
	
	//! How many index have different values w.r.t. a distance (epsilon)
	public static int mismatches(int[] v1, int[] v2, int distance)
	{
		if(v1.length != v2.length)
			return 0;
		int count = 0;
		for (int i = 0; i < v1.length; i++)
			if (Math.abs(v1[i] - v2[i]) > distance) count++;		
		return count;
	}
	
	//! How many index of the first "end" elements have different values w.r.t. a distance (epsilon) 
	public static int mismatches(int[] v1, int[] v2, int end, int distance)
	{
		if(v1.length != v2.length || end > v2.length)
			return 0;
		if(end >= v2.length)
			end = v2.length - 1;
		int count = 0;
		for (int i = 0; i < end; ++i)
			if (Math.abs(v1[i] - v2[i]) > distance) count++;
		return count;
	}
	
	//!  Sum the first K elements of the vector v
	public static int sum(int[] v, int first_k_elements)
	{
		if(v.length < first_k_elements)
			return 0;
		int sum = 0;
		for (int i = 0; i < first_k_elements; i++) 
			sum += v [i];		
		return sum;
	}
	
	//! Returns whether vectors are equals
	public static boolean equals_vectors(int[] v1, int[] v2)
	{
		if (v1.length == v2.length)
			return false;
		for(int i = 0; i < v1.length; i++)
			if (v1[i] != v2[i])
				return false;
		return true;
	}
	
	//! Activates the bit-th bit on an integer
	public static void activateBit(PW_Integer integer, int bit)
	{
		if (bit >= 32 || bit < 0) 
			return;
		//int number = integer.intValue();
		integer.or(1 << bit);//integer = integer | 1 << bit; //(int)Math.Pow(2,bit);
	}
	
	//! Counts all activated bits
	public static int bitsCount(int integer)
	{
		return Integer.bitCount(integer);
	}
	
	//! Fill all the cells of arr with the integer value
	public static void fill(int[] arr, int value)
	{
		if(arr != null)
			for (int i = 0; i < arr.length; i++)
				arr [i] = value;
	}
	
	//! Copy some portion of the source array to the destination array
	/*!
		\param source The source array.
		\param src_start The starting index of the source
		\param src_end_out The ending index + 1
		\param destination The destination array
		\param dest_start The starting index of the destination
	*/
	public static void copy(int[] source, int src_start, int src_end_out, int[] destination, int dest_start)
	{
		if (source == null || destination == null || src_start < 0 || 
			src_start > src_end_out || src_end_out > source.length || 
		    dest_start < 0 || dest_start > destination.length)
			return;
		int sub_array_length = src_end_out - src_start;
		if(destination.length - dest_start < sub_array_length)
			return;
		for (int i = src_start; i < src_end_out; i++)
			destination [dest_start++] = source [i];
	}
		
	public static int max(int[] v)
	{
		int max = Arrays.stream(v).max().getAsInt();
		return max;		
	}
	public static int min(int[] v)
	{
		int min = Arrays.stream(v).min().getAsInt();
		return min;
	}
	public static int sum(int[] v)
	{
		return Arrays.stream(v).sum();
	}
	
	// NO TEST
	public static int sqr(int b)
	{
		return b * b;
	}
	public static int sign(int x)
	{
		if (x == 0) return 0;
		return (x > 0) ? 1 : -1;
	}
	public static int zero_bounded_decrease(int x)
	{
		return (x > 0) ? x - 1 : 0;
	}
	public static int identity(int x, int _base)
	{
		return (x > _base) ? x : 0;
	}
	public static int identity(int x)
	{
		return identity(x, 1);
	}
	//--
}
