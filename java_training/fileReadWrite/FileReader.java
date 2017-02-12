package fileReadWrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader
{

	public static void main(String[] args) throws IOException
	{
		File f = new File("C:\\Users\\Ronai Mate\\Desktop\\mystyle.xml");
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new java.io.FileReader(f));
		String line;
		while ((line = br.readLine()) != null)
		{
			sb.append(line);
			sb.append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}

}
