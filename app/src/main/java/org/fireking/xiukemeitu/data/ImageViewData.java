package org.fireking.xiukemeitu.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fireking.xiukemeitu.data.bean.ImageDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ͼƬ��ϸԤ��
 * 
 * @author Administrator
 *
 */
public class ImageViewData {

	/**
	 * ��ϸԤ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public ImageDetail getDetailView(String uri) throws Exception {
		Document doc = Jsoup
				.connect(Config.BaseUri + uri)
				.header("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)")
				.get();
		Element viewbox = doc.select("div[class=viewbox]").first();
		Elements as = viewbox.select("div[class=pageinfo]").first().select("a");
		List<Integer> number = new ArrayList<Integer>();
		for (int i = 0; i < as.size(); i++) {
			Element a = as.get(i);
			String num = a.text();
			int t = 0;
			try {
				t = Integer.parseInt(num);
			} catch (NumberFormatException e) {
			}
			if (t != 0) {
				number.add(t);
			}
		}
		int max = Collections.max(number);
		ImageDetail detail = new ImageDetail(viewbox.select("div[class=title]")
				.first().select("h1").first().text(), max, viewbox
				.select("div[class=content]").first().select("img").first()
				.attr("alt"), viewbox.select("div[class=content]").first()
				.select("img").first().attr("src"));
		return detail;
	}
}
