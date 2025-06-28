package org.cft.statistics;

import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;

import java.util.List;

public interface StatisticsService {

    StringDto getStatisticsFromStrings(List<String> strings);

    NumbersDto getStatisticsFromIntegers(List<String> ints);

    NumbersDto getStatisticsFromRealNumbers(List<String> reals);

}
