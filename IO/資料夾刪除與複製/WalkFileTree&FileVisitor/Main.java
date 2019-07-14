import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path from = Paths.get("/home/Johnny/laboratory/simpleDir");
        Path to = Paths.get("/home/Johnny/laboratory/copyDir");

        Copyer copyer = new Copyer(from, to);
        copyer.startCopy();
    }
}
