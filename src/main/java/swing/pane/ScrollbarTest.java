package swing.pane;

// Пример работы с полосой прокрутки JScrollBar

import java.awt.BorderLayout;

import javax.swing.*;

public class ScrollbarTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	public ScrollbarTest() {
		super("Пример с полосой прокрутки JScrollBar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создание стандартной модели
		BoundedRangeModel model = new DefaultBoundedRangeModel(10, 40, 0, 100);
		// Создание полос прокрутки
		JScrollBar scrollbar1 = new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar scrollbar2 = new JScrollBar(JScrollBar.VERTICAL);
		// Подключение к полосам прокрутки модели
		scrollbar1.setModel(model);
		scrollbar2.setModel(model);
		// Размещение полос прокрутки в панели
		getContentPane().add(scrollbar1, BorderLayout.SOUTH); //  "South"
		getContentPane().add(scrollbar2, BorderLayout.EAST ); //  "East"
		// Вывод окна на экран
		setSize(400, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ScrollbarTest();
	}
}
