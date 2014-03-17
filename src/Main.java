import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
	// TODO Auto-generated method stub

	Gui.RunUi();

	int[] a = new int[] { 2, 2, 2, 2, 2 };
	int[] b = new int[] { 2, 2, 2, 2, 2 };
	Gui.setCb(a);

	while (true) {
	    Gui.setCb(b);

	    File[] arrayRoots = File.listRoots();
	    for (File root : arrayRoots) {
		if (root.getPath().equalsIgnoreCase("O:\\")) {
		    System.out.println("O = 1");
		    b[0] = 1;
		}

	    }
	    TimeUnit.SECONDS.sleep(1);
	    Gui.setCb(a);

	}
    }

}
