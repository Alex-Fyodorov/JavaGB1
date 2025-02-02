package swing.tree;

// Дерево с отображаением флажков в листьях 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class CheckBoxTree extends JTree
{
	private static final long serialVersionUID = 1L;
	// Стандартный объект для отображения узлов
	private DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	// Конструктор
	public CheckBoxTree(TreeModel model) 
	{
		super(model);
		// Слушатель мыши
		addMouseListener(new MouseListener());
		// Определение собственного отображающего объекта
		setCellRenderer(new CheckBoxRenderer());
	}
	// Объект отображения узлов дерева с использованием флажков
	class CheckBoxRenderer extends JCheckBox implements TreeCellRenderer
	{
		private static final long serialVersionUID = 1L;
		public CheckBoxRenderer() {
			// Флажок будет прозрачным
			setOpaque(false);
		}
		// Метод получения компонента узла
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
			                          boolean expanded, boolean leaf, int row, boolean hasFocus)
		{
			// Проверка принадлежности узла к стандартной модели
			if (!(value instanceof DefaultMutableTreeNode)) {
				// Если нет, то используется стандартный объект
				return renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			}
			Object data = ((DefaultMutableTreeNode)value).getUserObject();
			// Проверка, являются ли данные CheckBoxElement
			if (data instanceof CheckBoxElement) {
				CheckBoxElement element = (CheckBoxElement)data;
				// Настройка флажка и текста
				setSelected(element.selected);
				setText(element.text);
				return this;
			}
			// В противном случае метод возвращает стандартный объект
			return renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
	// Слушатель событий мыши
	class MouseListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			// Путь к узлу
			TreePath path = getClosestPathForLocation(e.getX(), e.getY());
			if (path == null) 
				return;
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
			// Проверка принадлежности узла к стандартной модели
			if (node.getUserObject() instanceof CheckBoxElement) {
				// Изменение состояния флажка
				CheckBoxElement element = (CheckBoxElement)node.getUserObject();
				element.selected = ! element.selected;
				repaint();
			}
		}
	}
}
