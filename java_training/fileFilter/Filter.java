package fileFilter;

import java.io.File;
import java.io.FileFilter;

public class Filter
{

	public File[] getExecutables(File directory) throws Exception
	{
		if (!directory.isDirectory())
		{
			throw new Exception();

		}

		return directory.listFiles(new FileFilter()
		{

			@Override
			public boolean accept(File pathname)
			{
				return pathname.isFile() && pathname.getName().toLowerCase().endsWith(".exe");
			}
		});

	}

	public static void main(String[] args)
	{
		Filter m = new Filter();

		try
		{
			File[] executable = m.getExecutables(new File("C:\\Windows"));
			for (File file : executable)
			{
				System.out.println(file);

			}
		} catch (Exception e)
		{

			e.printStackTrace();
		}

	}

}