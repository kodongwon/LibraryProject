package library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BM003 extends JPanel{
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
	public BM003()
	{
		this.setName("BM003");
		setLayout(null);
		cmb = new JComboBox<>();
		for(int i=0; i<sqlc.ComboList("BOOKLOG").length; i++)
		{
			cmb.addItem(sqlc.ComboList("BOOKLOG")[i][1]);
		}
		tf = new JTextField();
		btnSearch = new JButton("°Ë»ö");

		dtm = new DefaultTableModel(sqlc.TblHeader("BOOKLOG"), 0)
		{ 
			public boolean isCellEditable(int i, int c)
			{ 
				return false; 
			}
		};

		sqlc.BookLogInfo();
		tbl = new JTable(dtm);
		jsp = new JScrollPane(tbl);
		
		btnSearch.addActionListener(ClickBtnSearch);

		cmb.setBounds(10, 10, 90, 30);
		tf.setBounds(110, 10, 200, 30);
		btnSearch.setBounds(310, 10, 70, 30);
		jsp.setBounds(10, 45, 758, 606);	
		
		tf.setFont(ft.P_15);
		cmb.setFont(ft.P_15);
		btnSearch.setFont(ft.P_15);
		
		add(cmb);
		add(tf);
		add(btnSearch);
		add(jsp);
	}
	
	ActionListener ClickBtnSearch = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			dtm.setRowCount(0);
			sqlc.BookLogInfo();
		}
	};
	
}
