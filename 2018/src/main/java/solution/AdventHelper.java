package solution;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class AdventHelper {

    static List<String> getLines(String file) throws IOException {
        try {
            return Files.readAllLines(Paths.get(Resources.getResource(file).toURI()));
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
}
