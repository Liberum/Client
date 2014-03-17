import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Client {

	public static final int PORT = 4443;

	public void SSLClient(final java.util.List<String> lstUnMount)
			throws UnknownHostException, IOException {

		// System.setProperty("javax.net.ssl.trustStore",
		// "./src/keys/clientkeys");

		System.setProperty("javax.net.ssl.trustStore", "C:/Users/al/clientkeys");
		SocketFactory factory = SSLSocketFactory.getDefault();
		final Socket socket = factory.createSocket("127.0.0.1", PORT);
		new Thread() {
			public void run() {
				try {
					OutputStream s = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(s);

					oos.writeObject(lstUnMount);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			public void run() {
				try {
					InputStream s = socket.getInputStream();
					int b;
					while ((b = s.read()) != -1) {
						System.out.println("Client read {" + b + "}");
						if (b == -1) {
							s.close();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

}