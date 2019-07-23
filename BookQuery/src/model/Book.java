/**
 * 
 */
package model;

/**
 * @author 张志远&郑拓
 *
 */
public class Book {

	private Integer id;    //图书编号
	private String name;    //图书名称
	private String author;    //图书作者
	private String publisher;    //图书出版社
	private Integer amount;    //图书数量
	private String position;    //图书位置
	private Integer typeId;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}
	
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the type
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param type the type to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
}
