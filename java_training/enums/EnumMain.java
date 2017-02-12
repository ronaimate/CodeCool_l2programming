package enums;

public class EnumMain
{
	public static void main(String[] args)
	{
		Commands com = Commands.EXIT;
		if (com instanceof Commands && com.equals(Commands.START))
		{
			System.out.println("A program elindul.");
		}
		if (com instanceof Commands && com.equals(Commands.EXIT))
		{
			System.out.println("A program leall.");
		} else
		{
			System.out.println("Kulonben.");
		}
	}
}
