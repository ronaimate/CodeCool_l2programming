package classesAndExtends;

public class Human
{
	int age;
	String name;
	double height;

	public Human(int age, String name, double height)
	{
		this.age = age;
		this.name = name;
		this.height = height;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	@Override
	public String toString()
	{

		return "Name: " + name + "Age: " + age + "Height: " + height;
	}
}