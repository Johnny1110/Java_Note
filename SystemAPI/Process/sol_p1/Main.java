import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main {
    private static final String CMD = "python D:/buffer/logger.py";
    private static final Charset WIN32_CHARSET = Charset.forName("cp950");

    public static void main(String[] args) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(CMD);
        int exitCode = process.waitFor();
        System.out.println("exitCode : " + exitCode);

        try(BufferedReader printerReader = new BufferedReader(new InputStreamReader(process.getInputStream(), WIN32_CHARSET));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))){

            printerReader.lines().forEach(msg ->{
                System.out.println("python console : " + msg);
            });

            errorReader.lines().forEach(msg ->{
                System.out.println("python error : " + msg);
            });
        }
    }
}