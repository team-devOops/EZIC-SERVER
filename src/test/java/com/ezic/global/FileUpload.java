package com.ezic.global;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUpload implements CompletionHandler<Integer, ByteBuffer> {
    private Path path;
    private ExecutorService executorService;

    @BeforeEach
    void init() {
        path = Paths.get("src/test/resources/EZIC.csv");
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Test
    void test() throws IOException {
        long startTime = System.nanoTime();

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, EnumSet.of(StandardOpenOption.READ), executorService);

        ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
        long position = 0;

        fileChannel.read(buffer, position, buffer, new CompletionHandler<>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                result = -1;
                try {
                    if(result == -1) throw new IOException();

                    attachment.flip();

                    Charset charset = Charset.defaultCharset();
                    String data = charset.decode(buffer).toString();

                    attachment.clear();

                    fileChannel.close();
                } catch (IOException e) {
                    long endTime = System.nanoTime();
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
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }

    @AfterEach
    void close() {
        executorService.shutdown();
    }
}
