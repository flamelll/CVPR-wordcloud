package com.lunwen.DBUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static String db_url = "jdbc:mysql://localhost:3306/lunwen?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
	public static String db_user = "root";
	public static String db_pass = "123456";
	
	public static Connection getConn () {
		Connection conn = null;
		
		
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection(db_url, db_user, db_pass);
			System.out.println("���ӳɹ�!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void main(String[] args) {
		getConn();
	}
	
	/**
	 * @param state
	 * @param conn
	 */
	public static void close (Statement state, Connection conn) {
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close (ResultSet rs, Statement state, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}