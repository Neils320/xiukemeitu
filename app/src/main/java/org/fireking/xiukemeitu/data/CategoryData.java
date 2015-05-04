package org.fireking.xiukemeitu.data;

import java.util.ArrayList;
import java.util.List;

import org.fireking.xiukemeitu.data.bean.ImageBean;
import org.fireking.xiukemeitu.data.bean.MeinvListBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ���շ����ַ��ȡͼƬ
 * 
 * @author Administrator
 *
 */
public class CategoryData {

	/**
	 * ��ȡ�ձ���Ů
	 * 
	 * @return
	 * @throws Exception
	 */
	public MeinvListBean getList(String uri) throws Exception {
		Document doc = Jsoup
				.connect(Config.BaseUri + uri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element listbox = doc.select("div[class=listbox]").first();
		Elements lis = listbox.select("ul[class=e8]").first().select("li");
		List<ImageBean> images = new ArrayList<ImageBean>();
		for (int i = 0; i < lis.size(); i++) {
			Element li = lis.get(i);
			images.add(new ImageBean(li.select("a").first().select("img")
					.first().attr("alt"), li.select("a").first().attr("href"),
					li.select("a").first().select("img").first().attr("src")));
		}
		Element pageinfo = listbox.select("div[class=pageinfo]").first();
		String pageCount = pageinfo.select("a").last().attr("href");
		String tempUri = "";
		if (pageCount.contains(".")) {
			String temp = pageCount.substring(0, pageCount.indexOf("."));
			if (temp.contains("_")) {
				String[] temps = temp.split("_");
				if (temps.length == 3) {
					pageCount = temps[2];
					tempUri = temps[0] + "_" + temps[1] + "_M" + ".html";
				}
			}
		}
		int count = 0;
		try {
			count = Integer.parseInt(pageCount);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		MeinvListBean meituList = new MeinvListBean(images, count, tempUri);
		return meituList;
	}

	public static void main(String[] args) {
		//

		CategoryData dao = new CategoryData();
		try {
			System.out.println(dao.getList("/meinvdapei/").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
