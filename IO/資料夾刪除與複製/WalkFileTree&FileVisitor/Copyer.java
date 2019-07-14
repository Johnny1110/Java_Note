import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Copyer {
    private Path from;
    private Path to;


    public Copyer(Path from, Path to){
        this.from = from.toAbsolutePath();
        this.to = to.toAbsolutePath();
    }

    public void startCopy() throws IOException {
        FileVisitorDemo visitor = new FileVisitorDemo(from, to);
        if(!to.toFile().exists()) {
            Files.createDirectories(to);
        }
        Files.walkFileTree(from, visitor);
    }
}
