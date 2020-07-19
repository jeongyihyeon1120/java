package naverNews;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <pre>
 * naverNews 
 * GetArticle.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 19.
 * @author : yhyeon
 * @version : v1.0
 */
public class GetNews {

	public ArrayList<String> getArticle(String sid1, String sid2) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			String title = "";
			String script = "";
			String url = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=sec&sid1=" + sid1 + "&sid2=" + sid2;
			Document doc = Jsoup.connect(url).get();
			Element news = doc.select("div.list_body").get(0);
			Elements result = news.select("dt");
			for (Element element : result) {
				if(title.equals(element.text())) continue;
				title = element.text();
				String html = element.getElementsByAttribute("href").attr("href");
				Document inside = Jsoup.connect(html).get();
				Elements scripts = inside.select("#articleBodyContents");
				for (Element e : scripts) {
					if(script.equals(e.text())) continue;
					script = e.text();
					arr.add(script);
				}
			}
			return arr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
