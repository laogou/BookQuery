
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;
import service.UserManager;

/**
 * @author 张志远&郑拓
 *
 */
@SuppressWarnings("serial")
public class UserLoginFrame extends JFrame{

	public UserLoginFrame(){
		super( "用户登入" );
	}
	
	/**
	 * 创建窗体
	 */
	public void createWindow(){
		
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();    //创建 Point 对象，用来表示 (x,y) 坐标空间中的位置的点
		final int windowWidth = 320;    //设置窗体的宽度
		final int windowHeight = 240;    //设置窗体的高度

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
		
		JButton loginButton = new JButton("登入");    //创建 JButton 对象，实现按钮功能
		loginButton.setBounds(60, 150, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		loginButton.setIcon(new ImageIcon("img/ok.png"));    //为 JButton 对象添加图片
		
		content.add(loginButton);    //将 JButton 对象添加入容器
		
		JButton cancelButton = new JButton("返回");    //创建 JButton 对象，实现按钮功能
		cancelButton.setBounds(180, 150, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		cancelButton.setIcon(new ImageIcon("img/cancel.png"));    //为 JButton 对象添加图片
		
		content.add(cancelButton);    //将 JButton 对象添加入容器
		
		JLabel bookEdit = new JLabel("用户入口");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		bookEdit.setBounds(105, 10, 160, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(bookEdit);    //将 JLabel 对象添加入容器
		
		JLabel nameLabel = new JLabel("名称");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		nameLabel.setBounds(40, 50, 80, 40);//设置 JLabel 对象在窗口中的位置和大小
		content.add(nameLabel);    //将 JLabel 对象添加入容器
		
		final JTextField nameField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		nameField.setBounds(130, 58, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(nameField);    //将 JTextField 对象添加入容器
		
		JLabel passwordLabel = new JLabel("密码");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		passwordLabel.setBounds(40, 80, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(passwordLabel);    //将 JLabel 对象添加入容器
		
		final JPasswordField passwordField = new JPasswordField(15);    //创建 JTextField 对象，允许编辑单行文本
		passwordField.setBounds(130, 88, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(passwordField);    //将 JTextField 对象添加入容器
		//=================================================================
		//设置保存按钮事件处理
		loginButton.addActionListener(
				new ActionListener(){
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e){
						UserManager userManager = new UserManager();
						User user = new User();
						
						if( nameField.getText() != null && nameField.getText().trim().length() != 0 ){
							user = userManager.findByUserName( nameField.getText() );
						}
						
						if( user.getUserPassword().equals(passwordField.getText()) ){
							BorrowBackFrame BorrowBackFrame = new BorrowBackFrame();
							BorrowBackFrame.action();
							BorrowBackFrame.createWindow();
						}else{
							JOptionPane.showMessageDialog(null, "登入失败", 
									"Warning Form", JOptionPane.WARNING_MESSAGE);
						}
						
						dispose();    //关闭此窗口
					}
				});
		
		//设置取消按钮事件处理
		cancelButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();    //关闭此窗口
				}
			}
		);
	}
}
