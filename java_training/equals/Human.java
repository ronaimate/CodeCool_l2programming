package equals;

public class Human
{
	int age;
	String name;
	double height;

	public String getName()
	{
		return name;
	}

	public Human(int age, String name, double height)
	{
		this.age = age;
		this.name = name;
		this.height = height;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Human)
		{
			Human human = (Human) o;
			return name.equals(human.getName());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

}