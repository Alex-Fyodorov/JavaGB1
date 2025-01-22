package differents.swing.menu;

// �������� ���� � Swing ������� � ������������� ������������ � ����������� 

import javax.swing.*;

import java.awt.event.*;

public class JMenuBarTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private  final  String[][]  menuFile = {{"����"     ,  "�",  "", ""}, 
			                                {"�������"  ,  "�", "O", ""},
			                                {"���������",  "�", "S", ""}};
	private  final  String[][]  menuEdit = {{"��������������" , "�",  "", ""}, 
                                            {"��������"  , "�", "X", "images/cut.png"},
                                            {"����������", "�", "C", "images/copy.png"}};
	//--------------------------------------------------------
	/**
	 * ������� �������� ����������� ����
	 * @param items �������� ����
	 * @return JMenu ���������� ���� 
	 */
	private JMenu createMenuItems(final String[][] items)
	{
		// �������� ����������� ����
		JMenu menu = new JMenu(items[0][0]);
		menu.setMnemonic(items[0][1].charAt(0));
		for (int i = 1; i < items.length; i++) {
			// ����� ���� "�������"
			JMenuItem item = new JMenuItem(items[i][0]);
			item.setMnemonic(items[i][1].charAt(0)); // ������� �����
			// ��������� ������� �������� ������� (��������� �����)
			item.setAccelerator(KeyStroke.getKeyStroke(items[i][2].charAt(0), KeyEvent.CTRL_MASK));
			if (items[i][3].length() > 0)
				item.setIcon(new ImageIcon(items[i][3]));
			menu.add(item);
		}
		return menu;
	}
	//--------------------------------------------------------
	/**
	 * ������� �������� ����������� ���� � ���������� �����������
	 * @return
	 */
	private JMenu createSubmenus()
	{
		JMenu text = new JMenu("�����");
		// � ��������� ��������� ����
		JMenu style = new JMenu("�����");
		JMenuItem bold = new JMenuItem("������");
		JMenuItem italic = new JMenuItem("������");
		JMenu font = new JMenu("�����");
		JMenuItem arial = new JMenuItem("Arial");
		JMenuItem times = new JMenuItem("Times");
		font.add(arial); font.add(times);
		// ��������� ��� � ������ �������
		style.add(bold);
		style.add(italic);
		style.addSeparator();
		style.add(font);
		text.add(style);
		return text;
	}
	//--------------------------------------------------------
	public JMenuBarTest() {
		super("��������� ����");
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		// ������� ������ �������� ����
		JMenuBar menuBar = new JMenuBar();
		// �������� ���� "����"
		menuBar.add(createMenuItems(menuFile));
		// �������� ���� "��������������"
		menuBar.add(createMenuItems(menuEdit));
		
		menuBar.add(createSubmenus());
		
		// JMenuBar ���������� ������� ������������, ��� ��� ����������� ������ �������
		menuBar.add(Box.createHorizontalGlue());
		// ��������� � ������ ���� �� ���������� ����, � ������� �� �������
		JLabel exit = new JLabel(new ImageIcon("images/exit.png"));
		exit.setText("�����");
		exit.setBorder(BorderFactory.createEtchedBorder()); // .createLoweredBevelBorder());
		menuBar.add(exit);
		
		// �������� ���� � ���� ����
		setJMenuBar(menuBar);
		// ������� ���� �� �����
		setSize(300, 200);
		setVisible(true);
	}
	//--------------------------------------------------------
	public static void main(String[] args)
	{
		// ����������� ��������� ��� ����
		JFrame.setDefaultLookAndFeelDecorated(true);
		new JMenuBarTest();
	}
}