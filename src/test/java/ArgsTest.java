import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {

    // happy path
    @Test
    void should_set_boolean_option_to_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");
        assertTrue(option.logging());
    }

    @Test
    void should_set_boolean_option_to_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class);
        assertFalse(option.logging());
    }

    record BooleanOption(@Option("l") boolean logging) {
    }

    @Test
    void should_parse_int_as_option_value() {
        IntegerOption option = Args.parse(IntegerOption.class, "-p", "8080");
        assertEquals(8080, option.port());
    }

    record IntegerOption(@Option("p") int port) {
    }

    @Test
    void should_get_string_as_option_value() {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr");
        assertEquals("/usr", option.directory());
    }

    record StringOption(@Option("d") String directory) {
    }

    // TODO: multi args： "-l", "-p", "8080", "-d", "/usr"
    // sad path
    // TODO: bool -l xxx / -l xxx foo
    // TODO: int -p / -p 8080 2222
    // TODO: String -d / -d /usr /xxx
    // default path
    // TODO: bool: false
    // TODO: int: 0
    // TODO: String : ""
    @Disabled
    @Test
    void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr");

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr", options.dir());
    }

    record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {
    }

    @Disabled
    @Test
    void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.strings());
        assertArrayEquals(new int[]{1, 2, -3, 5}, options.ints());
    }

    record ListOptions(@Option("g") String[] strings, @Option("d") int[] ints) {
    }
}
