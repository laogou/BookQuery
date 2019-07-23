/**
 * 
 */
package frame;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Book;
import service.BookManager;

/**
 * @author 张志远&郑拓
 *
 */
@SuppressWarnings("serial")
public class BookListFrame extends JFrame{

	static Object[][] object = null;
	static String[] st = { "编号","图书名", "作者", "出版社", "数量", "位置" };
	static JTable jTable = new JTable();
	
	/**
	 * BookListFrame 类构造函数
	 */
	public BookListFrame(){
		
		super("图书列表");
	}

	/**
	 * 创建窗体
	 * 
	 */
	public void createWindow(){
		
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();    //创建 Point 对象，用来表示 (x,y) 坐标空间中的位置的点
		final int windowWidth = 605;    //设置窗体的宽度
		final int windowHeight = 500;    //设置窗体的高度

		this.setBounds( center.x - windowWidth / 2, center.y - windowHeight / 2, 
				windowWidth, windowHeight );    //设置窗口的位置和大小
		
		ImageIcon img = new ImageIcon("img/背景2.jpg"); 
		
        JLabel bgLabel = new JLabel(img); 
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); 
        bgLabel.setBounds(0,0,img.getIconWidth(),img.getIconHeight()); 
        ((JPanel)getContentPane()).setOpaque(false); 
        
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );    //设置窗口的关闭方式
		this.setResizable( false );    //设置窗口的大小不可以改变
		this.setVisible( true );    //设置窗口是可见的
	}
	
	/**
	 * 事件处理方法
	 * 
	 */
	public void action(){
		
		final Container content = this.getContentPane();    //创建容器
		content.setLayout(null);    //关闭自动布局模式
		
		JLabel bookList = new JLabel("图书列表");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		bookList.setBounds(260, 10, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(bookList);    //将 JLabel 对象添加入容器
		
		JButton editButton = new JButton("编辑");    //创建 JButton 对象，实现按钮功能
		editButton.setBounds(20, 400, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		editButton.setIcon(new ImageIcon("img/editing.png"));    //为 JButton 对象添加图片
		
		
		content.add(editButton);    //将 JButton 对象添加入容器
		
		JButton insertButton = new JButton("插入");    //创建 JButton 对象，实现按钮功能
		insertButton.setBounds(140, 400, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		insertButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		
		content.add(insertButton);    //将 JButton 对象添加入容器
		
		JButton deleteButton = new JButton("删除");    //创建 JButton 对象，实现按钮功能
		deleteButton.setBounds(260, 400, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		deleteButton.setIcon(new ImageIcon("img/delete.png"));    //为 JButton 对象添加图片
		
		
		content.add(deleteButton);    //将 JButton 对象添加入容器
		
		JButton updateButton = new JButton("刷新");    //创建 JButton 对象，实现按钮功能
		updateButton.setBounds(380, 400, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		updateButton.setIcon(new ImageIcon("img/flush.png"));    //为 JButton 对象添加图片
		
		content.add(updateButton);    //将 JButton 对象添加入容器
		
		JButton exitButton = new JButton("类别");    //创建 JButton 对象，实现按钮功能
		exitButton.setBounds(500, 400, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		//exitButton.setIcon(new ImageIcon("img/exit.png"));    //为 JButton 对象添加图片
		content.add(exitButton);    //将 JButton 对象添加入容器
		
		object = insertBookToTable(object);
		jTable.setModel( new DefaultTableModel( object,st ) );    //将此表的数据模型设置为newModel,并添加此表的新数据源
		final JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(jTable);    //创建一个视口并设置其视图
		scroll.setBounds(10, 60, 585, 330);    //设置此视图的位置和大小
		content.add(scroll);    //添加到Container容器中
		
		//editButton 按钮事件处理
		editButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						Book book = new Book();
						Object[][] ob = insertBookToTable( object );
						
						//判断是否选择了数据列表的一行，jTable.getSelectedRow() 获得列表中所选择的一行的行号
						if( jTable.getSelectedRow() == -1 ){
							
							JOptionPane.showMessageDialog(null, "请您选择要编辑的行！", 
									"Warning Dialog", JOptionPane.WARNING_MESSAGE);
						}else{
							
							//对 Book 对象进行初始化
							book.setId(Integer.parseInt( ob[jTable.getSelectedRow()][0].toString()) );
							book.setName( ob[jTable.getSelectedRow()][1].toString() );
							book.setAuthor( ob[jTable.getSelectedRow()][2].toString() );
							book.setPublisher( ob[jTable.getSelectedRow()][3].toString() );
							book.setAmount( Integer.parseInt(ob[jTable.getSelectedRow()][4].toString()) );
							book.setPosition( ob[jTable.getSelectedRow()][5].toString() );
							book.setTypeId( Integer.parseInt(ob[jTable.getSelectedRow()][6].toString()) );
							
							//创建 BookEditFrame 对象，并进行事件处理
							BookEditFrame bookEditFrame = new BookEditFrame( book );
							
							bookEditFrame.action();
							bookEditFrame.createWindow();
						}
					}
				});
		
		//updateButton 按钮事件处理
		updateButton.addActionListener(
				new ActionListener(){
					public void actionPerformed( ActionEvent e ){
						
						Object[][] ob = insertBookToTable( object );
						jTable.setModel( new DefaultTableModel( ob,st ) );
					}
				});
		
		//insertButton 按钮事件处理
		insertButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						//创建 BookInsertFrame 对象，并进行事件处理
						BookInsertFrame bookInsertFrame = new BookInsertFrame();
						
						bookInsertFrame.action();
						bookInsertFrame.createWindow();
					}
				});
		
		//deleteButton 按钮事件处理
		deleteButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						BookManager bookManager = new BookManager();    //创建 BookManager 对象
						
						//判断是否选择了数据列表的一行，jTable.getSelectedRow() 获得列表中所选择的一行的行号
						if( jTable.getSelectedRow() == -1 ){
							
							JOptionPane.showMessageDialog(null, "请您选择要删除的行！", 
									"Warning Dialog", JOptionPane.WARNING_MESSAGE);
						}else{
							//判断是否要真正删除选择的数据信息
							if(JOptionPane.showConfirmDialog(null, "确认要删除吗？", "Warning Dialog", JOptionPane.YES_NO_OPTION) == 0){
								//判断是否成功删除所选择的信息
								if( bookManager.delete(Integer.parseInt(insertBookToTable(object)[jTable.getSelectedRow()][0].toString())) ){
									
									Object[][] ob = insertBookToTable( object );
									jTable.setModel( new DefaultTableModel( ob,st ) );
						
									JOptionPane.showMessageDialog( null, "删除成功", 
											"Delete Prompt Dialog", JOptionPane.INFORMATION_MESSAGE );
								}else{
									JOptionPane.showMessageDialog( null, "删除失败", 
											"Delete Prompt Dialog", JOptionPane.WARNING_MESSAGE );
								}
							}else{
								
							}
						}
						
					}
				});
		
		//exitButton 按钮事件处理
		exitButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						TypeFrame typeFrame = new TypeFrame();
						typeFrame.action();
						typeFrame.createWindow();
					}
				});
	}
	
	/**
	 * 将图书信息添加到数据列表中
	 * @param object 存放图书信息的二维数组
	 * @return 存放图书信息的二维数组
	 * 
	 */
	public static Object[][] insertBookToTable( Object[][] object ){
		
		BookManager bookManager = new BookManager();    //创建 BookManager 对象
		Collection<Book> list = bookManager.findAll();    //创建 List 对象，通过调用BookManager 类中的 findAll() 为其初始化 
		object = new Object[list.size()][7];    //为二维数组分配内存
		Book book = new Book();    
		Iterator<Book> iterator = list.iterator();    //通过 List 创建迭代器对象
		
		//通过迭代器的限制，将 List 集合中的信息添加到二维数组中
		for(int i = 0; i < list.size(); i++){
			
				book = iterator.next();
				
				object[i][0] = book.getId();
				object[i][1] = book.getName();
				object[i][2] = book.getAuthor();
				object[i][3] = book.getPublisher();
				object[i][4] = book.getAmount();
				object[i][5] = book.getPosition();
				object[i][6] = book.getTypeId();
		}
		
		return object;    //返回存放物料信息的二维数组
	}
}