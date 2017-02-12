package socket;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ObjectClient
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("Client Started.");
		Socket socket = new Socket("localhost", 8000);
		System.out.println("Client Connecing to Server.");
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		while (true)
		{
			if (ois.read() > -1)
			// kell az 1 hogy ne forogjon a ciklus hanem belépjen az ifbe.
			{
				System.out.println("Server say: " + ois.readObject());
				break;
			}
		}
		ois.close();
		socket.close();
	}

}
