package org.fireking.xiukemeitu.data.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ͼƬ�б�
 *
 * @author Administrator
 */
public class MeinvListBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ��Ӧ���͵�ͼƬ
     */
    private List<ImageBean> beans = new ArrayList<ImageBean>();

    /**
     * ��ҳ��
     */
    private int pageCount;

    /**
     * ҳ����ת��ַ
     */
    private String pageUri;

    public List<ImageBean> getBeans() {
        return beans;
    }

    public void setBeans(List<ImageBean> beans) {
        this.beans = beans;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageUri() {
        return pageUri;
    }

    public void setPageUri(String pageUri) {
        this.pageUri = pageUri;
    }

    public MeinvListBean(List<ImageBean> beans, int pageCount, String pageUri) {
        super();
        this.beans = beans;
        this.pageCount = pageCount;
        this.pageUri = pageUri;
    }

    @Override
    public String toString() {
        return "MeinvListBean [beans=" + beans + ", pageCount=" + pageCount
                + ", pageUri=" + pageUri + "]";
    }

}
