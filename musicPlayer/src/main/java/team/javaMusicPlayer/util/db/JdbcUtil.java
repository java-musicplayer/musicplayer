package team.javaMusicPlayer.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JdbcUtil {
	private static String URL;
	private static String DIRVER;
	private static ResourceBundle rb = ResourceBundle.getBundle("team.javaMusicplayer.util.db.db-config.properties");
	static {
		
		URL = rb.getString("jdbc.url");
		DIRVER = rb.getString("jdbc.driver");
		try {
			Class.forName(DIRVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能:获取一个数据库链接对象
	 * 
	 * 改进:没有使用数据库连接池，比较消耗资源
	 */
	public static Connection getConnect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * 功能:关闭与数据库相关的对象
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		
		
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
