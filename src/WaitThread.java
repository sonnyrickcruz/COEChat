import java.io.IOException;
import java.io.ObjectInputStream;

public class WaitThread extends Thread {
		ObjectInputStream objectInputStream;
		
		public WaitThread(ObjectInputStream objectInputStream) {
			this.objectInputStream = objectInputStream;
		}
		public void run() {
			Message returnMessage;
			try {
				while ((returnMessage = (Message) objectInputStream.readObject()) != null) {
					System.out.println(returnMessage.getMessageBody());
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}