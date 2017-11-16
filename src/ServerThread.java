import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	Server server;

	ServerThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
	}

	public void run() {
		Message message;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			String username = (String) objectInputStream.readObject();
			System.out.println(username + " is now connected.");
			server.addUserThread(username, this);
			while ((message = (Message) objectInputStream.readObject()) != null) {
				doSomething(message);
				objectOutputStream.writeObject(message);
			}
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void doSomething(Message message) {
		//message.setMessageBody(messageBody);
	}
}
