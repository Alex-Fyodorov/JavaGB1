package swing.menu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLMenuParser extends DefaultHandler
{
	private JMenuBar currentMenuBar;           // ������� ������ ����
	                                           // ��������� ��� ���� ������ ������� ����
	private Map <String, Object> menuStorage = new HashMap<String, Object>();
                                               // ������ ��� ������������ ���������� ����
	private LinkedList<JMenu>    menus       = new LinkedList<JMenu>();

	private  final  String  ATTRIB_menubar     = "menubar"    ;
	private  final  String  ATTRIB_menu        = "menu"       ;
	private  final  String  ATTRIB_menuitem    = "menuitem"   ;
	private  final  String  ATTRIB_name        = "name"       ;
	private  final  String  ATTRIB_text        = "text"       ;
	private  final  String  ATTRIB_mnemonic    = "mnemonic"   ;
	private  final  String  ATTRIB_accelerator = "accelerator";
	private  final  String  ATTRIB_enabled     = "enabled"    ;
	private  final  String  ATTRIB_separator   = "separator"  ;
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Map<String, Object> getMenuStorage()
	{
		return menuStorage;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ����� ���� ���� XML
	public void startElement(String uri, String localName, String qName, 
			                 Attributes attributes)
	{
		// ���������� ��� ����
		if (qName.equals(ATTRIB_menubar))
			parseMenuBar(attributes);
		else if (qName.equals(ATTRIB_menu))
			parseMenu(attributes);
		else if (qName.equals(ATTRIB_menuitem))
			parseMenuItem(attributes);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ����� ����, ������������ ��� ����� ���������� ������� ����
	public void endElement(String uri, String localName, String qName) {
		if (qName.equals(ATTRIB_menu)) menus.removeFirst();
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// �������� ����� ������ ���� JMenuBar
	private void parseMenuBar(Attributes attrs) {
		JMenuBar menuBar = new JMenuBar();
		// ����������� �����
		String name = attrs.getValue(ATTRIB_name);
		menuStorage.put(name, menuBar);
		currentMenuBar = menuBar;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// �������� ����������� ����
	private void parseMenu(Attributes attrs) {
		// �������� ����
		JMenu menu = new JMenu();
		String name = attrs.getValue(ATTRIB_name);
		// ��������� ����� ���������
		adjustProperties(menu, attrs);
		menuStorage.put(name, menu);
		// ���������� ���� � ����������� ����������� ���� ��� � ������ ����
		if ( menus.size() != 0 ) {
			((JMenu)menus.getFirst()).add(menu);
		} else {
			currentMenuBar.add(menu);
		}
		// ��������� � ������ ���������� ����
		menus.addFirst(menu);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// �������� ������ ����
	private void parseMenuItem(Attributes attrs) {
		// ��������, �� ����������� �� ���
		String name = attrs.getValue(ATTRIB_name);
		if (name.equals(ATTRIB_separator)) {
			((JMenu)menus.getFirst()).addSeparator();
			return;
		}
		// �������� ������ ����
		JMenuItem menuItem = new JMenuItem();
		// ����������� ��������
		adjustProperties(menuItem, attrs);
		menuStorage.put(name, menuItem);
		// ���������� � �������� ����������� ����
		((JMenu)menus.getFirst()).add(menuItem);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ��������� ����� ��������� ������� ����
	private void adjustProperties(JMenuItem menuItem, Attributes attrs) {
		// ������ �������������� ���������
		String text        = attrs.getValue(ATTRIB_text       );
		String mnemonic    = attrs.getValue(ATTRIB_mnemonic   );
		String accelerator = attrs.getValue(ATTRIB_accelerator);
		String enabled     = attrs.getValue(ATTRIB_enabled    );
		// ����������� ��������� �������� ����
		menuItem.setText(text);
		if (mnemonic != null) {
			menuItem.setMnemonic(mnemonic.charAt(0));
		}
		if (accelerator != null) {
			menuItem.setAccelerator(
					KeyStroke.getKeyStroke(accelerator));
		}
		if (enabled != null) {
			boolean isEnabled = true;
			if (enabled.equals(String.valueOf(false)))
				isEnabled = false;
			menuItem.setEnabled(isEnabled);
		}
	}
}
