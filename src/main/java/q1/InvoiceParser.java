package q1;

import java.io.File;
import java.io.IOException;

public interface InvoiceParser {

    File parse(String in, String out) throws IOException;
}
