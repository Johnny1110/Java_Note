import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

    public void copyDir(Path from, Path to) throws IOException {
        try {
            System.out.printf("複製檔案 <%s> 到 <%s>\n", from, to);
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            if (from.toFile().isDirectory()) {
                for (String f : from.toFile().list()) {
                    copyDir(from.resolve(f), to.resolve(f));
                }
            }
        }catch (DirectoryNotEmptyException e){
            throw new DirectoryNotEmptyException("目標路經已有檔案或資料夾，請更換目標路徑。");
        }

    }

    private boolean isDirEmpty(Path path) {
        return path.toFile().list().length == 0;
    }
}

