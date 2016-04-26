package com.unlimited.oj.model;

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

/** This class represents the basic "user" object in AppFuse that allows for authentication
 * and user management.  It implements Acegi Security's UserDetails interface.
 * 
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Updated by Dan Kibler (dan@getrolling.com)
 *         Extended to implement Acegi UserDetails interface
 *         by David Carter david@carter.net */
@Entity(name="User")
@Table(name="users")
public class User implements UserDetails, java.io.Serializable {
   /** 维一ID */
   private java.lang.Long id;
   /** 用户名 */
   private java.lang.String username;
   /** 用户昵称 */
   private java.lang.String nick = "";
   /** 密码 */
   private java.lang.String password;
   /** 密码确认 */
   private java.lang.String confirmPassword;
   private java.lang.String oldPassword;
   /** 密码提示 */
   private String passwordHint;
   /** 姓 */
   private String firstName;
   /** 名 */
   private String lastName;
   /** 电子邮件 */
   private java.lang.String email;
   /** 电话号码 */
   private String phoneNumber;
   /** 网站 */
   private String website;
   private Integer version;
   /** 激活标志 */
   private java.lang.Boolean enabled = false;
   /** 帐号过期标志 */
   private java.lang.Boolean accountExpired = false;
   /** 帐号锁定 */
   private java.lang.Boolean accountLocked = false;
   /** 信用过期 */
   private java.lang.Boolean credentialsExpired = false;
   /** 提交解答次数 */
   private Integer submit = 0;
   /** 通过次数 */
   private Integer solved = 0;
   /** 近期登录IP */
   private java.lang.String ip = "";
   /** 近期访问时间 */
   private java.util.Date accesstime;
   /** 近期访问卷号 */
   private Integer volume = 1;
   /** 近期使用语言 */
   private Integer language = 1;
   /** 注册时间 */
   private java.util.Date regTime;
   /** 所在班级 */
   private java.lang.String className;
   /** 学号 */
   private java.lang.String studentNumber;
   /** 所在学校 */
   private java.lang.String school = "";
   /** acm.pku.edu.cn帐号 */
   private java.lang.String pkuAccount;
   /** 北大帐号是否有效(被激活) */
   private java.lang.Boolean pkuAccountValid = false;
   /** 北大帐号状态(0=停用;1=启用;2=禁用) */
   private int pkuAccountStatus = 0;
   /** 第一次激活时间(即注册时间) */
   private java.util.Date pkuAccountRegTime;
   /** 用于表示资格或等级（0-10000，大于5000为校队队员） */
   private int grade = 0;
   
   private java.util.List<Role> roleList = null;
   
   
   /**
    * @pdGenerated default getter
    */
   @ManyToMany(fetch=FetchType.EAGER)
   @JoinTable(
      name="Users_vs_Roles",
      joinColumns={
         @JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
      },
      inverseJoinColumns={
         @JoinColumn(name="role_id", referencedColumnName="id", nullable=false)
      }
   )
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
      //removeAllRoleList();
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
      if (!this.roleList.contains(newRole))
      {
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
         if (this.roleList.contains(oldRole))
         {
            this.roleList.remove(oldRole);
            oldRole.removeUserList(this);
         }
   }
   
   /**
    * @pdGenerated default removeAll
    */
   public void removeAllRoleList() {
      if (roleList != null)
      {
         Role oldRole;
         for (java.util.Iterator iter = getIteratorRoleList(); iter.hasNext();)
         {
            oldRole = (Role)iter.next();
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
   public java.lang.String getFullName() {
         return getLastName() + getFirstName();
   }
   
   @Transient
   public boolean isAdministrator() {
   	   for(Role role: getRoleList())
   	   {
   		   if(role.getName().equals("ROLE_ADMIN"))
   			   return true;
   	   }
   	   return false;
   }
   
   @Transient
   public boolean isContestAdministrator() {
      for(Role role: getRoleList())
      {
   	   if(role.getName().equals("ROLE_ADMIN_CONTEST"))
   		   return true;
      }
      return false;
   }
   
   @Transient
   public boolean isExerciseAdministrator() {
      for(Role role: getRoleList())
      {
   	   if(role.getName().equals("ROLE_ADMIN_EXERCISE"))
   		   return true;
      }
      return false;
   }
   
   @Transient
   public boolean isExamAdministrator() {
      for(Role role: getRoleList())
      {
   	   if(role.getName().equals("ROLE_ADMIN_EXAM"))
   		   return true;
      }
      return false;
   }
   
   @Transient
   public boolean isObserver() {
      for(Role role: getRoleList())
      {
   	   if(role.getName().equals("ROLE_OBSERVER"))
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
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable=false, insertable=true, updatable=true)
   public java.lang.Long getId()
   {
      return id;
   }
   
   /**
    * Set value of id
    *
    * @param newId 
    */
   public void setId(java.lang.Long newId)
   {
      this.id = newId;
   }
   
   /**
    * Get value of username
    *
    * @return username 
    */
   @Basic(optional=true)
   @Column(name="user_name", insertable=true, updatable=true, length=20)
   public java.lang.String getUsername()
   {
      return username;
   }
   
   /**
    * Set value of username
    *
    * @param newUsername 
    */
   public void setUsername(java.lang.String newUsername)
   {
      this.username = newUsername;
   }
   
   /**
    * Get value of nick
    *
    * @return nick 
    */
   @Basic(optional=true)
   @Column(name="nick", insertable=true, updatable=true, length=100)
   public java.lang.String getNick()
   {
      return nick;
   }
   
   /**
    * Set value of nick
    *
    * @param newNick 
    */
   public void setNick(java.lang.String newNick)
   {
      this.nick = newNick;
   }
   
   /**
    * Get value of password
    *
    * @return password 
    */
   @Basic(optional=true)
   @Column(name="password", insertable=true, updatable=true, length=80)
   public java.lang.String getPassword()
   {
      return password;
   }
   
   /**
    * Set value of password
    *
    * @param newPassword 
    */
   public void setPassword(java.lang.String newPassword)
   {
      this.password = newPassword;
   }
   
   /**
    * Get value of confirmPassword
    *
    * @return confirmPassword 
    */
   @Transient
   public java.lang.String getConfirmPassword()
   {
      return confirmPassword;
   }
   
   /**
    * Set value of confirmPassword
    *
    * @param newConfirmPassword 
    */
   public void setConfirmPassword(java.lang.String newConfirmPassword)
   {
      this.confirmPassword = newConfirmPassword;
   }
   
   /**
    * Get value of oldPassword
    *
    * @return oldPassword 
    */
   @Transient
   public java.lang.String getOldPassword()
   {
      return oldPassword;
   }
   
   /**
    * Set value of oldPassword
    *
    * @param newOldPassword 
    */
   public void setOldPassword(java.lang.String newOldPassword)
   {
      this.oldPassword = newOldPassword;
   }
   
   /**
    * Get value of passwordHint
    *
    * @return passwordHint 
    */
   @Basic(optional=true)
   @Column(name="passwordHint", insertable=true, updatable=true, length=254)
   public String getPasswordHint()
   {
      return passwordHint;
   }
   
   /**
    * Set value of passwordHint
    *
    * @param newPasswordHint 
    */
   public void setPasswordHint(String newPasswordHint)
   {
      this.passwordHint = newPasswordHint;
   }
   
   /**
    * Get value of firstName
    *
    * @return firstName 
    */
   @Basic(optional=true)
   @Column(name="firstName", insertable=true, updatable=true, length=254)
   public String getFirstName()
   {
      return firstName;
   }
   
   /**
    * Set value of firstName
    *
    * @param newFirstName 
    */
   public void setFirstName(String newFirstName)
   {
      this.firstName = newFirstName;
   }
   
   /**
    * Get value of lastName
    *
    * @return lastName 
    */
   @Basic(optional=true)
   @Column(name="lastName", insertable=true, updatable=true, length=254)
   public String getLastName()
   {
      return lastName;
   }
   
   /**
    * Set value of lastName
    *
    * @param newLastName 
    */
   public void setLastName(String newLastName)
   {
      this.lastName = newLastName;
   }
   
   /**
    * Get value of email
    *
    * @return email 
    */
   @Basic(optional=true)
   @Column(name="email", insertable=true, updatable=true, length=100)
   public java.lang.String getEmail()
   {
      return email;
   }
   
   /**
    * Set value of email
    *
    * @param newEmail 
    */
   public void setEmail(java.lang.String newEmail)
   {
      this.email = newEmail;
   }
   
   /**
    * Get value of phoneNumber
    *
    * @return phoneNumber 
    */
   @Basic(optional=true)
   @Column(name="phoneNumber", insertable=true, updatable=true, length=254)
   public String getPhoneNumber()
   {
      return phoneNumber;
   }
   
   /**
    * Set value of phoneNumber
    *
    * @param newPhoneNumber 
    */
   public void setPhoneNumber(String newPhoneNumber)
   {
      this.phoneNumber = newPhoneNumber;
   }
   
   /**
    * Get value of website
    *
    * @return website 
    */
   @Basic(optional=true)
   @Column(name="website", insertable=true, updatable=true, length=254)
   public String getWebsite()
   {
      return website;
   }
   
   /**
    * Set value of website
    *
    * @param newWebsite 
    */
   public void setWebsite(String newWebsite)
   {
      this.website = newWebsite;
   }
   
   /**
    * Get value of version
    *
    * @return version 
    */
   @Basic(optional=true)
   @Column(name="version", insertable=true, updatable=true)
   public Integer getVersion()
   {
      return version;
   }
   
   /**
    * Set value of version
    *
    * @param newVersion 
    */
   public void setVersion(Integer newVersion)
   {
      this.version = newVersion;
   }
   
   /**
    * Get value of enabled
    *
    * @return enabled 
    */
   @Basic(optional=true)
   @Column(name="enabled", insertable=true, updatable=true)
   public java.lang.Boolean getEnabled()
   {
      return enabled;
   }
   
   /**
    * Set value of enabled
    *
    * @param newEnabled 
    */
   public void setEnabled(java.lang.Boolean newEnabled)
   {
      this.enabled = newEnabled;
   }
   
   /**
    * Get value of accountExpired
    *
    * @return accountExpired 
    */
   @Basic(optional=true)
   @Column(name="accountExpired", insertable=true, updatable=true)
   public java.lang.Boolean getAccountExpired()
   {
      return accountExpired;
   }
   
   /**
    * Set value of accountExpired
    *
    * @param newAccountExpired 
    */
   public void setAccountExpired(java.lang.Boolean newAccountExpired)
   {
      this.accountExpired = newAccountExpired;
   }
   
   /**
    * Get value of accountLocked
    *
    * @return accountLocked 
    */
   @Basic(optional=true)
   @Column(name="accountLocked", insertable=true, updatable=true)
   public java.lang.Boolean getAccountLocked()
   {
      return accountLocked;
   }
   
   /**
    * Set value of accountLocked
    *
    * @param newAccountLocked 
    */
   public void setAccountLocked(java.lang.Boolean newAccountLocked)
   {
      this.accountLocked = newAccountLocked;
   }
   
   /**
    * Get value of credentialsExpired
    *
    * @return credentialsExpired 
    */
   @Basic(optional=true)
   @Column(name="credentialsExpired", insertable=true, updatable=true)
   public java.lang.Boolean getCredentialsExpired()
   {
      return credentialsExpired;
   }
   
   /**
    * Set value of credentialsExpired
    *
    * @param newCredentialsExpired 
    */
   public void setCredentialsExpired(java.lang.Boolean newCredentialsExpired)
   {
      this.credentialsExpired = newCredentialsExpired;
   }
   
   /**
    * Get value of submit
    *
    * @return submit 
    */
   @Basic(optional=true)
   @Column(name="submit", insertable=true, updatable=true)
   public Integer getSubmit()
   {
      return submit;
   }
   
   /**
    * Set value of submit
    *
    * @param newSubmit 
    */
   public void setSubmit(Integer newSubmit)
   {
      this.submit = newSubmit;
   }
   
   /**
    * Get value of solved
    *
    * @return solved 
    */
   @Basic(optional=true)
   @Column(name="solved", insertable=true, updatable=true)
   public Integer getSolved()
   {
      return solved;
   }
   
   /**
    * Set value of solved
    *
    * @param newSolved 
    */
   public void setSolved(Integer newSolved)
   {
      this.solved = newSolved;
   }
   
   /**
    * Get value of ip
    *
    * @return ip 
    */
   @Basic(optional=true)
   @Column(name="ip", insertable=true, updatable=true, length=20)
   public java.lang.String getIp()
   {
      return ip;
   }
   
   /**
    * Set value of ip
    *
    * @param newIp 
    */
   public void setIp(java.lang.String newIp)
   {
      this.ip = newIp;
   }
   
   /**
    * Get value of accesstime
    *
    * @return accesstime 
    */
   @Basic(optional=true)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="accesstime", insertable=true, updatable=true)
   public java.util.Date getAccesstime()
   {
      return accesstime;
   }
   
   /**
    * Set value of accesstime
    *
    * @param newAccesstime 
    */
   public void setAccesstime(java.util.Date newAccesstime)
   {
      this.accesstime = newAccesstime;
   }
   
   /**
    * Get value of volume
    *
    * @return volume 
    */
   @Basic(optional=true)
   @Column(name="volume", insertable=true, updatable=true)
   public Integer getVolume()
   {
      return volume;
   }
   
   /**
    * Set value of volume
    *
    * @param newVolume 
    */
   public void setVolume(Integer newVolume)
   {
      this.volume = newVolume;
   }
   
   /**
    * Get value of language
    *
    * @return language 
    */
   @Basic(optional=true)
   @Column(name="language", insertable=true, updatable=true)
   public Integer getLanguage()
   {
      return language;
   }
   
   /**
    * Set value of language
    *
    * @param newLanguage 
    */
   public void setLanguage(Integer newLanguage)
   {
      this.language = newLanguage;
   }
   
   /**
    * Get value of regTime
    *
    * @return regTime 
    */
   @Basic(optional=true)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="reg_time", insertable=true, updatable=true)
   public java.util.Date getRegTime()
   {
      return regTime;
   }
   
   /**
    * Set value of regTime
    *
    * @param newRegTime 
    */
   public void setRegTime(java.util.Date newRegTime)
   {
      this.regTime = newRegTime;
   }
   
   /**
    * Get value of className
    *
    * @return className 
    */
   @Basic(optional=true)
   @Column(name="class_name", insertable=true, updatable=true, length=254)
   public java.lang.String getClassName()
   {
      return className;
   }
   
   /**
    * Set value of className
    *
    * @param newClassName 
    */
   public void setClassName(java.lang.String newClassName)
   {
      this.className = newClassName;
   }
   
   /**
    * Get value of studentNumber
    *
    * @return studentNumber 
    */
   @Basic(optional=true)
   @Column(name="student_number", insertable=true, updatable=true, length=254)
   public java.lang.String getStudentNumber()
   {
      return studentNumber;
   }
   
   /**
    * Set value of studentNumber
    *
    * @param newStudentNumber 
    */
   public void setStudentNumber(java.lang.String newStudentNumber)
   {
      this.studentNumber = newStudentNumber;
   }
   
   /**
    * Get value of school
    *
    * @return school 
    */
   @Basic(optional=true)
   @Column(name="school", insertable=true, updatable=true, length=100)
   public java.lang.String getSchool()
   {
      return school;
   }
   
   /**
    * Set value of school
    *
    * @param newSchool 
    */
   public void setSchool(java.lang.String newSchool)
   {
      this.school = newSchool;
   }
   
   /**
    * Get value of pkuAccount
    *
    * @return pkuAccount 
    */
   @Basic(optional=true)
   @Column(name="pku_account", insertable=true, updatable=true, length=254)
   public java.lang.String getPkuAccount()
   {
      return pkuAccount;
   }
   
   /**
    * Set value of pkuAccount
    *
    * @param newPkuAccount 
    */
   public void setPkuAccount(java.lang.String newPkuAccount)
   {
      this.pkuAccount = newPkuAccount;
   }
   
   /**
    * Get value of pkuAccountValid
    *
    * @return pkuAccountValid 
    */
   @Basic(optional=true)
   @Column(name="pku_account_valid", insertable=true, updatable=true)
   public java.lang.Boolean getPkuAccountValid()
   {
      return pkuAccountValid;
   }
   
   /**
    * Set value of pkuAccountValid
    *
    * @param newPkuAccountValid 
    */
   public void setPkuAccountValid(java.lang.Boolean newPkuAccountValid)
   {
      this.pkuAccountValid = newPkuAccountValid;
   }
   
   /**
    * Get value of pkuAccountStatus
    *
    * @return pkuAccountStatus 
    */
   @Basic(optional=true)
   @Column(name="pku_account_status", insertable=true, updatable=true)
   public int getPkuAccountStatus()
   {
      return pkuAccountStatus;
   }
   
   /**
    * Set value of pkuAccountStatus
    *
    * @param newPkuAccountStatus 
    */
   public void setPkuAccountStatus(int newPkuAccountStatus)
   {
      this.pkuAccountStatus = newPkuAccountStatus;
   }
   
   /**
    * Get value of pkuAccountRegTime
    *
    * @return pkuAccountRegTime 
    */
   @Basic(optional=true)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pku_account_reg_time", insertable=true, updatable=true)
   public java.util.Date getPkuAccountRegTime()
   {
      return pkuAccountRegTime;
   }
   
   /**
    * Set value of pkuAccountRegTime
    *
    * @param newPkuAccountRegTime 
    */
   public void setPkuAccountRegTime(java.util.Date newPkuAccountRegTime)
   {
      this.pkuAccountRegTime = newPkuAccountRegTime;
   }
   
   /**
    * Get value of grade
    *
    * @return grade 
    */
   @Basic(optional=true)
   @Column(name="grade", insertable=true, updatable=true)
   public int getGrade()
   {
      return grade;
   }
   
   /**
    * Set value of grade
    *
    * @param newGrade 
    */
   public void setGrade(int newGrade)
   {
      this.grade = newGrade;
   }
   
   /* (non-Javadoc)
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
   
      
      if (this.getId()==null || cast.getId()==null)
      {
          if(this.getId()==null && cast.getId()==null)
              return true;
          else
              return false;
      }
         
      if (!this.getId().equals(cast.getId()))
          return false;
   
      return true;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   public int hashCode() {
      int hashCode = 0;
      if (this.username != null) 
         hashCode = 29 * hashCode + username.hashCode();
      if (this.nick != null) 
         hashCode = 29 * hashCode + nick.hashCode();
      if (this.password != null) 
         hashCode = 29 * hashCode + password.hashCode();
      if (this.passwordHint != null) 
         hashCode = 29 * hashCode + passwordHint.hashCode();
      if (this.firstName != null) 
         hashCode = 29 * hashCode + firstName.hashCode();
      if (this.lastName != null) 
         hashCode = 29 * hashCode + lastName.hashCode();
      if (this.email != null) 
         hashCode = 29 * hashCode + email.hashCode();
      if (this.phoneNumber != null) 
         hashCode = 29 * hashCode + phoneNumber.hashCode();
      if (this.website != null) 
         hashCode = 29 * hashCode + website.hashCode();
      if (this.version != null) 
         hashCode = 29 * hashCode + version.hashCode();
      if (this.enabled != null) 
         hashCode = 29 * hashCode + enabled.hashCode();
      if (this.accountExpired != null) 
         hashCode = 29 * hashCode + accountExpired.hashCode();
      if (this.accountLocked != null) 
         hashCode = 29 * hashCode + accountLocked.hashCode();
      if (this.credentialsExpired != null) 
         hashCode = 29 * hashCode + credentialsExpired.hashCode();
      if (this.submit != null) 
         hashCode = 29 * hashCode + submit.hashCode();
      if (this.solved != null) 
         hashCode = 29 * hashCode + solved.hashCode();
      if (this.ip != null) 
         hashCode = 29 * hashCode + ip.hashCode();
      if (this.accesstime != null) 
         hashCode = 29 * hashCode + accesstime.hashCode();
      if (this.volume != null) 
         hashCode = 29 * hashCode + volume.hashCode();
      if (this.language != null) 
         hashCode = 29 * hashCode + language.hashCode();
      if (this.regTime != null) 
         hashCode = 29 * hashCode + regTime.hashCode();
      if (this.className != null) 
         hashCode = 29 * hashCode + className.hashCode();
      if (this.studentNumber != null) 
         hashCode = 29 * hashCode + studentNumber.hashCode();
      if (this.school != null) 
         hashCode = 29 * hashCode + school.hashCode();
      if (this.pkuAccount != null) 
         hashCode = 29 * hashCode + pkuAccount.hashCode();
      if (this.pkuAccountValid != null) 
         hashCode = 29 * hashCode + pkuAccountValid.hashCode();
      hashCode = 29 * hashCode + (new Integer(pkuAccountStatus)).hashCode();
      if (this.pkuAccountRegTime != null) 
         hashCode = 29 * hashCode + pkuAccountRegTime.hashCode();
      hashCode = 29 * hashCode + (new Integer(grade)).hashCode();
      return hashCode;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString() {
      StringBuffer ret = new StringBuffer();
      ret.append( "com.unlimited.oj.common.user.User: " );
      ret.append( "id='" + id + "'");
      ret.append( "username='" + username + "'");
      ret.append( "nick='" + nick + "'");
      ret.append( "password='" + password + "'");
      ret.append( "confirmPassword='" + confirmPassword + "'");
      ret.append( "oldPassword='" + oldPassword + "'");
      ret.append( "passwordHint='" + passwordHint + "'");
      ret.append( "firstName='" + firstName + "'");
      ret.append( "lastName='" + lastName + "'");
      ret.append( "email='" + email + "'");
      ret.append( "phoneNumber='" + phoneNumber + "'");
      ret.append( "website='" + website + "'");
      ret.append( "version='" + version + "'");
      ret.append( "enabled='" + enabled + "'");
      ret.append( "accountExpired='" + accountExpired + "'");
      ret.append( "accountLocked='" + accountLocked + "'");
      ret.append( "credentialsExpired='" + credentialsExpired + "'");
      ret.append( "submit='" + submit + "'");
      ret.append( "solved='" + solved + "'");
      ret.append( "ip='" + ip + "'");
      ret.append( "accesstime='" + accesstime + "'");
      ret.append( "volume='" + volume + "'");
      ret.append( "language='" + language + "'");
      ret.append( "regTime='" + regTime + "'");
      ret.append( "className='" + className + "'");
      ret.append( "studentNumber='" + studentNumber + "'");
      ret.append( "school='" + school + "'");
      ret.append( "pkuAccount='" + pkuAccount + "'");
      ret.append( "pkuAccountValid='" + pkuAccountValid + "'");
      ret.append( "pkuAccountStatus='" + pkuAccountStatus + "'");
      ret.append( "pkuAccountRegTime='" + pkuAccountRegTime + "'");
      ret.append( "grade='" + grade + "'");
      return ret.toString();
   }

}