package com.sdkj.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tarticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tarticle", catalog = "")
public class Tarticle implements java.io.Serializable {
		
	// Fields

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String articleId;
		private Tcolumn tcolumn;
		private String content;
		private String pic;
		private String bigpic;
		private String tip;
		private String title;
		private Integer sort;
		private Timestamp createTime;

		// Constructors

		/** default constructor */
		public Tarticle() {
		}

		/** minimal constructor */
		public Tarticle(String articleId) {
			this.articleId = articleId;
		}

		/** full constructor */
		public Tarticle(String articleId, Tcolumn tcolumn, String content,
				String pic, String bigpic, String tip, String title, Integer sort,
				Timestamp createTime) {
			super();
			this.articleId = articleId;
			this.tcolumn = tcolumn;
			this.content = content;
			this.pic = pic;
			this.bigpic = bigpic;
			this.tip = tip;
			this.title = title;
			this.sort = sort;
			this.createTime = createTime;
		}

		// Property accessors
		@Id
		@Column(name = "articleId", unique = true, nullable = false, length = 20)
		public String getArticleId() {
			return this.articleId;
		}

		public void setArticleId(String articleId) {
			this.articleId = articleId;
		}

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "cid")
		public Tcolumn getTcolumn() {
			return this.tcolumn;
		}

		public void setTcolumn(Tcolumn tcolumn) {
			this.tcolumn = tcolumn;
		}

		@Column(name = "content", length = 65535)
		public String getContent() {
			return this.content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		@Column(name = "pic")
		public String getPic() {
			return this.pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		@Column(name = "bigpic")
		public String getBigpic() {
			return this.bigpic;
		}

		public void setBigpic(String bigpic) {
			this.bigpic = bigpic;
		}

		@Column(name = "tip")
		public String getTip() {
			return this.tip;
		}

		public void setTip(String tip) {
			this.tip = tip;
		}

		@Column(name = "title")
		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Column(name = "createTime", length = 19)
		public Timestamp getCreateTime() {
			return this.createTime;
		}

		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}
		
		@Column(name = "sort")
		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}
	
}