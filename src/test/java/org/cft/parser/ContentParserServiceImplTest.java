package org.cft.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentParserServiceImplTest {

    private final ParserService service = new ParserServiceImpl();

    @Test
    void parseFromGoodFiles() {
        String prefix = "src/test/resources/";
        List<String> args = List.of(prefix + "something.txt", prefix + "sm.txt", prefix + "path/to/file/1-2_3$r.txt",
                "123", "tttt", "rrrrr", "sf.txttt");
        List<String> expected = List.of("Я читаю текст", "1234", "123.123", "False", "False123",
                "False123.213", "false", "Text with content", "1234", "true", "1234.1234",
                "Something special", "1234", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> actual = service.parseFrom(args);
        assertEquals(expected, actual);
    }

    @Test
    void parseFromNonExistentFiles() {
        String prefix = "src/test/resources/";
        List<String> args = List.of(prefix + "empty.txt", prefix + "empty2.txt");
        List<String> expected = List.of();
        List<String> actual = service.parseFrom(args);
        assertEquals(expected, actual);
    }

    @Test
    void parseFromEmptyFiles() {
        String prefix = "src/test/resources/";
        List<String> args = List.of(prefix + "empty.txt");
        List<String> expected = List.of();
        List<String> actual = service.parseFrom(args);
        assertEquals(expected, actual);
    }

}