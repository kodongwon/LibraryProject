package library;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

	JFrame frame;
	JPanel panel;
	static JTextField id;
	JPasswordField pw;
	FontType ft = new FontType();
	SQLcommand sqlc = new SQLcommand();
	public Login() {
		setFrame();
		frame.repaint();
		id.requestFocus();
	}

	void setFrame() {
		frame = new JFrame("�������� ���α׷�");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		setPanel();
	}

	void setPanel() {
		panel = new JPanel();
		panel.setSize(400, 400);
		/* panel.setBackground(Color.white); */
		panel.setLayout(null);
		setObject();
		frame.add(panel);
	}

	void setObject() {
		// ����
		JLabel title = new JLabel("�� �� ��");
		JLabel lb1 = new JLabel("���̵�");
		JLabel lb2 = new JLabel("��й�ȣ");
		id = new JTextField(20);
		pw = new JPasswordField(20);
		JButton btnLogin = new JButton("�α���");
		JButton btnMember = new JButton("ȸ������");
		// ��ư ����
		btnLogin.addActionListener(ClickbtnLogin);
		btnMember.addActionListener(ClickbtnMember);
		// ��Ʈ
		
		  title.setFont(ft.B_30); 
		  lb1.setFont(ft.P_15); 
		  lb2.setFont(ft.P_15);
		  id.setFont(ft.P_15); 
		  pw.setFont(ft.P_15); 
		  btnLogin.setFont(ft.P_15);
		  btnMember.setFont(ft.P_15);
		 
		// ��ġ����
		title.setBounds(0, 20, 400, 50);
		title.setHorizontalAlignment(JLabel.CENTER);
		lb1.setBounds(50, 90, 300, 30);
		id.setBounds(50, 120, 300, 30);
		lb2.setBounds(50, 150, 300, 30);
		pw.setBounds(50, 180, 300, 30);
		btnLogin.setBounds(50, 240, 300, 30);
		btnMember.setBounds(50, 280, 300, 30);
		// �߰�
		panel.add(title);
		panel.add(lb1);
		panel.add(lb2);
		panel.add(id);
		panel.add(pw);
		panel.add(btnLogin);
		panel.add(btnMember);
	}

	ActionListener ClickbtnLogin = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			  if(id.getText().isEmpty() || pw.getPassword().length == 0)
			  {
				  JOptionPane.showMessageDialog(null, "��ĭ�� ä���ֽñ� �ٶ��ϴ�.");
			  } 
			  else if(!sqlc.chkID(id.getText()))
			  {
				  JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�."); 
			  }
			  else if(!sqlc.chkPW(id.getText(), new String(pw.getPassword())))
			  {
				  JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�."); 
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null, id.getText()+"�� ȯ���մϴ�."); 
				  new MainScreen();
				  frame.dispose();
			  }
			 
		}
	};
	ActionListener ClickbtnMember = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 new SignUp(); 
		}
	};
}
