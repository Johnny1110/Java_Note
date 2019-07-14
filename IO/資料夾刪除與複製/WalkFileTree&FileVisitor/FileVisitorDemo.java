import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorDemo extends SimpleFileVisitor<Path> {

    private Path from;
    private Path to;
    private int fromSize;
    private int toSize;


    public FileVisitorDemo(Path from, Path to){
        this.from = from.toAbsolutePath();
        this.to = to.toAbsolutePath();
        this.fromSize = from.getNameCount();
        this.toSize = to.getNameCount();
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException {
        copyFileAction(path);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attributes) throws IOException {
        copyFileAction(path);
        return FileVisitResult.CONTINUE;
    }

    private void copyFileAction(Path path) throws IOException {
        Path target = to.resolve(path.subpath(fromSize-1,path.getNameCount()));
        System.out.printf("Copy action : copy from %s \t to \t %s \n", path, target);
        Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
    }


}
