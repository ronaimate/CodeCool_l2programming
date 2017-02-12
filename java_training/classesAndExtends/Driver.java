package classesAndExtends;

public class Driver extends Human
{
	boolean haveCar;

	public Driver(int age, String name, double height)
	{
		super(age, name, height);
	}

	public boolean isHaveCar()
	{
		return haveCar;
	}

	public void setHaveCar(boolean haveCar)
	{
		this.haveCar = haveCar;
	}

}
