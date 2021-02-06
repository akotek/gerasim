package q1;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceParser7 implements InvoiceParser {

    // ===========================================================================

    private final int ROW_LEN = 3;
    private final int COL_LEN = 27;
    private final int LINES_LEN = 4;

    private final String[] DIGITS = Digits.SEVEN;

    private HashMap<String, Character> digitToNum = new HashMap<>();

    // ===========================================================================
    // utils

    public InvoiceParser7() {
        for (int i = 0; i < DIGITS.length; i++) {
            digitToNum.put(DIGITS[i], Character.forDigit(i, 10));
        }
    }

    private String parseChunk(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < COL_LEN - ROW_LEN + 1; i += ROW_LEN) {
            String digit =
                    lines.get(0).substring(i, i + ROW_LEN)
                            .concat(lines.get(1).substring(i, i + ROW_LEN))
                            .concat(lines.get(2).substring(i, i + ROW_LEN));
            sb.append(digitToNum.getOrDefault(digit, '?'));
        }
        String res = sb.toString();
        return (res.contains("?") ? res + " ILLEGAL" : res);
    }

    // ===========================================================================
    // API


    @Override
    public File parse(String in, String out) throws IOException {
        if (in == null || !(new File(in).isFile())){
            throw new IllegalArgumentException("Invalid input");
        }
        List<String> lines = new ArrayList<>();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            try (BufferedReader br = new BufferedReader(new FileReader(in))) {
                int n = 0;
                String line;
                while ((line = br.readLine()) != null) {
                    n += 1;
                    if (n % LINES_LEN == 0) {
                        String s = parseChunk(lines);
                        bw.write(s + "\n");
                        lines = new ArrayList<>();
                        continue;
                    }
                    lines.add(line);
                }
            }
        }
        return new File(out);
    }
}
