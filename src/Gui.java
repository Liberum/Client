import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class Gui {

	public static void RunUi() {

		final String dPach1 = "d:\\myCrypt";
		final String dPach2 = "d:\\myCrypt";
		final String dPach3 = "d:\\myCrypt";
		final String dPach4 = "d:\\myCrypt";
		final String dPach5 = "d:\\myCrypt";

		final String dsk1 = "o";
		final String dsk2 = "p";
		final String dsk3 = "r";
		final String dsk4 = "s";
		final String dsk5 = "t";

		JFrame jf = new JFrame("AutoCrypt");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// jf.setSize(400, 300);
		jf.setResizable(false);// изменение размеров
		jf.setLocation(300, 300);// имзменяем местоположение фрейма.
		jf.setVisible(true);

		// создаем панель.
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("CbL"));
		jf.add(p);

		// создаем элементы
		JCheckBox cb1 = new JCheckBox("Диск \"О\"");
		cb1.setEnabled(false); // отключаем изменение
		cb1.setBackground(Color.green); // меняем цвет фона
		// меняем цвет текста только если разрешены изменения
		// cb1.setForeground(Color.black);
		cb1.setSelected(true);

		JButton jb1 = new JButton("Смонтировать");
		JButton jbx1 = new JButton("X");
		final JPasswordField jp1 = new JPasswordField(15);

		JCheckBox cb2 = new JCheckBox("Диск \"P\"");
		cb2.setEnabled(false); // отключаем изменение
		cb2.setBackground(Color.red); // меняем цвет фона
		cb2.setSelected(false);

		JButton jb2 = new JButton("Смонтировать");
		JButton jbx2 = new JButton("X");
		final JPasswordField jp2 = new JPasswordField(15);

		JCheckBox cb3 = new JCheckBox("Диск \"R\"");
		JButton jb3 = new JButton("Смонтировать");
		JButton jbx3 = new JButton("X");
		final JPasswordField jp3 = new JPasswordField(15);

		JCheckBox cb4 = new JCheckBox("Диск \"S\"");
		JButton jb4 = new JButton("Смонтировать");
		JButton jbx4 = new JButton("X");
		final JPasswordField jp4 = new JPasswordField(15);

		JCheckBox cb5 = new JCheckBox("Диск \"T\"");
		JButton jb5 = new JButton("Смонтировать");
		JButton jbx5 = new JButton("X");
		final JPasswordField jp5 = new JPasswordField(15);

		// добавляем элементы к панели

		// создание горизонтальных ячеек
		Box box1 = Box.createHorizontalBox();
		box1.add(cb1);
		box1.add(jb1);
		box1.add(jp1);
		box1.add(jbx1);

		Box box2 = Box.createHorizontalBox();
		box2.add(cb2);
		box2.add(jb2);
		box2.add(jp2);
		box2.add(jbx2);

		Box box3 = Box.createHorizontalBox();
		box3.add(cb3);
		box3.add(jb3);
		box3.add(jp3);
		box3.add(jbx3);

		Box box4 = Box.createHorizontalBox();
		box4.add(cb4);
		box4.add(jb4);
		box4.add(jp4);
		box4.add(jbx4);

		Box box5 = Box.createHorizontalBox();
		box5.add(cb5);
		box5.add(jb5);
		box5.add(jp5);
		box5.add(jbx5);

		// добавление ячеек в одну вертикальную
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(2, 2, 12, 12));
		mainBox.add(box1);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box2);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box3);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box4);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box5);

		// добаление к общей панели
		p.add(mainBox);

		// задаем размеры окна
		jf.setSize(450, 250);

		// обработчики кнопок
		MountAdapter ma = new MountAdapter();

		// -- монтирование --
		ma.Mount(jb1, dPach1, dsk1, jp1);
		ma.Mount(jb2, dPach2, dsk2, jp2);
		ma.Mount(jb3, dPach3, dsk3, jp3);
		ma.Mount(jb4, dPach4, dsk4, jp4);
		ma.Mount(jb5, dPach5, dsk5, jp5);

		// -- размонтирование --
		ma.UnMount(jbx1, dsk1);
		ma.UnMount(jbx2, dsk2);
		ma.UnMount(jbx3, dsk3);
		ma.UnMount(jbx4, dsk4);
		ma.UnMount(jbx5, dsk5);

	}

}
