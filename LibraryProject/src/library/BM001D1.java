package library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BM001D1 extends JDialog
{
	FontType ft = new FontType();
	SQLcommand sqlc = new SQLcommand();
	JPanel panel;
	JDialog dialog;
	static JTextField[] jtf = new JTextField[6];
	JLabel[] jlb = new JLabel[6];
	JButton btnSave, btnDelete;
	
	public BM001D1()
	{
		setDialog();
		FormSetting(BM001.stats);
	}
	void setDialog()
	{
		dialog = new JDialog();
		dialog.setTitle("도서관리");
		dialog.setSize(400,550);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		setPanel();
	}
	void setPanel()
	{
		panel = new JPanel();
		panel.setSize(400,550);
		panel.setLayout(null);
		dialog.add(panel);
		setObject();
	}
	void setObject()
	{
		int y = 20;
		
		for(int i=0; i< 6; i++)
		{
			jlb[i] = new JLabel(sqlc.TblHeader("BOOK")[i]);
			jtf[i] = new JTextField();
			jlb[i].setFont(ft.P_16);
			jtf[i].setFont(ft.P_16);
			jlb[i].setBounds(30, 10 + y, 105, 30);
			jtf[i].setBounds(145, 10 + y, 205, 30);
			panel.add(jlb[i]);
			panel.add(jtf[i]);
			y +=60;
		}
		btnSave = new JButton("저장");
		btnDelete = new JButton("삭제");
		btnSave.setFont(ft.P_16);
		btnDelete.setFont(ft.P_16);
		btnSave.addActionListener(ClickBtnSave);
		btnDelete.addActionListener(ClickBtnDelete);
		btnSave.setBounds(30, 400, 320, 30);
		btnDelete.setBounds(30, 450, 320, 30);
		panel.add(btnSave);
		panel.add(btnDelete);
	}
	
	void FormSetting(String stats)
	{
		switch(stats)
		{
			case "I":  
						   jtf[5].setEnabled(false); 
						  btnDelete.setEnabled(false);
						  break;
			case "U" : 
							for(int i=0; i<6; i++)
							{
								jtf[i].setText((String)BM001.value[i]);
							}
							jtf[0].setEnabled(false);
							jtf[5].setEnabled(false);
			
		}
	}
	
	ActionListener ClickBtnSave= new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(BM001.stats.equals("I"))
			{
				sqlc.InsertBook(jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), jtf[3].getText(), jtf[4].getText());
				JOptionPane.showMessageDialog(null, jtf[0].getText()+ " 등록완료 ");
				BM001.dtm.setRowCount(0);
				sqlc.BookInfo();
				dialog.dispose();
			}
			else if(BM001.stats.equals("U"))
			{
				sqlc.UpdateBook(jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), jtf[3].getText(), jtf[4].getText());
				JOptionPane.showMessageDialog(null, jtf[0].getText()+ " 수정완료 ");
				BM001.dtm.setRowCount(0);
				sqlc.BookInfo();
				dialog.dispose();
			}
		}
	};
	
	ActionListener ClickBtnDelete= new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			sqlc.DeleteBook(jtf[0].getText());
			JOptionPane.showMessageDialog(null, jtf[0].getText()+ " 삭제완료 ");
			BM001.dtm.setRowCount(0);
			sqlc.BookInfo();
			dialog.dispose();
		}
	};
}
