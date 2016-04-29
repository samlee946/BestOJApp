package com.unlimited.appserver.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AppUser 
 * @Description: 最基础的用户model
 * @author Sam Lee
 * @date 2016-4-29 上午05:05:53 
 * @email samlee946@gmail.com
 *
 */
@Entity(name="app_users")
@Table(name="app_users")
public class AppUser {
	/** 唯一ID */
	private Long id;
	/** 唯一用户名 */
	private String userName;
	/** 密码 */
	private String password;
	/** 用户在OJ的token */
	private String token;
	/** 用户的积分 */
	private Long credit;
	/** 用户上次登陆的时间 */
	private String lastTimeLogin;
	/** 用户上次签到的时间*/
	private String lastTimeSign;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, insertable=true, updatable=true)
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	@Basic(optional=false)
	@Column(name="userName", nullable=false, insertable=true, updatable=true, length=64)
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	@Basic(optional=false)
	@Column(name="password", nullable=false, insertable=true, updatable=true, length=64)
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the token
	 */
	@Basic(optional=false)
	@Column(name="token", nullable=false, insertable=true, updatable=true, length=128)
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the credit
	 */
	@Basic(optional=false)
	@Column(name="credit", nullable=false, insertable=true, updatable=true)
	public Long getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Long credit) {
		this.credit = credit;
	}
	/**
	 * @return the lastTimeLogin
	 */
	@Basic(optional=true)
	@Column(name="lastTimeLogin", nullable=true, insertable=true, updatable=true, length=32)
	public String getLastTimeLogin() {
		return lastTimeLogin;
	}
	/**
	 * @param lastTimeLogin the lastTimeLogin to set
	 */
	public void setLastTimeLogin(String lastTimeLogin) {
		this.lastTimeLogin = lastTimeLogin;
	}
	/**
	 * @return the lastTimeSign
	 */
	@Basic(optional=true)
	@Column(name="lastTimeSign", nullable=true, insertable=true, updatable=true, length=32)
	public String getLastTimeSign() {
		return lastTimeSign;
	}
	/**
	 * @param lastTimeSign the lastTimeSign to set
	 */
	public void setLastTimeSign(String lastTimeSign) {
		this.lastTimeSign = lastTimeSign;
	}
	/**  */
	public AppUser(String userName, String password, String token) {
		super();
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.credit = 0L;
	}
	/**  */
	public AppUser() {
		super();
	}
}