package unitTest;

import static unitTest.PrimeFactors.generate;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.TestCase;

public class PrimeFactorTest extends TestCase
{
	@Test
	public void testGenerate_One_ReturnListContainsOne() throws Exception
	{
		assertEquals(Arrays.asList(), generate(1));
	}

	@Test
	public void testGenerate_Two_ReturnListContainsTwo() throws Exception
	{
		assertEquals(Arrays.asList(2), generate(2));
	}

	@Test
	public void testGenerate_Three_ReturnListContainsThree() throws Exception
	{
		assertEquals(Arrays.asList(3), PrimeFactors.generate(3));
	}

	@Test
	public void testGenerate_Four_ReturnListContainsTwoTwo() throws Exception
	{
		assertEquals(Arrays.asList(2, 2), PrimeFactors.generate(4));
	}

	@Test
	public void testGenerate_Eight_ReturnListContainsTwoTwoTwo() throws Exception
	{
		assertEquals(Arrays.asList(2, 2, 2), PrimeFactors.generate(8));
	}

	@Test
	public void testGenerate_Zero_ReturnListContainsEmptyList() throws Exception
	{
		assertEquals(Arrays.asList(), PrimeFactors.generate(0));
	}

	@Test
	public void testGenerate_Nine_ReturnListContains3and3() throws Exception
	{
		assertEquals(Arrays.asList(3, 3), PrimeFactors.generate(9));
	}

}
