
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;
import model.Book;
import model.User;

/**
 * @author 张志远&郑拓
 *
 */
public class UserManager {

	/**
	 * 创建数据库表格
	 */
	public void createTable(){
		
		//创建表格 SQL 语句
		String sql = "create table user(" +
					"id int not null auto_increment," +    //设置 id 为自增长
					"userName varchar(255) not null," +
					"userPassword varchar(255) not null," +
					"primary key (id)," +    //设置 id 为主键
					"unique key id (id)," +    //设置 id 唯一
					"unique key userName (userName)" +    //设置 userName 唯一
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
	 * 向数据库中增加信息
	 */
	public boolean creatByUserName( User user){
		
		
			//向数据库插入数据的 SQL 语句
			String sql = "insert into user(userName,userPassword)"+"values(?,?)";

			Connection conn = DBConnection.getConnection();    //创建数据库连接
			
			try{
				
				PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
				//将 Book 对象添加到 PreparedStatement 对象中
				pst.setString( 1,user.getUserName());
				pst.setString( 2,user.getUserPassword());
				
				int count = pst.executeUpdate();    //执行 SQL 语句
//				return count;
				
				
				
				return true;
			}catch( SQLException e ){
				System.out.println( "add() has a exception!" );
				e.printStackTrace( System.err );    //打印异常
//				return -1;
				return false;
			}finally{
				try{
					//判断数据库连接是否已经关闭
					if( !conn.isClosed() ){
						conn.close();    //关闭数据库连接
					}
				}catch( Exception e ){
					System.out.println( "add() has a exception!" );
					e.printStackTrace( System.err );    //打印异常
				}
			}
		
	}

	/**
	 * 根据管理员用户名检索其详细信息
	 * @param adminName
	 * @return
	 */
	public User findByUserName( String userName ){
		
		String sql = "select * from user where userName=?";
		
		Connection conn = DBConnection.getConnection();
		User user = null;
		
		try{
			PreparedStatement ps = conn.prepareStatement( sql );
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
						
			if( rs.next() ){
				user = new User();
				
				user.setId( rs.getInt("id") );
				user.setUserName( rs.getString("userName") );
				user.setUserPassword( rs.getString("userPassword") );
			}
		}catch( SQLException e ){
			System.out.println( "findByUserName() has a exception!" );
			e.printStackTrace( System.err );    //打印异常
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if(!conn.isClosed()){
					conn.close();    //关闭数据库连接
				}
			}catch(Exception e){
				System.out.println( "findByUserName() has a exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
		
		return user;
	}
}