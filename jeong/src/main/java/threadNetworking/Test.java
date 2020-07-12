package threadNetworking;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * threadNetworking 
 * Test.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 8.
 * @author : yhyeon
 * @version : v1.0
 */
public class Test {

	public boolean gameDetect() throws IOException {
		List<String> gameList = Arrays.asList("LeagueClientUxRender.exe", "MapleStory.exe", "Valorant.exe");
		Process process = Runtime.getRuntime().exec("tasklist.exe");
	    Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
	    ArrayList<Boolean> bool = new ArrayList<Boolean>();
	    while (scanner.hasNext()) {
	    	String list = scanner.nextLine();
	    	for (int i = 0; i < gameList.size(); i++) {
				if (list.contains(gameList.get(i))) {
					bool.add(true);
				}else {
					bool.add(false);
				}
			}
	    }
	    scanner.close();
	    return bool.contains(true);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Test t = new Test();
		System.out.println(t.gameDetect());
	}
//	public static void main(String[] args) throws IOException {
//	       Process process = Runtime.getRuntime().exec("tasklist.exe");
//	       Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
//	       while (scanner.hasNext()) {
//	           System.out.println(scanner.nextLine());
//	       }
//	       scanner.close();
//	}

}
