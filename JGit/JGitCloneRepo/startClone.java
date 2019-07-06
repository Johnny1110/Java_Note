package main;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

import main.CloneRepoThread;
import main.TimerThread;

public class startClone {
	
	final static String LOCAL_PATH = "c://git//JGit";
	final static String URL = "https://github.com/RickyJian/Go_Note.git";
	
	public static void StartClone() throws InterruptedException, NoHeadException, IOException, GitAPIException {
		CloneRepoThread cloneRepo = new CloneRepoThread(LOCAL_PATH, URL);
		TimerThread timer = new TimerThread();
		cloneRepo.start();
		timer.start();
		cloneRepo.join(); // join() 是等到 cloneRepo 執行緒死亡之後再繼續往下走。

		timer.exit = false; // 使用 volatile 動態改變 TimerThread 物件的 exit 值。
		
		//----- print git commit ------//
		WalkCommit wc = new WalkCommit();
		wc.printAllCommit(LOCAL_PATH);
	}
	
	public static void main(String[] args) throws InterruptedException, NoHeadException, IOException, GitAPIException {
		StartClone();
	}
}


//-----------------註記------------------//

// JGit 不能單獨使用，它有依賴的 jar 檔。

// 這裡我所引用的 jar 檔為 slf4j-api-1.8.0-beta2.jar。

// 這邊使用執行緒概念來印出 clone 時間，受益良多。

//-----------------註記------------------//
