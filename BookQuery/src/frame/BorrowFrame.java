package frame;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Book;
import service.BookManager;

//
public class BorrowFrame extends JFrame {

	/**
	 * BookInsertFrame 构造函数
	 * 
	 */
	public BorrowFrame(){
		
		super("借阅窗口");	
	}
	
	
	public void createWindow(){
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();    //创建 Point 对象，用来表示 (x,y) 坐标空间中的位置的点
		final int windowWidth = 420;    //设置窗体的宽度
		final int windowHeight = 380;    //设置窗体的高度

		this.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, 
				windowWidth, windowHeight);    //设置窗口的位置和大小
		
		ImageIcon img = new ImageIcon("img/背景2.jpg"); 
		
		
        JLabel bgLabel = new JLabel(img); 
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); 
        bgLabel.setBounds(0,0,img.getIconWidth(),img.getIconHeight()); 
        ((JPanel)getContentPane()).setOpaque(false); 
        
		this.setResizable(false);    //设置窗口的大小不可以改变
		this.action();
		this.setVisible(true);    //设置窗口是可见的
	}
	
public void action(){
		
		Container content = this.getContentPane();    //创建容器
		content.setLayout(null);    //关闭自动布局模式
		
		JButton insertButton = new JButton("借阅");    //创建 JButton 对象，实现按钮功能
		insertButton.setBounds(120, 280, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		insertButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		content.add(insertButton);    //将 JButton 对象添加入容器
		
		JButton cancelButton = new JButton("返回");    //创建 JButton 对象，实现按钮功能
		cancelButton.setBounds(220, 280, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		cancelButton.setIcon(new ImageIcon("img/cancel.png"));    //为 JButton 对象添加图片
		content.add(cancelButton);    //将 JButton 对象添加入容器
		
		JLabel bookInfo = new JLabel("要借阅的图书信息");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		bookInfo.setBounds(175, 10, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(bookInfo);    //将 JLabel 对象添加入容器
		
		JLabel nameLabel = new JLabel("书名");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		nameLabel.setBounds(40, 50, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(nameLabel);    //将 JLabel 对象添加入容器
		
		final JTextField nameField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		nameField.setBounds(130, 58, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(nameField);    //将 JTextField 对象添加入容器
		
		JLabel authorLabel = new JLabel("作者");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		authorLabel.setBounds(40, 80, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(authorLabel);    //将 JLabel 对象添加入容器
		
		final JTextField authorField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		authorField.setBounds(130, 88, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(authorField);    //将 JTextField 对象添加入容器
		
		JLabel publisherLabel = new JLabel("借书账号");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		publisherLabel.setBounds(40, 110, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(publisherLabel);    //将 JLabel 对象添加入容器
		
		final JTextField publisherField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		publisherField.setBounds(130, 118, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(publisherField);    //将 JTextField 对象添加入容器
		
		JLabel amountLabel = new JLabel("数量");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		amountLabel.setBounds(40, 140, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(amountLabel);    //将 JLabel 对象添加入容器
		
		final JTextField amountField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		amountField.setBounds(130, 148, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(amountField);    //将 JTextField 对象添加入容器
		
		
		
		//设置借阅按钮事件处理
		insertButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						
						BookManager bookManager = new BookManager();    //创建 BookManager 对象
						
						//为 material 对象初始化
						
						if(bookManager.borrow(nameField.getText(),amountField.getText(),publisherField.getText(),authorField.getText())){
							
							//Object[][] ob = BookListFrame.insertBookToTable( BookListFrame.object );
							//BookListFrame.jTable.setModel( new DefaultTableModel( ob,BookListFrame.st ) );

							JOptionPane.showMessageDialog(null, "借阅成功", 
									"Insert Form", JOptionPane.INFORMATION_MESSAGE);
						}else{
							
							JOptionPane.showMessageDialog(null, "借阅失败", 
									"Insert Form", JOptionPane.INFORMATION_MESSAGE);
						}
						
//						setVisible(false);
						dispose();    //关闭此窗口
					}
				});
		
		//设置取消按钮事件处理
		cancelButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
//					setVisible(false);
					dispose();    //关闭此窗口
				}
			}
		);	
	}


}
