package fileReadWrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriter
{
	public static void main(String[] args) throws IOException
	{
		File f = new File("C:\\Users\\Ronai Mate\\Desktop\\newFile.txt");
		List<String> list = new ArrayList<>();
		list.add("elso sor");
		list.add("masodik sor");
		list.add("harmadik sor");
		list.add("negyedik sor");

		BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(f, false));
		// true = append, false = felül irja.
		for (String string : list)
		{
			bw.write(string + "\n");
		}
		bw.close();
	}
}
