package library;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainScreen extends JFrame {

	FontType ft = new FontType();
	SQLcommand sqlc = new SQLcommand();
	JFrame frame;
	JPanel toppanel, leftpanel, tabpanel;
	JLabel navigation;
	String Tmenu = null;
	JButton[] BtnTopArray = null;
	static String loginID;
	static JButton[] BtnLeftArray=null; 
	static JTabbedPane tab;


	
	public MainScreen()
	{
		loginID = Login.id.getText();
		setFrame();
	}
	void setFrame()
	{
		frame = new JFrame("도서관리 프로그램");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		setTopPanel();
		setLeftPanel();
		setTabPanel();
	}
	void setTopPanel()
	{
		toppanel = new JPanel();
		toppanel.setBounds(200, 0, 800, 80);
		toppanel.setLayout(null);
		frame.add(toppanel);
		setTopObject();
	}
	void setLeftPanel()
	{
		leftpanel = new JPanel();
		leftpanel.setBounds(0, 0, 200, 800);
		leftpanel.setLayout(null);
		frame.add(leftpanel);
		setLogoObject();
		setLeftObject(Tmenu);
	}
	void setTabPanel()
	{
		tabpanel = new JPanel();
		tabpanel.setBounds(200,80,800,720);
		tabpanel.setLayout(null);
		frame.add(tabpanel);
		setTabObject();

	}
	void setTopObject()
	{
		BtnTopArray = new JButton[sqlc.getTitleMenu(loginID).length];
		int x=0;
		for(int i=0; i<BtnTopArray.length; i++)
		{
			BtnTopArray[i] = new JButton(sqlc.getTitleMenu(loginID)[i][1]);
			BtnTopArray[i].setName(sqlc.getTitleMenu(loginID)[i][0]);
			BtnTopArray[i].setVisible(true);
			BtnTopArray[i].setFont(ft.P_20);
			BtnTopArray[i].setBounds(x, 0, 120, 80);
			BtnTopArray[i].addActionListener(ClickBtnTitleMenu);
			x = x+120;
			toppanel.add(BtnTopArray[i]);
		}
		Tmenu = BtnTopArray[0].getName();
	}
	void setLogoObject()
	{
		JLabel logo = new JLabel("도서관리 프로그램");
		navigation = new JLabel("  Navigation >> ");
		//폰트
		logo.setFont(ft.P_20);
		navigation.setFont(ft.P_15);
		//위치
		logo.setBounds(0, 0, 200, 80);
		logo.setHorizontalAlignment(JLabel.CENTER);
		navigation.setBounds(0, 80, 200, 25);
		//
		leftpanel.add(logo);
		leftpanel.add(navigation);
	}
	void setLeftObject(String menuid)
	{
		BtnLeftArray = new JButton[sqlc.getSubMenu(menuid).length];
		int y=105;
		for(int i=0; i<BtnLeftArray.length; i++)
		{
			BtnLeftArray[i] = new JButton(sqlc.getSubMenu(menuid)[i][1]);
			BtnLeftArray[i].setName(sqlc.getSubMenu(menuid)[i][0]);
			BtnLeftArray[i].setVisible(true);
			BtnLeftArray[i].setFont(ft.P_20);
			BtnLeftArray[i].setBounds(0, y, 200, 40);
			BtnLeftArray[i].addActionListener(ClickBtnSubMenu);
			y = y+40;
			leftpanel.add(BtnLeftArray[i]);
		}
	}
	void setTabObject()
	{
		tab = new JTabbedPane();
		tab.setBounds(0,0,785,681);
		tabpanel.add(tab);
		tab.addTab("도서등록관리", new BM001());
		 
	}
	void RemoveSmallMenuList()
	{
		for(int i=0; i<BtnLeftArray.length; i++)
		{
			leftpanel.remove(BtnLeftArray[i]);
		}
	}
	ActionListener ClickBtnTitleMenu = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object obj = e.getSource();
			
			for(int i=0; i<BtnTopArray.length; i++)
			{
				String StartRoot = "  Navigation >> ";
				if(obj == BtnTopArray[i])
				{	
					navigation.setText(StartRoot + BtnTopArray[i].getText());
					RemoveSmallMenuList();
					setLeftObject(BtnTopArray[i].getName());
					frame.repaint();
				}
			}
		}
	};
	ActionListener ClickBtnSubMenu = new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object obj = e.getSource();
			
			for(int i=0; i<BtnLeftArray.length; i++)
			{
				if(obj == BtnLeftArray[i])
				{	
					new CallMenu(BtnLeftArray[i].getName());
				}
			}
		}
	};
}
