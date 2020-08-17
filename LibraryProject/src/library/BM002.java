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

public class BM002 extends JPanel{
	FontType ft = new FontType();
	static JComboBox<String> cmb;
	static JTextField tf;
	JButton btnSearch , btnRentInfo;
	String[] combo = {"도서아이디","도서명","회원아이디", "회원명"};
	static String[] combocode = {"ID","NAME","M_ID", "M_NAME"};
	String[] head = {"도서아이디", "도서명", "회원아이디", "회원명", "대여일", "반납예정일"};
	JScrollPane jsp;
	JTable tbl;
	static String stats = null;
	static DefaultTableModel dtm;
	static Object[] value = null;
	SQLcommand sqlc = new SQLcommand();
	int rentinfo = 0;
	public BM002()
	{
		this.setName("BM002");
		setLayout(null);
		cmb = new JComboBox<>();
		for(int i=0; i<combo.length; i++)
		{
			cmb.addItem(combo[i]);
		}
		tf = new JTextField();
		btnSearch = new JButton("검색");
		btnRentInfo = new JButton("대여중");

		dtm = new DefaultTableModel(head, 0)
		{ 
			public boolean isCellEditable(int i, int c)
			{ 
				return false; 
			}
		};

		sqlc.RentInfo("");
		tbl = new JTable(dtm);
		jsp = new JScrollPane(tbl);
		
		btnSearch.addActionListener(ClickBtnSearch);
		btnRentInfo.addActionListener(ClickBtnRentInfo);
		tbl.addMouseListener(new MyMouseListener());

		
		cmb.setBounds(10, 10, 120, 30);
		tf.setBounds(140, 10, 200, 30);
		btnSearch.setBounds(340, 10, 70, 30);
		btnRentInfo.setBounds(648, 10, 120, 30);
		jsp.setBounds(10, 45, 758, 606);	
		
		tf.setFont(ft.P_15);
		cmb.setFont(ft.P_15);
		btnSearch.setFont(ft.P_15);
		btnRentInfo.setFont(ft.P_15);
		
		add(cmb);
		add(tf);
		add(btnSearch);
		add(btnRentInfo);
		add(jsp);
	}
	
	ActionListener ClickBtnSearch = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			dtm.setRowCount(0);
			sqlc.RentInfo("");
		}
	};
	ActionListener ClickBtnRentInfo = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(rentinfo == 0)
			{
				dtm.setRowCount(0);
				btnRentInfo.setText("대여중");
				sqlc.RentInfo(" AND M_ID IS NOT NULL");
				rentinfo += 1;
			}
			else
			{
				dtm.setRowCount(0);
				btnRentInfo.setText("대여가능");
				sqlc.RentInfo(" AND M_ID IS NULL");
				rentinfo -= 1;
			}

		}
	};
	private class MyMouseListener extends MouseAdapter 
	{
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
		    if (e.getClickCount() == 2) 
		    { 
		    	stats = "U";
				int row = tbl.getSelectedRow();
				if(tbl.getValueAt(row, 2)==null)
				{
					String action = "대여";
					String m_id = JOptionPane.showInputDialog("회원 아이디를 입력하세요.");
					sqlc.InsertRent((String)tbl.getValueAt(row, 0), (String)tbl.getValueAt(row, 1), m_id);
					sqlc.InsertBookLog((String)tbl.getValueAt(row, 0), (String)tbl.getValueAt(row, 1), m_id, action);
					JOptionPane.showMessageDialog(null, "대여가 완료되었습니다..");
					
				}
				else
				{
					int result = JOptionPane.showConfirmDialog(null, "반납처리 하시겠습니까?","반납처리",JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION)
					{
						String action = "반납";
						sqlc.DeleteRent((String)tbl.getValueAt(row, 0));
						sqlc.InsertBookLog((String)tbl.getValueAt(row, 0), (String)tbl.getValueAt(row, 1), (String)tbl.getValueAt(row, 2), action);
						JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");
					}
				}
				dtm.setRowCount(0);
				sqlc.RentInfo("");
		    } // 더블클릭
	  
	    }
	}
}
