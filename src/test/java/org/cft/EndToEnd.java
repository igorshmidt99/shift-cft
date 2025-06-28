package org.cft;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEnd {

    @Test
    @Order(1)
    public void testEndToEndWithRightInputOfFlags() throws IOException {
        String prefix = "src/test/resources/";
        String[] args = {"-o", "src/test/resources/created", "-p", "endToEndTest",
                prefix + "something.txt", prefix + "sm.txt", prefix + "path/to/file/1-2_3$r.txt"};
        Main.main(args);
        Path stringsPath = Path.of("src/test/resources/created/endToEndTest_strings.txt");
        Path floatsPath = Path.of("src/test/resources/created/endToEndTest_floats.txt");
        Path intsPath = Path.of("src/test/resources/created/endToEndTest_integers.txt");

        assertTrue(Files.exists(stringsPath));
        assertTrue(Files.exists(floatsPath));
        assertTrue(Files.exists(intsPath));

        List<String> expectedStrings = List.of("Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> expectedInts = List.of("1234", "1234", "1234");
        List<String> expectedFloats = List.of("123.123", "1234.1234");

        List<String> actualStrings = Files.readAllLines(stringsPath);
        List<String> actualFloats = Files.readAllLines(floatsPath);
        List<String> actualInts = Files.readAllLines(intsPath);

        assertEquals(expectedStrings, actualStrings);
        assertEquals(expectedInts, actualInts);
        assertEquals(expectedFloats, actualFloats);
    }

    @Test
    @Order(2)
    public void testWithAppendFlag() throws IOException {
        String prefix = "src/test/resources/";
        String[] args = {"-o", "src/test/resources/created", "-p", "endToEndTest", "-a",
                prefix + "something.txt", prefix + "sm.txt", prefix + "path/to/file/1-2_3$r.txt"};
        Main.main(args);

        Path stringsPath = Path.of("src/test/resources/created/endToEndTest_strings.txt");
        Path floatsPath = Path.of("src/test/resources/created/endToEndTest_floats.txt");
        Path intsPath = Path.of("src/test/resources/created/endToEndTest_integers.txt");

        assertTrue(Files.exists(stringsPath));
        assertTrue(Files.exists(floatsPath));
        assertTrue(Files.exists(intsPath));

        List<String> expectedStrings = List.of("Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer",
                "Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> expectedInts = List.of("1234", "1234", "1234", "1234", "1234", "1234");
        List<String> expectedFloats = List.of("123.123", "1234.1234", "123.123", "1234.1234");

        List<String> actualStrings = Files.readAllLines(stringsPath);
        List<String> actualFloats = Files.readAllLines(floatsPath);
        List<String> actualInts = Files.readAllLines(intsPath);

        assertEquals(expectedStrings, actualStrings);
        assertEquals(expectedInts, actualInts);
        assertEquals(expectedFloats, actualFloats);

    }

    @Test
    void testWithErrorInputShouldProduceErrorOutput() {
        String[] args = {"-o", "-p", "src/test/resources/something.txt",
                "src/test/resources/sm.txt", "src/test/resources/path/to/file/1-2_3$r.txt"};
        Main.main(args);
    }

}