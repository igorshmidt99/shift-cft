package org.cft.statistics;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsServiceImplTest {

    private final StatisticsService statisticsService = new StatisticsServiceImpl();
    private final String filepath = "/home/i/IdeaProjects/util/src/test/resources/statistics/";
    private final String emptyFileName = "empty.txt";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readStringsStatisticsWithFullStringDataShouldProduceFullDto() {
        List<String> strings = List.of("Я читаю текст", "False", "False123", "False123.213", "false");
        StringDto expected = new StringDto(strings, "5", "13");

        StringDto actual = statisticsService.readStringsStatistics(filepath, "something_strings.txt");

        assertEquals(expected, actual);
    }

    @Test
    void readStringsStatisticsWithEmptyDataShouldProduceEmptyDto() {
        StringDto expected = new StringDto();

        StringDto actual = statisticsService.readStringsStatistics(filepath, emptyFileName);

        assertEquals(expected, actual);
    }

    @Test
    void readStringsStatisticsWithBrokenPathShouldProduceEmptyDto() {
        StringDto expected = new StringDto();

        StringDto actual = statisticsService.readStringsStatistics("", "");

        assertEquals(expected, actual);
    }

    @Test
    void readIntsStatisticsWithFullDataShouldProduceFullDto() {
        List<String> ints = List.of("1234");
        NumbersDto expected = new NumbersDto(ints, 1234L, 1234L, 1234L, 1234L);

        NumbersDto actual = statisticsService.readIntsStatistics(filepath, "something_ints.txt");

        assertEquals(expected, actual);
    }

    @Test
    void readIntsStatisticsWithEmptyDataShouldProduceEmptyDto() {
        NumbersDto expected = new NumbersDto();

        NumbersDto actual = statisticsService.readIntsStatistics(filepath, emptyFileName);

        assertEquals(expected, actual);
    }

    @Test
    void readIntsStatisticsWitBrokenPathsShouldProduceEmptyDto() {
        NumbersDto expected = new NumbersDto();

        NumbersDto actual = statisticsService.readIntsStatistics("", "");

        assertEquals(expected, actual);
    }

    @Test
    void readRealsStatisticsWithFullDataShouldProduceFullDto() {
        List<String> reals = List.of("123.123");
        NumbersDto expected = new NumbersDto(reals, 123.123D, 123.123D, 123.123D, 123.123);

        NumbersDto actual = statisticsService.readRealsStatistics(filepath, "something_reals.txt");

        assertEquals(expected, actual);
    }

    @Test
    void readRealsStatisticsWithEmptyDataShouldProduceEmptyDto() {
        NumbersDto expected = new NumbersDto();

        NumbersDto actual = statisticsService.readRealsStatistics(filepath, emptyFileName);

        assertEquals(expected, actual);
    }

    @Test
    void readRealsStatisticsWithBroketPathShouldProduceEmptyDto() {
        NumbersDto expected = new NumbersDto();

        NumbersDto actual = statisticsService.readRealsStatistics("", "");

        assertEquals(expected, actual);
    }
}