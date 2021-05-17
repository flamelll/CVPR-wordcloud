package com.lunwen.Dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lunwen.bean.data;
import com.lunwen.bean.lunwen;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.lunwen.DBUtil.DBUtil;
import com.lunwen.bean.data;;

public class ShowDao {
	public List<lunwen> select(){
		Connection conn = DBUtil.getConn(); //�������ݿ�
	    List<lunwen> list = new ArrayList<lunwen>();
	    try {
	        String sql="select * from lunwen";
	        Statement pstmt = (Statement) conn.createStatement();
	        ResultSet rs = (ResultSet) pstmt.executeQuery(sql);
	        while(rs.next()) {
	        	lunwen lunwen=new lunwen();
	            lunwen.setTitle(rs.getString("title"));//����title�ĳ�abstract����ʵ��  ժҪ   �ȴ���ȡ
	            list.add(lunwen);
	        }
	        System.out.println("ShowDao Success!!");
	        rs.close();
	        pstmt.close();
	        conn.close();

	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public List<data> select1(String name){
		Connection conn = DBUtil.getConn(); //�������ݿ�
	    List<data> list = new ArrayList<data>();
	    try {
	        String sql="select * from lunwen where title like '%"+name+"%'";//���������һ����ժҪ��
	        Statement pstmt = (Statement) conn.createStatement();
	        ResultSet rs = (ResultSet) pstmt.executeQuery(sql);
	        while(rs.next()) {
	        	data data=new data();
	        	data.setTitle(rs.getString("title"));
	        	data.setLink(rs.getString("link"));
	            System.out.println(data.getTitle()+":"+data.getLink());
	            list.add(data);
	        }
	        System.out.println("ShowDao select1 Success!!");
	        rs.close();
	        pstmt.close();
	        conn.close();

	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}