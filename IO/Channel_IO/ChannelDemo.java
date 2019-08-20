import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.*;

public class ChannelDemo{

    private static final String SOURCE = "C:\\git\\source.txt";
    private static final String TARGET = "C:\\git\\target.txt";

    public static void main(String[] args){
        try(FileChannel in = new FileInputStream(SOURCE).getChannel();
            FileChannel out = new FileOutputStream(TARGET).getChannel()){
                ByteBuffer buff = ByteBuffer.allocate((int)in.size());  // 為 ByteBuffer 分配跟檔案一致的大小。
                in.read(buff); // 直接全部讀取出來。
                buff.position(0); // 設定 buff 輸出的開始位置。
                out.write(buff); // 直接一口氣寫入。
            }catch (Exception ex){
                ex.printStackTrace();
                }
    }
            
}