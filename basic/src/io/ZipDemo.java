package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipDemo {
    public static void main(String[] args) throws IOException, InterruptedException {


Path source = Paths.get("D:\\ideaProjects");
Path target = Paths.get("D:\\新建文件夹");

Files.walk(source).forEach(path -> {
    //先得到path相对source的路径，然后再得到拷贝完成后它再target中的路径应该是什么
    Path p = target.resolve(source.relativize(path));

    try {
        if (Files.isDirectory(path)) {
            Files.createDirectories(p);
        } else {
            Files.copy(path, p);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
});


    }
}
