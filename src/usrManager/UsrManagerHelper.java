package usrManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import database.DBHelper;

public class UsrManagerHelper {

	protected static Map<String, Usr_settings> user_list = new HashMap<String, Usr_settings>();

	static {
		updateFromDB();
	}

	static void updateFromDB() {
		DBHelper db1 = null;
		ResultSet ret = null;
		String sql = "select * from java.user";

		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeQuery();
			user_list.clear();
			while (ret.next()) {
				int id = Integer.decode(ret.getString(1)).intValue();
				String usr_name = ret.getString(2);
				String password = ret.getString(3);
				boolean is_remember = ret.getString(4).equals("0") ? false : true;

				user_list.put(usr_name, new Usr_settings(password, is_remember));
			}
			ret.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db1.close();// 关闭连接
		}

	}

	public static boolean addUser(String usr_name, String password) {
		return addUser(usr_name, password, false);
	}

	public static boolean addUser(String usr_name, String password, boolean is_remember) {
		if (user_list.containsKey(usr_name)) {
			return false;
		} else {
			user_list.put(usr_name, new Usr_settings(password, is_remember));
			addUsrToDB(usr_name, password, is_remember);
			return true;
		}
	}

	private static void addUsrToDB(String usr_name, String password, boolean is_remember) {
		String sql = "insert into java.user (id,name,password,is_remember_password) values (default,?,?,default)";
		DBHelper db = new DBHelper(sql);
		try {
			db.pst.setString(2, usr_name);
			db.pst.setString(3, password);
			db.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();// 关闭连接
		}
	}

	public static boolean chkUsrExist(String usr_name) {
		return user_list.containsKey(usr_name);
	}

	public static boolean chkPassword(String usr_name, String password) throws NullPointerException {
		Usr_settings settings = user_list.get(usr_name);

		if (settings == null) {
			throw new NullPointerException("no user found!");
		} else {
			if (settings.password.equals(password)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean isRememberPassword(String usr_name) {
		return user_list.get(usr_name).is_remember;
	}

	public static void setRememberPassword(String usr_name, boolean is_remember) {
		Usr_settings settings = user_list.get(usr_name);

		if (settings == null) {
			throw new NullPointerException("no user found!");
		} else {
			settings.is_remember = is_remember;
		}
	}

	public static Set<String> getAllUsrList() {
		return user_list.keySet();
	}

}

class Usr_settings {
	String password;
	boolean is_remember;

	Usr_settings(String password) {
		this(password, false);
	}

	Usr_settings(String password, boolean is_remember) {
		this.password = password;
		this.is_remember = is_remember;
	}
}
