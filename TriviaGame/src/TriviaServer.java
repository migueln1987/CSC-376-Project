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
			ServerSocket server_socket = new ServerSocket(port);
			Socket client;
			boolean done = false;
			while (true)
			{
				if (done) break;
				client = server_socket.accept();
				clients.add(client);
				if (clients.size() == 2)
				{
					System.out.println("two clients in game");
					new Thread(new TriviaGame(clients)).start();
					clients = new ArrayList<Socket>();
				}

				System.out.println(clients.size());
			}
			server_socket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		TriviaServer ts = new TriviaServer(Integer.valueOf(args[0]));
		ts.clientConnection();
	}

}
