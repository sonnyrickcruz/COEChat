import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class ReadThread extends Thread {
		String name;
		ObjectOutputStream objectOutputStream;

		public ReadThread(String name, ObjectOutputStream objectOutputStream) {
			this.name = name;
			this.objectOutputStream = objectOutputStream;
		}

		public void run() {
			String readerInput;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {

				while (true) {
					readerInput = bufferedReader.readLine();
					Message message = new Message(name, "user1", readerInput);
					objectOutputStream.writeObject(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}