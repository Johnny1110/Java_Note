package main;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class WalkCommit {
	
	public void printAllCommit(String localPath) throws IOException, NoHeadException, GitAPIException {
		Repository localRepo = getLocalRepo(localPath);
		Git git = new Git(localRepo);
		Iterable<RevCommit> commits = git.log().all().call();
		commits.forEach(commit -> 
			System.out.println("Commit 紀錄: " + commit.getFullMessage() + commit.getAuthorIdent())
				);
	}
	
	public static Repository getLocalRepo(String localPath) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        File f = new File(localPath + "//.git"); // 這裡要的是 .git 資料夾
        return builder.readEnvironment()
                	  .setGitDir(f)
                      .build(); // 建立一個Repository
    }
}
