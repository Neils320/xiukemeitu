package org.fireking.xiukemeitu.data.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * �Ƽ�ͼƬ
 * 
 * @author Administrator
 *
 */
public class RecomBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * �Ƽ������б�
	 */
	private List<ImageBean> images = new ArrayList<ImageBean>();
	/**
	 * ����uri
	 */
	private String uri;
	/**
	 * ͼƬ�������
	 */
	private String title;

	public List<ImageBean> getImages() {
		return images;
	}

	public void setImages(List<ImageBean> images) {
		this.images = images;
	}

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

	public RecomBean(List<ImageBean> images, String uri, String title) {
		super();
		this.images = images;
		this.uri = uri;
		this.title = title;
	}

}
