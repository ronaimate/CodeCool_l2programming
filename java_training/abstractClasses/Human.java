package abstractClasses;

public class Human extends AbstactHuman
{

	public Human(String name)
	{
		super(name);
	}

	@Override
	public boolean beerDrinking()
	{
		if (this.name.equals("Pali"))
		{
			return true;
		}
		return false;
	}

	public static void main(String[] args)
	{
		Human pali = new Human("Pali");
		System.out.println(pali.beerDrinking());
	}

}
