import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {
    @Test
    void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr");

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr", options.dir());
    }

    record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {
    }

    @Test
    void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.strings());
        assertArrayEquals(new int[]{1, 2, -3, 5}, options.ints());
    }

    record ListOptions(@Option("g") String[] strings, @Option("d") int[] ints) {
    }
}
