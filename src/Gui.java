import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class Gui {

	static JCheckBox cb1 = new JCheckBox("Диск \"О\"");
	static JCheckBox cb2 = new JCheckBox("Диск \"P\"");
	static JCheckBox cb3 = new JCheckBox("Диск \"R\"");
	static JCheckBox cb4 = new JCheckBox("Диск \"S\"");
	static JCheckBox cb5 = new JCheckBox("Диск \"T\"");
	static MountAdapter ma = new MountAdapter();

	static JLabel labelStatus = new JLabel(
			"  Состояние: Сервер отключен или недоступен");

	public static void RunUi() {

		final String dPach1 = "\\\\serv\\dsc\\al";
		final String dPach2 = "\\\\serv\\dsc\\al";
		final String dPach3 = "\\\\serv\\dsc\\al";
		final String dPach4 = "\\\\serv\\dsc\\al";
		final String dPach5 = "\\\\serv\\dsc\\al";

		final String dsk1 = "o";
		final String dsk2 = "p";
		final String dsk3 = "r";
		final String dsk4 = "s";
		final String dsk5 = "t";
		final String dska = "a";

		JFrame jf = new JFrame("AutoCrypt");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);// изменение размеров
		jf.setLocation(300, 300);// имзменяем местоположение фрейма.
		jf.setVisible(true);

		// создаем панель.
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("CbL"));
		jf.add(p);

		// создаем элементы

		cb1.setEnabled(false); // отключаем изменение

		final JButton jb1 = new JButton("Смонтировать");
		JButton jbx1 = new JButton("X");
		final JPasswordField jp1 = new JPasswordField(15);

		// ------------------------------------------------------------
		// jp1.addKeyListener(new java.awt.event.KeyAdapter() {
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == 10) {
		// System.out.println("222");
		// jb1.doClick();
		// }
		// }
		//
		// });
		// ------------------------------------------------------------

		cb2.setEnabled(false);
		JButton jb2 = new JButton("Смонтировать");
		JButton jbx2 = new JButton("X");
		final JPasswordField jp2 = new JPasswordField(15);

		cb3.setEnabled(false);
		JButton jb3 = new JButton("Смонтировать");
		JButton jbx3 = new JButton("X");
		final JPasswordField jp3 = new JPasswordField(15);

		cb4.setEnabled(false);
		JButton jb4 = new JButton("Смонтировать");
		JButton jbx4 = new JButton("X");
		final JPasswordField jp4 = new JPasswordField(15);

		cb5.setEnabled(false);
		JButton jb5 = new JButton("Смонтировать");
		JButton jbx5 = new JButton("X");
		final JPasswordField jp5 = new JPasswordField(15);

		// Панелька размонтировать все и т.д.
		JButton jbxUnMounte = new JButton("Размонтировать все");

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

		Box box6 = Box.createHorizontalBox();
		box6.add(jbxUnMounte);
		box6.add(Box.createHorizontalGlue());
		box6.add(labelStatus);

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
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box6);

		// добаление к общей панели
		p.add(mainBox);

		// задаем размеры окна
		jf.setSize(450, 280);

		// -------------- обработчики кнопок --------------
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
		ma.UnMount(jbxUnMounte, dska);
		// --------------------------------------------------
	}

	public void setCb(int[] a, String text) {
		if (a[0] == 1) {
			cb1.setBackground(Color.green); // меняем цвет фона
			cb1.setSelected(true); // ставим галочку
		} else if (a[0] == 2) {
			cb1.setBackground(Color.red);
			cb1.setSelected(false);
		}

		if (a[1] == 1) {
			cb2.setBackground(Color.green);
			cb2.setSelected(true);
		} else if (a[1] == 2) {
			cb2.setBackground(Color.red);
			cb2.setSelected(false);
		}

		if (a[2] == 1) {
			cb3.setBackground(Color.green);
			cb3.setSelected(true);
		} else if (a[2] == 2) {
			cb3.setBackground(Color.red);
			cb3.setSelected(false);
		}

		if (a[3] == 1) {
			cb4.setBackground(Color.green);
			cb4.setSelected(true);
		} else if (a[3] == 2) {
			cb4.setBackground(Color.red);
			cb4.setSelected(false);
		}

		if (a[4] == 1) {
			cb5.setBackground(Color.green);
			cb5.setSelected(true);
		} else if (a[4] == 2) {
			cb5.setBackground(Color.red);
			cb5.setSelected(false);
		}

		labelStatus.setText("Состояние: " + text);
	}

}
