import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = -2723363051271966964L;
	String messageBody;
	String sender;
	String receiver;

	public Message(String sender, String receiver, String messageBody) {
		this.messageBody = messageBody;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
