package swing.menu;

/**
 * ����� ������������ ������ �������� ���� �� ����� XML
 */

import javax.swing.*;
import java.awt.event.*;

public class XMLMenuCreaterTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public XMLMenuCreaterTest()
	{
		super("XML Menu Loader");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			// ��������� ���� XML � ��������� ����
			XMLReader xmlReader = new XMLReader ("menu.xml", "UTF8");
			// ��������� ����
			XMLMenuCreater menuCreater = new XMLMenuCreater(xmlReader.getInputSource());
			menuCreater.parse();
			// ����������� ������ ����
			setJMenuBar(menuCreater.getMenuBar("mainMenu"));
			// ����������� ���������
			menuCreater.addActionListener("exit", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}});
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ������� ���� �� �����
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new XMLMenuCreaterTest();
	}
}