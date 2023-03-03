package com.ezic.global.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUtils {
    public static ExecutorService executorService;

    public static void csvUpload(String path) throws IOException {
        path = String.valueOf(Paths.get(path));
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Path.of(path), EnumSet.of(StandardOpenOption.READ), executorService);

        ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
        long position = 0;

        fileChannel.read(buffer, position, buffer, new CompletionHandler<>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

                attachment.flip();

                Charset charset = Charset.defaultCharset();
                String data = charset.decode(buffer).toString();

                attachment.clear();

                System.out.println(data);

                try {
                    fileChannel.close();
                } catch (IOException e) {

                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
                try {
                    fileChannel.close();
                } catch (IOException e) {

                }
            }
        });

        executorService.shutdown();
    }

    public static void saveFile(String saveName, MultipartFile file, String directoryPath) throws IOException {
        Path directory = Paths.get(directoryPath).toAbsolutePath().normalize();

        Files.createDirectories(directory);

        String fileName = StringUtils.cleanPath(saveName);

        Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
        Path targetPath = directory.resolve(saveName).normalize();

        Assert.state(!Files.exists(targetPath), saveName + " File alerdy exists.");
        file.transferTo(targetPath);
    }

    public static void deleteFile(String seq, String path) {
        File file = new File(path + "/" + seq);

        if(file.exists()) {
            file.delete();
        }
    }
}
