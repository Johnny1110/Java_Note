package NIO.WatchServiceTest;

import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class MyWatchService implements Runnable {

    //-- WatchService 可以監控 Path 的異動。 --//
    private WatchService watchService;

    public MyWatchService(WatchService watchService){
        this.watchService = watchService;
    }

    @Override
    public void run() {
        try {
            WatchKey key = watchService.take();
            while (key != null){
                for(WatchEvent event : key.pollEvents()){
                    System.out.printf("事件發生 : %s \t %s \n",event.kind(), event.context());
                }
                key.reset(); // 重置 key。
                key = watchService.take(); // 不斷的取出 key。
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
