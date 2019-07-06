package main;

import java.util.Timer;
import java.util.TimerTask;

public class TimerThread extends Thread {

	public static volatile boolean exit = true;

	private int timeSec = -1;

	@Override
	public void run() {

		System.out.println("...... starting clone ......");

		while (exit) {
			timeSec += 1;
			System.out.println("等待 clone 時間 : " + timeSec + " 秒......"); // 輸出等待時間。
			
			synchronized (this) { 
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 使用 wait() 方法，必須要在synchronized函數下使用。
		}

		System.out.println("...... finished ......");
	}

}
