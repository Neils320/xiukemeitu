package org.fireking.xiukemeitu.data.bean;

import java.io.Serializable;

public class ImageDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private int count;
	private String desc;
	private String images;

	public ImageDetail(String title, int count, String desc, String images) {
		super();
		this.title = title;
		this.count = count;
		this.desc = desc;
		this.images = images;
	}

	@Override
	public String toString() {
		return "ImageDetail [title=" + title + ", count=" + count + ", desc="
				+ desc + ", images=" + images + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
}
