package org.fireking.xiukemeitu.data.bean;

import java.io.Serializable;

/**
 * ͼƬʵ����
 * 
 * @author Administrator
 *
 */
public class ImageBean implements Serializable {
	// ����
	private String title;
	// ��תuri
	private String uri;
	// ͼƬ��ַ
	private String image;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ImageBean [title=" + title + ", uri=" + uri + ", image="
				+ image + "]";
	}

	public ImageBean(String title, String uri, String image) {
		super();
		this.title = title;
		this.uri = uri;
		this.image = image;
	}

}
