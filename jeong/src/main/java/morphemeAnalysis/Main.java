package morphemeAnalysis; 

import java.util.ArrayList;

import naverNews.GetNews;
/**
 * <pre>
 * morphemeAnalysis 
 * Main.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 19.
 * @author : yhyeon
 * @version : v1.0
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetNews gn = new GetNews();
		ArrayList<String> arr = gn.getArticle("100", "269");
		
	}

}
