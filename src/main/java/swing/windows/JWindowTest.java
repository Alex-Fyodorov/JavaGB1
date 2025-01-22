package swing.windows;

// ������ ������������� ���� ��� ����� JWindow
import javax.swing.*;

import java.awt.*;

// ����� ���������� �����������
class ImageDraw extends JComponent
{
	private static final long serialVersionUID = 1L;
	private Image capture;

	ImageDraw (Image capture) {
		this.capture = capture;
	}
	public void paintComponent(Graphics g) {
		// ���������� �����������
		g.drawImage(capture, 0, 0, this);
	}
}
public class JWindowTest extends JWindow
{
	private static final long serialVersionUID = 1L;
	// ����������� "�������� �����"
	private Image capture;
	// ������ ���� 
	private int window_w = 300, window_h = 300;
	
	public JWindowTest() {
		super();
		// ����������� ��������� ���� �� ������
		setLocation(200, 100);
		// ����������� ������� ����
		setSize (window_w, window_h);
		try {
			// "��������" ����� ����������� "�������� �����"
			Robot robot = new Robot();
			capture = robot.createScreenCapture(
				     new Rectangle(5, 5, window_w, window_h));
		} catch (Exception ex) { ex.printStackTrace(); }
		// ��������� � ��������� �����������
		getContentPane().add(new ImageDraw(capture));
		// ��������� ����
		setVisible(true);
		try {
			// ����������� ������ ����� 10 ���
			Thread.currentThread();
			Thread.sleep(10000);
		} catch (Exception e) { }
			System.exit(0);
	}
	public static void main(String[] args) {
		new JWindowTest();
	}
}