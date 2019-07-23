
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

/**
 * @author 张志远&郑拓
 *
 */
@SuppressWarnings("serial")
public class BookQueryLoginFrame extends JFrame{

public BookQueryLoginFrame(){
		
		super("图书信息索引系统");
	}
	
	public void createWindow(){
		
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();    //创建 Point 对象，用来表示 (x,y) 坐标空间中的位置的点
		final int windowWidth = 605;    //设置窗体的宽度
		final int windowHeight = 500;    //设置窗体的高度
		//用左上角的坐标来设置窗口
		this.setBounds( center.x - windowWidth / 2, center.y - windowHeight / 2, 
				windowWidth, windowHeight );    //设置窗口的位置和大小
		//添加背景
		ImageIcon img = new ImageIcon("img/背景.jpg"); 
		
        JLabel bgLabel = new JLabel(img); //添加“背景”标签
        //调整背景图片
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); 
        bgLabel.setBounds(0,0,img.getIconWidth(),img.getIconHeight()); 
        ((JPanel)getContentPane()).setOpaque(false); 


		this.setDefaultCloseOperation( EXIT_ON_CLOSE );    //设置窗口的关闭方式
		this.setResizable( false );    //设置窗口的大小不可以改变
		this.setVisible( true );    //设置窗口是可见的
	}
	

	public void action(){
		
		final Container content = this.getContentPane();    //创建容器content
		content.setLayout(null);    //关闭自动布局模式
		
		
		//借还书入口
		JButton borrowButton = new JButton("借/还书入口");    //创建 JButton 对象，实现按钮功能
		borrowButton.setBounds(400, 210, 160, 40);    //设置 JButton 对象在窗口中的位置和大小
		borrowButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		content.add(borrowButton);    //将 JButton 对象添加入容器
		
		
		//注册窗口
		JButton registerButton = new JButton("用户注册");    //创建 JButton 对象，实现按钮功能
		registerButton.setBounds(400, 255, 160, 40);    //设置 JButton 对象在窗口中的位置和大小
		registerButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		content.add(registerButton);    //将 JButton 对象添加入容器
		
		
		//第一个入口“借阅者入口”
		JButton userButton = new JButton("阅览查询入口");    //创建 JButton 对象，实现按钮功能
		userButton.setBounds(400, 300, 160, 40);    //设置 JButton 对象在窗口中的位置和大小
		userButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		content.add(userButton);    //将 JButton 对象添加入容器
		
		//第二个入口“图书管理员入口”
		JButton adminButton = new JButton("图书管理员入口");    //创建 JButton 对象，实现按钮功能
		adminButton.setBounds(400, 345, 160, 40);    //设置 JButton 对象在窗口中的位置和大小
		adminButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		content.add(adminButton);    //将 JButton 对象添加入容器
		
		//出口“退出”
		JButton exitButton = new JButton("退出");    //创建 JButton 对象，实现按钮功能
		exitButton.setBounds(400, 390, 160, 40);    //设置 JButton 对象在窗口中的位置和大小
		exitButton.setIcon(new ImageIcon("img/exit.png"));    //为 JButton 对象添加图片
		content.add(exitButton);    //将 JButton 对象添加入容器
		
		
		
		
		//借还书按钮处理事件
		borrowButton.addActionListener(
				new ActionListener(){
					public void actionPerformed( ActionEvent e ){
						//转到UserLoginFrame
						UserLoginFrame userLoginFrame = new UserLoginFrame();
						userLoginFrame.action();  //监听事件
						userLoginFrame.createWindow();	//管理员窗口
					}
				});
		
		
		//registerButton按钮事件处理
		registerButton.addActionListener(
				new ActionListener(){
					public void actionPerformed( ActionEvent e ){
						//转到registerFrame
						registerFrame registerframe = new registerFrame();
						registerframe.action();  //监听事件
						registerframe.createWindow();	//注册窗口
					}
				});
		
		
		
		//userButton按钮事件处理
		userButton.addActionListener(
				//事件监听
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//转到BookQueryFrame
						BookQueryFrame bookQueryFrame = new BookQueryFrame();
						bookQueryFrame.action();
						bookQueryFrame.createWindow();	//创建借阅者出现的窗口					
					}
				});
		
		//adminButton 按钮事件处理
		adminButton.addActionListener(
				new ActionListener(){
					public void actionPerformed( ActionEvent e ){
						//转到AdminLoginFrame
						AdminLoginFrame adminLoginFrame = new AdminLoginFrame();
						adminLoginFrame.action();  //监听事件
						adminLoginFrame.createWindow();	//管理员窗口
					}
				});
		
		//exitButton 按钮事件处理
		exitButton.addActionListener(
				new ActionListener(){
					public void actionPerformed( ActionEvent event ){
						//对是否真正要退出进行判断
						if(JOptionPane.showConfirmDialog( null, "确认要退出吗？", "Warning Dialog", JOptionPane.YES_NO_OPTION) == 0 ){
							System.exit( 0 ); 
						}
					} 
				});
	}
	
	public static void main( String args[] ){
		
		BookQueryLoginFrame bookQueryLoginFrame = new BookQueryLoginFrame();
		
		bookQueryLoginFrame.action();
		bookQueryLoginFrame.createWindow();
		
	}
}
