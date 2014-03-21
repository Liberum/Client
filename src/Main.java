import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	static final int MAX_THREADS = 40;
	static final int INDICATION_PORT = 7894;
	static final String SERVER = "127.0.0.1";

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws InterruptedException,
			UnknownHostException, IOException {

		Gui.RunUi();

		// старт индикации, максимум 40 клиентов
		if (Indication.threadCount() < MAX_THREADS) {
			new Indication(SERVER);
		}

	}

}
