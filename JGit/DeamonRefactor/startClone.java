import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

public class startClone {
	
	final static String LOCAL_PATH = "/home/Johnny/laboratory/testCloneRepo";
	final static String URL = "https://github.com/RickyJian/Go_Note.git";
	
	public static void StartClone() throws InterruptedException, NoHeadException, IOException, GitAPIException {
		CloneRepoThread cloneRepo = new CloneRepoThread(LOCAL_PATH, URL);
		TimerThread timer = new TimerThread();
		timer.setDaemon(true); // 設定為守護進程
		cloneRepo.start();
		timer.start();
		cloneRepo.join(); // join() 是等到 cloneRepo 執行緒結束之後再繼續往下走，不然下面 walkcommit 會提前執行(根本沒 clone 完，哪來的 commit)。

		//timer.exit = false; // 使用 volatile 動態改變 TimerThread 物件的 exit 值。
		
		//----- print git commit ------//
		WalkCommit wc = new WalkCommit();
		wc.printAllCommit(LOCAL_PATH);
	}
	
	public static void main(String[] args) throws InterruptedException, NoHeadException, IOException, GitAPIException {
		StartClone();
	}
}