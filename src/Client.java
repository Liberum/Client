import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Client {

	public void SSLClient(final java.util.List<String> lstUnMount)
			throws UnknownHostException, IOException {

		// System.setProperty("javax.net.ssl.trustStore",
		// "./src/keys/clientkeys");

		System.setProperty("javax.net.ssl.trustStore", "C:/clientkeys");
		SocketFactory factory = SSLSocketFactory.getDefault();
		final Socket socket = factory.createSocket(Main.SERVER, Main.PORTSSL);
		new Thread() {
			public void run() {
				try {
					OutputStream s = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(s);

					oos.writeObject(lstUnMount);

					for (int i = 0; i < lstUnMount.size(); i++) {
						lstUnMount.set(i, "0");
					}

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
