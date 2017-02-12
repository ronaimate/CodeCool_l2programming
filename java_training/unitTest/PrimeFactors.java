package unitTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeFactors
{
	public static List generate(int n)
	{
		List<Integer> primeFactors = new ArrayList<>(Arrays.asList());
		if (n != 0)
		{
			for (int candidate = 2; candidate <= n; candidate++)
			{
				for (; n % candidate == 0; n /= candidate)
				{
					primeFactors.add(candidate);
				}

			}
		}
		return primeFactors;
	}
}
