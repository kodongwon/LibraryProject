package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLcommand {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	String CreateTime = "to_date(sysdate,'yyyy/mm/dd hh24:mi:ss')";
	//아이디체크//
	boolean chkID(String id)
	{
		Boolean b = false;
		try
		{
			String sql = "SELECT ID FROM MEMBER WHERE ID LIKE ?";
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			if(rs.next())
			{
				b = true;
			}
			else
			{
				b = false;
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	//비밀번호체크//
	boolean chkPW(String id, String pw)
	{
		Boolean b = false;
		try
		{
			String sql = "SELECT ID FROM MEMBER WHERE ID LIKE ? AND PW LIKE ?";
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2,pw);
			rs = pstm.executeQuery();
			if(rs.next())
			{
				b = true;
			}
			else
			{
				b = false;
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return b;
	}	
	//큰메뉴//
	String[][] getTitleMenu(String id)
	{
		String[][] arr = null;
		try
		{
			String sql = "SELECT ID, NAME, (SELECT COUNT(*) FROM MENU WHERE PERMIT=(SELECT KIND FROM MEMBER WHERE ID = ? ) AND LVL = 0) AS CNT "
					+ "FROM MENU "
					+ "WHERE PERMIT=(SELECT KIND FROM MEMBER WHERE ID= ? ) AND LVL=0";
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, id);
			rs = pstm.executeQuery();
			int i=0;
			while(rs.next())
			{
				if(arr == null)
				{
					int x = rs.getInt("CNT");
					arr = new String[x][2];
					arr[i][0] = rs.getString("ID");
					arr[i][1]  = rs.getString("NAME");
					i++;
				}
				else
				{
					arr[i][0] = rs.getString("ID");
					arr[i][1] = rs.getString("NAME");
					i++;
				}
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return arr;
	}
	//작은메뉴//
	  String[][] getSubMenu(String menuid)
	  {
		  String[][] arr = null;
			try
			{
				String sql = "SELECT ID, NAME, (SELECT COUNT(*) FROM MENU WHERE P_ID = ? AND LVL = 1) AS CNT FROM MENU WHERE P_ID = ? AND LVL = 1";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, menuid);
				pstm.setString(2, menuid);
				rs = pstm.executeQuery();
				int i=0;
				while(rs.next())
				{
					if(arr == null)
					{
						int x = rs.getInt("CNT");
						arr = new String[x][2];
						arr[i][0] = rs.getString("ID");
						arr[i][1]  = rs.getString("NAME");
						i++;
					}
					else
					{
						arr[i][0] = rs.getString("ID");
						arr[i][1] = rs.getString("NAME");
						i++;
					}
				}

			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
			return arr;
	  }
	  // 콤보박스 리스트
	  String[][] ComboList(String type)
	  {
		  String[][] arr = null;
			try
			{
				String sql = "SELECT ID, NAME, (SELECT COUNT(*) FROM CODE WHERE TBL = ? AND USE = 'SORT') AS CNT FROM CODE WHERE TBL = ? AND USE = 'SORT'";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, type);
				pstm.setString(2, type);
				rs = pstm.executeQuery();
				int i=0;
				while(rs.next())
				{
					if(arr == null)
					{
						int x = rs.getInt("CNT");
						arr = new String[x][2];
						arr[i][0] = rs.getString("ID");
						arr[i][1]  = rs.getString("NAME");
					}
					else
					{
						arr[i][0] = rs.getString("ID");
						arr[i][1] = rs.getString("NAME");
					}
					i++;
				}

			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
			return arr;
	  }
	  //테이블 헤더 리스트
	  String[] TblHeader(String tbl)
		{
			String arr[] = null;
			try
			{
				String sql = "SELECT COMMENTS , (SELECT COUNT(*) FROM ALL_COL_COMMENTS WHERE TABLE_NAME = ? ) AS CNT"
						+ " FROM ALL_COL_COMMENTS "
						+ "WHERE TABLE_NAME  = ? ";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, tbl);
				pstm.setString(2, tbl);
				rs = pstm.executeQuery();
				int i=0;
				while(rs.next())
				{
					if(arr == null) 
					{
						int x = rs.getInt("CNT");
						arr = new String[x];
						arr[i] = rs.getString("COMMENTS");
					}
					else
					{
						arr[i] = rs.getString("COMMENTS");
					}
					i++;
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
			return arr;
		}
	  //[회원] SELECT//
	  void MemberInfo()
		{
			String selectComboSQL = null;
			selectComboSQL = "WHERE " + ComboList("MEMBER")[MM001.cmb.getSelectedIndex()][0]
					+ " LIKE '%" 
					+ MM001.tf.getText()
					+ "%'" ;
			try
			{
				String sql ="SELECT ID, PW, NAME, EMAIL, PHONE, KIND, CREATEDATE "
						+ "FROM MEMBER "
						+ selectComboSQL
						+ " ORDER BY ID ASC";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					MM001.dtm.addRow(	new Object[]{
							rs.getString("ID"),
							rs.getString("PW"),
							rs.getString("NAME"),
							rs.getString("EMAIL"),
							rs.getString("PHONE"),
							rs.getString("KIND"),
							rs.getString("CREATEDATE")
							});
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  //[회원] INSERT//
	  void InsertMember(String id, String pw, String name, String email, String phone, String kind)
	{
		try 
		{            
            String quary = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'))";
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            pstm.setString(1,  id);
            pstm.setString(2, pw);
            pstm.setString(3, name);
            pstm.setString(4, email);
            pstm.setString(5, phone);
            pstm.setString(6, kind);
            
            int success = pstm.executeUpdate();
        } 
		catch (SQLException sqle) 
		{
            sqle.printStackTrace();
        }finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
	}
	  //[회원] UPDATE//
	  void UpdateMember(String id, String pw, String name, String email, String phone, String kind)
	  {
		  try 
			{            
	            String sql = "Update MEMBER "
	            		+ "Set PW = ?, "
	            		+ "NAME = ?, "
	            		+ "EMAIL = ?, "
	            		+ "PHONE = ?, "
	            		+ "KIND = ? "
	            		+ "Where ID = ? ";

	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, pw);
	            pstm.setString(2, name);
	            pstm.setString(3, email);
	            pstm.setString(4, phone);
	            pstm.setString(5, kind);
	            pstm.setString(6, id);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
	  }
	  //[회원] DELETE//
	  void DeleteMember(String id)
	  {
		  try 
			{            
	            String sql = "DELETE FROM MEMBER WHERE ID = ?";

	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, id);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
	  }
	  //[책] SELECT//
	  void BookInfo()
		{
			String selectComboSQL = null;
			selectComboSQL = "WHERE " + ComboList("BOOK")[BM001.cmb.getSelectedIndex()][0]
					+ " LIKE '%" 
					+ BM001.tf.getText()
					+ "%'" ;
			try
			{
				String sql ="SELECT ID, NAME, TYPE, MAKER, PUBLISHER, CREATEDATE "
						+ "FROM BOOK "
						+ selectComboSQL
						+ " ORDER BY ID ASC";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				int i=0;
				while(rs.next())
				{
					BM001.dtm.addRow(	new Object[]{
							rs.getString("ID"),
							rs.getString("NAME"),
							rs.getString("TYPE"),
							rs.getString("MAKER"),
							rs.getString("PUBLISHER"),
							rs.getString("CREATEDATE")
							});
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  //[책] INSERT//
	  void InsertBook(String id, String name, String type, String maker, String publisher)
		{
			try 
			{            
	            String quary = "INSERT INTO BOOK VALUES(?, ?, ?, ?, ?, to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'))";
	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(quary);
	            pstm.setString(1,  id);
	            pstm.setString(2, name);
	            pstm.setString(3, type);
	            pstm.setString(4, maker);
	            pstm.setString(5, publisher);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  //[책] UPDATE//
	  void UpdateBook(String id, String name, String type, String maker, String publisher)
	  {
		  try 
			{            
	            String sql = "Update BOOK "
	            		+ "Set NAME = ?, "
	            		+ "TYPE = ?, "
	            		+ "MAKER = ?, "
	            		+ "PUBLISHER = ? "
	            		+ "Where ID = ? ";

	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, name);
	            pstm.setString(2, type);
	            pstm.setString(3, maker);
	            pstm.setString(4, publisher);
	            pstm.setString(5, id);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
	  }
	  //[책] DELETE//
	  void DeleteBook(String id)
	  {
		  try 
			{            
	            String sql = "DELETE FROM BOOK WHERE ID = ?";

	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, id);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
	  }
	  //[대여] SELECT//
	  void RentInfo(String stat)
		{
			String selectComboSQL = null;
			if((BM002.cmb.getSelectedIndex() == 2 || BM002.cmb.getSelectedIndex() == 3) && BM002.tf.getText().equals(""))
			{
				selectComboSQL = "";
			}
			else
			{
				selectComboSQL = "WHERE " + BM002.combocode[BM002.cmb.getSelectedIndex()]
						+ " LIKE '%" 
						+ BM002.tf.getText()
						+ "%'" 
						+ stat;
			}
			try
			{
				String sql ="SELECT A.ID, "
						+ "A.NAME, "
						+ "B.M_ID, "
						+ "B.M_NAME, "
						+ "B.RENTDATE, "
						+ "B.RETURNDATE, "
						+ "CASE WHEN B.M_ID IS NULL THEN '대여가능' ELSE '대여중' END AS STATS "
						+ "FROM BOOK A "
						+ "LEFT JOIN RENTAL B "
						+ "ON A.ID = B.B_ID "
						+ selectComboSQL
						+ " ORDER BY A.ID ASC";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				int i=0;
				while(rs.next())
				{
					BM002.dtm.addRow(	new Object[]{
							rs.getString("ID"),
							rs.getString("NAME"),
							rs.getString("M_ID"),
							rs.getString("M_NAME"),
							rs.getString("RENTDATE"),
							rs.getString("RETURNDATE")
							});
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  //[대여] INSERT//
	  void InsertRent(String b_id, String b_name, String m_id)
		{
			try 
			{            
	            String quary = "INSERT INTO RENTAL VALUES(?, ?, ?, (select name from member where id = '"
	            		+ m_id
	            		+ "') , to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'), to_char(sysdate+7,'yyyy/mm/dd hh24:mi:ss'))";
	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(quary);
	            pstm.setString(1,  b_id);
	            pstm.setString(2, b_name);
	            pstm.setString(3, m_id);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  //[대여] DELETE//
	  void DeleteRent(String id)
	  {
		  try 
			{            
	            String sql = "DELETE FROM RENTAL WHERE B_ID = ?";

	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, id);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
	  }
	  //[로그] SELECT//
	  void BookLogInfo()
		{
			String selectComboSQL = null;
			selectComboSQL = "WHERE " + ComboList("BOOKLOG")[BM003.cmb.getSelectedIndex()][0]
					+ " LIKE '%" 
					+ BM003.tf.getText()
					+ "%'" ;
			try
			{
				String sql ="SELECT B_ID, B_NAME, M_ID, M_NAME, ACTION, LOGDATE "
						+ "FROM BOOKLOG "
						+ selectComboSQL
						+ " ORDER BY B_ID ASC";
				conn = DBConnection.getConnection();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					BM003.dtm.addRow(	new Object[]{
							rs.getString("B_ID"),
							rs.getString("B_NAME"),
							rs.getString("M_ID"),
							rs.getString("M_NAME"),
							rs.getString("ACTION"),
							rs.getString("LOGDATE"),
							});
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  //[로그] INSERT//
	  void InsertBookLog(String b_id, String b_name, String m_id, String action)
		{
			try 
			{            
	            String quary =  "INSERT INTO BOOKLOG VALUES(?, ?, ?, (select name from member where id = '"
	            		+ m_id
	            		+ "') , ?, to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'))";
	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(quary);
	            pstm.setString(1,  b_id);
	            pstm.setString(2, b_name);
	            pstm.setString(3, m_id);
	            pstm.setString(4, action);
	            
	            int success = pstm.executeUpdate();
	        } 
			catch (SQLException sqle) 
			{
	            sqle.printStackTrace();
	        }finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
	  
}
