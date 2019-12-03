import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMDCaller {
    public static void main(String[] args) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("python ./RunThread.py");
        Process process = Runtime.getRuntime().exec(sb.toString());

        int exitCode = process.waitFor();
        System.out.println("exitCode : " + exitCode);

        try(BufferedReader printerReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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