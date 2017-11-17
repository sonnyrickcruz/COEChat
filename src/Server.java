import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
	private static Map<String, ServerThread> users = new HashMap<>();
	
	public static final int PORT = 1234;

	public static void main(String[] args) {
		new Server().runServer();
	}

	public void runServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server's up & ready for connections.");
			while(true) {
				Socket socket = serverSocket.accept();
				new ServerThread(socket, this).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addUserThread(String username, ServerThread thread) {
		users.put(username, thread);
	}
	
	public ServerThread getUserThread(String username) {
		if (users.get(username) != null) {
			return users.get(username);
		}
		return null;
	}
}
