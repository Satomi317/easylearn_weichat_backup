package com.easylearn.modules.baseservice.beans;

public class Article {
	/*
	 * 图文消息名称
	 */
	private String title;
	/*
	 * 图文消息描述
	 */
	private String description;
	
	/*
	 * 图文链接，支持jpg，png格式较好的效果为大图640*320，小图80*80，限制图片的链接域名需要与开发者填写的基本资料中的url一致
	 */
	private String picurl;
	/*
	 * 点击图片消息跳转链接
	 */
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
