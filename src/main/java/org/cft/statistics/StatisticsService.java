package org.cft.statistics;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;

public interface StatisticsService {
    void printStatistics(NumbersDto intsWithStats, StringDto stringsWithStats, NumbersDto realsWithStats);

    NumbersDto readRealsStatistics(String filePath, String fileName);

    StringDto readStringsStatistics(String filePath, String fileName);

    NumbersDto readIntsStatistics(String filePath, String fileName);
}
