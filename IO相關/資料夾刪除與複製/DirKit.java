package NIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirKit {

    public void deleteDir(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            if(isDirEmpty(path)){
                Files.deleteIfExists(path);
            }else{
                for(File f : path.toFile().listFiles()){
                    deleteDir(f.toPath());
                }
                Files.deleteIfExists(path);
            }
        }else {
            Files.deleteIfExists(path);
        }
    }

    public void copyDir(Path from, Path to){
        // -- 尚未實作 -- //
    }

    private boolean isDirEmpty(Path path) {
        return path.toFile().list().length == 0;
    }
}
