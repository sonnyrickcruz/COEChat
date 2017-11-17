import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserStreams {
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public ObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}

	public void setObjectInputStream(ObjectInputStream objectInputStream) {
		this.objectInputStream = objectInputStream;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}

	@Override
	public String toString() {
		return "UserStreams [objectInputStream=" + objectInputStream + ", objectOutputStream=" + objectOutputStream
				+ "]";
	}
}
