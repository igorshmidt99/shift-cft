package org.cft.extract;

import org.cft.filter.FilterServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtractServiceImplTest {

    ExtractService service = new ExtractServiceImpl(
            new FilterServiceImpl(
                    Pattern.compile("^-?\\d+$"),
                    Pattern.compile("^-?\\d+\\.\\d+([eE][-+]?\\d+)?$")
            )
    );

    @Test
    void extractFromGoodFiles() {
        List<String> fromFile = List.of("Я читаю текст", "1234", "123.123", "False", "False123",
                "False123.213", "false", "Text with content", "1234", "true", "1234.1234",
                "Something special", "1234", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> strings = List.of("Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        List<String> ints = List.of("1234", "1234", "1234");
        List<String> realNums = List.of("123.123", "1234.1234");

        DataFromFileDto expected = new DataFromFileDto(strings, ints, realNums);

        DataFromFileDto actual = service.extract(fromFile);

        assertEquals(expected, actual);
    }

}