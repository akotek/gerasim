package q3;


import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;


public class WordCounterTest {

    private final String PATH = "src/test/java/q3/files";

    private WordsCounter wc;

    @Before
    public void setup() {
        wc = new WordsCounter();
    }

    @Test
    public void testSingleFile() throws InterruptedException, IOException {
        String fp1 = PATH + "/File1";
        wc.load(fp1);
        System.setOut(new PrintStream(new File(fp1 + "_output")));
        wc.displayStatus();
        assertTrue(FileUtils.contentEquals(new File(fp1 + "_output"), new File(fp1 + "_expected")));
    }

    @Test
    public void testMultipleFiles() throws IOException, InterruptedException {
        String fp1 = PATH + "/AllFiles";
        wc.load(PATH + "/File1", PATH + "/File2", PATH + "/File3", PATH + "/File4", PATH + "/File5");
        System.setOut(new PrintStream(new File(fp1 + "_output")));
        wc.displayStatus();
        assertTrue(FileUtils.contentEquals(new File(fp1 + "_output"), new File(fp1 + "_expected")));
    }

    @Test
    public void testEmptyFile() throws InterruptedException, IOException {
        String fp1 = PATH + "/File5";
        wc.load(fp1);
        System.setOut(new PrintStream(new File(fp1 + "_output")));
        wc.displayStatus();
        assertTrue(FileUtils.contentEquals(new File(fp1 + "_output"), new File(fp1 + "_expected")));
    }

}
