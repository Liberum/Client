import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Main {

	static final int INDICATION_PORT = 7894;
	public static final int PORTSSL = 44443;
	static final String SERVER = "192.168.1.100";

	private static Logger log = Logger.getLogger(Main.class.getName());

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws InterruptedException,
			UnknownHostException, IOException {

		// в jar файле не находит файл конфигурации логов ((
		// try {
		// LogManager.getLogManager().readConfiguration(
		// Main.class.getResourceAsStream("C:/logging.properties"));
		// } catch (IOException e) {
		// System.err.println("Could not setup logger configuration: "
		// + e.toString());
		// } catch (NullPointerException e2) {
		// System.err.println("NullPointerException: " + e2.toString());
		// }

		new Thread() {
			public void run() {
				log.info("Start Gui");
				Gui.RunUi();
			}
		}.start();

		log.info("Start StartIndication");
		StartIndication();

	}

	public static void StartIndication() throws InterruptedException {
		// старт индикации, если надо будет, то с условием
		if (Indication.threadCount() < 40000) {
			new Indication();
		}
	}

}
