package org.fireking.xiukemeitu.data;

import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.data.bean.RecomBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ȡ�Ƽ�����
 * 
 * @author Administrator
 *
 */
public class RecomData {

	/**
	 * ��ȡ���ͼ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getAd() throws Exception {
		List<ImageBean> beans = new ArrayList<ImageBean>();
		Document doc = Jsoup
				.connect(Config.BaseUri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element slideJs = doc.select("div[class=slideJs]").first();
		Elements lis = slideJs.select("ul").first().select("li");
		for (int i = 0; i < lis.size(); i++) {
			Element li = lis.get(i);
			beans.add(new ImageBean(Config.BaseUri
					+ li.select("a").first().select("img").first().attr("alt"),
					Config.BaseUri + li.select("a").first().attr("href"),
					Config.BaseUri
							+ li.select("a").first().select("img").first()
									.attr("src")));
		}
		return beans;
	}

	/**
	 * ��ȡ�Ƽ����ݼ���
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<RecomBean> getRecomList() throws IOException {
		List<RecomBean> beans = new ArrayList<RecomBean>();
		Document doc = Jsoup
				.connect(Config.BaseUri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element pleft = doc.select("div[class=pleft]").first();
		Elements picnews = pleft.select("div[class=picnews]");
		for (int i = 0; i < picnews.size(); i++) {
			Element picnew = picnews.get(i);
			Element dt = picnew.select("dl[class=tbox1 light]").first()
					.select("dt[class=light]").first();
			Element dd = picnew.select("dl[class=tbox1 light]").first()
					.select("dd[class=light]").first();
			Elements lis = dd.select("ul[class=e1]").first().select("li");
			List<ImageBean> images = new ArrayList<ImageBean>();
			for (int j = 0; j < lis.size(); j++) {
				Element li = lis.get(j);
				images.add(new ImageBean(Config.BaseUri
						+ li.select("a").first().select("img").attr("alt"),
						Config.BaseUri + li.select("a").first().attr("href"),
						Config.BaseUri
								+ li.select("a").first().select("img")
										.attr("src")));
			}
			beans.add(new RecomBean(images, Config.BaseUri
					+ dt.select("span[class=more]").first().select("a").first()
							.attr("href"), dt.select("span[class=more]")
					.first().select("a").first().text()));
		}
		return beans;
	}
}
