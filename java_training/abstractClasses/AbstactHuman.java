package abstractClasses;

public abstract class AbstactHuman
{
	String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public AbstactHuman(String name)
	{
		this.name = name;
	}

	public abstract boolean beerDrinking();

}
