package equals;

import java.util.HashSet;

public class HumanHandler
{
	public static void main(String[] args)
	{
		Human h1 = new Human(50, "Mate", 1.8);
		Human h2 = new Human(50, "Mate", 1.9);
		Human h3 = new Human(50, "Ronai", 1.5);
		Human h4 = new Human(50, "Idegen", 1.9);
		Human h5 = new Human(50, "Idegen", 1.9);
		HashSet<Human> hset = new HashSet<Human>();
		hset.add(h1);
		hset.add(h2);
		hset.add(h3);
		hset.add(h4);
		hset.add(h5);
		System.out.println(hset.size());

	}
}
