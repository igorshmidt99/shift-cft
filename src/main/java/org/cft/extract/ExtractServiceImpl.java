package org.cft.extract;

import org.cft.dto.DataFromFileDto;
import org.cft.dto.NumbersDto;
import org.cft.dto.StringDto;
import org.cft.filter.FilterService;
import org.cft.statistics.StatisticsService;

import java.util.ArrayList;
import java.util.List;

public class ExtractServiceImpl implements ExtractService {

    private final FilterService filterService;

    private final StatisticsService statisticsService;

    public ExtractServiceImpl(FilterService filterService, StatisticsService statisticsService) {
        this.filterService = filterService;
        this.statisticsService = statisticsService;
    }

    @Override
    public DataFromFileDto extract(List<String> content) {
        List<String> fromFile = new ArrayList<>(content);
        List<String> integers = filterService.filterIntegers(fromFile);
        List<String> reals = filterService.filterRealNumbers(fromFile);
        NumbersDto statisticsFromIntegers = statisticsService.getStatisticsFromIntegers(integers);
        NumbersDto statisticsFromRealNumbers = statisticsService.getStatisticsFromRealNumbers(reals);
        StringDto statisticsFromStrings = statisticsService.getStatisticsFromStrings(fromFile);
        return new DataFromFileDto(statisticsFromStrings, statisticsFromIntegers, statisticsFromRealNumbers);
    }
}
