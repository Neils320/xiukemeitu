package org.fireking.xiukemeitu.data.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StarInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String desc;
	private List<ImageBean> beans = new ArrayList<ImageBean>();

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<ImageBean> getBeans() {
		return beans;
	}

	public void setBeans(List<ImageBean> beans) {
		this.beans = beans;
	}

	public StarInfoBean(String desc, List<ImageBean> beans) {
		super();
		this.desc = desc;
		this.beans = beans;
	}

	@Override
	public String toString() {
		return "StarInfoBean [desc=" + desc + ", beans=" + beans + "]";
	}

}
