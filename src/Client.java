import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			if (args.length == 1) {
				String name = args[0];
				Socket socket = new Socket("localhost", Server.PORT);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				objectOutputStream.writeObject(name);
				System.out.println(name);
					new ReadThread(name, objectOutputStream).start();
					new WaitThread(objectInputStream).start();
			} else {
				System.out.println("Usage: Client <name>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
