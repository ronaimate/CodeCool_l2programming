package comperator;

import java.util.Comparator;

public class HumanNameLengthComparator implements Comparator<Human>
{
	@Override
	public int compare(Human o1, Human o2)
	{
		if (o1.getName().length() < o2.getName().length())
		{
			return -1;
		}
		if (o1.getName().length() > o2.getName().length())
		{
			return 1;
		}
		return 0;
	}

}