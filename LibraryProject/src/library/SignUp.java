package library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JDialog
{
	JPanel panel;
	JDialog dialog;
	JTextField id, name, email, phone, kind, createdate;
	JPasswordField pw;
	JButton btnSave;
	FontType ft = new FontType();
	SQLcommand sqlc = new SQLcommand();
	Member mb;
	public SignUp()
	{
		setDialog();
		FormSetting("I");

	}
	
	void setDialog()
	{
		dialog = new JDialog();
		dialog.setTitle("회원가입");
		dialog.setSize(400,630);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		setPanel();
	}
	
	void setPanel()
	{
		panel = new JPanel();
		panel.setSize(400,630);
		panel.setLayout(null);
		dialog.add(panel);
		setObject();
	}
	
	void setObject()
	{
		//생성
		JLabel title = new JLabel("회원가입");
		JLabel l_id = new JLabel("아이디");
		JLabel l_pw = new JLabel("패스워드");
		JLabel l_name = new JLabel("이름");
		JLabel l_email = new JLabel("이메일");
		JLabel l_phone = new JLabel("전화번호");
		JLabel l_kind = new JLabel("권한");
		JLabel l_createdate = new JLabel("생성일자");
		
		id = new JTextField(20);
		name = new JTextField(20);
		email = new JTextField(20);
		phone = new JTextField(20);
		kind = new JTextField(20);
		createdate = new JTextField(20);
		pw = new JPasswordField(20);
		
		btnSave = new JButton("저장");
		//폰트설정
		title.setFont(ft.B_30);
		l_id.setFont(ft.P_15);
		l_pw.setFont(ft.P_15);
		l_name.setFont(ft.P_15);
		l_email.setFont(ft.P_15);
		l_phone.setFont(ft.P_15);
		l_kind.setFont(ft.P_15);
		l_createdate.setFont(ft.P_15);
		
		id.setFont(ft.P_15);
		pw.setFont(ft.P_15);
		name.setFont(ft.P_15);
		email.setFont(ft.P_15);
		phone.setFont(ft.P_15);
		kind.setFont(ft.P_15);
		createdate.setFont(ft.P_15);
		btnSave.setFont(ft.P_15);
		pw.setEchoChar('*');
		//위치설정
		title.setBounds(0, 20, 400, 50);
		title.setHorizontalAlignment(JLabel.CENTER);
		l_id.setBounds(50, 70, 300, 30);
		id.setBounds(50, 100, 300, 30);
		l_pw.setBounds(50, 130, 300, 30);
		pw.setBounds(50, 160, 300, 30);
		l_name.setBounds(50, 190, 300, 30);
		name.setBounds(50, 220, 300, 30);
		l_email.setBounds(50, 250, 300, 30);
		email.setBounds(50, 280, 300, 30);
		l_phone.setBounds(50, 310, 300, 30);
		phone.setBounds(50, 340, 300, 30);
		l_kind.setBounds(50, 370, 300, 30);
		kind.setBounds(50, 400, 300, 30);
		l_createdate.setBounds(50, 430, 300, 30);
		createdate.setBounds(50, 460, 300, 30);
		btnSave.setBounds(50, 510, 300, 30);
		//버튼 이벤트 추가
		btnSave.addActionListener(ClickbtnSave);
		//패널에 넣기
		panel.add(title);
		panel.add(l_id);
		panel.add(id);
		panel.add(l_pw);
		panel.add(pw);
		panel.add(l_name);
		panel.add(name);
		panel.add(l_email);
		panel.add(email);
		panel.add(l_phone);
		panel.add(phone);
		panel.add(l_kind);
		panel.add(kind);
		panel.add(l_createdate);
		panel.add(createdate);
		panel.add(btnSave);
	}
	
	String setRank()
	{
		if(id.getText().toLowerCase().contains("admin"))
		{
			return "AD";
		}
		else
		{
			return "IB";
		}
	}

	void FormSetting(String stats)
	{
		switch(stats)
		{
			case "U" : 
							id.setText((String)MM001.value[0]);
							pw.setText((String)MM001.value[1]);
							name.setText((String)MM001.value[2]);
							email.setText((String)MM001.value[3]);
							phone.setText((String)MM001.value[4]);
							kind.setText((String)MM001.value[5]);
							createdate.setText((String)MM001.value[6]);
							id.setEnabled(false);
							createdate.setEnabled(false);
							break;
			default : 
							 kind.setEnabled(false); 
							 createdate.setEnabled(false);
							 break;
		}
	}
	
	ActionListener ClickbtnSave = new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			  if(id.getText().isEmpty() || pw.getPassword().length==0 || name.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty())
			  { 
				  JOptionPane.showMessageDialog(null, "빈칸을 채워주시기 바랍니다."); 
			  } 
			  else if(sqlc.chkID(id.getText())) 
			  {
				  JOptionPane.showMessageDialog(null, "아이디가 존재합니다."); 
				  id.setText(null);
				  id.requestFocus(); 
			  } 
			  else 
			  { 
				  sqlc.InsertMember(id.getText(), new
				  String(pw.getPassword()), name.getText(), email.getText(), phone.getText(),
				  setRank()); JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				  dialog.dispose(); 
			  }
		}
	};

	
}
