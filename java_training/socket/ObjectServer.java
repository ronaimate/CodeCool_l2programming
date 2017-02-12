package socket;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("Server Started.");
		ServerSocket serverSocket = new ServerSocket(8000);
		System.out.println("Waiting for clients...");
		Socket socket = serverSocket.accept();
		System.out.println("Client connected.");
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		while (true)
		{
			oos.write(1);
			// az 1 azért kell ha több dolgot is akarunk küldeni és forog a
			// ciklus.
			oos.writeObject("Hello");
			System.out.println("Client connected: " + socket);
			break;
		}
		oos.close();
		socket.close();
		serverSocket.close();
	}
}
