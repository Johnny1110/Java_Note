import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    private static final String CMD = "python D:/buffer/logger.py";
    private static final Charset WIN32_CHARSET = Charset.forName("cp950");

    public static void main(String[] args) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(CMD);

        new Thread(new ConsoleReader(process.getInputStream())).start();
        new Thread(new ConsoleReader(process.getErrorStream())).start();

        int exitCode = process.waitFor();
        System.out.println("exitCode : " + exitCode);
    }
}