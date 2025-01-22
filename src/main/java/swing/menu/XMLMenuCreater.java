package differents.swing.menu;

/**
 * ����� �������� ���� �� ����� XML
 */

import org.xml.sax.*;
import javax.swing.*;

import javax.xml.parsers.*;

import java.awt.event.*;

public class XMLMenuCreater
{
	private  InputSource    source;      // �������� ������ XML
	private  SAXParser      parser;      // ���������� XML
	private  XMLMenuParser  xmlHandler;  // ���������� XML

	/**
	 * ����������� ������ �������� ����
	 * @param source �������� ������ XML
	 */
	public XMLMenuCreater(InputSource source)
	{
		this.source = source;
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		// �������� ����������� XML
		xmlHandler = new XMLMenuParser();
	}

	// �������� ������� ����
	public void parse() throws Exception {
		parser.parse(source, xmlHandler);
	}
	
	/**
	 * ������� ��������� ����������� ����
	 * @param name ������ ����
	 * @return
	 */
	public JMenuBar getMenuBar(String name) {
		return (JMenuBar) xmlHandler.getMenuStorage().get(name);
	}
	
	// ��������� �������� ���������� ����
	public JMenu getMenu(String name) {
		return (JMenu) xmlHandler.getMenuStorage().get(name);
	}
	
	// ��������� �������� ������� ����
	public JMenuItem getMenuItem(String name) {
		return (JMenuItem) xmlHandler.getMenuStorage().get(name);
	}

	// ������� ����� ��� �������� ���������� ��������� �������
	public void addActionListener(String name, ActionListener listener) {
		getMenuItem(name).addActionListener(listener);
	}
}
