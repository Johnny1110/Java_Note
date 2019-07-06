package NIO;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final String DELETE_DIR = "/home/Johnny/laboratory/testDir";
    private static final String FROM_DIR = "/home/Johnny/laboratory/buffer";
    private static final String TO_DIR = "/home/Johnny/laboratory/buffer_bak";

    private static DirKit dirKit = new DirKit();

    public static void main(String[] args) throws IOException {
        //-- 刪除 --//
        Path dir1 = Paths.get(DELETE_DIR);
        dirKit.deleteDir(dir1);

        //-- 複製 --//
        Path dir2 = Paths.get(FROM_DIR);
        Path dir3 = Paths.get(TO_DIR);
        dirKit.copyDir(dir2, dir3);
    }
}
