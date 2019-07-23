/**
 * 
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Book;

/**
 * @author 张志远&郑拓
 *
 */
public class BookManager {

	/**
	 * 创建数据库表格
	 * 
	 */
	public void createTable(){
		
		//创建表格 SQL 语句
		String sql = "create table bookquery(" +
					"id int not null auto_increment," +    //设置 id 为自增长
					"name varchar(255) not null," +
					"author varchar(255) default null," +
					"pulisher varchar(255) default null," +
					"amount integer default null," +
					"position varchar(255) default null, " +
					"typeId int(11) not null " +
					"primary key (id)," +    //设置 id 为主键
					"unique key id (id)," +    //设置 id 唯一
					"KEY `typeId` (`typeId`),CONSTRAINT `bookquery_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `booktype` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
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
	 * 增加图书信息
	 * @param book 要增加的图书对象
	 * @return 图书添加到数据库后，返回 true；否则，返回 false
	 * 
	 */
	public boolean add( Book book ){
		
		//向数据库插入数据的 SQL 语句
		String sql = "insert into bookquery(name,author,publisher,amount,position,typeId)" +
				" values(?,?,?,?,?,?)";
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			
			//将 Book 对象添加到 PreparedStatement 对象中
			pst.setString( 1,book.getName() );
			pst.setString( 2,book.getAuthor() );
			pst.setString( 3,book.getPublisher() );
			pst.setInt( 4,book.getAmount() );
			pst.setString( 5,book.getPosition() );
			pst.setInt( 6,book.getTypeId() );
			
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
	
	/**
	 * 根据主键删除图书信息
	 * @param id 要删除的图书主键
	 * @return 删除数据库中数据后，返回 true；否则，返回 false
	 */
	public boolean delete( int id ){
		
		String sql = "delete from bookquery where id = ?" ;    //删除数据库中数据的 SQL 语句
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
		
			@SuppressWarnings({ "unused", "unused" })
			
			int count = 0;
			
			pst.setInt( 1, id) ;
			count = pst.executeUpdate();    //执行 SQL 语句
			
//			return count;
			return true;
		}catch( SQLException e ){
			System.out.println( "delete() has exception!" );
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
				System.out.println( "delete() has exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
	
	/** 
	 * 更新图书信息
	 * @param book 要更新的图书
	 * @return 更新数据库数据，成功返回 true；否则，返回 false
	 * 
	 */
	public boolean update( Book book ){
		
		//更新数据库信息的 SQL 语句
		String sql = "update bookquery set name = ?," +
				"author = ?,publisher = ?,amount = ?," +
				"position = ?,typeId = ? where id = ?";
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			
			//将 Book 对象添加到 PreparedStatement 对象中
			pst.setString( 1,book.getName() );
			pst.setString( 2,book.getAuthor() );
			pst.setString( 3,book.getPublisher() );
			pst.setInt( 4,book.getAmount() );
			pst.setString( 5,book.getPosition() );
			pst.setInt( 6,book.getTypeId() );
			pst.setInt( 7,book.getId() );
			
			@SuppressWarnings("unused")
			int count = pst.executeUpdate();    //执行 SQL 语句
//			return count;
			return true;
		}catch( SQLException e ){
			System.out.println( "update() has exception!" );
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
				System.out.println( "update() has exception!" );
				e.printStackTrace( System.err );     //打印异常
			}
		}
	}
	
	/**
	 * 检索全部图书的信息
	 * @return List 集合
	 *
	 */
	public List<Book> findAll(){
		
		String sql = "select * from bookquery";    //检索数据库中所有数据的 SQL 语句
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
			
			Book book = null;
			List<Book> list = new ArrayList<Book>();    //创建 List 集合对象
			
			while( rs.next() ){
				book = new Book();    //创建 Book 对象
				
				//为 Book 对象初始化
				book.setId( rs.getInt("id") );
				book.setName( rs.getString("name") );
				book.setAuthor( rs.getString("author") );
				book.setPublisher( rs.getString("publisher") );
				book.setAmount( rs.getInt("amount") );
				book.setPosition( rs.getString("position") );
				book.setTypeId( rs.getInt("typeId") );
				
				list.add( book );
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
	
	/**
	 * 通过图书名检索图书信息
	 * @param name
	 * @return
	 */
	public List<Book> findByName( String name ){
		
		String sql = "select * from bookquery where name=?";    //检索数据库中数据的 SQL 语句
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			pst.setString( 1, name );
			ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
			
			Book book = null;    //创建 Book 对象
			List<Book> list = new ArrayList<Book>();    //创建 List 集合对象
			
			while( rs.next() ){
				book = new Book();    //创建 Book 对象
				
				//为 Book 对象初始化
				book.setId( rs.getInt("id") );
				book.setName( rs.getString("name") );
				book.setAuthor( rs.getString("author") );
				book.setPublisher( rs.getString("publisher") );
				book.setAmount( rs.getInt("amount") );
				book.setPosition( rs.getString("position") );
				book.setTypeId( rs.getInt("typeId") );
				
				list.add( book );
			}
			
			return list;
		}catch( SQLException e ){
			System.out.println( "findByName() has exception!" );
			e.printStackTrace( System.err );    //打印异常
			
			return null;
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if( !conn.isClosed() ){
					conn.close();    //关闭数据库连接
				}
			}catch( Exception e ){
				System.out.println( "findByName() has exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
	
	/**
	 * 通过作者检索图书信息
	 * @param author
	 * @return
	 */
	public List<Book> findByAuthor( String author ){
		
		String sql = "select * from bookquery where author=?";    //检索数据库中数据的 SQL 语句
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			pst.setString( 1, author );
			ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
			
			Book book = null;    //创建 Book 对象
			List<Book> list = new ArrayList<Book>();    //创建 List 集合对象
			
			while( rs.next() ){
				book = new Book();    //创建 Book 对象
				
				//为 Book 对象初始化
				book.setId( rs.getInt("id") );
				book.setName( rs.getString("name") );
				book.setAuthor( rs.getString("author") );
				book.setPublisher( rs.getString("publisher") );
				book.setAmount( rs.getInt("amount") );
				book.setPosition( rs.getString("position") );
				book.setTypeId( rs.getInt("typeId") );
				
				list.add( book );
			}
			
			return list;    //返回 Material 对象
		}catch( SQLException e ){
			System.out.println( "findByAuthor() has exception!" );
			e.printStackTrace( System.err );    //打印异常
			
			return null;
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if( !conn.isClosed() ){
					conn.close();    //关闭数据库连接
				}
			}catch( Exception e ){
				System.out.println( "findByAuthor() has exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
	
	/**
	 * 通过出版社检索图书信息
	 * @param publisher
	 * @return
	 */
	public List<Book> findByPulisher( String publisher ){
		
		String sql = "select * from bookquery where publisher=?";    //检索数据库中数据的 SQL 语句
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			pst.setString( 1, publisher );
			ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
			
			Book book = null;    //创建 Book 对象
			List<Book> list = new ArrayList<Book>();    //创建 List 集合对象
			
			while( rs.next() ){
				book = new Book();    //创建 Book 对象
				
				//为 Book 对象初始化
				book.setId( rs.getInt("id") );
				book.setName( rs.getString("name") );
				book.setAuthor( rs.getString("author") );
				book.setPublisher( rs.getString("publisher") );
				book.setAmount( rs.getInt("amount") );
				book.setPosition( rs.getString("position") );
				book.setTypeId( rs.getInt("typeId") );
				
				list.add( book );
			}
			
			return list;    //返回 Material 对象
		}catch( SQLException e ){
			System.out.println( "findByPulisher() has exception!" );
			e.printStackTrace( System.err );    //打印异常
			
			return null;
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if( !conn.isClosed() ){
					conn.close();    //关闭数据库连接
				}
			}catch( Exception e ){
				System.out.println( "findByPulisher() has exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
	
	/**
	 * 精确查找
	 * @param name
	 * @param author
	 * @param publisher
	 * @return
	 */
	public List<Book> findByBook( String name,String author,String publisher ){
		
		String sql = "select * from bookquery where 1=1 ";
		
		if( name != null && name.trim().length() != 0 ){
			sql = sql + "and name like '%" + name + "%' ";
		}
		
		if( author != null && author.trim().length() != 0 ){
			sql = sql + "and author like '%" + author + "%' ";
		}
		
		if( publisher != null && publisher.trim().length() != 0 ){
			sql = sql + "and publisher like '%" + publisher + "%'";
		}

		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			ResultSet rs = pst.executeQuery();    //创建结果集 ResultSet 对象，并执行 SQL 语句
			
			Book book = null;    //创建 Book 对象
			List<Book> list = new ArrayList<Book>();    //创建 List 集合对象
			
			while( rs.next() ){
				book = new Book();    //创建 Book 对象
				
				//为 Book 对象初始化
				book.setId( rs.getInt("id") );
				book.setName( rs.getString("name") );
				book.setAuthor( rs.getString("author") );
				book.setPublisher( rs.getString("publisher") );
				book.setAmount( rs.getInt("amount") );
				book.setPosition( rs.getString("position") );
				book.setTypeId( rs.getInt("typeId") );
				
				list.add( book );
			}
			
			return list;    //返回 Material 对象
		}catch( SQLException e ){
			System.out.println( "findByBook() has exception!" );
			e.printStackTrace( System.err );    //打印异常
			
			return null;
		}finally{
			try{
				//判断数据库连接是否已经关闭
				if( !conn.isClosed() ){
					conn.close();    //关闭数据库连接
				}
			}catch( Exception e ){
				System.out.println( "findByBook() has exception!" );
				e.printStackTrace( System.err );    //打印异常
			}
		}
	}
	
	
	
	//借阅函数
public boolean borrow( String str ,String number,String username,String author){
		
		//更新数据库信息的 SQL 语句
		String sql[] = {"update bookquery set amount=amount-? where name=? and author=?",
						"insert into borrow(userName,borrowBook,number,author) values(?,?,?,?)"};
		Connection conn = DBConnection.getConnection();    //创建数据库连接
		
		try{
			PreparedStatement pst = conn.prepareStatement( sql[0] );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
			
			//将 要找的Book对象添加到 PreparedStatement 对象中
			pst.setString(1,number);
			pst.setString(2,str);
			pst.setString(3,author);
			//要在这里添加一个向数据库中两个表修改信息的sql语句，根据string为键
			@SuppressWarnings("unused")
			int count = pst.executeUpdate();    //执行 SQL 语句
//			return count;
			
			PreparedStatement pst1 = conn.prepareStatement( sql[1] );
			pst1.setString(1,username);
			pst1.setString( 2,str);
			pst1.setString( 3,number);
			pst1.setString( 4,author);
			
			@SuppressWarnings("unused")
			int count1 = pst1.executeUpdate();    //执行 SQL 语句
			
			return true;
		}catch( SQLException e ){
			System.out.println( "borrow() has exception!" );
			e.printStackTrace( System.err );    //打印异常
//			return -1;
			return false;
		}
	}
	

public boolean back( String str ,String number,String username,String author){
	
	//更新数据库信息的 SQL 语句
	String sql[] = {"delete from borrow where userName=? and borrowBook=? and number=? and author=?",
					"update bookquery set amount=amount+? where name=? and author=?"};
	Connection conn = DBConnection.getConnection();    //创建数据库连接
	
	try{
		PreparedStatement pst = conn.prepareStatement( sql[0] );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
		
		//将 要找的Book对象添加到 PreparedStatement 对象中
		pst.setString(1,username);
		pst.setString(2,str);
		pst.setString(3,number);
		pst.setString(4,author);
		//要在这里添加一个向数据库中两个表修改信息的sql语句，根据string为键

		int count = pst.executeUpdate();    //执行 SQL 语句
	//	return count;
		
		if(count==0) {
			return false;
		}

		PreparedStatement pst1 = conn.prepareStatement( sql[1] );    //创建 PreparedStatement 对象，表示预编译的 SQL 语句的对象
		
		//将 要找的Book对象添加到 PreparedStatement 对象中
		pst1.setString(1,number);
		pst1.setString(2,str);
		pst1.setString(3,author);
		
		//要在这里添加一个向数据库中两个表修改信息的sql语句，根据string为键
		@SuppressWarnings("unused")
		int count1 = pst1.executeUpdate();    //执行 SQL 语句
		
		return true;
	}catch( SQLException e ){
		System.out.println( "back() has exception!" );
		e.printStackTrace( System.err );    //打印异常
//		return -1;
		return false;
	}
}
	
}