/**
 * 
 */
package service;

import java.sql.*;
import java.sql.DriverManager;

/**
 * @author 张志远&郑拓
 *
 */
public class DBConnection {
	
	/**
	 * 创建数据库连接
	 * @param Connection 要创建的数据库连接
	 * @return 若数据库创建成功，则返回数据库连接，否则返回null
	 * 
	 */
	public static Connection getConnection(){
		
		String url = "jdbc:mysql://XXX.XXX.XXX.XXX:3306/book?useUnicode=true&characterEncoding=utf-8&useSSL=false";    //
		String username = "user03";    //设置数据库用户名
		String password = "XXXXXX";    //设置数据库密码
		
		try{
			Class.forName( "com.mysql.jdbc.Driver" );    //添加 mysql 驱动
			Connection conn = DriverManager.getConnection( url,username,password );    //数据库 URL 的连接
			
			return conn;    //返回数据库连接
		}catch( Exception e ){
			System.out.println("getConnection() has a exception!");
			e.printStackTrace();    //打印异常
			
			return null;
		}
	}
}
