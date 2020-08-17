package library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class MM001 extends JPanel{
	FontType ft = new FontType();
	static JComboBox<String> cmb;
	static JTextField tf;
	JButton btnSearch , btnInsert;
	JScrollPane jsp;
	JTable tbl;
	static String stats = null;
	static DefaultTableModel dtm;
	static Object[] value = null;
	SQLcommand sqlc = new SQLcommand();
	
	public MM001()
	{
		this.setName("MM001");
		setLayout(null);
		cmb = new JComboBox<>();
		for(int i=0; i<sqlc.ComboList("MEMBER").length; i++)
		{
			cmb.addItem(sqlc.ComboList("MEMBER")[i][1]);
		}
		tf = new JTextField();
		btnSearch = new JButton("검색");
		btnInsert = new JButton("회원등록");

		dtm = new DefaultTableModel(sqlc.TblHeader("MEMBER"), 0)
		{ 
			public boolean isCellEditable(int i, int c)
			{ 
				return false; 
			}
		};

		sqlc.MemberInfo();
		tbl = new JTable(dtm);
		jsp = new JScrollPane(tbl);
		
		btnSearch.addActionListener(ClickBtnSearch);
		btnInsert.addActionListener(ClickBtnDetail);
		tbl.addMouseListener(new MyMouseListener());

		
		cmb.setBounds(10, 10, 90, 30);
		tf.setBounds(110, 10, 200, 30);
		btnSearch.setBounds(310, 10, 70, 30);
		btnInsert.setBounds(648, 10, 120, 30);
		jsp.setBounds(10, 45, 758, 606);	
		
		tf.setFont(ft.P_15);
		cmb.setFont(ft.P_15);
		btnSearch.setFont(ft.P_15);
		btnInsert.setFont(ft.P_15);
		
		add(cmb);
		add(tf);
		add(btnSearch);
		add(btnInsert);
		add(jsp);
	}
	
	ActionListener ClickBtnSearch = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			dtm.setRowCount(0);
			sqlc.MemberInfo();
		}
	};
	ActionListener ClickBtnDetail = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			stats = "I";
			new MM001D1();
			dtm.setRowCount(0);
			sqlc.MemberInfo();

		}
	};
	private class MyMouseListener extends MouseAdapter 
	{
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    	stats = "U";
		    if (e.getClickCount() == 2) 
		    { 
				int row = tbl.getSelectedRow();
				if(row != -1)
				{
					value = new Object[]{tbl.getValueAt(row, 0), 
													tbl.getValueAt(row, 1),
													tbl.getValueAt(row, 2),
													tbl.getValueAt(row, 3),
													tbl.getValueAt(row, 4),
													tbl.getValueAt(row, 5),
													tbl.getValueAt(row, 6)};
				}
		    	new MM001D1();
		    } // 더블클릭
	  
	    }
	}
}
