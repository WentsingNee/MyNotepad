package tipContentManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DBHelper;

public class TipContentManager {

	static Vector<Tip> tips = new Vector<Tip>();

	static {
		updateFromDB();
	}

	static void updateFromDB() {
		DBHelper db1 = null;
		ResultSet ret = null;
		String sql = "select * from java.tip";// SQL语句

		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			tips.clear();
			while (ret.next()) {
				int id = Integer.decode(ret.getString(1)).intValue();
				String title = ret.getString(2);
				String content = ret.getString(3);
				String user = ret.getString(4);

				tips.add(new Tip(id, user, title, content));
			} // 显示数据
			ret.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();// 关闭连接
		}

	}

	public static void addNewTip(String usr, String title, String content) {
		String sql = "insert into java.tip (id,title,content,user) values (default,?,?,?)";
		DBHelper db = new DBHelper(sql);
		try {
			db.pst.setString(1, title);
			db.pst.setString(2, content);
			db.pst.setString(3, usr);

			db.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();// 关闭连接
		}
		updateFromDB();
	}

	public static Vector<Tip> getAllTipsOf(String usr) {
		Vector<Tip> result = new Vector<Tip>();
		for (Tip tip : tips) {
			if (tip.usr.equals(usr)) {
				result.add(tip);
			}
		}
		return result;
	}

	public static void remove(int id) {
		for (int i = 0; i < tips.size(); ++i) {
			if (tips.get(i).ID == id) {
				tips.remove(i);
				rmTipFromDB(id);
				return;
			}
		}
	}

	public static void rmTipFromDB(int id) {
		System.err.println(id);
		String sql = "delete from java.tip where id=?";
		DBHelper db = new DBHelper(sql);
		try {
			db.pst.setInt(1, id);

			db.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();// 关闭连接
		}
	}

	public static Tip getTip(int ID) {
		for (Tip tip : tips) {
			if (tip.ID == ID) {
				return tip;
			}
		}
		throw new NullPointerException();
	}

	public static void updateTip(int ID, String newTitle, String newContent) {
		for (Tip tip : tips) {
			if (tip.ID == ID) {
				tip.title = newTitle;
				tip.content = newContent;

				updateTipToDB(tip);
				return;
			}
		}
		throw new NullPointerException();
	}

	public static void updateTipToDB(Tip tip) {
		String sql = "update java.tip set title=? , content=? where id=?";
		DBHelper db = new DBHelper(sql);
		try {
			db.pst.setString(1, tip.title);
			db.pst.setString(2, tip.content);
			db.pst.setInt(3, tip.ID);

			db.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();// 关闭连接
		}

	}

}