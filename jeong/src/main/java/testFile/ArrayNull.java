package testFile;

import java.util.ArrayList;

/**
 * <pre>
 * testFile 
 * ArrayNull.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 15.
 * @author : yhyeon
 * @version : v1.0
 */
public class ArrayNull {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			a.add(null);
		}
		System.out.println(a.size());
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}

}
