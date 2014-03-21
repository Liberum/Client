import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Indication extends Thread {
	private Socket socket;
	private static int counter = 0;
	private int id = counter++;
	private static int threadcount = 0;

	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	Gui gui = new Gui();
	int[] a = new int[5];

	public static int threadCount() {
		return threadcount;
	}

	public Indication(String server) {
		System.out.println("Making client " + id);
		threadcount++;
		try {
			socket = new Socket(server, Main.INDICATION_PORT);
		} catch (IOException e) {
			System.err.println("Socket failed");
			// Если создание сокета провалилось,
			// ничего ненужно чистить.
		}
		try {

			// os = socket.getOutputStream();
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			os = socket.getOutputStream();
			start();

		} catch (IOException e) {
			// Сокет должен быть закрыт при любой
			// ошибке, кроме ошибки конструктора сокета:
			try {
				socket.close();
			} catch (IOException e2) {
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

				System.out.println(lst);

				for (int i = 0; i < lst.size(); i++) {
					a[i] = Integer.valueOf(lst.get(i));
				}

				gui.setCb(a);
				t++;
				TimeUnit.SECONDS.sleep(2);
				// через месяц прекращаем индикацию (:
				if (t > 13000000) {
					break;
				}

			}

		} catch (IOException e) {
			System.err.println("IO Exception");
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
				System.err.println("Socket not closed");
			}
			threadcount--; // Завершаем эту нить
		}
	}
}