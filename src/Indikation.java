import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

class Indication extends Thread {
	private Socket socket;
	private static int counter = 0;
	private int id = counter++;
	private static int threadcount = 0;
	private static Logger log = Logger.getLogger(Indication.class.getName());

	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	Gui gui = new Gui();
	int[] a = new int[5];

	public static int threadCount() {
		return threadcount;
	}

	public Indication() throws InterruptedException {
		System.out.println("Making client " + id);
		threadcount++;
		try {
			socket = new Socket(Main.SERVER, Main.INDICATION_PORT);
		} catch (IOException e) {
			log.info("Socket failed " + e + "/n Restart indikation");
			System.err.println("Socket failed");
			// пытаемся перезапустить раз в 3 сек
			TimeUnit.SECONDS.sleep(3);
			Main.StartIndication();
			// Если создание сокета провалилось,
			// ничего ненужно чистить.
		}
		try {
			if (socket != null) {
				is = socket.getInputStream();
				ois = new ObjectInputStream(is);
				os = socket.getOutputStream();
				start();
			}

		} catch (IOException e) {
			// Сокет должен быть закрыт при любой
			// ошибке, кроме ошибки конструктора сокета:
			try {
				socket.close();
			} catch (IOException e2) {
				log.info("Socket not closed " + e2);
				System.err.println("Socket not closed");
			}
		}
		// В противном случае сокет будет закрыт
		// в методе run() нити.
	}

	public void run() {
		try {
			int t = 0;
			while (true) {

				os.write(1);
				List<String> lst = (List<String>) ois.readObject();

				for (int i = 0; i < lst.size(); i++) {
					a[i] = Integer.valueOf(lst.get(i));
				}
				// log.info("Server connected");
				gui.setCb(a, "Сервер подключен");
				t++;
				TimeUnit.SECONDS.sleep(2);
				// через месяц прекращаем индикацию (:
				if (t > 13000000) {
					break;
				}

			}

		} catch (IOException e) {
			System.err.println("IO Exception");
			log.info("IO Exeption " + e
					+ "/n Server failed, restart indikation");
			gui.setCb(a, "Потеряна связь с сервером");

			// пытаемся перезапустить раз в 3 сек
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Main.StartIndication();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Всегда закрывает:
			try {
				socket.close();
			} catch (IOException e) {
				log.info("Socket not closed " + e);
				System.err.println("Socket not closed");
			}
			threadcount--; // Завершаем эту нить
		}
	}
}