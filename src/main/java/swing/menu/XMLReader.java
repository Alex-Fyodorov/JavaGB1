package swing.menu;

/**
 * ������ ������ ����� XML.
 */

import java.io.Reader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

import org.xml.sax.InputSource;

public class XMLReader 
{
	private  InputSource  source;  // �������� ������ XML

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public XMLReader (){}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * ����������� ������
	 * @param fname ������ ���� � ����� XML
	 * @throws FileNotFoundException
	 */
	public XMLReader (final String fname) 
			          throws FileNotFoundException
	{
		this (new FileInputStream(fname));
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * ����������� ������
	 * @param fname ������ ���� � ����� XML
	 * @param charset ��������� ����������� XML ����� 
	 * @throws FileNotFoundException
	 */
	public XMLReader (final String fname, final String charset) 
			          throws FileNotFoundException
	{
		this (new FileInputStream(fname), charset);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * ����������� ������
	 * @param stream ����� ������
	 */
	public XMLReader (InputStream stream)
	{
		// ������ ������ XML
		try {
			Reader reader = new InputStreamReader(stream);
			source = new InputSource(reader);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * ����������� ������
	 * @param stream ����� ������
	 * @param charset ��������� ���� UTF8
	 */
	public XMLReader (final InputStream stream, final String charset)
	{
		// ������ ������ XML
		try {
			Reader reader = new InputStreamReader(stream, charset);
			source = new InputSource(reader);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * ������� ��������� ��������� ������ XML
	 * @return InputSource �������� ������ XML
	 */
	public InputSource getInputSource()
	{
		return source;
	}
}
