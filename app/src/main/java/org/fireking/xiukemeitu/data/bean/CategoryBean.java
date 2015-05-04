package org.fireking.xiukemeitu.data.bean;

import java.io.Serializable;

/**
 * ����
 * 
 * @author Administrator
 *
 */
public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// �����Ӧuri
	private String uri;
	// �����Ӧ����
	private String title;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CategoryBean(String uri, String title) {
		super();
		this.uri = uri;
		this.title = title;
	}

	@Override
	public String toString() {
		return "CategoryBean [uri=" + uri + ", title=" + title + "]";
	}

}
