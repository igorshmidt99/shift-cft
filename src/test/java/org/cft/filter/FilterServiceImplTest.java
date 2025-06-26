package org.cft.filter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterServiceImplTest {

    private final FilterService filterService = new FilterServiceImpl(
            Pattern.compile("^-?\\d+$"),
            Pattern.compile("^-?\\d+\\.\\d+([eE][-+]?\\d+)?$")
    );

    @Test
    void filterIntsWithMixedContent() {
        List<String> content = new ArrayList<>(List.of("Я читаю текст", "1234", "123.123", "False", "False123",
                "False123.213", "false", "Text with content", "1234", "true", "1234.1234",
                "Something special", "1234", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer"));
        List<String> expected = List.of("1234", "1234", "1234");
        List<String> actual = filterService.filterIntegers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterIntsWithoutContent() {
        List<String> content = new ArrayList<>();
        List<String> expected = List.of();
        List<String> actual = filterService.filterIntegers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterIntsWithIntsNumsContent() {
        List<String> content = new ArrayList<>(List.of("1234", "555", "1234.324", "435123", "123124123123123", "0.0"));
        List<String> expected = List.of("1234", "555", "435123", "123124123123123");
        List<String> actual = filterService.filterIntegers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterWithOnlyInts() {
        List<String> content = new ArrayList<>(List.of("1234", "555", "435123", "123124123123123"));
        List<String> expected = List.of("1234", "555", "435123", "123124123123123");
        List<String> actual = filterService.filterIntegers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterWitIntsSizeOfOne() {
        List<String> content = new ArrayList<>(List.of("1234"));
        List<String> expected = List.of("1234");
        List<String> actual = filterService.filterIntegers(content);
        assertEquals(expected, actual);
    }


    @Test
    void filterWithRealNumbersWithMixedContent() {
        List<String> content = new ArrayList<>(List.of("Я читаю текст", "1234", "123.123", "False", "False123",
                "False123.213", "false", "Text with content", "1234", "true", "1234.1234",
                "Something special", "1234", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer"));
        List<String> expected = List.of("123.123", "1234.1234");
        List<String> actual = filterService.filterRealNumbers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterWithRealNumbersWithoutContent() {
        List<String> content = new ArrayList<>();
        List<String> expected = List.of();
        List<String> actual = filterService.filterRealNumbers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterWithRealNumbersWithNumsContent() {
        List<String> content = new ArrayList<>(List.of("1234", "555", "1234.324", "435123", "123124123123123", "0.0"));
        List<String> expected = List.of("1234.324", "0.0");
        List<String> actual = filterService.filterRealNumbers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterRealNumbersWithOnlyRealNumbers() {
        List<String> content = new ArrayList<>(List.of("1234.324", "0.0", "1.528535047E-25"));
        List<String> expected = List.of("1234.324", "0.0", "1.528535047E-25");
        List<String> actual = filterService.filterRealNumbers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterRealNumbersWithSizeOfOne() {
        List<String> content = new ArrayList<>(List.of("1.528535047E-25"));
        List<String> expected = List.of("1.528535047E-25");
        List<String> actual = filterService.filterRealNumbers(content);
        assertEquals(expected, actual);
    }

    @Test
    void filterStringsWithMixedContent() {
        List<String> content = new ArrayList<>(List.of("Я читаю текст", "1234", "123.123", "False", "False123",
                "False123.213", "false", "Text with content", "1234", "true", "1234.1234",
                "Something special", "1234", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer"));
        List<String> expected = List.of("Я читаю текст", "False", "False123", "False123.213", "false", "Text with content",
                "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        filterService.filterIntegers(content);
        filterService.filterRealNumbers(content);
        assertEquals(expected, content);
    }

    @Test
    void filterStringsWithOnlyStringsContent() {
        List<String> content = new ArrayList<>(List.of("Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer"));
        List<String> expected = List.of("Я читаю текст", "False", "False123", "False123.213", "false",
                "Text with content", "true", "Something special", "qwer457", "&^%%^$**$&#*#)$", "1234.2345!@#@$*!@&$@$qwer");
        filterService.filterIntegers(content);
        filterService.filterRealNumbers(content);
        assertEquals(expected, content);
    }
}