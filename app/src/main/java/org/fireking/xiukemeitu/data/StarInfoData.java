package org.fireking.xiukemeitu.data;

import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.data.bean.StarBean;
import org.fireking.xiukemeitu.data.bean.StarInfoBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ů��������
 * 
 * @author Administrator
 *
 */
public class StarInfoData {

	/**
	 * ��ȡ�Ƽ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getRecomStarInfo(String uri) throws Exception {
		List<ImageBean> beans = new ArrayList<ImageBean>();
		Document doc = Jsoup
				.connect(Config.BaseUri + uri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element tbox1 = doc.select("dl[class=tbox1]").first();
		Element dd = tbox1.select("dd").first();
		Elements lis = dd.select("li");
		for (int i = 0; i < lis.size(); i++) {
			Element li = lis.get(i);
			beans.add(new ImageBean(li.select("a").first().select("img")
					.first().attr("alt"), li.select("a").first().attr("href"),
					li.select("a").first().select("img").first().attr("src")));
		}
		return beans;
	}

	/**
	 * ��ȡ��Ů���ǵ���ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public StarInfoBean getDetail(String uri) throws Exception {
		Document doc = Jsoup
				.connect(Config.BaseUri + uri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element listbox = doc.select("div[class=listbox]").first();
		Elements lis = listbox.select("li");
		List<ImageBean> images = new ArrayList<ImageBean>();
		for (int i = 0; i < lis.size(); i++) {
			Element li = lis.get(i);
			images.add(new ImageBean(li.select("a").first().select("img")
					.first().attr("alt"), li.select("a").first().attr("href"),
					li.select("a").first().select("img").first().attr("src")));
		}
		String title = doc.select("div[class=spectxt]").text();
		int length = doc.select("div[class=sepctitle]").text().length();
		title = title.substring(length, title.length());
		String more = doc.select("div[class=spectxt]").first()
				.select("div[class=more]").text();
		String desc = title.substring(0, title.length() - more.length());
		StarInfoBean info = new StarInfoBean(desc, images);
		return info;
	}

	/**
	 * ��ȡ�����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<StarBean> getStarList(String uri) throws Exception {
		List<StarBean> beans = new ArrayList<StarBean>();
		Document doc = Jsoup
				.connect(Config.BaseUri + uri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element listbox = doc.select("div[class=listbox]").first();
		Elements lis = listbox.select("li");
		for (int i = 0; i < lis.size(); i++) {
			Element li = lis.get(i);
			beans.add(new StarBean(li.select("a").first().attr("href"), li
					.select("a").first().text()));
		}
		return beans;
	}
}
