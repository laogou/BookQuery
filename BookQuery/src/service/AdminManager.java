/**
 * 
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;

/**
 * @author 张志远&郑拓
 *
 */
public class AdminManager {

	/**
	 * 创建数据库表格
	 */
	public void createTable(){
		
		//创建表格 SQL 语句
		String sql = "create table admin(" +
					"id int not null auto_increment," +    //设置 id 为自增长
					"adminName varchar(255) not null," +
					"adminPassword varchar(255) not null," +
					"primary key (id)," +    //设置 id 为主键
					"unique key id (id)," +    //设置 id 唯一
					"unique key adminName (adminName)" +    //设置 adminName 唯一
					") ENGINE=InnoDB DEFAULT CHARSET=utf8"; 
		
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			Statement stat = conn.createStatement();    //创建语句对象
			stat.execute( sql );    //执行 SQL 语句
		}catch( SQLException e ){
			System.out.println( "createTable() has a exception!" );
			e.printStackTrace( System.err );    //打印异常
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if(!conn.isClosed()){
					conn.close();    //关闭数据库连接
				}
			}catch(Exception e){
				System.out.println( "createTable() has a exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
	
	/**
	 * 根据管理员用户名检索其详细信息
	 * @param adminName
	 * @return
	 */
	public Admin findByAdminName( String adminName ){
		
		String sql = "select * from admin where adminName=?";
		
		Connection conn = DBConnection.getConnection();
		Admin admin = null;
		
		try{
			PreparedStatement ps = conn.prepareStatement( sql );
			ps.setString(1, adminName);
			ResultSet rs = ps.executeQuery();
						
			if( rs.next() ){
				admin = new Admin();
				
				admin.setId( rs.getInt("id") );
				admin.setAdminName( rs.getString("adminName") );
				admin.setAdminPassword( rs.getString("adminPassword") );
			}
		}catch( SQLException e ){
			System.out.println( "findByAdminName() has a exception!" );
			e.printStackTrace( System.err );    //打印异常
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if(!conn.isClosed()){
					conn.close();    //关闭数据库连接
				}
			}catch(Exception e){
				System.out.println( "findByAdminName() has a exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
		
		return admin;
	}
}