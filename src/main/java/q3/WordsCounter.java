package q3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class WordsCounter {

    // ===========================================================================

    private ConcurrentHashMap<String, AtomicLong> counts = new ConcurrentHashMap<>();
    private final int POOL_SIZE = 20;
    ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    // ===========================================================================
    // utils

    private void updateFreqs(String fp) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fp))) {
            stream
                    .map(line -> line.trim().split("\\s+"))
                    .flatMap(arr -> Stream.of(arr))
                    .forEach(s -> counts.computeIfAbsent(s.toLowerCase(), k -> new AtomicLong())
                                        .getAndIncrement());
        }
    }

    class Task implements Runnable{

        private String path;

        public Task(String s) {
            path = s;
        }

        @Override
        public void run() {
            try {
                updateFreqs(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ===========================================================================
    // API

    public void load(String... files) throws InterruptedException {
        Arrays.asList(files).forEach(s -> pool.execute(new Task(s)));
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
    }

    public void displayStatus() {
        counts.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.print("** total: " + counts.values().stream().mapToInt(AtomicLong::intValue).sum());
    }

    // ===========================================================================

    public static void main(String[] args) throws InterruptedException {
        WordsCounter wc = new WordsCounter();
        wc.load(args);
        wc.displayStatus();
    }
}
