package com.sdkj.pmodel;

import java.sql.Timestamp;

import com.sdkj.util.ZJ_UrlUtils;


public class ArticleModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String cid;
	private String cname;
	private String articleId;
	private String title;
	private String content;
	private String pic;
	private String bigpic;
	private String tip;
	private Integer sort;
	private Timestamp createTime;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		content = ZJ_UrlUtils.changeURL(content);
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		pic = ZJ_UrlUtils.changeURL(pic);
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBigpic() {
		return bigpic;
	}

	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public ArticleModel(String cid, String cname, String articleId,
			String title, String content, String pic, String bigpic,
			String tip, Integer sort, Timestamp createTime) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.pic = pic;
		this.bigpic = bigpic;
		this.tip = tip;
		this.sort = sort;
		this.createTime = createTime;
	}

	public ArticleModel() {
		super();
	}

	@Override
	public String toString() {
		return "ArticleModel [cid=" + cid + ", cname=" + cname + ", articleId="
				+ articleId + ", title=" + title + ", content=" + content
				+ ", pic=" + pic + ", bigpic=" + bigpic + ", tip=" + tip
				+ ", sort=" + sort + ", createTime=" + createTime + "]";
	}
	
}