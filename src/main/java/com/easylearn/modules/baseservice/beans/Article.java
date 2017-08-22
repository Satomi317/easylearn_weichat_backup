package com.easylearn.modules.baseservice.beans;

public class Article {
	/*
	 * 图文消息名称
	 */
	private String Title;
	/*
	 * 图文消息描述
	 */
	private String Description;
	
	/*
	 * 图文链接，支持jpg，png格式较好的效果为大图640*320，小图80*80，限制图片的链接域名需要与开发者填写的基本资料中的url一致
	 */
	private String PicUrl;
	/*
	 * 点击图片消息跳转链接
	 */
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getPicurl() {
		return PicUrl;
	}

	public void setPicurl(String picurl) {
		this.PicUrl = picurl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		this.Url = url;
	}
}
