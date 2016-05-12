package com.unlimited.appserver.model;

import javax.persistence.*;

import com.unlimited.oj.model.*;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the basic "user" object in AppFuse that allows for
 * authentication and user management. It implements Acegi Security's
 * UserDetails interface.
 * 
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 */
@Entity(name = "User")
@Table(name = "users")
public class User implements UserDetails, java.io.Serializable {
	/** 维一ID */
	private java.lang.Long id;
	/** 用户名 */
	private java.lang.String username;
	/** 用户昵称 */
	private java.lang.String nick = "";
	/** 密码 */
	private java.lang.String password;
	/** 用户在OJ的token */
	private String token;
	/** 用户的积分 */
	private Long credit;
	/** 用户上次登陆的时间 */
	private String lastTimeLogin;
	/** 用户上次签到的时间 */
	private String lastTimeSign;
	private Integer version;
	/** 激活标志 */
	private java.lang.Boolean enabled = false;

	private java.util.List<Role> roleList = null;

	/**
	 * @pdGenerated default getter
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Users_vs_Roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false) })
	public java.util.List<Role> getRoleList() {
		if (roleList == null)
			roleList = new java.util.ArrayList<Role>();
		return roleList;
	}

	/**
	 * @pdGenerated default iterator getter
	 */
	@Transient
	public java.util.Iterator getIteratorRoleList() {
		if (roleList == null)
			roleList = new java.util.ArrayList<Role>();
		return roleList.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newRoleList
	 */
	public void setRoleList(java.util.List<Role> newRoleList) {
		// removeAllRoleList();
		this.roleList = newRoleList;
	}

	/**
	 * @pdGenerated default add
	 * @param newRole
	 */
	public void addRoleList(Role newRole) {
		if (newRole == null)
			return;
		if (this.roleList == null)
			this.roleList = new java.util.ArrayList<Role>();
		if (!this.roleList.contains(newRole)) {
			this.roleList.add(newRole);
			newRole.addUserList(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldRole
	 */
	public void removeRoleList(Role oldRole) {
		if (oldRole == null)
			return;
		if (this.roleList != null)
			if (this.roleList.contains(oldRole)) {
				this.roleList.remove(oldRole);
				oldRole.removeUserList(this);
			}
	}

	/**
	 * @pdGenerated default removeAll
	 */
	public void removeAllRoleList() {
		if (roleList != null) {
			Role oldRole;
			for (java.util.Iterator iter = getIteratorRoleList(); iter
					.hasNext();) {
				oldRole = (Role) iter.next();
				iter.remove();
				oldRole.removeUserList(this);
			}
		}
	}

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public User() {
		// TODO Add your own initialization code here.
	}

	@Transient
	public boolean isAccountNonExpired() {
		// TODO: implement
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		// TODO: implement
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO: implement
		return true;
	}

	@Transient
	public boolean isEnabled() {
		// TODO: implement
		return enabled;
	}

	@Transient
	public GrantedAuthority[] getAuthorities() {
		return roleList.toArray(new GrantedAuthority[0]);
	}

	@Transient
	public boolean isAdministrator() {
		for (Role role : getRoleList()) {
			if (role.getName().equals("ROLE_ADMIN"))
				return true;
		}
		return false;
	}

	@Transient
	public boolean isContestAdministrator() {
		for (Role role : getRoleList()) {
			if (role.getName().equals("ROLE_ADMIN_CONTEST"))
				return true;
		}
		return false;
	}

	@Transient
	public boolean isExerciseAdministrator() {
		for (Role role : getRoleList()) {
			if (role.getName().equals("ROLE_ADMIN_EXERCISE"))
				return true;
		}
		return false;
	}

	@Transient
	public boolean isExamAdministrator() {
		for (Role role : getRoleList()) {
			if (role.getName().equals("ROLE_ADMIN_EXAM"))
				return true;
		}
		return false;
	}

	@Transient
	public boolean isObserver() {
		for (Role role : getRoleList()) {
			if (role.getName().equals("ROLE_OBSERVER"))
				return true;
		}
		return false;
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	public java.lang.Long getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.Long newId) {
		this.id = newId;
	}

	/**
	 * Get value of username
	 * 
	 * @return username
	 */
	@Basic(optional = true)
	@Column(name = "user_name", insertable = true, updatable = true, length = 20)
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * Set value of username
	 * 
	 * @param newUsername
	 */
	public void setUsername(java.lang.String newUsername) {
		this.username = newUsername;
	}

	/**
	 * Get value of nick
	 * 
	 * @return nick
	 */
	@Basic(optional = true)
	@Column(name = "nick", insertable = true, updatable = true, length = 100)
	public java.lang.String getNick() {
		return nick;
	}

	/**
	 * Set value of nick
	 * 
	 * @param newNick
	 */
	public void setNick(java.lang.String newNick) {
		this.nick = newNick;
	}

	/**
	 * Get value of password
	 * 
	 * @return password
	 */
	@Basic(optional = true)
	@Column(name = "password", insertable = true, updatable = true, length = 80)
	public java.lang.String getPassword() {
		return password;
	}

	/**
	 * Set value of password
	 * 
	 * @param newPassword
	 */
	public void setPassword(java.lang.String newPassword) {
		this.password = newPassword;
	}

	/**
	 * Get value of version
	 * 
	 * @return version
	 */
	@Basic(optional = true)
	@Column(name = "version", insertable = true, updatable = true)
	public Integer getVersion() {
		return version;
	}

	/**
	 * Set value of version
	 * 
	 * @param newVersion
	 */
	public void setVersion(Integer newVersion) {
		this.version = newVersion;
	}

	/**
	 * Get value of enabled
	 * 
	 * @return enabled
	 */
	@Basic(optional = true)
	@Column(name = "enabled", insertable = true, updatable = true)
	public java.lang.Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Set value of enabled
	 * 
	 * @param newEnabled
	 */
	public void setEnabled(java.lang.Boolean newEnabled) {
		this.enabled = newEnabled;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (!(other instanceof User))
			return false;

		User cast = (User) other;

		if (this.getId() == null || cast.getId() == null) {
			if (this.getId() == null && cast.getId() == null)
				return true;
			else
				return false;
		}

		if (!this.getId().equals(cast.getId()))
			return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nick=" + nick
				+ ", password=" + password + ", token=" + token + ", credit="
				+ credit + ", lastTimeLogin=" + lastTimeLogin
				+ ", lastTimeSign=" + lastTimeSign + ", version=" + version
				+ ", enabled=" + enabled + ", roleList=" + roleList + "]";
	}

	/**  */
	public User(String username, String password, String token) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	
}