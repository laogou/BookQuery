/**
 * 
 */
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

/**
 * @author 张志远&郑拓
 *
 */
@SuppressWarnings("serial")
public class BookEditFrame extends JFrame{

	Book book = new Book();    //创建 Book 对象
	
	/**
	 * BookEditFrame 默认构造函数
	 * 
	 */
	public BookEditFrame(){
		
	}
	
	/**
	 * BookEditFrame 构造函数
	 * @param book Book 对象
	 * 
	 */
	public BookEditFrame( Book book ){
		
		super("图书信息编辑窗口");
		this.book = book;
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
		
		JLabel bookEdit = new JLabel("图书信息编辑");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		bookEdit.setBounds(175, 10, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(bookEdit);    //将 JLabel 对象添加入容器
		
		JLabel nameLabel = new JLabel("名称");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		nameLabel.setBounds(40, 50, 80, 40);//设置 JLabel 对象在窗口中的位置和大小
		content.add(nameLabel);    //将 JLabel 对象添加入容器
		
		final JTextField nameField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		nameField.setBounds(130, 58, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(nameField);    //将 JTextField 对象添加入容器
		nameField.setText(book.getName());
		
		JLabel authorLabel = new JLabel("作者");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		authorLabel.setBounds(40, 80, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(authorLabel);    //将 JLabel 对象添加入容器
		
		final JTextField authorField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		authorField.setBounds(130, 88, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(authorField);    //将 JTextField 对象添加入容器
		authorField.setText(book.getAuthor());
		
		JLabel publisherLabel = new JLabel("出版社");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		publisherLabel.setBounds(40, 110, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(publisherLabel);    //将 JLabel 对象添加入容器
		
		final JTextField publisherField = new JTextField(15);    //创建 JTextField 对象，允许编辑单行文本
		publisherField.setBounds(130, 118, 150, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(publisherField);    //将 JTextField 对象添加入容器
		publisherField.setText(book.getPublisher());
		
		JLabel amountLabel = new JLabel("数量");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		amountLabel.setBounds(40, 140, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(amountLabel);    //将 JLabel 对象添加入容器
		
		final JTextField amountField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		amountField.setBounds(130, 148, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(amountField);    //将 JTextField 对象添加入容器
		amountField.setText(book.getAmount().toString());
		
		JLabel positionLabel = new JLabel("位置");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		positionLabel.setBounds(40, 170, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(positionLabel);    //将 JLabel 对象添加入容器
		
		final JTextField positionField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		positionField.setBounds(130, 178, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(positionField);    //将 JTextField 对象添加入容器
		positionField.setText(book.getPosition());
		
		JLabel bookTypeLabel = new JLabel("类别");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		bookTypeLabel.setBounds(40, 200, 80, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(bookTypeLabel);    //将 JLabel 对象添加入容器
		
		final JTextField bookTypeField = new JTextField(10);    //创建 JTextField 对象，允许编辑单行文本
		bookTypeField.setBounds(130, 208, 100, 25);    //设置 JTextField 对象在窗口中的位置和大小
		content.add(bookTypeField);    //将 JTextField 对象添加入容器
		bookTypeField.setText(book.getTypeId().toString());
		
		JLabel typeLabel = new JLabel("类别说明            1:计算机类 2:文学类 3:语言类 4：心理类");    //创建 JLabel 对象，用于短文本字符串或图像或二者的显示区
		typeLabel.setBounds(40, 230, 350, 40);    //设置 JLabel 对象在窗口中的位置和大小
		content.add(typeLabel);    //将 JLabel 对象添加入容器
		
		//设置保存按钮事件处理
		saveButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						BookManager bookManager = new BookManager();    //创建 BookManager 对象
						
						//为 book 对象初始化
						book.setId(book.getId());
						book.setName(nameField.getText());
						book.setAuthor(authorField.getText());
						book.setPublisher(publisherField.getText());
						book.setAmount(Integer.parseInt(amountField.getText().toString()));
						book.setPosition(positionField.getText());
						book.setTypeId(Integer.parseInt(bookTypeField.getText().toString()));
						
						//判断数据是否被更新，输出提示窗口
						if(bookManager.update(book)){
							
							Object[][] ob = BookListFrame.insertBookToTable( BookListFrame.object );
							BookListFrame.jTable.setModel( new DefaultTableModel( ob,BookListFrame.st ) );
							
							JOptionPane.showMessageDialog(null, "保存成功", 
									"Save Form", JOptionPane.INFORMATION_MESSAGE);
						}else{
							
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
