/**
 * 
 */
package frame;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import model.Book;
import service.BookManager;
import service.BookTypeManager;
import service.DBConnection;
import service.TypeManager;
import java.sql.Connection;
/**
 * @author 张志远&郑拓
 *
 */
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@SuppressWarnings("serial")
public class BookQueryFrame extends JFrame{

	static Object[][] object = null;
	static String[] st = { "图书名", "作者", "出版社", "数量", "位置" }; //用于标头
	
	static JTable jTable = new JTable();  //创建下拉式菜单按钮
	private JComboBox typeJComboBox;
	//private String names[] = { "","计算机类","文学类","语言类","心理类" };
	
	TypeManager typeman=new TypeManager();
	
	
	private String[] names=typeman.findType().toArray(new String[typeman.findType().size()]);

	
	private JLabel nameLabel;  //右侧设计四个标签 ：类别 、图书名 、作者 、出版社
	private JLabel stylLabel;
	private JLabel authorLabel;
	private JLabel publisherLabel;
	
	private static JTextField nameField;  //其中类别是用下拉式菜单按钮可以完成
	private static JTextField authorField; //剩余三个是用键盘来输入进行的
	private static JTextField publisherField;
	
	private static List<Book> list;  //定义书的list集合
	private JButton selectButton;  //借阅者下边的查询按钮
	private JButton exitButton;    //借阅者下边的退出按钮
	
	private JScrollPane scroll;   //

	/**
	 * MaterialListFrame 类构造函数
	 * 
	 */
	public BookQueryFrame(){
		
		super("图书查询");  //窗口左上名字
	}

	/**
	 * 创建窗体
	 * 
	 */
	public void createWindow(){
		//找到窗口位置
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
		
		stylLabel = new JLabel("类别:");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		stylLabel.setBounds(470, 90, 50, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(stylLabel);    //将 JLabel 对象添加入容器
		
		typeJComboBox = new JComboBox( names );
		typeJComboBox.setMaximumRowCount( 4 );
		typeJComboBox.setBounds(510, 100, 60, 20);
		content.add( typeJComboBox );
		
		nameLabel = new JLabel("图书名:");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		nameLabel.setBounds(470, 130, 50, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(nameLabel);    //将 JLabel 对象添加入容器
				
		nameField = new JTextField(20);
		nameField.setBounds(510, 140, 60, 20);
		content.add(nameField);
		
		authorLabel = new JLabel("作者:");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		authorLabel.setBounds(470, 180, 40, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(authorLabel);    //将 JLabel 对象添加入容器
		
		authorField = new JTextField(20);
		authorField.setBounds(510, 190, 60, 20);
		content.add(authorField);
		
		publisherLabel = new JLabel("出版社:");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		publisherLabel.setBounds(470, 230, 50, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(publisherLabel);    //将 JLabel 对象添加入容器
		
		publisherField = new JTextField(20);
		publisherField.setBounds(510, 240, 80, 20);
		content.add(publisherField);
		
		selectButton = new JButton("查询");    //创建 JButton 对象，实现按钮功能
		selectButton.setBounds(490, 270, 100, 40);    //设置 JButton 对象在窗口中的位置和大小
		selectButton.setIcon(new ImageIcon("img/search.png"));    //为 JButton 对象添加图片
		
		content.add(selectButton);    //将 JButton 对象添加入容器
		
		exitButton = new JButton("退出");    //创建 JButton 对象，实现按钮功能
		exitButton.setBounds(490, 330, 100, 40);    //设置 JButton 对象在窗口中的位置和大小
		exitButton.setIcon(new ImageIcon("img/exit.png"));    //为 JButton 对象添加图片
		
		content.add(exitButton);    //将 JButton 对象添加入容器
		
		object = insertBookToTable(object);
		jTable.setModel( new DefaultTableModel( object,st ) );    //将此表的数据模型设置为newModel,并添加此表的新数据源
		scroll = new JScrollPane();
		scroll.setViewportView(jTable);    //创建一个视口并设置其视图
		scroll.setBounds(23, 25, 445, 415);    //设置此视图的位置和大小
		content.add(scroll);    //添加到Container容器中
		
		typeJComboBox.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if ( event.getStateChange() == ItemEvent.SELECTED ){
							BookTypeManager bookTypeManager = new BookTypeManager();
							list = bookTypeManager.findByBookType( names[typeJComboBox.getSelectedIndex()]);
							
							object = new Object[list.size()][5];    //为二维数组分配内存
							Book book = new Book();    
							Iterator<Book> iterator = list.iterator();    //通过 List 创建迭代器对象
							
							//通过迭代器的限制，将 List 集合中的信息添加到二维数组中
							for(int i = 0; i < list.size(); i++){
								
									book = iterator.next();
									
									object[i][0] = book.getName();
									object[i][1] = book.getAuthor();
									object[i][2] = book.getPublisher();
									object[i][3] = book.getAmount();
									object[i][4] = book.getPosition();
							}
							
							if( list.size() == 0 ){
								
								jTable.setModel( new DefaultTableModel( object,st ) );
								
								JOptionPane.showMessageDialog(null, "没有查询到你要找的图书信息！", 
										"查询提示", JOptionPane.INFORMATION_MESSAGE);
							}else{
								jTable.setModel( new DefaultTableModel( object,st ) );
							}	
						}
						
					}
				});
		
		//selectButton 按钮事件处理
		selectButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						Object[][] ob = insertBookToTable( object );    //通过 insertMaterialToTable() 方法为二维数组 ob 进行初始化
						
						//判断是否检索到了指定时间段的数据信息
						if( list.size() == 0 ){
							
							jTable.setModel( new DefaultTableModel( ob,st ) );
							
							JOptionPane.showMessageDialog(null, "没有查询到你要找的图书信息！", 
									"查询提示", JOptionPane.INFORMATION_MESSAGE);
						}else{
							jTable.setModel( new DefaultTableModel( ob,st ) );
						}	
					}
				});
		
		//exitButton 按钮事件处理
		exitButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();    //关闭此窗口
					}
				});
	}
	
	public static Object[][] insertBookToTable( Object[][] object ){
		
		BookManager bookManager = new BookManager();    //创建 BookManager 对象
		
		list = bookManager.findByBook( nameField.getText(),authorField.getText(),publisherField.getText() );
		
		object = new Object[list.size()][5];    //为二维数组分配内存
		Book book = new Book();    
		Iterator<Book> iterator = list.iterator();    //通过 List 创建迭代器对象
		
		//通过迭代器的限制，将 List 集合中的信息添加到二维数组中
		for(int i = 0; i < list.size(); i++){
			
				book = iterator.next();
				
				object[i][0] = book.getName();
				object[i][1] = book.getAuthor();
				object[i][2] = book.getPublisher();
				object[i][3] = book.getAmount();
				object[i][4] = book.getPosition();
		}
		
		return object;    //返回存放物料信息的二维数组
	}
}
