package socket;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient
{
	private Socket s;

	public FileClient(String host, int port, File file)
	{
		try
		{
			s = new Socket(host, port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			while (fis.read(buffer) > 0)
			{
				dos.write(buffer);
				dos.flush();
			}
			fis.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			s.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new FileClient("localhost", 1988, new File("C:\\Users\\Ronai Mate\\Desktop\\Week1\\zene.mp3"));
	}

}