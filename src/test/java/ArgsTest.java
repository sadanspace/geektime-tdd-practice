import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgsTest {
    @Test
    void should() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr");

        assertEquals(options.logging(), true);
        assertEquals(options.port(), 8080);
        assertEquals(options.dir(), "/usr");
    }

    record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {
    }

}
