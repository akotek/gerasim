package q3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

public class WordsCounter {

    // ===========================================================================

    private ConcurrentHashMap<String, LongAdder> counts = new ConcurrentHashMap<>();
    private final int POOL_SIZE = 20;
    ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    // ===========================================================================
    // utils

    private void updateFreqs(String fp) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fp))) {
            stream
                    .map(line -> line.trim().split("\\s+"))
                    .flatMap(arr -> Stream.of(arr))
                    .forEach(s -> counts.computeIfAbsent(s.toLowerCase(), k -> new LongAdder())
                                        .increment());
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

    public void load(String... files) {
        Arrays.asList(files).forEach(s -> pool.execute(new Task(s)));
        pool.shutdown();
    }

    public void displayStatus() {
        System.out.println(counts.toString());
        System.out.println("** total: " + counts.values().stream().mapToInt(LongAdder::intValue).sum());
    }

    // ===========================================================================

    public static void main(String[] args) throws InterruptedException {
        WordsCounter wc = new WordsCounter();
        wc.load(args);
        Thread.sleep(4000); // replace with Threads.join()
        wc.displayStatus();
    }
}
