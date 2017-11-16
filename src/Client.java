import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			if (args.length == 1) {
				String name = args[0];
				System.out.println(name);
				Socket socket = new Socket("localhost", Server.PORT);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				objectOutputStream.writeObject(name);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				while (true) {
					String readerInput = bufferedReader.readLine();
					Message message = new Message(name, "user1", readerInput);
					objectOutputStream.writeObject(message);
					Message returnMessage = (Message) objectInputStream.readObject();
					System.out.println(returnMessage.getMessageBody());
				}
			} else {
				System.out.println("Usage: Client <name>");
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isInteger(String val) {
		boolean result = true;
		try {
			Integer.parseInt(val);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}
