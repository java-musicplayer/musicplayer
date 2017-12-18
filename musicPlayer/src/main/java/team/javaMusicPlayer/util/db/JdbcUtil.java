package team.javaMusicPlayer.util.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JdbcUtil {
	private static String URL;
	private static String DIRVER;
	private static ResourceBundle rb = ResourceBundle.getBundle("team.javaMusicPlayer.util.db.db-config");
	static {
		
		URL = rb.getString("jdbc.url");
		DIRVER = rb.getString("jdbc.driver");
		Connection conn=null;
		PreparedStatement preState=null;
		try {
			Class.forName(DIRVER);
			//检测数据库是否有表
			conn=getConnect();
			String checkTables="select * from music";
			preState=conn.prepareStatement(checkTables);
			preState.executeQuery();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			//没有表
			createTables(conn);
			//e.printStackTrace();
		}
		finally {
			close(null, preState, conn);
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
	
	private static void createTables(Connection conn) {
		BufferedReader bReader=null;
		PreparedStatement preState=null;
			System.out.println("无表!");
			//创建表
			StringBuffer result = new StringBuffer();
			try {
				bReader = new BufferedReader(new FileReader(
						new File("src/main/java/team/javaMusicPlayer/util/db/init.sql")));
				String line = "";
				while ((line = bReader.readLine()) != null)
					result.append(line);
				preState=conn.prepareStatement(result.toString());
				preState.executeUpdate();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(preState!=null)
					try {
						preState.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(bReader!=null)
					try {
						bReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		
	}
	
}
