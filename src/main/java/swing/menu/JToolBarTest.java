package differents.swing.menu;

// ����� �������� ��������������� ������� ������������
import javax.swing.*;

import java.awt.event.*;

public class JToolBarTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public JToolBarTest()
	{
		super("������ ������������� JToolBar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// �������� ������� ������������
		JToolBar tbCommon = new JToolBar();
		tbCommon.add(new OpenAction());
		tbCommon.add(new ExitAction());
		tbCommon.addSeparator();
		tbCommon.add(new JButton("�����"));
		// ��������� ����������� �������������� ������
		tbCommon.setFloatable(false);
		
		String[] schools = new String[] {"�����", "��������", "��������"};
		JToolBar tbEducation = new JToolBar();
		tbEducation.add(new JButton("�����������"));
		tbEducation.add(new JComboBox<String> (schools));
		// ��������� ����������� �������������� ������
		tbEducation.setFloatable(false);
		// ��������� ����������� ������� ��������������� - ��� ���������
		// ���� ������ ����������
		tbEducation.setRollover(false);
		
		JToolBar tbStyle = new JToolBar();
		tbStyle.add(new JButton("�������"));
		tbStyle.add(new JButton("����������"));
		tbStyle.add(new JButton("������������"));
		
		// ������������ �����������
		tbEducation.add(Box.createGlue());

		// ���������� ���� ������� ������������
		BoxLayoutUtils blUtils = new BoxLayoutUtils();
		// ������ � �������������� ������������� �����������
		JPanel first = blUtils.createHorizontalPanel();
		first.add(tbCommon);
		first.add(Box.createHorizontalStrut(5));
		first.add(tbEducation);
		// ������ � ������������ ������������� �����������
		JPanel all = blUtils.createVerticalPanel();
		all.add(first);
		all.add(tbStyle);
		// ������������ �� �����������
		blUtils.setGroupAlignmentX(new JComponent[] { first, tbStyle }, 
		                        JComponent.LEFT_ALIGNMENT);
		// ���������� � ������� ����� ����
		getContentPane().add(all, "North");
		// ������� ���� �� �����
		setSize(400, 300);
		setVisible(true);
	}
	//-----------------------------------------------------------------------------
	// ������� ��� ������ "��������"
	class OpenAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public OpenAction() {
			// ��������� ������
			putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/open.png"));
		}
		// ��������� ��������
		public void actionPerformed(ActionEvent e) {
			// ������ �� ������
		}
	}
	//-----------------------------------------------------------------------------
	// ������� ��� ������ "������"
	class ExitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ExitAction() {
			// ��������� ������
			putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/exit.png"));
		}
		// ��������� ��������
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	//-----------------------------------------------------------------------------
	/**
	 * ����� ������������ ����������� � ����������
	 */
	class BoxLayoutUtils 
	{
		// ������������ ����������� �� ��� X
		public void setGroupAlignmentX(JComponent[] cs, float alignment) {
			for (int i=0; i<cs.length; i++) {
				cs[i].setAlignmentX(alignment);
			}
		}
		// ������������ ����������� �� ��� Y
		public void setGroupAlignmentY(JComponent[] cs, float alignment) {
			for (int i=0; i<cs.length; i++) {
				cs[i].setAlignmentY(alignment);
				}
		}
		// �������� ������ � ������������� ������������ ������� �������������
		public JPanel createVerticalPanel() {
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			return p;
		}
		// �������� ������ � ������������� �������������� ������� �������������
		public JPanel createHorizontalPanel() {
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
			return p;
		}
	}
	//-----------------------------------------------------------------------------
	public static void main(String[] args) {
		new JToolBarTest();
	}
}
