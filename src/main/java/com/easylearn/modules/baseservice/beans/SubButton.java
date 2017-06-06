package com.easylearn.modules.baseservice.beans;

import java.util.List;

public class SubButton {
	private String name;
	private List<CommProperty> sub_button;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CommProperty> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<CommProperty> sub_button) {
		this.sub_button = sub_button;
	}

}
