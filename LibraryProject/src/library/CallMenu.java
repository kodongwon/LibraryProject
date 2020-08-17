package library;

public class CallMenu 
{
	public CallMenu(String pgid)
	{
		/*
		 * if(pgid.equals("BM001")) { MainScreen.tab.remove(0);
		 * MainScreen.tab.addTab("도서등록관리", new BM001()); } else if(pgid.equals("MM001"))
		 * { MainScreen.tab.remove(0); MainScreen.tab.addTab("회원등록관리", new MM001()); }
		 */
		switch(pgid)
		{
			case "BM001" : 
				MainScreen.tab.remove(0); 
				MainScreen.tab.addTab("도서등록관리", new BM001());
				break;
			case "MM001" :
				MainScreen.tab.remove(0);
				MainScreen.tab.addTab("회원등록관리", new MM001());
				break;
			case "BM002" :
				MainScreen.tab.remove(0);
				MainScreen.tab.addTab("도서대여관리", new BM002());
				break;
			case "BM003" :
				MainScreen.tab.remove(0);
				MainScreen.tab.addTab("도서로그", new BM003());
				break;
		}

	}
}
