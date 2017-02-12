package comperator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HumanHandler
{

	public static void main(String[] args)
	{
		Human martin = new Human(45, "Martin", 1.80);
		Human mate = new Human(48, "Mate", 1.90);
		Human mariska = new Human(15, "Mariska", 1.70);

		List<Human> humanList = new ArrayList<>();
		humanList.add(martin);
		humanList.add(mate);
		humanList.add(mariska);
		Comparator<Human> comparator = new HumanNameLengthComparator();
		humanList.sort(comparator);

		for (Human human : humanList)
		{
			System.out.println(human);
		}

	}

}