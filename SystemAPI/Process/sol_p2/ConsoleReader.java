import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ConsoleReader implements Runnable {
    private static Charset sysCharset = Charset.forName("cp950");
    private InputStream console;

    public ConsoleReader(InputStream console){
        this.console = console;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(console, sysCharset))){
            reader.lines().forEach(msg ->{
                System.out.println("console : " + msg.strip());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}