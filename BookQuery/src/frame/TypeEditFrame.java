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
public class TypeEditFrame extends JFrame{

	Types type = new Types();    //创建 Book 对象
	
	/**
	 * BookEditFrame 默认构造函数
	 * 
	 */
	public TypeEditFrame(){
		
	}
	
	/**
	 * BookEditFrame 构造函数
	 * @param book Book 对象
	 * 
	 */
	public TypeEditFrame( Types type ){
		
		super("图书信息编辑窗口");
		this.type = type;
	}
	
	/**
	 * 进行事件处理方法
	 * 
	 */
	public void action(){
		
		Container content = this.getContentPane();    //创建容器
		content.setLayout(null);    //关闭自动布局模式
		
		
		JButton saveButton = new JButton("保存");    //创建 JButton 对象，实现按钮功能
		saveButton.setBounds(120, 280, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		saveButton.setIcon(new ImageIcon("img/ok.png"));    //为 JButton 对象添加图片
		content.add(saveButton);    //将 JButton 对象添加入容器
		
		JButton cancelButton = new JButton("返回");    //创建 JButton 对象，实现按钮功能
		cancelButton.setBounds(220, 280, 90, 40);    //设置 JButton 对象在窗口中的位置和大小
		cancelButton.setIcon(new ImageIcon("img/cancel.png"));    //为 JButton 对象添加图片
		content.add(cancelButton);    //将 JButton 对象添加入容器
		
		JLabel typeEdit = new JLabel("类型信息编辑");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		typeEdit.setBounds(175, 10, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(typeEdit);    //将 JLabel 对象添加入容器
		
		
		
		JLabel nameLabel = new JLabel("类别名称");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		nameLabel.setBounds(40, 50, 80, 40);//设置 JLabel 对象在窗口中的位置和大小
		content.add(nameLabel);    //将 JLabel 对象添加入容器
		
		final JTextField nameField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		nameField.setBounds(130, 58, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(nameField);    //将 JTextField 对象添加入容器
		nameField.setText(type.getName());
		
		
		JLabel IDLabel = new JLabel("ID");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		IDLabel.setBounds(40, 80, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(IDLabel);    //将 JLabel 对象添加入容器
		
		final JTextField IDField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		IDField.setBounds(130, 88, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(IDField);    //将 JTextField 对象添加入容器
		IDField.setText(type.getId().toString());
		
		
		//设置保存按钮事件处理
		saveButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						  //创建 TypeManager 对象
						TypeManager typeManager = new TypeManager();
						//为 book 对象初始化
						type.setId(type.getId());
						type.setName(nameField.getText());
						
						//判断数据是否被更新，输出提示窗口
						if(typeManager.update(type)) {
							Object[][] ob = BookListFrame.insertBookToTable( BookListFrame.object );
							BookListFrame.jTable.setModel( new DefaultTableModel( ob,BookListFrame.st ) );
							JOptionPane.showMessageDialog(null, "保存成功", 
									"Save Form", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							
							JOptionPane.showMessageDialog(null, "保存失败", 
									"Save Form", JOptionPane.INFORMATION_MESSAGE);
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
