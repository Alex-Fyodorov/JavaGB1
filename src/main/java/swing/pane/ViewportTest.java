package differents.swing.pane;

// Пример работы с компонентом JViewport

import javax.swing.*;

import java.awt.*;

public class ViewportTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public ViewportTest()
	{
		super("Пример JViewport");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Метка с изибражением
		JLabel label = new JLabel(new ImageIcon("images/wolf.jpg"));
		// "Видоискатель"
		JViewport viewport = new JViewport();
		// Настройка "видоискателя" - размещение метки
		viewport.setView(label);
		// Определение видимого диапазона
		viewport.setExtentSize(new Dimension(200, 200));
		// Точка начала видимой области
		viewport.setViewPosition(new Point(150, 150));
		// Ограничение размера "видоискателя"
		viewport.setPreferredSize(new Dimension(200, 200));
		// Определение менеджера расположения
		getContentPane().setLayout(new FlowLayout());
		// Вывод окна на экран
		getContentPane().add(viewport);
		setSize(400, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ViewportTest();
	}
}
