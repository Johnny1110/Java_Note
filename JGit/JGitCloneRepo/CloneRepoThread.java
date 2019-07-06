package main;

import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class CloneRepoThread extends Thread {
	
	private String localPath;
	private String url;
	
	public CloneRepoThread(String localPath, String url) {
		this.localPath = localPath;
		this.url = url;
	}

	@Override
	public void run(){
		File destination = new File(localPath);
		CloneCommand cc = Git.cloneRepository().setURI(url).setDirectory(destination);
		try {
			cc.call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		// call() 方法是用來執行建置好的 git 命令

	}
}
