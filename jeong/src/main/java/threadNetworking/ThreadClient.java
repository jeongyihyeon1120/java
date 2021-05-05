package threadNetworking;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreadClient {

	public boolean gameDetect() throws IOException {
		List<String> gameList = Arrays.asList("leagueclientuxrender.exe", "MapleStory.exe", "Valorant.exe",
				"TslGame.exe", "overwatch", "fifa", "MinecraftLauncher.exe");
		Process process = Runtime.getRuntime().exec("tasklist.exe");
		Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
		ArrayList<Boolean> bool = new ArrayList<Boolean>();
		while (scanner.hasNext()) {
			String list = scanner.nextLine();
			for (int i = 0; i < gameList.size(); i++) {
				if (list.contains(gameList.get(i))) {
					bool.add(true);
				} else {
					bool.add(false);
				}
			}
		}
		scanner.close();
		return bool.contains(true);
	}

	public static void main(String[] args) {
		ThreadClient tc = new ThreadClient();

		Scanner input = new Scanner(System.in);

		Socket CS = null;
		try {
			CS = new Socket();

			System.out.print("Login? (학번을 입력하세요): ");
			String temp = input.nextLine() + "0";

			CS.connect(new InetSocketAddress("59.12.245.249", 30001));
			System.out.println("SUCCESS");

			byte[] as1 = temp.getBytes("UTF-8");
			OutputStream OS1 = CS.getOutputStream();
			OS1.write(as1);

			String message;
			while (true) {
				if (tc.gameDetect())
					message = "Yes";
				else
					message = "No";
				byte[] as = message.getBytes("UTF-8");
				OutputStream OS = CS.getOutputStream();
				OS.write(as);
				Thread.sleep(10000);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			CS.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}