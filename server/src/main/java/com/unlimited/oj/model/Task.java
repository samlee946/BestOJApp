/***********************************************************************
 * Module:  Task.java
 * Author:  develop
 * Purpose: Defines the Class Task
 ***********************************************************************/

package com.unlimited.oj.model;

import javax.persistence.*;
import com.unlimited.oj.model.*;
import java.util.*;

@Entity(name="Task")
@Table(name="Task")
public class Task implements java.io.Serializable {
   private Long id;
   /** 任务的名称 */
   private java.lang.String title;
   /** 任务创建的时间 */
   private Date inDate;
   private java.lang.String author;
   
   /**
    * Empty constructor which is required by EJB 3.0 spec.
    *
    */
   public Task() {
      // TODO Add your own initialization code here.
   }
   
   /**
    * Get value of id
    *
    * @return id 
    */
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable=false, insertable=true, updatable=true)
   public Long getId()
   {
      return id;
   }
   
   /**
    * Set value of id
    *
    * @param newId 
    */
   public void setId(Long newId)
   {
      this.id = newId;
   }
   
   /**
    * Get value of title
    *
    * @return title 
    */
   @Basic(optional=true)
   @Column(name="title", insertable=true, updatable=true, length=50)
   public java.lang.String getTitle()
   {
      return title;
   }
   
   /**
    * Set value of title
    *
    * @param newTitle 
    */
   public void setTitle(java.lang.String newTitle)
   {
      this.title = newTitle;
   }
   
   /**
    * Get value of inDate
    *
    * @return inDate 
    */
   @Basic(optional=true)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="inDate", insertable=true, updatable=true)
   public Date getInDate()
   {
      return inDate;
   }
   
   /**
    * Set value of inDate
    *
    * @param newInDate 
    */
   public void setInDate(Date newInDate)
   {
      this.inDate = newInDate;
   }
   
   /**
    * Get value of author
    *
    * @return author 
    */
   @Basic(optional=true)
   @Column(name="author", insertable=true, updatable=true, length=50)
   public java.lang.String getAuthor()
   {
      return author;
   }
   
   /**
    * Set value of author
    *
    * @param newAuthor 
    */
   public void setAuthor(java.lang.String newAuthor)
   {
      this.author = newAuthor;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object other) {
   
      if (other == null)
         return false;
      
      if (other == this)
         return true;   
   
      if (!(other instanceof Task))
         return false;
   
      Task cast = (Task) other;
   
      
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
      if (this.title != null) 
         hashCode = 29 * hashCode + title.hashCode();
      if (this.inDate != null) 
         hashCode = 29 * hashCode + inDate.hashCode();
      if (this.author != null) 
         hashCode = 29 * hashCode + author.hashCode();
      return hashCode;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString() {
      StringBuffer ret = new StringBuffer();
      ret.append( "com.unlimited.oj.task.Task: " );
      ret.append( "id='" + id + "'");
      ret.append( "title='" + title + "'");
      ret.append( "inDate='" + inDate + "'");
      ret.append( "author='" + author + "'");
      return ret.toString();
   }

}