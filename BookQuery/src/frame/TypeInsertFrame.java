
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
import model.Types;
import service.BookManager;
import service.TypeManager;

/**
 * @author 张志远&郑拓
 *
 */
@SuppressWarnings("serial")
public class TypeInsertFrame extends JFrame{

	/**
	 * TypeInsertFrame 构造函数
	 * 
	 */
	public TypeInsertFrame(){
		
		super("类别信息插入窗口");	
	}
	
	/**
	 * 进行事件处理方法
	 * 
	 */
	public void action(){
		
		Container content = this.getContentPane();    //创建容器
		content.setLayout(null);    //关闭自动布局模式
		
		JButton insertButton = new JButton("插入");    //创建 JButton 对象，实现按钮功能
		insertButton.setBounds(120, 280, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		insertButton.setIcon(new ImageIcon("img/insert.png"));    //为 JButton 对象添加图片
		
		content.add(insertButton);    //将 JButton 对象添加入容器
		
		JButton cancelButton = new JButton("返回");    //创建 JButton 对象，实现按钮功能
		cancelButton.setBounds(220, 280, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		cancelButton.setIcon(new ImageIcon("img/cancel.png"));    //为 JButton 对象添加图片
		
		
		content.add(cancelButton);    //将 JButton 对象添加入容器
		
		JLabel bookInfo = new JLabel("图书类别信息插入");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		bookInfo.setBounds(175, 10, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(bookInfo);    //将 JLabel 对象添加入容器
		
		JLabel nameLabel = new JLabel("名称");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		nameLabel.setBounds(40, 50, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(nameLabel);    //将 JLabel 对象添加入容器
		
		final JTextField nameField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		nameField.setBounds(130, 58, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(nameField);    //将 JTextField 对象添加入容器
		
		
		
		//设置插入按钮事件处理
		insertButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						 //创建 Type对象
						TypeManager typeManager =new TypeManager();
						Types types = new Types();
						
						//为 material 对象初始化
						types.setName(nameField.getText());
						
						//判断数据是否被插入，输出提示窗口
						if(typeManager.add(types)){
							
							Object[][] ob = BookListFrame.insertBookToTable( BookListFrame.object );
							BookListFrame.jTable.setModel( new DefaultTableModel( ob,BookListFrame.st ) );

							JOptionPane.showMessageDialog(null, "插入成功", 
									"Insert Form", JOptionPane.INFORMATION_MESSAGE);
						}else{
							
							JOptionPane.showMessageDialog(null, "插入失败", 
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
	
	/**
	 * 创建窗体
	 * 
	 */
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
}
