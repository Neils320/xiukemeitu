package org.fireking.xiukemeitu.data.bean;

import java.io.Serializable;

public class StarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uri;
	private String name;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StarBean(String uri, String name) {
		super();
		this.uri = uri;
		this.name = name;
	}

	@Override
	public String toString() {
		return "StarBean [uri=" + uri + ", name=" + name + "]";
	}

}
