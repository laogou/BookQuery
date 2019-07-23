package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.*;

public class TypeManager {

public List<Types> findAll(){
		
		String sql = "select * from booktype";    //检索数据库中所有数据的 SQL 语句
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
			
			Types type = null;
			List<Types> list = new ArrayList<Types>();    //创建 List 集合对象
			
			while( rs.next() ){
				type = new Types();    //创建 Book 对象
				
				//为 Book 对象初始化
				type.setId( rs.getInt("id") );
				type.setName( rs.getString("type") );
				list.add(type);
			}
			
			return list;    //返回 List 集合
		}catch( SQLException e ){
			System.out.println( "findAll() has exception!" );
			e.printStackTrace( System.err );    //打印异常
			
			return null;
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if( !conn.isClosed() ){
					conn.close();    //关闭数据库连接
				}
			}catch( Exception e ){
				System.out.println( "findAll() has exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}		
	}

public boolean update( Types type ){
	
	//更新数据库信息的 SQL 语句
	String sql = "update booktype set type = ? where id = ?";
	Connection conn = DBConnection.getConnection();    //创建数据库连接
	
	try{
		PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
		
		//将 Book 对象添加到 PreparedStatement 对象中
		pst.setString( 1,type.getName() );
		pst.setInt( 2,type.getId() );
		
		@SuppressWarnings("unused")
		int count = pst.executeUpdate();    //执行 SQL 语句
//		return count;
		return true;
	}catch( SQLException e ){
		System.out.println( "update() has exception!" );
		e.printStackTrace( System.err );    //打印异常
//		return -1;
		return false;
	}finally{
		try{
			//判断数据库连接是否已经关闭
			if( !conn.isClosed() ){
				conn.close();    //关闭数据库连接
			}
		}catch( Exception e ){
			System.out.println( "update() has exception!" );
			e.printStackTrace( System.err );     //打印异常
		}
	}
}

public boolean delete( int id ){
	
	String sql = "delete from booktype where id = ?" ;    //删除数据库中数据的 SQL 语句
	Connection conn = DBConnection.getConnection();    //创建数据库连接
	
	try{
		PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
	
		@SuppressWarnings({ "unused", "unused" })
		
		int count = 0;
		
		pst.setInt( 1, id) ;
		count = pst.executeUpdate();    //执行 SQL 语句
		
//		return count;
		return true;
	}catch( SQLException e ){
		System.out.println( "delete() has exception!" );
		e.printStackTrace( System.err );    //打印异常
//		return -1;
		return false;
	}finally{
		try{
			//判断数据库连接是否已经关闭
			if( !conn.isClosed() ){
				conn.close();    //关闭数据库连接
			}
		}catch( Exception e ){
			System.out.println( "delete() has exception!" );
			e.printStackTrace( System.err );    //打印异常
		}
	}
}

public boolean add( Types types ){
		
		//向数据库插入数据的 SQL 语句
		String sql = "insert into booktype(type) values(?)";
		Connection conn = DBConnection.getConnection();    //创建数据库连接
	
		
	
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			
			//将 Book 对象添加到 PreparedStatement 对象中
			pst.setString( 1,types.getName() );
			
			
			System.out.println(sql);
			
			@SuppressWarnings("unused")
			int count = pst.executeUpdate();    //执行 SQL 语句
//			return count;
			return true;
		}catch( SQLException e ){
			System.out.println( "add() has a exception!" );
			e.printStackTrace( System.err );    //打印异常
//			return -1;
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
	
public List<String> findType(){
	
	String sql = "select type from booktype";    //检索数据库中所有数据的 SQL 语句
	Connection conn = DBConnection.getConnection();    //创建数据库连接
	
	try{
		PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
		ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
		
		
		List<String> list = new ArrayList<String>();    //创建 List 集合对象
		
		while( rs.next() ){
			
			//为 Book 对象初始化
			String strr=rs.getString("type");
			list.add(strr);
		}
		
		return list;    //返回 List 集合
	}catch( SQLException e ){
		System.out.println( "findAll() has exception!" );
		e.printStackTrace( System.err );    //打印异常
		
		return null;
	}finally{
		try{
			//判断数据库连接是否已经关闭
			if( !conn.isClosed() ){
				conn.close();    //关闭数据库连接
			}
		}catch( Exception e ){
			System.out.println( "findAll() has exception!" );
			e.printStackTrace( System.err );    //打印异常
		}
	}		
}



}
