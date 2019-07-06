package main;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class WalkAllCommits {

	public static void main(String[] args) throws IOException, GitAPIException {
        Repository repository = getLocalRepo();
        Git git = new Git(repository);
        Iterable<RevCommit> commits = git.log().all().call();
        commits.forEach(commit -> 
		System.out.println("Commit 紀錄: " + commit.getFullMessage() + commit.getAuthorIdent())
			);
    }

	public static Repository getLocalRepo() throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		File f = new File("c://git//JGit//.git");
		return builder.readEnvironment().setGitDir(f).build(); // 建立一個Repository
	}
}
