package model;

/**
 * @author 张志远&郑拓
 *
 */
public class User {

	private Integer id;    //管理员编号
	private String userName;    //管理员名称
	private String userPassword;    //管理员密码
	
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
	 * @return the adminName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param adminName the adminName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the adminPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	
	/**
	 * @param adminPassword the adminPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
