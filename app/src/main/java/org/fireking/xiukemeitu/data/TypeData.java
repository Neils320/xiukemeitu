package org.fireking.xiukemeitu.data;

import java.util.ArrayList;
import java.util.List;

import org.fireking.xiukemeitu.data.bean.CategoryBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ��ȡ�����Ϣ
 * 
 * @author Administrator
 *
 */
public class TypeData {

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getTypes() throws Exception {
		List<CategoryBean> beans = new ArrayList<CategoryBean>();
		Document doc = Jsoup
				.connect(Config.BaseUri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element navMenu = doc.select("div[id=navMenu]").first();
		Elements lis = navMenu.select("li");
		for (int i = 0; i < lis.size(); i++) {
			Element li = lis.get(i);
			beans.add(new CategoryBean(li.select("a").attr("href"), li.select(
					"a").text()));
		}
		return beans;
	}
}
