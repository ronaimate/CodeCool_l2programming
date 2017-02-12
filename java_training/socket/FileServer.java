package socket;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer
{

	private ServerSocket ss;

	public FileServer(int port)
	{
		try
		{
			ss = new ServerSocket(port);
			Socket clientSock = ss.accept();
			while (true)
			{
				DataInputStream dis = new DataInputStream(clientSock.getInputStream());
				File f = new File("C:\\Users\\Ronai Mate\\Desktop\\atjottfile.mp3");
				FileOutputStream fos = new FileOutputStream(f);
				int fileSize = 474449;
				byte[] buffer = new byte[4096];
				int read = 0;
				int totalRead = 0;
				int remaining = fileSize;
				while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0)
				{
					totalRead += read;
					remaining -= read;
					fos.write(buffer, 0, read);
				}
				fos.close();
				break;
			}
			clientSock.close();
			ss.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		FileServer fs = new FileServer(1988);
	}

}