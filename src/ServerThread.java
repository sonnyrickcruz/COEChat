import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	Server server;
	UserStreams user;

	ServerThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		user = new UserStreams();
	}

	public void run() {
		Message message;
		try {
			user.setObjectInputStream(new ObjectInputStream(socket.getInputStream()));
			user.setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));
			String username = (String) user.getObjectInputStream().readObject();
			Server.users.put(username, this);
			System.out.println(username + " is now connected.");
			while (true) {
				message = (Message) user.getObjectInputStream().readObject();
				doSomething(message);
				user.getObjectOutputStream().writeObject(message);
				System.out.println(Server.users);
				if (!message.getReceiver().equalsIgnoreCase(username) && Server.users.get(message.getReceiver()) != null) {
					System.out.println("Sending message to: " + message.getReceiver());
					Server.users.get(message.getReceiver()).user.getObjectOutputStream().writeObject(message);
				}
			}
			//socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void doSomething(Message message) {
		//message.setMessageBody(messageBody);
	}
}
