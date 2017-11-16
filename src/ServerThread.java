import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	Server server;
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;

	ServerThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		try {
			objectInputStream = new ObjectInputStream(this.socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		Message message;
		try {
			String username = (String) objectInputStream.readObject();
			System.out.println(username + " is now connected.");
			server.addUsers(username, this);
			while ((message = (Message) objectInputStream.readObject()) != null) {
				objectOutputStream.writeObject(message);
				if (!username.equalsIgnoreCase(message.getReceiver())) {
					message = (Message) this.server.getUser(message.getReceiver()).objectInputStream.readObject();
					this.server.getUser(message.getReceiver()).objectOutputStream.writeObject(message);
				}
			}
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
