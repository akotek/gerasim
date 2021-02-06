package q1;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


public class InvoiceParserTest {

    private final String PATH = "/Users/kotek/IdeaProjects/gerasim/src/test/java/q1/files"; // replace with some getPath()

    private final InvoiceParser p = new InvoiceParser7();

    // ===========================================================================
    // tests

    // Assuming valid given files (input.size is divisible by 4)

    @Test
    public void sanityTest() throws IOException {
        File f1 = p.parse(PATH + "/input_Q1a.txt", PATH + "/output_Q1a.txt");
        File f2 = new File(PATH + "/expected_Q1a.txt");
        assertTrue(FileUtils.contentEquals(f1, f2));
    }

    @Test
    public void testLegalFile() throws IOException {
        File f1 = p.parse(PATH + "/input_Q1a2.txt", PATH + "/output_Q1a2.txt");
        File f2 = new File(PATH + "/expected_Q1a2.txt");
        assertTrue(FileUtils.contentEquals(f1, f2));
    }

    @Test
    public void testIllegalFile() throws IOException {
        File f1 = p.parse(PATH + "/input_Q1b.txt", PATH + "/output_Q1b.txt");
        File f2 = new File(PATH + "/expected_Q1b.txt");
        assertTrue(FileUtils.contentEquals(f1, f2));
    }

    @Test
    public void testLegalIllegalFile() throws IOException {
        File f1 = p.parse(PATH + "/input_Q1b2.txt", PATH + "/output_Q1b2.txt");
        File f2 = new File(PATH + "/expected_Q1b2.txt");
        assertTrue(FileUtils.contentEquals(f1, f2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() throws IOException {
        p.parse(null, "daa.txt");
    }
}
