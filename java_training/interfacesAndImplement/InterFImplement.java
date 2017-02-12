package interfacesAndImplement;

public class InterFImplement implements MateInterFace
{

	public static void main(String[] args)
	{
		InterFImplement ifi = new InterFImplement();
		System.out.println(ifi.mateVagyok("mate"));
	}

	@Override
	public String mateVagyok(String string)
	{
		return string;
	}
}
