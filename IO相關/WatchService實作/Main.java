package NIO.WatchServiceTest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {

    private static final String route = "/home/Johnny/laboratory/walkFileTree"; //被監控的路徑

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(route);
        WatchService watchService = path.getFileSystem().newWatchService(); // 從 Path 取得 watchService 實例。
        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE); // 注冊 WatchService 以及 異動事件類型。

        Runnable task = new MyWatchService(watchService);
        Thread thread = new Thread(task);
        thread.start();
    }
}
