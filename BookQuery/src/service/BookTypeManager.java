/**
 * 
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;

/**
 * @author 张志远&郑拓
 *
 */
public class BookTypeManager {

	public List<Book> findByBookType( String bookType ){
		
		String sql = "select * from bookquery where typeId=( select id from booktype where type=? )";
		
		Connection conn = DBConnection.getConnection();
		Book book = null;
		
		try{
			PreparedStatement ps = conn.prepareStatement( sql );
			ps.setString(1, bookType);
			ResultSet rs = ps.executeQuery();
			List<Book> list = new ArrayList<Book>();
						
			while( rs.next() ){
				book = new Book();
				
				book.setId( rs.getInt("id") );
				book.setName( rs.getString("name") );
				book.setAuthor( rs.getString("author") );
				book.setPublisher( rs.getString("publisher") );
				book.setAmount( rs.getInt("amount") );
				book.setPosition( rs.getString("position") );
				
				list.add( book );
			}
			
			return list;
		}catch( SQLException e ){
			System.out.println( "findByBookType() has a exception!" );
			e.printStackTrace( System.err );    //打印异常
			return null;
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if(!conn.isClosed()){
					conn.close();    //关闭数据库连接
				}
			}catch(Exception e){
				System.out.println( "findByBookType() has a exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
}
