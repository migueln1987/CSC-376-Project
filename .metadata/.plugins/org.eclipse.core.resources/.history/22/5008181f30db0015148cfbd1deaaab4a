import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TriviaServer {
	private ArrayList<Socket> clients;
	private int port;
	public TriviaServer(int port)
	{
		this.port = port;
		clients = new ArrayList<Socket>();
	}
	
	public void clientConnection()
	{
		try
		{
			@SuppressWarnings("resource")
			ServerSocket server_socket = new ServerSocket(port);
			Socket client;
			
			while (true)
			{
				client = server_socket.accept();
				if (clients.size() == 2)
				{
					new Thread(new TriviaGame(clients)).start();
					clients = new ArrayList<Socket>();
				}
				else
				{
				clients.add(client);
				}
			}
			//server_socket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		TriviaServer ts = new TriviaServer(Integer.valueOf(args[0]));
	}

}
