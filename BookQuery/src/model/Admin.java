/**
 * 
 */
package model;

/**
 * @author 张志远&郑拓
 *
 */
public class Admin {

	private Integer id;    //管理员编号
	private String adminName;    //管理员名称
	private String adminPassword;    //管理员密码
	
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
	public String getAdminName() {
		return adminName;
	}
	
	/**
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	/**
	 * @return the adminPassword
	 */
	public String getAdminPassword() {
		return adminPassword;
	}
	
	/**
	 * @param adminPassword the adminPassword to set
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
